package Pokemons;

import java.lang.reflect.InvocationTargetException;

public class PokemonFactory {
    private static final String packageName = PokemonFactory.class.getPackageName();

    public static Pokemon generatePokemon(Pokemons pokemon, int level) {
        String pokemonClassName = packageName + "." + pokemon.name();
        try {
            return (Pokemon)Class.forName(pokemonClassName).getDeclaredConstructor(Integer.TYPE).newInstance(level);
        } catch (InstantiationException |
                 IllegalAccessException |
                 InvocationTargetException |
                 NoSuchMethodException |
                 ClassNotFoundException ignored) {
        }
        return new MissingNo(level);
    }

    public static void main(String[] args) {
        for (Pokemons pokemon : Pokemons.values()) {
            Pokemon result = generatePokemon(pokemon, 0);
            if (!result.getName().equals(pokemon.name())) {
                System.out.println("Class: " + pokemon.name() + "(Pokemon) does not exist.");
            }
        }
    }

    public enum Pokemons {
        Bulbasaur,
        Squirtle,
        Charmander,
        Chikorita,
        Totodile,
        Cyndaquil,
        Pikachu,
        Eevee,
        MissingNo;
    }
}
