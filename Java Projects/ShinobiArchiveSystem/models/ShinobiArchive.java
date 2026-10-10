package models;

import java.util.HashMap;
import java.util.Map;
import utils.Rank;
import utils.Validation;
import java.util.List;
import java.util.Collections;
import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.stream.Collectors;

public class ShinobiArchive{
    private final String id;
    private Map<Integer, Ninja> ninjas;
    private Map<String, Mission> missions;

    public ShinobiArchive(String id){
        this.id = Validation.validateNotBlank(id, "ID");
        ninjas = new HashMap<>();
        missions = new HashMap<>();
    }

    public String getID(){
        return id;
    }

    public List<Ninja> getNinjasList(){
        return List.copyOf(ninjas.values());
    }

    public List<Mission> getMissionsList(){
        return List.copyOf(missions.values());
    }

    public void addNinja(Ninja ninja){
        if(ninja == null){
            throw new IllegalArgumentException("Ninja cannot be null.");
        }
        if(ninjas.containsKey(ninja.getId())){
            throw new IllegalArgumentException(String.format("Ninja with ID %d already exists.", ninja.getId()));
        }
        ninjas.put(ninja.getId(), ninja);
    }

    public void addMission(Mission mission){
        if(mission == null){
            throw new IllegalArgumentException("Mission cannot be null.");
        }
        if(missions.containsKey(mission.getCode())){
            throw new IllegalArgumentException(String.format("Mission with code %s already exists.", mission.getCode()));
        }
        missions.put(mission.getCode(), mission);
    }

    public Ninja findNinjaById(int id){
        if(ninjas.isEmpty()){
            throw new IllegalStateException("No ninjas in the archive.");
        }
        if(!ninjas.containsKey(id)){
            throw new NoSuchElementException(String.format("No ninja with ID %d in the archive.", id));
        }
        return ninjas.get(id);
    }

    public Mission findMissionByCode(String code){
        if(code == null || code.trim().isEmpty()){
            throw new IllegalArgumentException("Mission code cannot be empty.");
        }
        if(missions.isEmpty()){
            throw new IllegalStateException("No missions in the archive.");
        }
        if(!missions.containsKey(code)){
            throw new NoSuchElementException(String.format("No mission with code %s was found.", code));
        }
        return missions.get(code);
    }

    public void assignMission(int ninjaId, String missionCode){
        if(missionCode == null || missionCode.trim().isEmpty()){
            throw new IllegalArgumentException("Mission code cannot be empty.");
        }
        if(!ninjas.containsKey(ninjaId)){
            throw new NoSuchElementException(String.format("Ninja with ID %d is not in the archive.", ninjaId));
        }
        if(!missions.containsKey(missionCode)){
            throw new NoSuchElementException(String.format("Mission with code %s not in the archive.", missionCode));
        }
        ninjas.get(ninjaId).completeMission(missions.get(missionCode));
    }

    public List<Ninja> getTopNinjas(int count){
        if(count <= 0){
            throw new IllegalArgumentException("Ninja count cannot be negative.");
        }
        if(ninjas.isEmpty()){
            throw new IllegalStateException("No ninjas in the archive.");
        }
        if(count > ninjas.size()){
            count = ninjas.size();
        }
        List<Ninja> topNinjas = ninjas.values().stream().sorted(Comparator.comparingInt(Ninja::getLevel).reversed()).limit(count).collect(Collectors.toList());
        return Collections.unmodifiableList(topNinjas);
    }

    public Map<Rank, Long> getRankDistribution(){
        return ninjas.values().stream().collect(Collectors.collectingAndThen(Collectors.groupingBy(Ninja::getRank, Collectors.counting()),Map::copyOf));    
    }

    public int getVillageTotalRewards(){
        return ninjas.values().stream().mapToInt(Ninja::getTotalEarnedReward).sum();
    }

    @Override
    public String toString(){
        return String.format("ID: %s\nNinja count: %d\nMission count: %d\nTotal village reward: %d", id, ninjas.size(), missions.size(), getVillageTotalRewards());
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj){
            return true;
        }
        if(obj == null || getClass() != obj.getClass()){
            return false;
        }
        ShinobiArchive other = (ShinobiArchive) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public int hashCode(){
        return Objects.hash(id);
    }
}