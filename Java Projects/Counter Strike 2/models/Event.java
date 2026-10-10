package models;

import java.time.LocalDate;
import utils.Validation;
import java.util.Objects;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Event{
    private final String name;
    private final String location;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final char tier;
    private final int prizeMoney;
    private Map<String, Team> teams;
    private Player MVP;

    public Event(String name, String location, String startDate, String endDate, char tier, int prizeMoney){
        this.name = Validation.validateNotBlank(name, "Event name");
        this.location = Validation.validateNotBlank(location, "Event location");
        this.startDate = Validation.validateFormat(startDate, "Start date");
        this.endDate = Validation.validateFormat(endDate, "End date");
        Validation.validateDateOrder(this.startDate, this.endDate);  
        this.tier = Validation.validateTier(tier); 
        this.prizeMoney = Validation.validateNotNegative(prizeMoney, "Prize money");
        this.teams = new HashMap<>();
        this.MVP = null;
    }

    public String getName(){
        return name;
    }

    public String getLocation(){
        return location;
    }

    public LocalDate getStartDate(){
        return startDate;
    }

    public LocalDate getEndDate(){
        return endDate;
    }

    public char getTier(){
        return tier;
    }

    public int getPrizeMoney(){
        return prizeMoney;
    }

    public List<Team> getTeams(){
        return List.copyOf(teams.values());
    }

    public Player getMVP(){
        return MVP;
    }

    public void setMVP(Player player){
        if(player == null){
            throw new IllegalArgumentException("Player cannot be null.");
        }
        if(teams.isEmpty()){
            throw new IllegalArgumentException("Cannot assign the MVP without teams.");
        }
        if(!teams.values().stream().anyMatch(team -> team.hasPlayer(player))){
            throw new IllegalArgumentException(String.format("This player did not play in %s.", name));
        }
        this.MVP = player;
    }

    public void addTeam(Team team){
        if(team == null){
            throw new IllegalArgumentException("Team cannot be null.");
        }
        if(team.getTeamSize() < 5){
            throw new IllegalStateException("Teams must have at least 5 players.");
        }
        if(teams.containsKey(team.getID())){
            throw new IllegalStateException("The team is already in the event.");
        }
        teams.put(team.getID(), team);
    }

    public void removeTeam(Team team){
        if(team == null){
            throw new IllegalArgumentException("Team cannot be null.");
        }
        if(teams.isEmpty()){
            throw new IllegalStateException("No teams in the event yet.");
        }
        if(!teams.containsKey(team.getID())){
            throw new IllegalArgumentException(String.format("Team %s is not in the event.", team.getName()));
        }
        teams.remove(team.getID());
    }

    public void changeTeams(Team leavingTeam, Team joiningTeam){
        removeTeam(leavingTeam);
        addTeam(joiningTeam);
    }

    public String getAllTeamNames(){
        if(teams.isEmpty()){
            return "No teams in the event.";
        }
        StringBuilder sb = new StringBuilder(String.format("--- All %d Teams in %s ---\n", teams.size(), name));
        for(Team team : teams.values()){
            sb.append(team.getName()).append("\n");
        }
        return sb.toString().trim();
    }

    public String getAllPlayerNames(){
        if(teams.isEmpty()){
            return "No teams in the event.";
        }
        StringBuilder sb = new StringBuilder(String.format("--- All %d Teams and their Players ---\n", teams.size()));
        for(Team team : teams.values()){
            sb.append(team.getName()).append("\n");
            for(Player player : team.getPlayers()){
                sb.append(player.getName()).append("\n");
            }
            sb.append("\n");
        }
        return sb.toString().trim();
    }

    @Override
    public String toString(){
        return String.format("Name: %s\nLocation: %s\nStart date: %s\nEnd date: %s\nEvent tier: %c\nPrize money: %d", name, location, startDate.toString(), endDate.toString(), tier, prizeMoney);
    }

    @Override 
    public boolean equals(Object obj){
        if(this == obj){
            return true;
        }
        if(obj == null || getClass() != obj.getClass()){
            return false;
        }
        Event other = (Event) obj;
        return Objects.equals(this.name, other.name) && Objects.equals(this.location, other.location) && Objects.equals(this.startDate, other.startDate) && Objects.equals(this.endDate, other.endDate);
    }

    @Override
    public int hashCode(){
        return Objects.hash(name, location, startDate, endDate);
    }
}