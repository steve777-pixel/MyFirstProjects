package models;

import utils.Fighter;
import utils.Validation;

public class Mage extends AbstractFighter{
    private static final int FIREBALL_MANA_COST = 200;
    private static final int MAX_MANA = 5_000;
    private int mana;

    public Mage(String name, int health, int defense, int attackPower, int mana){
        super(name, health, defense, attackPower);
        int validatedMana = Validation.validateNotNegative(mana, "Mana amount");
        this.mana = Math.min(MAX_MANA, validatedMana);
    }

    public int getMana(){
        return mana;
    }

    public void setMana(int mana){
        int validatedMana = Validation.validateNotNegative(mana, "Mana amount");
        this.mana = Math.min(MAX_MANA, validatedMana);
    }

    public void addMana(int amount){
        setMana(this.mana + amount);
    }

    private void FireballAttack(AbstractFighter target){
        target.takeDamage(this.getAttackPower() * 2);
        setMana(this.mana - FIREBALL_MANA_COST);
    }

    @Override
    public void attack(Fighter fighter){
        if(!(fighter instanceof AbstractFighter)){
            return;
        }
        AbstractFighter target = (AbstractFighter) fighter;
        if(this.mana >= FIREBALL_MANA_COST){
            FireballAttack(target);
        }else{
            target.takeDamage(this.getAttackPower());
        }
    }
}