package Pokemons;

import Elements.Element;
import Moves.Move;

import java.util.HashMap;

public class Pikachu extends Pokemon{
    private static final int hpStat = 230;
    private static final int physicalAttackStat = 163;
    private static final int physicalDefenceStat = 108;
    private static final int specialAttackStat = 154;
    private static final int specialDefenceStat = 136;
    private static final int speedStat = 226;
    private static final Element[] elements = {Element.electric};

    private static HashMap<Integer, Move> learnableMoves = new HashMap<>() {{
        put(1, Move.thunderShock);
        put(5, Move.growl);
        put(11, Move.thunderWave);
        put(20, Move.quickAttack);
    }};

    public Pikachu(int level){
        super(level, hpStat, physicalAttackStat, physicalDefenceStat, specialAttackStat,
                specialDefenceStat, speedStat, elements, learnableMoves);
    }
}
