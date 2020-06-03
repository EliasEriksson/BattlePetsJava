package PC;

import Pokemons.Pokemon;

public class Box {
    private static final int boxSize = 30;

    private Pokemon[] pokemons = new Pokemon[boxSize];

    public void addPokemon(Pokemon pokemon) throws BoxFull {
        for (int i = 0; i < this.pokemons.length; i++) {
            if (this.pokemons[i] == null){
                this.pokemons[i] = pokemon;
                return;
            }
        }
        throw new BoxFull("Box is full.");
    }
}
