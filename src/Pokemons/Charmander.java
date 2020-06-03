package Pokemons;

import Elements.Element;
import Moves.Move;

import java.util.HashMap;

public class Charmander extends Pokemon {
    private static final int hpStat = 242;
    private static final int physicalAttackStat = 110;
    private static final int physicalDefenceStat = 141;
    private static final int specialAttackStat = 192;
    private static final int specialDefenceStat = 124;
    private static final int speedStat = 181;
    private static final Element[] elements = {Element.fire};

    private static HashMap<Integer, Move> learnableMoves = new HashMap<>() {{
        put(1, Move.scratch);
        put(5, Move.tailWip);
        put(9, Move.ember);
    }};

    public Charmander(int level){
        super(level, hpStat, physicalAttackStat, physicalDefenceStat, specialAttackStat,
                specialDefenceStat, speedStat, elements, learnableMoves);
    }
}
