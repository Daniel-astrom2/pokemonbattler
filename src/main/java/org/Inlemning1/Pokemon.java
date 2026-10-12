package org.Inlemning1;
import java.util.ArrayList;
import java.util.List;



public class Pokemon {

    private String name;
    private Type type;
    private int maxHp;
    private int currentHp;
    private List<Attack> attacks;

    public Pokemon(String name, Type type, int maxHp) {
        this.name = name;
        this.type = type;
        this.maxHp = maxHp;
        this.currentHp = maxHp;
        this.attacks = new ArrayList<>();

    }

    public void addAttack(Attack attack) {
        attacks.add(attack);
    }

    public String getName() {
        return name;
    }
    public Type getType() {
        return type;
    }
    public int getMaxHp()  {
        return maxHp;
    }
    public int getCurrentHp() {
        return currentHp;
    }
    public void setType(Type type) {
        this.type = type;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
        this.currentHp = maxHp;
    }
    public void takeDamage(int amount) {

        if (amount < 0) {
            throw new IllegalArgumentException("Skada kan inte vara negativ");
        }


        currentHp = Math.max(0, currentHp - amount);
    }
    public boolean isFainted() {
        return currentHp == 0;
    }




    public List<Attack> getAttacks()  {
        return attacks;
    }
}
