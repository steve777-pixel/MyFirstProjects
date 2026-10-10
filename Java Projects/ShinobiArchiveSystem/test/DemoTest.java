package test;

import models.*;
import utils.Rank;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.junit.Test;
import utils.Difficulty;
import java.util.NoSuchElementException;
import java.util.TreeMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class DemoTest{

    @Test 
    public void duplicateNinjaIDsAreNotAllowed(){
        ShinobiArchive konohakagure = new ShinobiArchive("AD99809");
        Ninja ninja = new Ninja(9908, "Naruto", 100, Rank.KAGE);
        konohakagure.addNinja(ninja);
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> konohakagure.addNinja(ninja));
        assertEquals("Ninja with ID 9908 already exists.", thrown.getMessage());
    }

    @Test 
    public void duplicateMissionCodesAreNotAllowed(){
        ShinobiArchive konohakagure = new ShinobiArchive("AD99809");
        Mission mission = new Mission("AD98182", "Kill Kabuto", Difficulty.A, 500_000);
        konohakagure.addMission(mission);
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> konohakagure.addMission(mission));
        assertEquals("Mission with code AD98182 already exists.", thrown.getMessage());
    }

    @Test
    public void missionRewardMustMatchDifficulty(){
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> new Mission("AD98182", "Kill Kabuto", Difficulty.A, 5_000_000));
        assertEquals("Invalid reward (5000000 credits) for A-rank mission. Allowed range: 100000 - 999999 credits.", thrown.getMessage());
    }

    @Test 
    public void noNinjaWithSuchId(){
        ShinobiArchive konohakagure = new ShinobiArchive("AD99809");
        Ninja ninja = new Ninja(9908, "Naruto", 100, Rank.KAGE);
        konohakagure.addNinja(ninja);
        NoSuchElementException thrown = assertThrows(NoSuchElementException.class, () -> konohakagure.findNinjaById(9907));
        assertEquals("No ninja with ID 9907 in the archive.", thrown.getMessage());
    }

    @Test 
    public void emptyNinjaList(){
        ShinobiArchive konohakagure = new ShinobiArchive("AD99809");
        IllegalStateException thrown = assertThrows(IllegalStateException.class, () -> konohakagure.findNinjaById(9908));
        assertEquals("No ninjas in the archive.", thrown.getMessage());
    }

    @Test 
    public void noMissionWithSuchCode(){
        ShinobiArchive konohakagure = new ShinobiArchive("AD99809");
        Mission mission = new Mission("AD98182", "Kill Kabuto", Difficulty.A, 500_000);
        konohakagure.addMission(mission);
        NoSuchElementException thrown = assertThrows(NoSuchElementException.class, () -> konohakagure.findMissionByCode("AD97182"));
        assertEquals("No mission with code AD97182 was found.", thrown.getMessage());
    }

    @Test 
    public void findNinjaByID(){
        ShinobiArchive konohakagure = new ShinobiArchive("AD99809");
        Ninja ninja = new Ninja(9908, "Naruto", 100, Rank.KAGE);
        konohakagure.addNinja(ninja);
        Ninja newNinja = konohakagure.findNinjaById(ninja.getId());
        assertEquals(9908, newNinja.getId());
    }

    @Test
    public void findMissionByCode(){
        ShinobiArchive konohakagure = new ShinobiArchive("AD99809");
        Mission mission = new Mission("AD98182", "Kill Kabuto", Difficulty.A, 500_000);
        konohakagure.addMission(mission);
        Mission newMission = konohakagure.findMissionByCode(mission.getCode());
        assertEquals("AD98182", newMission.getCode());
    }

    @Test
    public void shouldFailWhenUknownNinja(){
        ShinobiArchive konohakagure = new ShinobiArchive("AD99809");
        Mission mission = new Mission("AD98182", "Kill Kabuto", Difficulty.A, 500_000);
        konohakagure.addMission(mission);
        Ninja ninja = new Ninja(9908, "Naruto", 100, Rank.KAGE);
        NoSuchElementException thrown = assertThrows(NoSuchElementException.class, () -> konohakagure.assignMission(ninja.getId(), mission.getCode()));
        assertEquals("Ninja with ID 9908 is not in the archive.", thrown.getMessage());
    }

    @Test
    public void shouldFailWhenUknownMission(){
        ShinobiArchive konohakagure = new ShinobiArchive("AD99809");
        Mission mission = new Mission("AD98182", "Kill Kabuto", Difficulty.A, 500_000);
        Ninja ninja = new Ninja(9908, "Naruto", 100, Rank.KAGE);
        konohakagure.addNinja(ninja);
        NoSuchElementException thrown = assertThrows(NoSuchElementException.class, () -> konohakagure.assignMission(ninja.getId(), mission.getCode()));
        assertEquals("Mission with code AD98182 not in the archive.", thrown.getMessage());
    }

    @Test
    public void assignMissionSuccessfully(){
        ShinobiArchive konohakagure = new ShinobiArchive("AD99809");
        Mission mission = new Mission("AD98182", "Kill Kabuto", Difficulty.A, 500_000);
        konohakagure.addMission(mission);
        Ninja ninja = new Ninja(9908, "Naruto", 100, Rank.KAGE);
        konohakagure.addNinja(ninja);
        konohakagure.assignMission(ninja.getId(), mission.getCode());
        assertEquals(1, ninja.getCompletedMissionCount()); // Automatic mission count update
        assertEquals(500_000, ninja.getTotalEarnedReward()); // Automatic reward update
        assertEquals(500_000, konohakagure.getVillageTotalRewards()); // Village reward updated successfully
    }

    @Test
    public void shouldFailWhenTheSameMissionAssignedTwice(){
        ShinobiArchive konohakagure = new ShinobiArchive("AD99809");
        Mission mission = new Mission("AD98182", "Kill Kabuto", Difficulty.A, 500_000);
        konohakagure.addMission(mission);
        Ninja ninja = new Ninja(9908, "Naruto", 100, Rank.KAGE);
        konohakagure.addNinja(ninja);
        konohakagure.assignMission(ninja.getId(), mission.getCode());
        IllegalStateException thrown = assertThrows(IllegalStateException.class, () -> konohakagure.assignMission(ninja.getId(), mission.getCode()));
        assertEquals("The ninja has already completed this mission.", thrown.getMessage());
    }

    @Test
    public void getValidRankDistribution(){
        ShinobiArchive konohakagure = new ShinobiArchive("AD99809");
        Ninja ninja = new Ninja(9908, "Naruto", 100, Rank.KAGE);
        Ninja ninja1 = new Ninja(9909, "Sasuke", 105, Rank.KAGE);
        Ninja ninja2 = new Ninja(9907, "Sakura", 60, Rank.JONIN);
        Ninja ninja3 = new Ninja(9906, "Kakashi", 125, Rank.ANBU);
        konohakagure.addNinja(ninja3); konohakagure.addNinja(ninja2); konohakagure.addNinja(ninja1); konohakagure.addNinja(ninja);
        Map<Rank, Long> rank = new TreeMap<>();
        rank.put(Rank.KAGE, Long.valueOf(2));
        rank.put(Rank.JONIN, Long.valueOf(1));
        rank.put(Rank.ANBU, Long.valueOf(1));
        assertEquals(rank, konohakagure.getRankDistribution());
        List<Ninja> topDuo = konohakagure.getTopNinjas(2);
        List<Ninja> topDueCopy = new ArrayList<>();
        topDueCopy.add(ninja3); topDueCopy.add(ninja1);
        assertEquals(topDueCopy, topDuo);
    }
}