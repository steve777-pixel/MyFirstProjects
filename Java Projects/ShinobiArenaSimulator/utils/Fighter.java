package utils;

public interface Fighter {

    String getName();
    int getHealth();
    int getDefense();
    int getAttackPower();

    void attack(Fighter target);
    void takeDamage(int damage);
    void takeDamageWithoutDefense(int damage);

    boolean isAlive();
}