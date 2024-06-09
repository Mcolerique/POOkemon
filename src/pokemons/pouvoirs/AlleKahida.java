package pokemons.pouvoirs;

import affichage.*;
import jeu.Jeu;
import joueurs.Joueur;
import joueurs.Terrain;
import pokemons.Pokemon;

import java.util.ArrayList;
import java.util.List;

/**
 * La classe AlleKahida représente un pouvoir spécial d'un Pokémon.
 */
public class AlleKahida extends Pouvoir {

    Musique m_bruitage = new Musique("musiques/bruitage/alleKahida.wav");

    //CONSTRUCTEUR

    /**
     * Constructeur de la classe AlleKahida, qui initialise le pouvoir avec un nom et une description.
     */
    public AlleKahida() {
        super("AllKahida","AlleKahida, utilisable à chaque tour : Permet de kamikaze un pokemon allié ou soit même");
    }




    //MeETHODES REDEFINIES

    /**
     * Utilise le pouvoir AlleKahida.
     *
     * @param terrain Le terrain de jeu.
     * @param allie Le joueur allié utilisant le pouvoir.
     * @param adversaire Le joueur adverse.
     * @param pokemon Le Pokémon utilisant le pouvoir.
     * @param intPokemon L'indice du Pokémon utilisant le pouvoir.
     * @see Terrain#retirerPokemon(Joueur, int)
     */
    @Override
    public void utiliser(Terrain terrain, Joueur allie, Joueur adversaire, Pokemon pokemon, int intPokemon) {
        try {
            m_bruitage.play();
            List<Pokemon> pokeAttaquant = new ArrayList<>();
            pokeAttaquant.addAll(terrain.getPokemonsJoueur(allie));
            System.out.println("Choisissez un pokemon à sacrifier"+Affichage.selectionPokemon(pokeAttaquant));
            Affichage.affichePokemon(terrain.getPokemonsJoueur(adversaire));
            int pokemonAttaquant = allie.selection(pokeAttaquant.size());

            List<Pokemon> pokeAttaquer = new ArrayList<>();
            pokeAttaquer.addAll(terrain.getPokemonsJoueur(adversaire));
            System.out.println("Choisissez un pokemon à attaquer"+Affichage.selectionPokemon(pokeAttaquer));
            Affichage.affichePokemon(terrain.getPokemonsJoueur(adversaire));
            int pokemonAttaque = allie.selection(pokeAttaquer.size());

            //Défausser les 2 pokemons avec retirerPokemon()
            terrain.retirerPokemon(allie, pokemonAttaquant);
            terrain.retirerPokemon(adversaire, pokemonAttaque);
        }
        catch (IndexOutOfBoundsException e){

        }
    }



    /**
     * Utilise le pouvoir AlleKahida dans un test.
     *
     * @param terrain Le terrain de jeu.
     * @param allie Le joueur allié utilisant le pouvoir.
     * @param adversaire Le joueur adverse.
     * @param pokemon Le Pokémon utilisant le pouvoir.
     * @param intPokemon L'indice du Pokémon utilisant le pouvoir.
     * @param choix L'indice du Pokémon à sacrifier.
     * @see Terrain#retirerPokemon(Joueur, int)
     */
    @Override
    public void utilisertest(Terrain terrain, Joueur allie, Joueur adversaire, Pokemon pokemon, int intPokemon, int choix) {
        try {
            //Défausser les 2 pokemons avec retirerPokemon()
            terrain.retirerPokemon(allie, choix);
            terrain.retirerPokemon(adversaire, choix);
        }
        catch (IndexOutOfBoundsException e){

        }
    }




    /**
     * Annule l'effet du pouvoir AlleKahida.
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