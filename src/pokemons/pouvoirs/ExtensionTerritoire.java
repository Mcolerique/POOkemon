package pokemons.pouvoirs;

import jeu.Jeu;
import joueurs.Joueur;
import joueurs.Terrain;
import pokemons.Pokemon;

public class ExtensionTerritoire extends Pouvoir {

    //Constructeur
    public ExtensionTerritoire() {
        super("Extension du territoire");
    }


    //Methodes redefinies
    @Override
    public void utiliser(Terrain terrain, Joueur allie, Joueur adversaire, Pokemon pokemon,int intPokemon){
        allie.ajouterPlaceTerrain();
        allie.placerPokemon(terrain);
        this.m_utilise=true;
    }

    @Override
    public void annulerPouvoir(Terrain terrain, Joueur allie, Joueur adversaire, Pokemon pokemon){
        allie.enleverPlaceTerrain();
        Jeu.getPokemonAvecPouvoir().remove(pokemon);
    }
}
