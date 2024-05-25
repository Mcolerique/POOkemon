package pokemons.pouvoirs;

import jeu.Jeu;
import joueurs.Joueur;
import joueurs.Terrain;
import pokemons.Pokemon;

public class SoinDeZone extends Pouvoir{
    public SoinDeZone(){
        super("Soin de zone");
    }

    @Override
    public void utiliser(Terrain terrain, Joueur allie, Joueur adversaire, Pokemon pokemon, int intPokemon) {
        for (int i = 0; i<terrain.getM_pokemonsJoueur(allie).size();i++){
            terrain.getPokemon(allie,i).soigner(10);
        }
    }

    @Override
    public void annulerPouvoir(Terrain terrain, Joueur allie, Joueur adversaire, Pokemon pokemon) {
        Jeu.getM_pokemonAvecPouvoir().remove(pokemon);
    }
}
