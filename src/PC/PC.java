package PC;

import Pokemons.Pokemon;

public class PC {
    private static final int ammountOfBoxes = 10;
    private Box[] boxes = new Box[ammountOfBoxes];

    public void addPokemon(Pokemon pokemon) throws PcFull {
        for (Box box : this.boxes) {
            try {
                box.addPokemon(pokemon);
                return;
            } catch (BoxFull ignored) {}
        }
        throw new PcFull("All boxes in the PC are full.");
    }
}
