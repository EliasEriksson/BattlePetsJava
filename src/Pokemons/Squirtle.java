package Pokemons;

import Elements.Element;
import Moves.Move;

import java.util.HashMap;

public class Squirtle extends Pokemon {
    private static final int hpStat = 258;
    private static final int physicalAttackStat = 100;
    private static final int physicalDefenceStat = 171;
    private static final int specialAttackStat = 154;
    private static final int specialDefenceStat = 189;
    private static final int speedStat = 141;
    private static final Element[] elements = {Element.water};

    private static HashMap<Integer, Move> learnableMoves = new HashMap<>() {{
        put(1, Move.tackle);
        put(5, Move.tailWip);
        put(8, Move.waterGun);
    }};

    public Squirtle(int level){
        super(level, hpStat, physicalAttackStat, physicalDefenceStat, specialAttackStat,
                specialDefenceStat, speedStat, elements, learnableMoves);
    }
}
