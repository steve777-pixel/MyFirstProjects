package models;

import utils.Fighter;
import utils.Validation;

public class Ninja extends AbstractFighter{
    private int speed;
    private String swordType;

    public Ninja(String name, int health, int defense, int attackPower, int speed, String swordType){
        super(name, health, defense, attackPower);
        this.speed = Validation.validateNotNegative(speed, "Speed");
        this.swordType = Validation.validateNotBlank(swordType, "Sword type");
    }

    public int getSpeed(){
        return speed;
    }

    public String getSwordType(){
        return swordType;
    }

    public void setSpeed(int speed){
        this.speed = Validation.validateNotNegative(speed, "Speed");
    }

    public void setSwordType(String swordType){
        this.swordType = Validation.validateNotBlank(swordType, "Sword type");
    }

    @Override
    public void attack(Fighter fighter){
        if(fighter instanceof AbstractFighter){
            AbstractFighter target = (AbstractFighter) fighter;
            target.takeDamageWithoutDefense(this.getAttackPower());
        }
    }
}