package Effects.Conditions;

import java.util.HashMap;

public class Conditions {
    private HashMap<Condition, Boolean> conditions;

    public Conditions(){
        this.reset();
    }

    public boolean getCondition(Condition condition){
        return this.conditions.get(condition);
    }

    public Conditions modifyCondition(Condition condition, boolean affected) {
        this.conditions.put(condition, affected);
        return this;
    }

    public void modifyConditions(Conditions conditions, boolean affected) {
        for (Condition condition: Condition.values()) {
            this.conditions.put(condition, affected);
        }
    }

    public boolean contains(Condition condition){
        return this.conditions.containsKey(condition);
    }

    public void reset(){
        this.conditions = new HashMap<>() {{
            put(Condition.paralyzed, false);
            put(Condition.poisoned, false);
            put(Condition.sleeping, false);
            put(Condition.confused, false);
            put(Condition.burned, false);
            put(Condition.frozen, false);
            put(Condition.feint, false);
        }};
    }

    public enum Condition {
        paralyzed,
        poisoned,
        sleeping,
        confused,
        burned,
        frozen,
        feint
    }

    public static void main(String[] args) {
        Conditions conditions = new Conditions();
        for (Condition condition: Condition.values()) {
            if (!(conditions.conditions.containsKey(condition))){
                System.out.println("Condition " + condition + " is missing in the hashmap.");
            }
        }
    }
}
