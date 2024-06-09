package pokemons.pouvoirs;

import affichage.Musique;
import jeu.Jeu;
import joueurs.Joueur;
import joueurs.Terrain;
import pokemons.Pokemon;

public class ExtensionTerritoire extends Pouvoir {

    Musique m_bruitage = new Musique("musiques/bruitage/extension_territoire.wav");

    //Constructeur
    public ExtensionTerritoire() {
        super("Ext terr","Extension du territoire, à utilisation unique :  le terrain du joueur gagne un quatrième emplacement sur lequel il peut placer immédiatement un Pokémon de sa main. Lorsque le Pokémon qui a utilisé ce pouvoir meurt, son emplacement est perdu et le terrain possède de nouveau trois emplacements.");
    }


    //Methodes redefinies
    @Override
    public void utiliser(Terrain terrain, Joueur allie, Joueur adversaire, Pokemon pokemon,int intPokemon){
        m_bruitage.play();
        allie.modifPlaceTerrain(1);
        allie.placerPokemon(terrain);
        this.m_utilise=true;
    }
    @Override
    public void utilisertest(Terrain terrain, Joueur allie, Joueur adversaire, Pokemon pokemon,int intPokemon, int choix){
        allie.modifPlaceTerrain(1);
        allie.placerPokemon(terrain);
        this.m_utilise=true;
        System.out.println("Le joueur a utilisé le pouvoir ExtensionTerritoire");
        System.out.println(m_utilise);
    }


    @Override
    public void annulerPouvoir(Terrain terrain, Joueur allie, Joueur adversaire, Pokemon pokemon){
        allie.modifPlaceTerrain(-1);
        Jeu.getPokemonAvecPouvoir().remove(pokemon);
    }
}
