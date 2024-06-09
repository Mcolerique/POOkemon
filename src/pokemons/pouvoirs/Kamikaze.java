package pokemons.pouvoirs;

import affichage.Affichage;
import affichage.Musique;
import jeu.Jeu;
import joueurs.Joueur;
import joueurs.Terrain;
import pokemons.Pokemon;

import java.util.ArrayList;
import java.util.List;

/**
 * La classe Kamikaze représente un pouvoir spécial d'un Pokémon.
 */
public class Kamikaze extends Pouvoir{

    Musique m_bruitage = new Musique("musiques/bruitage/kamikaze.wav");


    //CONSTRUCTEUR

    /**
     * Constructeur de la classe Kamikaze, qui initialise le pouvoir avec un nom et une description.
     */
    public Kamikaze() {
        super("Kamikaze","Kamikaze, à utilisation unique : le Pokémon choisit un Pokémon du camp adverse. Les deux Pokémons sont alors éliminés.");
    }




    //METHODES REDEFINIES

    /**
     * Utilise le pouvoir Kamikaze.
     *
     * @param terrain Le terrain de jeu.
     * @param allie Le joueur allié utilisant le pouvoir.
     * @param adversaire Le joueur adverse.
     * @param pokemon Le Pokémon utilisant le pouvoir.
     * @param intPokemon L'indice du Pokémon utilisant le pouvoir.
     */
    @Override
    public void utiliser(Terrain terrain, Joueur allie, Joueur adversaire, Pokemon pokemon, int intPokemon) {
        try {
            m_bruitage.play();
            List<Pokemon> pokeAttaquer = new ArrayList<>();
            pokeAttaquer.addAll(terrain.getPokemonsJoueur(adversaire));
            System.out.println("Choisissez un pokemon à attaquer"+Affichage.selectionPokemon(pokeAttaquer));
            Affichage.affichePokemon(terrain.getPokemonsJoueur(adversaire));
            int pokemonAttaque = allie.selection(pokeAttaquer.size());

            //Défausser les 2 pokemons avec retirerPokemon()
            terrain.retirerPokemon(allie, intPokemon);
            terrain.retirerPokemon(adversaire, pokemonAttaque);
        }
        catch (IndexOutOfBoundsException e){

        }
    }



    /**
     * Utilise le pouvoir Kamikaze dans un test.
     *
     * @param terrain Le terrain de jeu.
     * @param allie Le joueur allié utilisant le pouvoir.
     * @param adversaire Le joueur adverse.
     * @param pokemon Le Pokémon utilisant le pouvoir.
     * @param intPokemon L'indice du Pokémon utilisant le pouvoir.
     * @param choix L'indice du Pokémon adverse à attaquer.
     */
    @Override
    public void utilisertest(Terrain terrain, Joueur allie, Joueur adversaire, Pokemon pokemon, int intPokemon, int choix) {
        terrain.retirerPokemon(allie, intPokemon);
        terrain.retirerPokemon(adversaire, choix);
    }




    /**
     * Annule l'effet du pouvoir Kamikaze.
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