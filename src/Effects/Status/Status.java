package Effects.Status;

import java.util.HashMap;

public class Status {
    private static final int maxStacks = 15;
    private static final int minStacks = 5;
    private HashMap<Stat, Integer> status;

    public Status(){
        this.reset();
    }

    public Status modifyStat(Stat stat, int change){
        this.status.put(stat, this.status.get(stat) + change);
        this.checkStatStacks(stat);
        return this;
    }

    public void modifyStatus(Status status){
        for (Stat stat: Stat.values()) {
            this.status.put(stat, this.status.get(stat) + status.getStat(stat));
            this.checkStatStacks(stat);
        }
    }

    private void checkStatStacks(Stat stat){
        if (this.status.get(stat) > maxStacks) {
            this.status.put(stat, maxStacks);
        } else if (this.status.get(stat) < minStacks){
            this.status.put(stat, minStacks);
        }
    }

    public Integer getStat(Stat stat){
        return this.status.get(stat);
    }

    public void reset(){
        this.status = new HashMap<>() {{
            put(Stat.physicalAttack, 10);
            put(Stat.physicalDefence, 10);
            put(Stat.specialAttack, 10);
            put(Stat.specialDefence, 10);
            put(Stat.speed, 10);
            put(Stat.evasiveness, 10);
            put(Stat.accuracy, 10);
        }};
    }

    public enum Stat {
        physicalAttack,
        physicalDefence,
        specialAttack,
        specialDefence,
        speed,
        evasiveness,
        accuracy
    }

    @Override
    public String toString() {
        return " Status {" +
                "\n\t\tphysicalAttack=" + this.status.get(Stat.physicalAttack) +
                "\n\t\tphysicalDefence=" + this.status.get(Stat.physicalDefence) +
                "\n\t\tspecialAttack=" + this.status.get(Stat.specialAttack) +
                "\n\t\tspecialDefence" + this.status.get(Stat.specialDefence) +
                "\n\t\tspeed=" + this.status.get(Stat.speed) +
                "\n\t\tevasiveness=" + this.status.get(Stat.evasiveness) +
                "\n\t\taccuracy=" + this.status.get(Stat.accuracy) +
                "\n\t}\n";
    }

    public static void main(String[] args) {
        Status status = new Status();

        for (Status.Stat stat: Status.Stat.values()) {
            if (!status.status.containsKey(stat)){
                System.out.println("Status class missing value " + stat + " in hashmap.");
            }
        }
    }
}
