package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.NoSuchElementException;

import utils.Validation;


public class Team{
    private final String id;
    private String name;
    private int points;
    private String organizationName;
    private int budget;
    private List<Event> eventsWon;
    private Map<String, Player> players;
    private List<MatchResult> lastTenMatches;

    public Team(String id, String name, int points, String organizationName, int budget){
        this.id = Validation.validateNotBlank(id, "Team ID");
        this.name = Validation.validateNotBlank(name, "Team name");
        this.points = Validation.validateNotNegative(points, "Team points");
        this.organizationName = Validation.validateNotBlank(organizationName, "Organization name");
        this.budget = Validation.validateNotNegative(budget, "Budget");
        eventsWon = new ArrayList<>();
        players = new HashMap<>();
        lastTenMatches = new ArrayList<>();
    }

    public String getID(){
        return id;
    }

    public String getName(){
        return name;
    }

    public int getPoints(){
        return points;
    }

    public String getOrganizationName(){
        return organizationName;
    }

    public int getBudget(){
        return budget;
    }

    public List<Player> getPlayers(){
        return List.copyOf(players.values());
    }

    public int getTeamSize(){
        return players.size();
    }

    public List<MatchResult> getLast10MatchResults(){
        return lastTenMatches;
    }

    public void setName(String name){
        this.name = Validation.validateNotBlank(name, "Team name");
    }

    public void setPoints(int points){
        this.points = Validation.validateNotNegative(points, "Team points");
    }

    public void addPoints(int points){
        this.points+=Validation.validateNotNegative(points, "Points");
    }

    public void subtractPoints(int points){
        this.points-=Validation.validateNotNegative(points, "Points");
        if(this.points < 0){
            this.points = 0;
        }
    }

    public void setOrganizationName(String name){
        this.organizationName = Validation.validateNotBlank(name, "Organization name");
    }

    public void setBudget(int budget){
        this.budget = Validation.validateNotNegative(budget, "Budget");
    }

    public void addBudget(int budget){
        this.budget+=Validation.validateNotNegative(budget, "Budget");
    }

    public void subtractBudget(int budget){
        if(this.budget < budget){
            throw new IllegalArgumentException(String.format("Not enough budget! Current budget: %d.", this.budget));
        }
        this.budget-=Validation.validateNotNegative(budget, "Budget");
    }

    public boolean hasPlayer(Player player){
        if(player == null || players.isEmpty()){
            return false;
        }
        return players.containsKey(player.getID());
    }

    public void addTrophy(Event event){
        if(event == null){
            throw new IllegalArgumentException("Trophy cannot be null.");
        }
        if(eventsWon.contains(event)){
            throw new IllegalStateException("The trophy is already in the list.");
        }
        eventsWon.add(event);
    }

    public Event searchTrophy(String name){
        if(name == null || name.isBlank()){
            throw new IllegalArgumentException("Trophy cannot be null.");
        }
        if(eventsWon.isEmpty()){
            throw new IllegalStateException("The team has no achievements.");
        }
        for(Event event : eventsWon){
            if(event.getName().equals(name)){
                return event;
            }
        }
        throw new NoSuchElementException("No such trophy in the list");
    }

    public void addPlayer(Player player){
        if(player == null){
            throw new IllegalArgumentException("Player cannot be null.");
        }
        if(players.containsKey(player.getID())){
            throw new IllegalStateException("Player is already in the team.");
        }
        players.put(player.getID(), player);
    }

    public void removePlayer(String id){
        if(id == null || id.isBlank()){
            throw new IllegalArgumentException("ID cannot be null.");
        }
        if(players.isEmpty()){
            throw new IllegalArgumentException("The team is empty.");
        }
        if(!players.containsKey(id)){
            throw new IllegalArgumentException(String.format("Player with ID %s is not in the team.", id));
        }
        players.remove(id);
    }

    public Player searchPlayerByID(String id){
        if(id == null || id.isBlank()){
            throw new IllegalArgumentException("ID cannot be null.");
        }
        Player player = players.get(id);
        if(player == null){
            if(players.isEmpty()){
                throw new NoSuchElementException("The team roster is currently empty.");
            }else{
                throw new NoSuchElementException(String.format("The player with ID %s is not in the team.", id));
            }
        }
        return player;
    }

    public void addMatch(MatchResult match){
        if(match == null){
            throw new IllegalArgumentException("Match result cannot be null.");
        }
        if(lastTenMatches.size() >= 10){
            lastTenMatches.remove(0);
        }
        lastTenMatches.add(match);
    }

    public String getAllEventsWon(){
        if(eventsWon.isEmpty()){
            return "No events won yet.";
        }
        StringBuilder sb = new StringBuilder(String.format("--- All %d Events Won ---\n", eventsWon.size()));
        for(Event event : eventsWon){
            sb.append(event.toString()).append("\n");
        }
        return sb.toString().trim();
    }

    public String getAllPlayerNames(){
        if(players.isEmpty()){
            return "No players in the team.";
        }
        StringBuilder sb = new StringBuilder(String.format("--- All %d player's names ---\n", players.size()));
        for(Player player : players.values()){
            sb.append(player.getName().toString()).append("\n");
        }
        return sb.toString().trim();
    }

    public double getAverageRating(){
        if(players.isEmpty()){
            return 0.0;
        }
        double rawAvg = players.values().stream().mapToDouble(player -> player.getRating()).sum() / players.size();
        return Math.round(rawAvg * 100.0) / 100.0;
    }

    public double getAverageAge(){
        if(players.isEmpty()){
            return 0.0;
        }
        double rawAvg = players.values().stream().mapToDouble(player -> player.getAge()).sum() / players.size();
        return Math.round(rawAvg * 10.0) / 10.0;
    }

    public int getWholePrizeMoney(){
        if(players.isEmpty()){
            return 0;
        }
        return players.values().stream().mapToInt(player -> player.getPrizeMoney()).sum();
    }

    @Override
    public String toString(){
        return String.format(
            "==================\n" +
            "TEAM PROFILE\n" +
            "==================\n" +
            "Name: %s\n" +
            "ID: %s\n" +
            "Points: %d\n" +
            "Organization name: %s\n" +
            "Budget: $%d\n\n" +
            "Average rating: %.2f\n" + 
            "Average age: %.1f\n" +
            "Prize money: $%d\n\n" +
            "--- Players ---\n%s\n\n" +
            "%s",
            name, id, points, organizationName, budget, getAverageRating(), getAverageAge(), getWholePrizeMoney(), getAllPlayerNames(), getAllEventsWon()
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
        Team other = (Team) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override 
    public int hashCode(){
        return Objects.hash(id);
    }
}