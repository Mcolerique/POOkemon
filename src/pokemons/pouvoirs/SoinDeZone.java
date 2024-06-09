package pokemons.pouvoirs;

import affichage.Musique;
import jeu.Jeu;
import joueurs.Joueur;
import joueurs.Terrain;
import pokemons.Pokemon;

import java.lang.management.MemoryUsage;


/**
 * La classe SoinDeZone représente un pouvoir de soin de zone pour les Pokémons d'un joueur.
 */
public class SoinDeZone extends Pouvoir {

    Musique m_bruitage = new Musique("musiques/bruitage/soin.wav");



    //CONSTRUCTEUR

    /**
     * Constructeur de la classe SoinDeZone, qui initialise le pouvoir avec un nom et une description.
     */
    public SoinDeZone(){
        super("Soin zone","Soin de zone, utilisable à chaque tour : chaque Pokémon de son camp regagne 10 points de vie.");
    }




    //METHODES REDEFINIES

    /**
     * Utilise le pouvoir SoinDeZone.
     *
     * @param terrain Le terrain de jeu.
     * @param allie Le joueur allié utilisant le pouvoir.
     * @param adversaire Le joueur adverse.
     * @param pokemon Le Pokémon utilisant le pouvoir.
     * @param intPokemon L'indice du Pokémon utilisant le pouvoir.
     */
    @Override
    public void utiliser(Terrain terrain, Joueur allie, Joueur adversaire, Pokemon pokemon, int intPokemon) {
        m_bruitage.play();
        for (int i = 0; i<terrain.getPokemonsJoueur(allie).size(); i++){
            terrain.getPokemon(allie,i).soigner(10);
        }
    }



    /**
     * Utilise le pouvoir SoinDeZone dans un test.
     *
     * @param terrain Le terrain de jeu.
     * @param allie Le joueur allié utilisant le pouvoir.
     * @param adversaire Le joueur adverse.
     * @param pokemon Le Pokémon utilisant le pouvoir.
     * @param intPokemon L'indice du Pokémon utilisant le pouvoir.
     * @param choix L'indice du choix effectué.
     */
    @Override
    public void utilisertest(Terrain terrain, Joueur allie, Joueur adversaire, Pokemon pokemon, int intPokemon, int choix) {
        for (int i = 0; i<terrain.getPokemonsJoueur(allie).size(); i++){
            terrain.getPokemon(allie,i).soigner(10);
        }
    }



    /**
     * Annule l'effet du pouvoir SoinDeZone.
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
