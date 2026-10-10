package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import utils.Validation;
import java.util.stream.Collectors;

public class Guild{
    private int guildRewardValue = 0;
    private Map<String, GuildMember> members; // <ID, Member>
    private List<Quest> quests; // quests in progress

    public Guild(){
        members = new HashMap<>();
        quests = new ArrayList<>();
    }
    
    public int getGuildRewardValue(){
        return guildRewardValue;
    }

    public void assignQuest(Quest quest){
        if(quest == null){
            throw new IllegalArgumentException("Quest cannot be null.");
        }
        if(quests.contains(quest)){
            throw new IllegalStateException("The quest is already assigned to the guild.");
        }
        quests.add(quest);
    }

    public void removeQuest(Quest quest){
        if(quest == null){
            throw new IllegalArgumentException("Quest cannot be null.");
        }
        if(!quests.contains(quest)){
            throw new IllegalStateException("This quest was not assigned to the guild.");
        }
        quests.remove(quest);
    }

    public void displayAllQuests(){
        if(quests.isEmpty()){
            return;
        }
        for(Quest quest : quests){
            System.out.println(quest);
        }
    }

    public void completeQuest(Quest quest){
        if(quest == null){
            throw new IllegalArgumentException("Quest cannot be null.");
        }
        if(!quests.contains(quest)){
            throw new IllegalStateException("This quest was not assigned to this guild.");
        }
        quest.completeQuest();
        guildRewardValue+=quest.getReward();
    }


    public void addMember(GuildMember member){
        if(member == null){
            throw new IllegalArgumentException("Member cannot be null.");
        }
        if(members.containsKey(member.getId())){
            throw new IllegalStateException("Member with such ID already exists.");
        }
        members.put(member.getId(), member);
    }

    public void removeMember(String id){
        if(id == null){
            throw new IllegalArgumentException("ID cannot be null.");
        }
        if(members.isEmpty()){
            throw new IllegalStateException("Members list is empty.");
        }
        if(!members.containsKey(id)){
            throw new IllegalStateException("Member with this ID was not found.");
        }
        members.remove(id);
    }

    public GuildMember searchMemberByID(String id){
        if(members.isEmpty()){
            throw new IllegalStateException("The members list is empty.");
        }
        if(!members.containsKey(id)){
            throw new IllegalStateException("There is no member with such ID.");
        }
        return members.get(id);
    }

    public void displayMembersByType(){
        Map<String, List<GuildMember>> grouped = members.values().stream().collect(Collectors.groupingBy(GuildMember::getType));
        String[] typesOrder = {"Guild member", "Warrior", "Mage", "Healer"};
        for(String type : typesOrder){
            System.out.println(type + "s:");
            if(grouped.containsKey(type)){
                grouped.get(type).forEach(System.out::println);
            }
        }
    }

    public void displayMembersofSpecificType(String type){
        if(!Validation.isValidMemberType(type)){
            throw new IllegalArgumentException("Invalid member type!");
        }
        for(GuildMember member : members.values()){
            if(member.getType().equals(type)){
                System.out.println(member);
            }
        }
    }

    public void displayMembersAndLevels(){
        if(members.isEmpty()){
            return;
        }
        for(GuildMember member : members.values()){
            System.out.println(member.getName() + " | Level: " + member.getLevel());
        }
    }
}