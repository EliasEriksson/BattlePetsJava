package Pokemons;

import Elements.Element;
import Moves.Move;

import java.util.HashMap;

public class Cyndaquil  extends Pokemon {
    private static final int hpStat = 235;
    private static final int physicalAttackStat = 160;
    private static final int physicalDefenceStat = 142;
    private static final int specialAttackStat = 176;
    private static final int specialDefenceStat = 156;
    private static final int speedStat = 186;
    private static final Element[] elements = {Element.fire};

    private static HashMap<Integer, Move> learnableMoves = new HashMap<>() {{
        put(1, Move.tackle);
        put(5, Move.growl);
        put(9, Move.ember);
    }};

    public Cyndaquil(int level){
        super(level, hpStat, physicalAttackStat, physicalDefenceStat, specialAttackStat,
                specialDefenceStat, speedStat, elements,learnableMoves);
    }
}
