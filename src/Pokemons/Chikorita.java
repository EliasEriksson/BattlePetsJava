package Pokemons;

import Elements.Element;
import Moves.Move;

import java.util.HashMap;

public class Chikorita extends Pokemon {
    private static final int hpStat = 240;
    private static final int physicalAttackStat = 152;
    private static final int physicalDefenceStat = 181;
    private static final int specialAttackStat = 142;
    private static final int specialDefenceStat = 181;
    private static final int speedStat = 135;
    private static final Element[] elements = {Element.grass};

    private static HashMap<Integer, Move> learnableMoves = new HashMap<>() {{
        put(1, Move.tackle);
        put(5, Move.growl);
        put(12, Move.razorLeaf);
    }};

    public Chikorita(int level){
        super(level, hpStat, physicalAttackStat, physicalDefenceStat, specialAttackStat,
                specialDefenceStat, speedStat, elements, learnableMoves);
    }
}
