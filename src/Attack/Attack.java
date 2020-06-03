package Attack;

import Effects.Status.Status;
import Moves.Move;
import Pokemons.Pokemon;


public class Attack {
    private Pokemon pokemon;
    private Move move;

    public Attack(Pokemon pokemon, Move move){
        this.pokemon = pokemon;
        this.move = move;
    }


    public Move getMove() {
        return this.move;
    }

    public Pokemon getPokemon() {
        return pokemon;
    }

    public int getSpeed(){
        Status status = this.pokemon.getStatus();
        double speedModifier = status.getStat(Status.Stat.speed) / 10.;
        return (int)Math.round(this.pokemon.getSpeed() * speedModifier);
    }

    public int getPriority(){
        return this.getMove().getPriority();
    }
}
