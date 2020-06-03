package Pokemons;

import Elements.Element;
import Moves.Move;

import java.util.HashMap;

public class Totodile extends Pokemon {
    private static final int hpStat = 250;
    private static final int physicalAttackStat = 161;
    private static final int physicalDefenceStat = 139;
    private static final int specialAttackStat = 133;
    private static final int specialDefenceStat = 100;
    private static final int speedStat = 141;
    private static final Element[] elements = {Element.water};

    private static HashMap<Integer, Move> learnableMoves = new HashMap<>() {{
        put(1, Move.scratch);
        put(5, Move.growl);
        put(12, Move.waterGun);
    }};

    public Totodile(int level){
        super(level, hpStat, physicalAttackStat, physicalDefenceStat, specialAttackStat,
                specialDefenceStat, speedStat, elements, learnableMoves);
    }
}
