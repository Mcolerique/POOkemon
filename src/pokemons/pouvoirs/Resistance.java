package pokemons.pouvoirs;

import affichage.Affichage;
import affichage.Musique;
import jeu.Jeu;
import joueurs.Joueur;
import joueurs.Terrain;
import pokemons.Pokemon;

import java.util.ArrayList;


/**
 * La classe Resistance représente un pouvoir spécial d'un Pokémon.
 */
public class Resistance extends Pouvoir{

    Musique m_bruitage = new Musique("musiques/bruitage/resistance.wav");


    //CONSTRUCTEUR

    /**
     * Constructeur de la classe Resistance, qui initialise le pouvoir avec un nom et une description.
     */
    public Resistance(){
        super("Resist","Résistance, à utilisation unique : le Pokémon choisit un Pokémon de son camp (éventuellement lui-même). Jusqu'à la fin de la partie ou à la mort du Pokémon choisi, à chaque attaque reçue celui-ci subit subit 10 dégâts de moins.");
    }




    //METHODES REDEFINIES

    /**
     * Utilise le pouvoir Resistance.
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
        ArrayList<Pokemon> pokeADefendre = new ArrayList<>();
        pokeADefendre.addAll(terrain.getPokemonsJoueur(allie));
        try
        {
            System.out.print("choisissez un pokemon a défendre : "+Affichage.selectionPokemon(pokeADefendre));
            int pokemonAmeliorer = allie.selection(pokeADefendre.size());
            terrain.getPokemonsJoueur(allie).get(pokemonAmeliorer).modifDefense(10);
            Terrain.getPouvoirsUtilises().put(this,terrain.getPokemonsJoueur(allie).get(pokemonAmeliorer ));
            this.m_utilise = true;
        }
        catch (IndexOutOfBoundsException e){
        }
    }



    /**
     * Utilise le pouvoir Resistance dans un test.
     *
     * @param terrain Le terrain de jeu.
     * @param allie Le joueur allié utilisant le pouvoir.
     * @param adversaire Le joueur adverse.
     * @param pokemon Le Pokémon utilisant le pouvoir.
     * @param intPokemon L'indice du Pokémon utilisant le pouvoir.
     * @param choixPokemon L'indice du Pokémon à défendre.
     */
    @Override
    public void utilisertest(Terrain terrain, Joueur allie, Joueur adversaire, Pokemon pokemon, int intPokemon, int choixPokemon) {
        terrain.getPokemonsJoueur(allie).get(choixPokemon).modifDefense(10);
        Terrain.getPouvoirsUtilises().put(this,terrain.getPokemonsJoueur(allie).get(choixPokemon ));
        this.m_utilise = true;
        System.out.println("Le joueur a utilisé le pouvoir Resistance");
        System.out.println(m_utilise);
    }



    /**
     * Annule l'effet du pouvoir Resistance.
     *
     * @param terrain Le terrain de jeu.
     * @param allie Le joueur allié utilisant le pouvoir.
     * @param adversaire Le joueur adverse.
     * @param pokemon Le Pokémon utilisant le pouvoir.
     */
    @Override
    public void annulerPouvoir(Terrain terrain, Joueur allie, Joueur adversaire, Pokemon pokemon) {
        Terrain.getPouvoirsUtilises().get(this).modifDefense(-10);
        Jeu.getPokemonAvecPouvoir().remove(pokemon);
        Terrain.getPouvoirsUtilises().remove(this);
    }
}