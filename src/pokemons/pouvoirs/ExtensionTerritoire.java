package pokemons.pouvoirs;

import jeu.Jeu;
import joueurs.Joueur;
import joueurs.Terrain;
import pokemons.Pokemon;

public class ExtensionTerritoire extends Pouvoir {

    //Constructeur
    public ExtensionTerritoire() {
        super("Extension du territoire","Extension du territoire, à utilisation unique :  le terrain du joueur gagne un quatrième emplacement sur lequel il peut placer immédiatement un Pokémon de sa main. Lorsque le Pokémon qui a utilisé ce pouvoir meurt, son emplacement est perdu et le terrain possède de nouveau trois emplacements.");
    }


    //Methodes redefinies
    @Override
    public void utiliser(Terrain terrain, Joueur allie, Joueur adversaire, Pokemon pokemon,int intPokemon){
        allie.modifPlaceTerrain(1);
        allie.placerPokemon(terrain);
        this.m_utilise=true;
    }

    @Override
    public void annulerPouvoir(Terrain terrain, Joueur allie, Joueur adversaire, Pokemon pokemon){
        allie.modifPlaceTerrain(-1);
        Jeu.getPokemonAvecPouvoir().remove(pokemon);
    }
}
