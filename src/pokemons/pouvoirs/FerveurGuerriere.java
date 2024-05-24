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
        int pokemonAmeliorer = allie.selection(pokeAAmeliorer);
        terrain.getM_pokemonsJoueur(allie).get(pokemonAmeliorer ).boostAttaque(10);
        Terrain.getM_pouvoirUtiliser().put(this,terrain.getM_pokemonsJoueur(allie).get(pokemonAmeliorer ));
        this.m_utilise = true;
    }

    @Override
    public void annulerPouvoir(Terrain terrain, Joueur allie, Joueur adversaire, Pokemon pokemon) {

        Terrain.getM_pouvoirUtiliser().get(this).boostAttaque(-10);
        Jeu.getM_pokemonAvecPouvoir().remove(pokemon);
        Terrain.getM_pouvoirUtiliser().remove(this);

    }
}
