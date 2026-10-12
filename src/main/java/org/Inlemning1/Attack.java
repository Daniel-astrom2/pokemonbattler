package org.Inlemning1;

public class Attack {
    private String name;
    private int baseDamage;
    private int accuracy;
    private Type type;


    public Attack(String name, int baseDamage, int accuracy, Type type) {
        this.name = name;
        this.baseDamage = baseDamage;
        this.accuracy = accuracy;
        this.type = type;
    }
    public String getName() {
        return name;
    }
    public int getBaseDamage()  {
        return baseDamage;

    }
    public int getAccuracy()  {
        return accuracy;
    }
    public  Type getType()  {
        return type;
    }
}
