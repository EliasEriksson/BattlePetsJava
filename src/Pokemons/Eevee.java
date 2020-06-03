package Pokemons;

import Elements.Element;
import Moves.Move;
import java.util.HashMap;

public class Eevee extends Pokemon {
    private static final int hpStat = 267;
    private static final int physicalAttackStat = 166;
    private static final int physicalDefenceStat = 156;
    private static final int specialAttackStat = 146;
    private static final int specialDefenceStat = 186;
    private static final int speedStat = 166;
    private static final Element[] elements = {Element.normal};

    private static HashMap<Integer, Move> learnableMoves = new HashMap<>() {{
        put(1, Move.tackle);
        put(5, Move.growl);
        put(9, Move.quickAttack);
    }};

    public Eevee(int level){
        super(level, hpStat, physicalAttackStat, physicalDefenceStat, specialAttackStat,
                specialDefenceStat, speedStat, elements,learnableMoves);
    }
}
