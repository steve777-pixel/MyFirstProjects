package models;

import utils.Validation;
import java.time.LocalDate;

public class Healer extends GuildMember{
    private int healingPower;
    private String specialization;

    public Healer(String name, String id, int level, LocalDate joinedDate, int healingPower, String specialization){
        super(name, id, level, joinedDate);
        this.healingPower = Validation.validateNotNegative(healingPower, "Healing power");
        this.specialization = Validation.validateNotBlank(specialization, "Specialization");
    }

    public int getHealingPower(){
        return healingPower;
    }

    public String getSpecialization(){
        return specialization;
    }

    @Override 
    public String getType(){
        return "Healer";
    }

    @Override
    public String getRoleDescription() {
        return "A fierce frontline fighter specialized in " + specialization + ".";
    }

    @Override
    public String toString(){
        return super.toString() + "\nHealing power: " + healingPower + "\nSpecialization: " + specialization;
    }
}