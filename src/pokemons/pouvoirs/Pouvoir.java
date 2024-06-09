package pokemons.pouvoirs;

import joueurs.Joueur;
import joueurs.Terrain;
import pokemons.Pokemon;

/**
 * La classe abstraite Pouvoir représente un pouvoir spécial qu'un Pokémon peut utiliser.
 */
public abstract class Pouvoir {

    //ATTRIBUTS
    private String m_nom;
    protected boolean m_utilise;
    private String m_desc;



    //CONSTRUCTEUR

    /**
     * Constructeur de la classe Pouvoir, qui initialise le pouvoir.
     *
     * @param nom Le nom du pouvoir.
     * @param desc La description du pouvoir.
     */
    public Pouvoir(String nom, String desc) {
        this.m_nom = nom;
        this.m_desc = desc;
        this.m_utilise = false;
    }




    //METHODES ABSTRAITES

    /**
     * Utilise le pouvoir.
     *
     * @param terrain Le terrain de jeu.
     * @param allie Le joueur allié utilisant le pouvoir.
     * @param adversaire Le joueur adverse.
     * @param pokemon Le Pokémon utilisant le pouvoir.
     * @param intPokemon L'indice du Pokémon utilisant le pouvoir.
     */
    public abstract void utiliser(Terrain terrain, Joueur allie, Joueur adversaire, Pokemon pokemon, int intPokemon);



    /**
     * Utilise le pouvoir dans un test.
     *
     * @param terrain Le terrain de jeu.
     * @param allie Le joueur allié utilisant le pouvoir.
     * @param adversaire Le joueur adverse.
     * @param pokemon Le Pokémon utilisant le pouvoir.
     * @param intPokemon L'indice du Pokémon utilisant le pouvoir.
     * @param choix L'indice du choix effectué.
     */
    public abstract void utilisertest(Terrain terrain, Joueur allie, Joueur adversaire, Pokemon pokemon, int intPokemon, int choix);



    /**
     * Annule l'effet du pouvoir.
     *
     * @param terrain Le terrain de jeu.
     * @param allie Le joueur allié utilisant le pouvoir.
     * @param adversaire Le joueur adverse.
     * @param pokemon Le Pokémon utilisant le pouvoir.
     */
    public abstract void annulerPouvoir(Terrain terrain, Joueur allie, Joueur adversaire, Pokemon pokemon);




    //GETTERS

    /**
     * Obtient le nom du pouvoir.
     *
     * @return Le nom du pouvoir.
     */
    public String getNom(){
        return this.m_nom;
    }



    /**
     * Vérifie si le pouvoir a été utilisé.
     *
     * @return true si le pouvoir a été utilisé, sinon false.
     */
    public boolean getUtilise(){
        return this.m_utilise;
    }


    /**
     * Obtient la description du pouvoir.
     *
     * @return La description du pouvoir.
     */
    public String getDesc(){return this.m_desc;}
}
