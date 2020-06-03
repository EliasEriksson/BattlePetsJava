package Pokemons;

import Elements.Element;
import Moves.Move;
import java.util.HashMap;

public class MissingNo extends Pokemon {
    private static final int hpStat = 0;
    private static final int physicalAttackStat = 0;
    private static final int physicalDefenceStat = 0;
    private static final int specialAttackStat = 0;
    private static final int specialDefenceStat = 0;
    private static final int speedStat = 0;
    private static final Element[] elements = {};

    private static HashMap<Integer, Move> learnableMoves = new HashMap<>() {{
    }};

    public MissingNo(int level){
        super(level, hpStat, physicalAttackStat, physicalDefenceStat, specialAttackStat,
                specialDefenceStat, speedStat, elements, learnableMoves);
    }
}
