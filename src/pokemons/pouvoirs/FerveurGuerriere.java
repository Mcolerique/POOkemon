package pokemons.pouvoirs;

import affichage.Affichage;
import jeu.Jeu;
import joueurs.Joueur;
import joueurs.Terrain;
import pokemons.Pokemon;

import java.util.ArrayList;

public class FerveurGuerriere extends Pouvoir {

    //Constructeur
    public FerveurGuerriere(){
        super("Ferveur guerrière");
    }


    //Methodes redefinies
    @Override
    public void utiliser(Terrain terrain, Joueur allie, Joueur adversaire, Pokemon pokemon, int intPokemon) {
        ArrayList<Pokemon> pokeAAmeliorer = new ArrayList<>();
        pokeAAmeliorer.addAll(terrain.getPokemonsJoueur(allie));
        Affichage.afficher("choisissez un pokemon a ameliorer");
        Affichage.selectionPokemon(pokeAAmeliorer);
        Affichage.affichePokemon(terrain.getPokemonsJoueur(allie));
        int pokemonAmeliorer = allie.selection(pokeAAmeliorer);
        terrain.getPokemonsJoueur(allie).get(pokemonAmeliorer ).modifAttaque(10);
        Terrain.getPouvoirsUtilises().put(this,terrain.getPokemonsJoueur(allie).get(pokemonAmeliorer ));
        this.m_utilise = true;
    }

    @Override
    public void annulerPouvoir(Terrain terrain, Joueur allie, Joueur adversaire, Pokemon pokemon) {
        Terrain.getPouvoirsUtilises().get(this).modifAttaque(-10);
        Jeu.getPokemonAvecPouvoir().remove(pokemon);
        Terrain.getPouvoirsUtilises().remove(this);
    }
}
