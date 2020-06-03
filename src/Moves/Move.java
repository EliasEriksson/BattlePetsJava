package Moves;

import Effects.Conditions.Conditions;
import Effects.Status.Status;
import Elements.Element;
import Types.Type;

public enum Move {
    tackle(Element.normal, Type.physical, 35, 95, 0,
            new Status(), new Conditions()),
    scratch(Element.normal, Type.physical, 35, 100, 0,
            new Status(), new Conditions()),
    quickAttack(Element.normal, Type.physical, 40, 100, 1,
            new Status(), new Conditions()),
    thunderWave(Element.electric, Type.special, 0, 100, 0,
            new Status(),
            new Conditions().modifyCondition(Conditions.Condition.paralyzed, true)),
    growl(Element.typeLess, Type.status, 0, 100, 0,
            new Status().modifyStat(Status.Stat.physicalAttack, -1),
            new Conditions()),
    tailWip(Element.typeLess, Type.status, 0, 100, 0,
            new Status().modifyStat(Status.Stat.physicalDefence, -1),
            new Conditions()),
    ember(Element.fire, Type.special, 40, 100, 0,
            new Status(), new Conditions()),
    waterGun(Element.water, Type.special, 40, 100, 0,
            new Status(), new Conditions()),
    razorLeaf(Element.grass, Type.special, 55, 95, 0,
            new Status(), new Conditions()),
    thunderShock(Element.electric, Type.special, 30, 100, 0,
            new Status(), new Conditions())
    ;


    private String name;
    private Element element;
    private Type type;
    private int power;
    private int accuracy;
    private int priority;
    private Status status;
    private Conditions conditions;

    Move(
        Element element,
        Type type,
        int power,
        int accuracy,
        int priority,
        Status status,
        Conditions conditions
    ){
        this.name = this.name();
        this.element = element;
        this.power = power;
        this.type = type;
        this.accuracy = accuracy;
        this.priority = priority;
        this.status = status;
        this.conditions = conditions;
    }

    public Element getElement() {
        return element;
    }

    public Type getType() {
        return type;
    }

    public int getPower() {
        return power;
    }

    public int getAccuracy() {
        return accuracy;
    }

    public int getPriority() {
        return priority;
    }

    public Status getStatus(){
        return this.status;
    }

    public Conditions getConditions() {
        return conditions;
    }

    @Override
    public String toString() {
        return this.name;
    }

    public void view(){
        System.out.println(
                this.name + ": Element " + this.element + ", Power " +
                this.power + ", Accuracy " + this.accuracy + ", Type " +
                this.type + ", priority " + this.priority);
    }

    public static String moveArrayToString(Move[] moves){
        StringBuilder builder = new StringBuilder();
        for (Move move: moves) {
            if (move != null){
                builder.append(", ").append(move.toString());
            }
        }
        if (builder.length() > 2){
            return "[" + builder.substring(2) + "]";
        }
        return "[]";
    }
}
