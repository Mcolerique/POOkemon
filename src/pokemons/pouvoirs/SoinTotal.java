package pokemons.pouvoirs;

import affichage.Affichage;
import affichage.Musique;
import jeu.Jeu;
import joueurs.Joueur;
import joueurs.Terrain;
import pokemons.Pokemon;

import java.util.ArrayList;


/**
 * La classe SoinTotal représente un pouvoir de soin complet pour les Pokémons d'un joueur.
 */
public class SoinTotal extends Pouvoir {

    Musique m_bruitage = new Musique("musiques/bruitage/soin.wav");



    //CONSTRUCTEUR

    /**
     * Constructeur de la classe SoinTotal, qui initialise le pouvoir avec un nom et une description.
     */
    public SoinTotal(){
        super("Soin tot","Soin total, à utilisation unique : le Pokémon choisit un Pokémon de son camp (éventuellement lui-même). Celui-ci regagne toute sa vie.");
    }




    //METHODES REDEFINIES

    /**
     * Utilise le pouvoir SoinTotal.
     *
     * @param terrain Le terrain de jeu.
     * @param allie Le joueur allié utilisant le pouvoir.
     * @param adversaire Le joueur adverse.
     * @param pokemon Le Pokémon utilisant le pouvoir.
     * @param intPokemon L'indice du Pokémon utilisant le pouvoir.
     */
    @Override
    public void utiliser(Terrain terrain, Joueur allie, Joueur adversaire, Pokemon pokemon,int intPokemon) {
        m_bruitage.play();
        ArrayList<Pokemon> pokeSoigner = new ArrayList<>();
        pokeSoigner.addAll(terrain.getPokemonsJoueur(allie));
        try{
            System.out.print("choisissez un pokemon a soigner : "+Affichage.selectionPokemon(pokeSoigner));
            int pokemonSoigne = allie.selection(pokeSoigner.size());
            pokeSoigner.get(pokemonSoigne).soigner(pokeSoigner.get(pokemonSoigne).getPvMax());
            this.m_utilise = true;
        }
        catch (IndexOutOfBoundsException e){

        }
    }



    /**
     * Utilise le pouvoir SoinTotal dans un test.
     *
     * @param terrain Le terrain de jeu.
     * @param allie Le joueur allié utilisant le pouvoir.
     * @param adversaire Le joueur adverse.
     * @param pokemon Le Pokémon utilisant le pouvoir.
     * @param intPokemon L'indice du Pokémon utilisant le pouvoir.
     * @param choix L'indice du Pokémon à soigner.
     */
    @Override
    public void utilisertest(Terrain terrain, Joueur allie, Joueur adversaire, Pokemon pokemon, int intPokemon, int choix) {
        ArrayList<Pokemon> pokeSoigner = new ArrayList<>();
        pokeSoigner.addAll(terrain.getPokemonsJoueur(allie));
        try{
            pokeSoigner.get(choix).soigner(pokeSoigner.get(choix).getPvMax());
            this.m_utilise = true;
        }
        catch (IndexOutOfBoundsException e){

        }
    }



    /**
     * Annule l'effet du pouvoir SoinTotal.
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
