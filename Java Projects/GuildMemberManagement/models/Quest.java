package models;

import utils.Validation;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Quest{
    private boolean isComplete = false; // all quests are incomplete when created or assigned
    private String title;
    private int difficulty;
    private int reward;
    private List<GuildMember> assignedMembers;

    public Quest(String title, int difficulty, int reward){
        this.title = Validation.validateNotBlank(title, "Quest title");
        this.difficulty = Validation.validateNotNegative(difficulty, "Quest difficulty");
        this.reward = Validation.validateNotNegative(reward, "Quest reward");
        this.assignedMembers = new ArrayList<>();
    }

    public void assignMembers(GuildMember... members){ 
        for(GuildMember member : members){
            if(member == null || assignedMembers.contains(member)){
                continue;
            }
            assignedMembers.add(member);
        }
    }

    public void completeQuest(){
        if(isComplete){
            throw new IllegalStateException("The quest is already completed.");
        }
        for(GuildMember member : assignedMembers){
            member.addLevel(difficulty, assignedMembers.size());
        }
        this.isComplete = true;
    }

    public String getTitle(){
        return title;
    }

    public int getDifficulty(){
        return difficulty;
    }

    public int getReward(){
        return reward;
    }

    public boolean getStatus(){
        return isComplete;
    }

    public List<GuildMember> getAssignedMembers(){
        return assignedMembers;
    }

    @Override
    public String toString(){
        String membersLine = assignedMembers.isEmpty() ? "None" : assignedMembers.stream().map(GuildMember::getName).collect(Collectors.joining(", "));
        return "Title: " + title + " (" + isComplete + ")" + "\nDifficulty: " + difficulty + "\nReward: " + reward + "\nAssigned Members: " + membersLine;
    }
}