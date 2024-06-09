package pokemons.pouvoirs;

import affichage.Musique;
import jeu.Jeu;
import joueurs.Joueur;
import joueurs.Terrain;
import pokemons.Pokemon;

/**
 * La classe Confusion représente un pouvoir spécial d'un Pokémon.
 */
public class Confusion extends Pouvoir {

    Musique m_bruitage = new Musique("musiques/bruitage/confusion.wav");


    //CONSTRUCTEUR

    /**
     * Constructeur de la classe Confusion, qui initialise le pouvoir avec un nom et une description.
     */
    public Confusion() {
        super("Confusion","Confusion, utilisable à chaque tour : le joueur adverse doit défausser toutes les cartes de sa main dans sa pioche, mélanger sa pioche et piocher 5 Pokémons.");
    }




    //METHODES REDEFINIES

    /**
     * Utilise le pouvoir Confusion.
     *
     * @param terrain Le terrain de jeu.
     * @param allie Le joueur allié utilisant le pouvoir.
     * @param adversaire Le joueur adverse.
     * @param pokemon Le Pokémon utilisant le pouvoir.
     * @param intPokemon L'indice du Pokémon utilisant le pouvoir.
     */
    @Override
    public void utiliser(Terrain terrain, Joueur allie, Joueur adversaire, Pokemon pokemon,int intPokemon)
    {
        m_bruitage.play();
        System.out.println("Confusion !");
        // Vide la main de l'adversaire et la met dans la pioche de l'adversaire
        adversaire.viderMain();
        // Mélange de la pioche de l'adversaire
        adversaire.melangerPioche();
        // Remplit la main de l'adversaire
        adversaire.remplirMain();
    }




    /**
     * Utilise le pouvoir Confusion dans un test.
     *
     * @param terrain Le terrain de jeu.
     * @param allie Le joueur allié utilisant le pouvoir.
     * @param adversaire Le joueur adverse.
     * @param pokemon Le Pokémon utilisant le pouvoir.
     * @param intPokemon L'indice du Pokémon utilisant le pouvoir.
     */
    @Override
    public void utilisertest(Terrain terrain, Joueur allie, Joueur adversaire, Pokemon pokemon, int intPokemon, int choix) {
        System.out.println("Confusion !");
        // Vide la main de l'adversaire et la met dans la pioche de l'adversaire
        adversaire.viderMain();
        // Remplit la main de l'adversaire
        adversaire.remplirMain();
    }




    /**
     * Annule l'effet du pouvoir Confusion.
     *
     * @param terrain Le terrain de jeu.
     * @param allie Le joueur allié utilisant le pouvoir.
     * @param adversaire Le joueur adverse.
     * @param pokemon Le Pokémon utilisant le pouvoir.
     */
    @Override
    public void annulerPouvoir(Terrain terrain, Joueur allie, Joueur adversaire, Pokemon pokemon) {
        Jeu.getPokemonAvecPouvoir().remove(pokemon);
    }
}