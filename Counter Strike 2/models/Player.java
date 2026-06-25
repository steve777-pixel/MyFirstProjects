package models;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.TreeMap;
import utils.Validation;
import java.util.ArrayList;
import java.util.List;
import java.time.Year;
import java.util.Objects;

public class Player{
    private final String id;
    private String name;
    private int age;
    private String currentTeam;
    private int prizeMoney;
    private Map<Integer, Integer> top20;
    private List<Event> achievements;
    private Statistics statistics;
    private List<MatchResult> lastTenMatches;

    public Player(String id, String name, int age, String currentTeam, int prizeMoney){
        this.id = Validation.validateNotBlank(id, "Player ID:");
        this.name = Validation.validateNotBlank(name, "Player name");
        this.age = Validation.validateNotNegative(age, "Player age");
        this.currentTeam = Validation.validateNotBlank(currentTeam, "Current team name");
        this.prizeMoney = Validation.validateNotNegative(prizeMoney, "Prize money");
        top20 = new TreeMap<>();
        achievements = new ArrayList<>();
        statistics = new Statistics(0,0,0,0,0,0,0);
        lastTenMatches = new ArrayList<>();
    }

    public String getID(){
        return id;
    }

    public String getName(){
        return name;
    }

    public int getAge(){
        return age;
    }

    public String getCurrentTeam(){
        return currentTeam;
    }

    public int getPrizeMoney(){
        return prizeMoney;
    }

    public double getRating(){
        return statistics.getRating();
    }

    public Map<Integer, Integer> getTop20(){
        return top20;
    }

    public List<Event> getAchievements(){
        return List.copyOf(achievements);
    }

    public void setName(String name){
        this.name = Validation.validateNotBlank(name, "Player name");
    }

    public void setAge(int age){
        this.age = Validation.validateNotNegative(age, "Player age");
    }

    public void setCurrentTeam(String currentTeam){
        this.currentTeam = Validation.validateNotBlank(currentTeam, "Current team name");
    }

    public void setPrizeMoney(int prizeMoney){
        this.prizeMoney = Validation.validateNotNegative(prizeMoney, "Prize money");
    }

    public void addPrizeMoney(int amount){
        this.prizeMoney+=Validation.validateNotNegative(amount, "Prize money amount");
    }

    public void addTop20Achievement(int year, int position){
        Year currentYear = Year.now();
        if(year > currentYear.getValue()){
            throw new IllegalArgumentException(String.format("%d year achievements are not out yet.", year));
        }
        if(position < 1 || position > 20){
            throw new IllegalArgumentException("Top 20 achievements must be in range [1-20].");
        }
        top20.put(year, position);
    }

    public void displayTop20Achievements(){
        if(top20.isEmpty()){
            return;
        }
        top20.forEach((key, value) -> System.out.println(key + " : " + value));
    }

    public void addTrophy(Event event){
        if(event == null){
            throw new IllegalArgumentException("Trophy cannot be null.");
        }
        if(achievements.contains(event)){
            throw new IllegalStateException("The trophy is already in the list.");
        }
        achievements.add(event);
    }

    public Event searchTrophy(String name){
        if(name == null || name.isBlank()){
            throw new IllegalArgumentException("Trophy cannot be null.");
        }
        if(achievements.isEmpty()){
            throw new IllegalStateException("The player has no achievements.");
        }
        for(Event event : achievements){
            if(event.getName().equals(name)){
                return event;
            }
        }
        throw new NoSuchElementException("No such trophy in the list");
    }

    public void displayTrophies(){
        if(achievements.isEmpty());
        achievements.forEach(event -> System.out.println(event.getName()));
    }

    public void setPlayerStatistics(int firepower, int entrying, int trading, int opening, int clutching, int sniping, int utility){
        statistics.updateAll(firepower, entrying, trading, opening, clutching, sniping, utility);
    }

    public void resetStatistics(){
        statistics.resetAll();   
    }

    public void updateFirepower(int firepower){
        statistics.setFirepower(firepower);
    }

    public void updateEntrying(int entrying){
        statistics.setEntrying(entrying);
    }

    public void updateTrading(int trading){
        statistics.setTrading(trading);
    }

    public void updateOpening(int opening){
        statistics.setOpening(opening);
    }

    public void updateClutching(int clutching){
        statistics.setClutching(clutching);
    }

    public void updateSniping(int sniping){
        statistics.setSniping(sniping);
    }

    public void updateUtility(int utility){
        statistics.setUtility(utility);
    }

    public void addMatch(MatchResult result){
        if(result == null){
            throw new IllegalArgumentException("Match result cannot be null.");
        }
        if(lastTenMatches.size() >= 10){
            lastTenMatches.remove(0);
        }
        lastTenMatches.add(result);
    }

    public void removeMatch(MatchResult result){
        if(result == null){
            throw new IllegalArgumentException("Match result cannot be null.");
        }
        boolean removed = lastTenMatches.remove(result);
        if(!removed){
            throw new IllegalStateException("No match with such results was played recently.");
        }
    }

    public String getLastRecentMatchesSummary(){
        if(lastTenMatches.isEmpty()){
            return "No recent matches!";
        }
        StringBuilder sb = new StringBuilder(String.format("--- Last %d Matches ---\n", lastTenMatches.size()));
        for(MatchResult result : lastTenMatches){
            sb.append(result.toString()).append("\n");
        }
        return sb.toString().trim();
    }

    @Override 
    public String toString() {
        return String.format(
            "====================\n" +
            "PLAYER PROFILE\n" +
            "====================\n\n" +
            "Name: %s\n" +
            "ID: %s\n" +
            "Age: %d\n" +
            "Current Team: %s\n" +
            "Prize Money: $%d\n\n" +
            "--- Statistics ---\n%s\n\n" +
            "%s", // Appends your last matches summary cleanly
            name, id, age, currentTeam, prizeMoney, statistics.toString(), getLastRecentMatchesSummary()
        );
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj){
            return true;
        }
        if(obj == null || getClass() != obj.getClass()){
            return false;
        }
        Player other = (Player) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public int hashCode(){
        return Objects.hash(id);
    }
}