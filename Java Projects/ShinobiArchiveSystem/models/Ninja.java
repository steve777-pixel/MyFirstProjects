package models;

import utils.Rank;
import utils.Validation;
import java.util.Objects;
import java.util.ArrayList;
import java.util.List;

public class Ninja{
    private final int id;
    private String name;
    private int level;
    private Rank rank;
    private List<Mission> completedMissions;

    public Ninja(int id, String name, int level, Rank rank){
        this.id = Validation.validateNotNegative(id, "ID");
        this.name = Validation.validateNotBlank(name, "Name");
        this.level = Validation.validateNotNegative(level, "Level");
        this.rank = Objects.requireNonNull(rank, "Rank cannot be null.");
        completedMissions = new ArrayList<>();
    }

    public int getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public int getLevel(){
        return level;
    }
    public Rank getRank(){
        return rank;
    }

    public void completeMission(Mission mission){
        if(completedMissions.contains(mission)){
            throw new IllegalStateException("The ninja has already completed this mission.");
        }
        completedMissions.add(mission);
    }

    public int getCompletedMissionCount(){
        return completedMissions.size();
    }

    public int getTotalEarnedReward(){
        if(completedMissions == null || completedMissions.isEmpty()){
            return 0;
        }
        return completedMissions.stream().mapToInt(Mission::getReward).sum();
    }

    public boolean hasCompletedMission(String missionCode){
        if(completedMissions == null || completedMissions.isEmpty() || missionCode == null || missionCode.trim().isEmpty()){
            return false;
        }
        return completedMissions.stream().anyMatch(mission -> mission.getCode().equalsIgnoreCase(missionCode));
    }

    @Override
    public String toString(){
        return String.format("Name: %s\nID: %d\nLevel: %d\nRank: %s", name, id, level, rank.getDescription());
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj){
            return true;
        }
        if(obj == null || getClass() != obj.getClass()){
            return false;
        }
        Ninja other = (Ninja) obj;
        return this.id == other.id;
    }

    @Override
    public int hashCode(){
        return Objects.hash(id);
    }
}