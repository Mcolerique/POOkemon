package pokemons.pouvoirs;

import affichage.Affichage;
import jeu.Jeu;
import joueurs.Joueur;
import joueurs.Terrain;
import pokemons.Pokemon;

import java.util.ArrayList;

public class Resistance extends Pouvoir{
    public Resistance(){
        super("Resistance");
    }

    @Override
    public void utiliser(Terrain terrain, Joueur allie, Joueur adversaire, Pokemon pokemon, int intPokemon) {
        ArrayList<Pokemon> pokeADefendre = new ArrayList<>();
        pokeADefendre.addAll(terrain.getPokemonsJoueur(allie));
        System.out.print("choisissez un pokemon a défendre");
        Affichage.selectionPokemon(pokeADefendre);
        Affichage.affichePokemon(terrain.getPokemonsJoueur(allie));
        int pokemonAmeliorer = allie.selection(pokeADefendre);
        terrain.getPokemonsJoueur(allie).get(pokemonAmeliorer).modifDefense(10);
        Terrain.getPouvoirsUtilises().put(this,terrain.getPokemonsJoueur(allie).get(pokemonAmeliorer ));
        this.m_utilise = true;
    }

    @Override
    public void annulerPouvoir(Terrain terrain, Joueur allie, Joueur adversaire, Pokemon pokemon) {
        Terrain.getPouvoirsUtilises().get(this).modifDefense(-10);
        Jeu.getPokemonAvecPouvoir().remove(pokemon);
        Terrain.getPouvoirsUtilises().remove(this);
    }
}
