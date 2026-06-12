package app;

import models.*;
import java.time.LocalDate;

public class Index{
    public static void main(String[] args){
        GuildMember Naruto = new Warrior("Naruto Uzumaki", "AA8738912", 105, LocalDate.of(2025, 10, 12), 85, "No weapon type");
        GuildMember Sasuke = new Warrior("Sasuke Uchiha", "AA8658912", 100, LocalDate.of(2025, 10, 10), 90, "Jian sword");
        GuildMember Sakura = new Healer("Sakura Haruno", "AF9382901", 80, LocalDate.of(2024, 8, 16), 80, "Rapid healing");
        GuildMember Kakashi = new Mage("Kakashi Hatake", "FK9785840", 120, LocalDate.of(2017, 1, 10), 150, "Hatake spells");
        // System.out.println(Naruto);
        Guild teamSeven = new Guild();
        teamSeven.addMember(Kakashi);
        teamSeven.addMember(Naruto);
        teamSeven.addMember(Sakura);
        teamSeven.addMember(Sasuke);
        Quest dragonQuest = new Quest("Kill the Mountain Dragon", 500, 1000);
        // teamSeven.displayAllQuests();
        teamSeven.assignQuest(dragonQuest);
        // teamSeven.displayAllQuests();
        dragonQuest.assignMembers(Kakashi, Naruto, Sakura, Sasuke);
        // System.out.println(dragonQuest);
        // System.out.println(teamSeven.getGuildRewardValue());
        // teamSeven.displayAllQuests();
        // teamSeven.displayMembersAndLevels();
        // teamSeven.completeQuest(dragonQuest);
        // teamSeven.displayMembersAndLevels();
        // teamSeven.displayAllQuests();
        // System.out.println(teamSeven.getGuildRewardValue());
        teamSeven.displayMembersByType();
    }
}