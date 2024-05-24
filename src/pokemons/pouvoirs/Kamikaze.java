package pokemons.pouvoirs;

import affichage.Affichage;
import joueurs.Joueur;
import joueurs.Terrain;
import pokemons.Pokemon;
import joueurs.Terrain;

import java.util.Scanner;

public class Kamikaze extends Pouvoir{
    public Kamikaze() {
        super("Kamikaze");
    }

    @Override
    public void utiliser(Terrain terrain, Joueur allie, Joueur adversaire, Pokemon pokemon, int intPokemon) {

        System.out.println("Choisissez un pokemon à attaquer");
        Affichage.affichePokemon(terrain.getM_pokemonsJoueur(adversaire));
        int pokemonAttaque;
        Scanner scanner2 = new Scanner(System.in);
        pokemonAttaque = scanner2.nextInt() - 1;

        // défausser les 2 pokemons avec retirerPokemon()
        terrain.retirerPokemon(allie, intPokemon);
        terrain.retirerPokemon(adversaire, pokemonAttaque);
    }

    @Override
    public void annulerPouvoir(Terrain terrain, Joueur allie, Joueur adversaire, Pokemon pokemon) {

    }
}
