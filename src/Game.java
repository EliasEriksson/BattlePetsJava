import Pokemons.Pokemon;
import Pokemons.PokemonFactory;
import Trainer.Ai;
import Trainer.Player;
import Trainer.Trainer;

import java.util.Random;
import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
        Game game = new Game();
        game.runGame();
    }
    Scanner scanner = new Scanner(System.in);
    Player player = new Player("Ash");
    Random rng = new Random();

    public void runGame(){
        this.startGame();
        Ai trainer = generateTrainer(2, 3);
        this.battle(trainer);

    }

    private void battle(Ai trainer){
        this.battleStart(this.player, trainer);
        while (!this.player.isFullTeamFaint() && !trainer.isFullTeamFaint()){
            this.player.getCurrentlyFighting().attack(this.player.moveSelect(this.scanner));
            trainer.getCurrentlyFighting().attack(trainer.moveSelect());
            this.attackHandle(this.player, trainer);
        }
    }

    private void executeAttack(Trainer attackingTrainer, Trainer defendingTrainer){
        System.out.println(
                attackingTrainer.getName() + "´s " +
                        attackingTrainer.getCurrentlyFighting().getName() + " uses " +
                        attackingTrainer.getCurrentlyFighting().getAttack().getMove() + " against " +
                        defendingTrainer.getName() + "´s " +
                        defendingTrainer.getCurrentlyFighting().getName()
        );
        defendingTrainer.getCurrentlyFighting().defend(attackingTrainer.getCurrentlyFighting().getAttack());
        Press.enter(this.scanner);
        if (defendingTrainer.getCurrentlyFighting().isFaint()){
            this.announceFeint(defendingTrainer);
        }
    }

    private void battleStart(Trainer player, Trainer trainer){
        player.summonLead();
        trainer.summonLead();
        System.out.println(
            "A battle has started between " +
            player.getName() + " and " +
            trainer.getName()
        );
        System.out.println(
            player.getName() + " sent out " +
            player.getCurrentlyFighting().getName()
        );
        System.out.println(
            trainer.getName() + " sent out " +
            trainer.getCurrentlyFighting().getName()
        );
        Press.enter(this.scanner);
    }

    private void announceFeint(Trainer trainer){
        System.out.println(
            trainer.getName() + "`s " +
            trainer.getCurrentlyFighting().getName() + " feinted!"
        );
        Press.enter(this.scanner);
        if (trainer.isFullTeamFaint()){
            System.out.println(
                trainer.getName() + " lost the battle."
            );
        } else {
            trainer.pokemonSelect();
            System.out.println(
                trainer.getName() + " sent out " +
                trainer.getCurrentlyFighting()
            );
        }
        Press.enter(this.scanner);
    }

    private void attackHandle(Trainer player, Trainer trainer){
        // TODO add support for accuracy and conditions
        if (player.getCurrentlyFighting().getAttack().getSpeed() > trainer.getCurrentlyFighting().getAttack().getSpeed()){
            executeAttack(player, trainer);
            if (!trainer.getCurrentlyFighting().isFaint()){
                this.executeAttack(trainer, player);
            }
        } else if (trainer.getCurrentlyFighting().getAttack().getSpeed() > player.getCurrentlyFighting().getAttack().getSpeed()){
            executeAttack(trainer, player);
            if (!player.getCurrentlyFighting().isFaint()){
                this.executeAttack(player, trainer);
            }
        } else {
            if (this.rng.nextDouble() > 0.5){
                this.executeAttack(player, trainer);
                if (!trainer.getCurrentlyFighting().isFaint()){
                    this.executeAttack(trainer, player);
                } else {
                    this.announceFeint(trainer);
                }
            } else {
                this.executeAttack(trainer, player);
                if (!player.getCurrentlyFighting().isFaint()){
                    this.executeAttack(player, trainer);
                }
            }
        }
    }

    private Ai generateTrainer(int pokemonAmmount, int levels){
        Ai ai = new Ai("Cool Trainer John");
        Pokemon pokemon = null;
        for (int i = 0; i < pokemonAmmount; i++) {
            while (pokemon == null || !pokemon.getName().equals(PokemonFactory.Pokemons.MissingNo.name())){
                int choice = rng.nextInt(PokemonFactory.Pokemons.values().length);
                pokemon = PokemonFactory.generatePokemon(PokemonFactory.Pokemons.values()[choice], levels);
            }
            ai.addPokemon(pokemon);
        }
        ai.addPokemon(PokemonFactory.generatePokemon(PokemonFactory.Pokemons.Pikachu, 5));
        return ai;
    }

    private void startGame(){
        System.out.println("Select a starter pokemon");
        int starterLevel = 5;
        Pokemon[] starters = new Pokemon[]{
                PokemonFactory.generatePokemon(PokemonFactory.Pokemons.Bulbasaur, 5),
                PokemonFactory.generatePokemon(PokemonFactory.Pokemons.Squirtle, starterLevel),
                PokemonFactory.generatePokemon(PokemonFactory.Pokemons.Charmander, starterLevel)
        };
        Pokemon selectedStarter = Player.select(starters, scanner);
        this.player.addPokemon(selectedStarter);
        System.out.println("You have selected " + selectedStarter.getName() + " as your starter!");
    }
}
