package pokemons.pouvoirs;

import affichage.Affichage;
import jeu.Jeu;
import joueurs.Joueur;
import joueurs.Terrain;
import pokemons.Pokemon;

import java.util.ArrayList;

public class FerveurGuerriere extends Pouvoir{
    public FerveurGuerriere(){
        super("Ferveur guerrière");
    }

    @Override
    public void utiliser(Terrain terrain, Joueur allie, Joueur adversaire, Pokemon pokemon, int intPokemon) {
        ArrayList<Pokemon> pokeAAmeliorer = new ArrayList<>();
        pokeAAmeliorer.addAll(terrain.getM_pokemonsJoueur(allie));
        System.out.print("choisissez un pokemon a ameliorer");
        Affichage.selectionPokemon(pokeAAmeliorer);
        Affichage.affichePokemon(terrain.getM_pokemonsJoueur(allie));
        int pokemonSoigne = allie.selection(pokeAAmeliorer);
        terrain.getM_pokemonsJoueur(allie).get(pokemonSoigne).boostAttaque(10);
        this.m_utilise = true;
    }

    @Override
    public void annulerPouvoir(Terrain terrain, Joueur allie, Joueur adversaire, Pokemon pokemon) {
        pokemon.boostAttaque(-10);
        Jeu.getM_pokemonAvecPouvoir().remove(pokemon);
    }
}
