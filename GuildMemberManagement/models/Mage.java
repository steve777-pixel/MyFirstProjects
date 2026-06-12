package models;

import utils.Validation;
import java.time.LocalDate;

public class Mage extends GuildMember{
    private int mana;
    private String spellSchool;

    public Mage(String name, String id, int level, LocalDate joinedDate, int mana, String spellSchool){
        super(name, id, level, joinedDate);
        this.mana = Validation.validateNotNegative(mana, "Mana");
        this.spellSchool = Validation.validateNotBlank(spellSchool, "Spell school");
    }

    public int getMana(){
        return mana;
    }

    public String getSpellSchool(){
        return spellSchool;
    }

    @Override 
    public String getType(){
        return "Mage";
    }

    @Override
    public String getRoleDescription() {
        return "A fierce frontline fighter specialized in wielding a " + spellSchool + ".";
    }

    @Override
    public String toString(){
        return super.toString() + "\nMana: " + mana + "\nSpell school: " + spellSchool;
    }
}