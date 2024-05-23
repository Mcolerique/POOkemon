package pokemons.pouvoirs;

import joueurs.Joueur;
import joueurs.Terrain;
import pokemons.Pokemon;

public class ExtensionTerritoire extends Pouvoir{
    public ExtensionTerritoire() {
        super("Extension du territoire", false);
    }
    public void utiliser(Terrain terrain, Joueur allie, Joueur adversaire, Pokemon pokemon){
        allie.ajouterPlaceTerrain();
        allie.placerPokemon(terrain);
    }
    public void annulerPouvoir(Joueur joueur){
        joueur.enleverPlaceTerrain();
    }
}
