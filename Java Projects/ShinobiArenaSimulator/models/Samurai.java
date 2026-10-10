package models;

import java.util.Random;
import utils.Fighter;
import utils.Validation;

public class Samurai extends AbstractFighter{
    private static final Random rand = new Random();
    private String weaponType;

    public Samurai(String name, int health, int defense, int attackPower, String weaponType){
        super(name, health, defense, attackPower);
        this.weaponType = Validation.validateNotBlank(weaponType, "Weapon type");
    }

    public String getWeaponType(){
        return weaponType;
    }

    public final void setWeaponType(String weaponType){
        this.weaponType = Validation.validateNotBlank(weaponType, "Weapon type");
    }

    @Override
    public void attack(Fighter fighter){
        if(!(fighter instanceof AbstractFighter)){
            return;
        }
        AbstractFighter target = (AbstractFighter) fighter;
        boolean isCritical = rand.nextInt(5) == 0;
        if(isCritical){
            target.takeDamage(this.getAttackPower()*2);
            System.out.println("Critical hit!");
        }else{
            target.takeDamage(this.getAttackPower());
        }
    }
}