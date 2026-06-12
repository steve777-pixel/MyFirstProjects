package models;

import utils.Validation;
import java.time.LocalDate;

public class GuildMember {
    private String name;
    private String id;
    private int level;
    private LocalDate joinedDate;

    public GuildMember(String name, String id, int level, LocalDate joinedDate){
        this.name = Validation.validateNotBlank(name, "Name");
        this.id = Validation.validateNotBlank(id, "Member ID");
        this.level = Validation.validateNotNegative(level, "Member level");
        this.joinedDate = Validation.validateDate(joinedDate, "Joined date");
    }

    public String getName(){
        return name;
    }

    public String getId(){
        return id;
    }

    public int getLevel(){
        return level;
    }

    public String getType(){
        return "Guild member";
    }

    public LocalDate getJoinedDate(){
        return joinedDate;
    }

    public void addLevel(int level, int groupSize){
        if(level <= 0){
            throw new IllegalArgumentException("Cannot decrease the member level.");
        }
        this.level+=Math.round(level/(10.0*groupSize));
    }

    public String getRoleDescription() {
        return "A standard guild member helping the community.";
    }

    @Override
    public String toString(){
        return "Name: " + name + "\nID: " + id + "\nLevel: " + level + "\nJoined date: " + joinedDate;
    }
}
