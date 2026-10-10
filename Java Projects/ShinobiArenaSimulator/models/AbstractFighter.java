package models;

import utils.Fighter;
import utils.Validation;
import java.util.Objects;

public abstract class AbstractFighter implements Fighter{
    private String name;
    private int health;
    private int defense;
    private int attackPower;

    public AbstractFighter(String name, int health, int defense, int attackPower){
        this.name = Validation.validateNotBlank(name, "Name");
        this.health = Validation.validateNotNegative(health, "Health");
        this.defense = Validation.validateNotNegative(defense, "Defense");
        this.attackPower = Validation.validateNotNegative(attackPower, "Attack power");
    }

    @Override public String getName(){return name;}
    @Override public int getHealth(){return health;}
    @Override public int getDefense(){return defense;}
    @Override public int getAttackPower(){return attackPower;}
    @Override public boolean isAlive(){return this.health > 0;}

    public void setName(String name){
        this.name = Validation.validateNotBlank(name, "Name");
    }

    public void setHealth(int health){
        this.health = Validation.validateNotNegative(health, "Health");
    }

    public void setDefense(int defense){
        this.defense = Validation.validateNotNegative(defense, "Defense");
    }

    public void setAttackPower(int attackPower){
        this.attackPower = Validation.validateNotNegative(attackPower, "Attack power");
    }

    @Override
    public void takeDamage(int damage){
        int actualDamage = Math.max(1, damage - this.defense);
        setHealth(Math.max(0, this.health - actualDamage));
    }

    @Override
    public void takeDamageWithoutDefense(int damage){
        setHealth(Math.max(0, this.health - damage));
    }

    @Override
    public String toString(){
        return String.format("Name: %s\nHealth: %d\nDefense: %d\nAttack power: %d", name, health, defense, attackPower);
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj){
            return true;
        }
        if(obj == null || getClass() != obj.getClass()){
            return false;
        }
        AbstractFighter other = (AbstractFighter) obj;
        return Objects.equals(this.name, other.name);
    }

    @Override
    public int hashCode(){
        return Objects.hash(name);
    }
}