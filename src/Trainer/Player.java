package Trainer;

import Moves.Move;
import PC.PC;
import PC.PcFull;
import Pokemons.Pokemon;

import java.util.Scanner;

public class Player extends Trainer{
    private PC pc = new PC();

    public Player(String name) {
        super(name);
    }

    @Override
    public boolean addPokemon(Pokemon pokemon) {
        if (super.addPokemon(pokemon)){
            return true;
        }
        try {
            this.pc.addPokemon(pokemon);
            return true;
        } catch (PcFull pcFull) {
            pcFull.printStackTrace();
        }
        return false;
    }

    public Move moveSelect(Scanner scanner) {
        return select(this.getCurrentlyFighting().getCurrentMoves(), scanner);
    }

    public Pokemon pokemonSelect(Scanner scanner){
        Pokemon selected = this.getCurrentlyFighting();
        while (selected != this.getCurrentlyFighting()){
            selected = select(this.getPokemons(), scanner);
        }
        this.swapPokemon(selected);
        return selected;
    }

    public static <T> T select(T[] array, Scanner scanner) {
        for (int i = 0; i < array.length; i++) {
            System.out.println((i + 1) + ": " + array[i]);
        }
        while (scanner.hasNext()){
            if (scanner.hasNextInt()){
                int selection = scanner.nextInt();
                if (1 <= selection && selection < array.length + 1){
                    return array[selection - 1];
                }
                System.out.println("Selection must be in interval " + 1 + " <= selection <= " + (array.length));
            } else {
                scanner.next();
            }
        }
        throw new NullPointerException();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] strings = new String[]{"hello", "there", "how", "is", "it"};
        System.out.println(Player.select(strings, scanner));
    }
}
