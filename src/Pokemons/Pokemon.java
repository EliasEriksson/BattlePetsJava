package Pokemons;

import Attack.Attack;
import Effects.Conditions.Conditions;
import Effects.Status.Status;
import Elements.Element;
import Moves.Move;
import Types.Type;

import java.util.HashMap;
import java.util.Random;

public abstract class Pokemon {
    private static final int maxLevel = 100;
    private int level;
    private int hp;
    private int hpGrowth;
    private int physicalAttack;
    private int physicalAttackGrowth;
    private int physicalDefence;
    private int physicalDefenceGrowth;
    private int specialAttack;
    private int specialAttackGrowth;
    private int specialDefence;
    private int specialDefenceGrowth;
    private int speed;
    private int speedGrowth;
    private Element[] elements;
    private Move[] currentMoves;
    private HashMap<Integer, Move> learnableMoves;
    private Status status;
    private Conditions conditions;
    private Attack attack = null;

    private Random RNG = new Random(); // TODO move to factory and pass down

    Pokemon(
        int level,
        int hpStat,
        int physicalAttackStat,
        int physicalDefenceStat,
        int specialAttackStat,
        int specialDefenceStat,
        int speedStat,
        Element[] elements,
        HashMap<Integer, Move> learnableMoves
    ){
        this.hpGrowth = (int)(hpStat * random());
        this.physicalAttackGrowth = (int)(physicalAttackStat * random());
        this.physicalDefenceGrowth = (int)(physicalDefenceStat * random());
        this.specialAttackGrowth = (int)(specialAttackStat * random());
        this.specialDefenceGrowth = (int)(specialDefenceStat * random());
        this.speedGrowth = (int)(speedStat * random());
        this.elements = elements;
        this.learnableMoves = learnableMoves;
        this.currentMoves = new Move[4];
        this.status = new Status();
        this.conditions = new Conditions();


        for (int i = 0; i < level; i++) {
            this.levelUp();
        }
    }

    public int getHealth(){
        return this.hp;
    }

    private void setStats(){
        this.hp = (int)(hpGrowth / (double)(maxLevel) * this.level);
        this.physicalAttack = (int)(physicalAttackGrowth / (double)(maxLevel) * this.level);
        this.physicalDefence = (int)(physicalDefenceGrowth / (double)(maxLevel) * this.level);
        this.specialAttack = (int)(specialAttackGrowth / (double)(maxLevel) * this.level);
        this.specialDefence = (int)(specialDefenceGrowth / (double)(maxLevel) * this.level);
        this.speed = (int)(speedGrowth / (double)(maxLevel) * this.level);
    }

    private int ammountOfLearnedMoves(){
        int ret = 0;
        for (Move currentMove : this.currentMoves) {
            if (currentMove != null) {
                ret++;
            }
        }
        return ret;
    }

    private void addMove(Move move){
        int ammountOfMoves = this.ammountOfLearnedMoves();
        if (ammountOfMoves < this.currentMoves.length){
            this.currentMoves[ammountOfMoves] = move;
        } else {
            int moveIndex = this.RNG.nextInt(this.currentMoves.length);
            this.currentMoves[moveIndex] = move;
        }
    }

    private void levelUp(){
        if (level < maxLevel) {
            this.level++;
            this.setStats();
        }
        if (this.learnableMoves.containsKey(this.level)){
            Move move = this.learnableMoves.get(this.level);
            this.addMove(move);
        }
    }

    private void takeDamage(int damage){
        this.hp -= damage;
        if (this.hp < 0) {
            this.hp = 0;
            this.conditions.modifyCondition(Conditions.Condition.feint, true);
        }
    }

    private void takeStatus(Status status){
        this.status.modifyStatus(status);
    }

    private void takeCondition(Conditions conditions){
        this.conditions.modifyConditions(conditions, true);
    }

    private double calculateModifier(Pokemon attacker, Move move){
        double modifier = 1.;
        for (Element element : attacker.getElements()) {
            if (element == move.getElement()){
                modifier *= 1.5;
                break;
            }
        }

        for (Element element : this.elements){
            Element e = move.getElement();
            if (element.is_immune(e)){
                modifier *= 0;
            }
            if (element.is_resistant(e)){
                modifier *= 0.5;
            }
            if (element.is_super_effective(e)){
                modifier *= 1.5;
            }
        }

        if (attacker.conditions.contains(Conditions.Condition.burned)){
            if (move.getType() == Type.physical){
                modifier *= 0.5;
            }
        }
        return modifier;
    }

    private int calculateDamage(int attack, int defence, double modifier, Move move){
        double damage = 2.;
        damage *= move.getPower();
        damage *= attack / (double)defence;
        damage /= 10;
        damage *= modifier;
        return (int)Math.round(damage);
    }

    public void attack(Move move) {
        this.attack = new Attack(this, move);
    }

    public Attack getAttack() {
        return attack;
    }

    public void defend(Attack attack){
        Pokemon enemy = attack.getPokemon();
        Move move = attack.getMove();
        Type type = move.getType();

        if (type == Type.status) {
            System.out.println("Status changed");
            this.takeStatus(move.getStatus());
        } else if (type == Type.condition){
            System.out.println("Condition changed");
            this.takeCondition(move.getConditions());
        } else {
            double damage;
            if (type == Type.physical){
                System.out.print("Physical ");
                damage = calculateDamage(enemy.physicalAttack, this.physicalDefence,
                        this.calculateModifier(enemy, move), move);
                damage *= (enemy.status.getStat(Status.Stat.physicalAttack) / 10.);
            } else if (type == Type.special) {
                System.out.print("Special ");
                damage = calculateDamage(enemy.specialAttack, this.specialDefence,
                        this.calculateModifier(enemy, move), move);
                damage *= (enemy.status.getStat(Status.Stat.specialAttack) / 10.);
            } else {
                System.out.println("Typeless ");
                int atk = (Math.min(enemy.physicalAttack, enemy.specialAttack));
                atk += Math.round((enemy.physicalAttack - enemy.specialAttack) / 2.);
                int def = (Math.min(this.physicalDefence, this.specialDefence));
                def += Math.round((this.physicalDefence - this.specialDefence) / 2.);
                damage = calculateDamage(atk, def, this.calculateModifier(enemy, move), move);
                damage *= ((enemy.status.getStat(Status.Stat.physicalAttack) +
                            enemy.status.getStat(Status.Stat.specialAttack)) / 2.);
            }
            this.takeDamage((int)damage);
            System.out.println("damage dealt " + (int)damage);
        }
    }

    public int getLevel(){
        return this.level;
    }

    public Status getStatus(){
        return this.status;
    }

    public boolean isFaint(){
        return this.conditions.getCondition(Conditions.Condition.feint);
    }

    public int getSpeed(){
        return this.speed;
    }

    public Move[] getCurrentMoves(){
        Move[] moves = new Move[this.ammountOfLearnedMoves()];
        System.arraycopy(this.currentMoves, 0, moves, 0, moves.length);
        return moves;
    }

    public Element[] getElements() {
        return elements;
    }

    public String getName(){
        return this.getClass().getSimpleName();
    }

    private static double random(){
        double min = 0.9;
        double max = 1.1;
        return min + Math.random() * (max - min);
    }

    @Override
    public String toString() {
        return this.getName() + " {" +
                "\n\tlevel=" + level +
                ",\n\thp=" + hp +
                ",\n\tphysicalAttack=" + physicalAttack +
                ",\n\tphysicalDefence=" + physicalDefence +
                ",\n\tspecialAttack=" + specialAttack +
                ",\n\tspecialDefence=" + specialDefence +
                ",\n\tspeed=" + speed +
                ",\n\tcurrentMoves=" + Move.moveArrayToString(currentMoves) +
                ",\n\tstatus=" + this.status +
                "}\n";
    }
}
