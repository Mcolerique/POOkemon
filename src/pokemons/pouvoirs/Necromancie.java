package pokemons.pouvoirs;

import jeu.Jeu;
import joueurs.Joueur;
import joueurs.Terrain;
import affichage.Affichage;
import pokemons.Pokemon;

public class Necromancie extends Pouvoir {

    //Constructeur
    public Necromancie() {
        super("Necromancie", "Ressucite un pokemon mort", 0);
    }

    //Methodes
    @Override
    public void utiliser(Terrain terrain, Joueur allie, Joueur adversaire, Pokemon pokemon, int intPokemon) {
        //On ressucite le pokemon mort
        if (allie.getPokemon(intPokemon).getPv() == 0) {
            allie.getPokemon(intPokemon).soigner(allie.getPokemon(intPokemon).getPvMax() + 40);
            System.out.println(allie.getPokemon(intPokemon).getNom() + " a été ressucité !");
        } else {
            System.out.println(allie.getPokemon(intPokemon).getNom() + " n'est pas mort !");
        }

    }

    @Override
    public void annulerPouvoir(Terrain terrain, Joueur allie, Joueur adversaire, Pokemon pokemon) {
        Jeu.getPokemonAvecPouvoir().remove(pokemon);
    }
}
