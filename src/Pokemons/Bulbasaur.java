package Pokemons;

import Elements.Element;
import Moves.Move;

import java.util.HashMap;

public class Bulbasaur extends Pokemon {
    private static final int hpStat = 250;
    private static final int physicalAttackStat = 142;
    private static final int physicalDefenceStat = 142;
    private static final int specialAttackStat = 191;
    private static final int specialDefenceStat = 131;
    private static final int speedStat = 135;
    private static final Element[] elements = {Element.grass};

    private static HashMap<Integer, Move> learnableMoves = new HashMap<>() {{
        put(1, Move.tackle);
        put(5, Move.growl);
        put(8, Move.razorLeaf);
    }};

    public Bulbasaur(int level){
        super(level, hpStat, physicalAttackStat, physicalDefenceStat, specialAttackStat,
                specialDefenceStat, speedStat, elements, learnableMoves);
    }
}
