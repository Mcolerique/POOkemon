package pokemons.pouvoirs;

import affichage.Affichage;
import jeu.Jeu;
import joueurs.Joueur;
import joueurs.Terrain;
import pokemons.Pokemon;

import java.util.ArrayList;
import java.util.List;

public class Kamikaze extends Pouvoir {

    //Constructeur
    public Kamikaze() {
        super("Kamikaze","Kamikaze, à utilisation unique : le Pokémon choisit un Pokémon du camp adverse. Les deux Pokémons sont alors éliminés.");
    }


    //Methodes redefinies
    @Override
    public void utiliser(Terrain terrain, Joueur allie, Joueur adversaire, Pokemon pokemon, int intPokemon) {
        List<Pokemon> pokeAttaquer = new ArrayList<>();
        pokeAttaquer.addAll(terrain.getPokemonsJoueur(adversaire));
        System.out.println("Choisissez un pokemon à attaquer");
        Affichage.selectionPokemon(pokeAttaquer);
        Affichage.affichePokemon(terrain.getPokemonsJoueur(adversaire));
        int pokemonAttaque = allie.selection(pokeAttaquer);

        //Défausser les 2 pokemons avec retirerPokemon()
        terrain.retirerPokemon(allie, intPokemon);
        terrain.retirerPokemon(adversaire, pokemonAttaque);
    }

    @Override
    public void annulerPouvoir(Terrain terrain, Joueur allie, Joueur adversaire, Pokemon pokemon) {
        Jeu.getPokemonAvecPouvoir().remove(pokemon);
    }
}
