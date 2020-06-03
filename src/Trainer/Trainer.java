package Trainer;

import Moves.Move;
import Pokemons.Pokemon;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public abstract class Trainer {
    private static final int maxPokemons = 6;
    private int currentAmmountOfPokemon = 0;
    private Pokemon[] pokemons = new Pokemon[maxPokemons];
    private Pokemon currentlyFighting = null;
    private String name;
    private Random rng = new Random();

    Trainer(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean addPokemon(Pokemon pokemon){
        for (int i = 0; i < this.pokemons.length; i++) {
            if (this.pokemons[i] == null){
                this.pokemons[i] = pokemon;
                currentAmmountOfPokemon ++;
                return true;
            }
        }
        return false;
    }

    public Pokemon getCurrentlyFighting() {
        return currentlyFighting;
    }

    public void summonLead(){
        this.currentlyFighting = this.pokemons[0];
    }

    public boolean isFullTeamFaint(){
        for (Pokemon pokemon : this.pokemons) {
            if (pokemon != null) {
                if (!pokemon.isFaint()) {
                    return false;
                }
            }
        }
        return true;
    }

    public Pokemon[] getPokemons() {
        return pokemons;
    }

    public Move moveSelect(Scanner scanner) {
        return this.moveSelect();
    }

    public Move moveSelect(){
        Move[] moves = this.getCurrentlyFighting().getCurrentMoves();
        return moves[this.rng.nextInt(moves.length)];
    }

    public void pokemonSelect(){
        for (Pokemon pokemon : this.getPokemons()) {
            if (!pokemon.isFaint()){
                swapPokemon(pokemon);
            }
        }
    }

    public void swapPokemon(Pokemon pokemon){
        this.currentlyFighting = pokemon;
    }

    @Override
    public String toString() {
        return "Trainer{" +
                "currentAmmountOfPokemon=" + currentAmmountOfPokemon +
                ", pokemons=" + Arrays.toString(pokemons) +
                ", currentlyFighting=" + currentlyFighting +
                '}';
    }
}
