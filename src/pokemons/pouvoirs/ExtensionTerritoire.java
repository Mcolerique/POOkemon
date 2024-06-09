package pokemons.pouvoirs;

import affichage.Musique;
import jeu.Jeu;
import joueurs.Joueur;
import joueurs.Terrain;
import pokemons.Pokemon;

/**
 * La classe ExtensionTerritoire représente un pouvoir spécial d'un Pokémon.
 */
public class ExtensionTerritoire extends Pouvoir {

    Musique m_bruitage = new Musique("musiques/bruitage/extension_territoire.wav");


    //CONSTRUCTEUR

    /**
     * Constructeur de la classe ExtensionTerritoire, qui initialise le pouvoir avec un nom et une description.
     */
    public ExtensionTerritoire() {
        super("Ext terr","Extension du territoire, à utilisation unique :  le terrain du joueur gagne un quatrième emplacement sur lequel il peut placer immédiatement un Pokémon de sa main. Lorsque le Pokémon qui a utilisé ce pouvoir meurt, son emplacement est perdu et le terrain possède de nouveau trois emplacements.");
    }




    //METHODES REDEFINIES

    /**
     * Utilise le pouvoir ExtensionTerritoire.
     *
     * @param terrain Le terrain de jeu.
     * @param allie Le joueur allié utilisant le pouvoir.
     * @param adversaire Le joueur adverse.
     * @param pokemon Le Pokémon utilisant le pouvoir.
     * @param intPokemon L'indice du Pokémon utilisant le pouvoir.
     */
    @Override
    public void utiliser(Terrain terrain, Joueur allie, Joueur adversaire, Pokemon pokemon,int intPokemon){
        m_bruitage.play();
        allie.modifPlaceTerrain(1);
        allie.placerPokemon(terrain);
        this.m_utilise=true;
    }



    /**
     * Utilise le pouvoir ExtensionTerritoire dans un test.
     *
     * @param terrain Le terrain de jeu.
     * @param allie Le joueur allié utilisant le pouvoir.
     * @param adversaire Le joueur adverse.
     * @param pokemon Le Pokémon utilisant le pouvoir.
     * @param intPokemon L'indice du Pokémon utilisant le pouvoir.
     * @param choix L'indice de la sélection (non utilisé dans cette méthode).
     */
    @Override
    public void utilisertest(Terrain terrain, Joueur allie, Joueur adversaire, Pokemon pokemon,int intPokemon, int choix){
        allie.modifPlaceTerrain(1);
        allie.placerPokemon(terrain);
        this.m_utilise=true;
        System.out.println("Le joueur a utilisé le pouvoir ExtensionTerritoire");
        System.out.println(m_utilise);
    }



    /**
     * Annule l'effet du pouvoir ExtensionTerritoire.
     *
     * @param terrain Le terrain de jeu.
     * @param allie Le joueur allié utilisant le pouvoir.
     * @param adversaire Le joueur adverse.
     * @param pokemon Le Pokémon utilisant le pouvoir.
     */
    @Override
    public void annulerPouvoir(Terrain terrain, Joueur allie, Joueur adversaire, Pokemon pokemon){
        allie.modifPlaceTerrain(-1);
        Jeu.getPokemonAvecPouvoir().remove(pokemon);
    }
}