package models;

import utils.Validation;
import java.time.LocalDate;

public class Warrior extends GuildMember{
    private int strength;
    private String weaponType;

    public Warrior(String name, String id, int level, LocalDate joinedDate, int strength, String weaponType){
        super(name, id, level, joinedDate);
        this.strength = Validation.validateNotNegative(strength, "Strength");
        this.weaponType = Validation.validateNotBlank(weaponType, "Weapon type");
    }

    public int getStrength(){
        return strength;
    }

    public String getWeaponType(){
        return weaponType;
    }

    @Override 
    public String getType(){
        return "Warrior";
    }

    @Override
    public String getRoleDescription() {
        return "A fierce frontline fighter specialized in wielding a " + weaponType + ".";
    }

    @Override
    public String toString(){
        return super.toString() + "\nStrength: " + strength + "\nWeapon type: " + weaponType;
    }
}