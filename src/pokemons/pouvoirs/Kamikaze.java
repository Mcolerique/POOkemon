package pokemons.pouvoirs;

import affichage.Affichage;
import jeu.Jeu;
import joueurs.Joueur;
import joueurs.Terrain;
import pokemons.Pokemon;
import joueurs.Terrain;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Kamikaze extends Pouvoir{
    public Kamikaze() {
        super("Kamikaze");
    }

    @Override
    public void utiliser(Terrain terrain, Joueur allie, Joueur adversaire, Pokemon pokemon, int intPokemon) {
        List<Pokemon> pokeAttaquer = new ArrayList<>();
        pokeAttaquer.addAll(terrain.getM_pokemonsJoueur(adversaire));
        System.out.println("Choisissez un pokemon à attaquer");
        Affichage.selectionPokemon(pokeAttaquer);
        Affichage.affichePokemon(terrain.getM_pokemonsJoueur(adversaire));
        int pokemonAttaque = allie.selection(pokeAttaquer);

        // défausser les 2 pokemons avec retirerPokemon()
        terrain.retirerPokemon(allie, intPokemon);
        terrain.retirerPokemon(adversaire, pokemonAttaque);
    }

    @Override
    public void annulerPouvoir(Terrain terrain, Joueur allie, Joueur adversaire, Pokemon pokemon) {
        Jeu.getM_pokemonAvecPouvoir().remove(pokemon);
    }
}
