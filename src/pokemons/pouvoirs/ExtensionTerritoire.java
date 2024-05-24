package pokemons.pouvoirs;

import joueurs.Joueur;
import joueurs.Terrain;
import pokemons.Pokemon;

public class ExtensionTerritoire extends Pouvoir{
    public ExtensionTerritoire() {
        super("Extension du territoire");
    }
    public void utiliser(Terrain terrain, Joueur allie, Joueur adversaire, Pokemon pokemon,int intPokemon){
        allie.ajouterPlaceTerrain();
        allie.placerPokemon(terrain);
        this.m_utilise=true;
    }
    public void annulerPouvoir(Terrain terrain, Joueur allie, Joueur adversaire, Pokemon pokemon){
        allie.enleverPlaceTerrain();
    }
}
