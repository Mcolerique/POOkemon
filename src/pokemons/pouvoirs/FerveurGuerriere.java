package pokemons.pouvoirs;

import affichage.Affichage;
import affichage.Musique;
import jeu.Jeu;
import joueurs.Joueur;
import joueurs.Terrain;
import pokemons.Pokemon;

import java.util.ArrayList;


/**
 * La classe FerveurGuerriere représente un pouvoir spécial d'un Pokémon.
 */
public class FerveurGuerriere extends Pouvoir {

    Musique m_bruitage = new Musique("musiques/bruitage/ferveur_guerriere.wav");


    /**
     * Constructeur de la classe FerveurGuerriere, qui initialise le pouvoir avec un nom et une description.
     */
    //CONSTRUCTEUR
    public FerveurGuerriere(){
        super("Ferv guer","Ferveur guerrière, à utilisation unique : le Pokémon choisit un Pokémon de son camp (éventuellement lui-même). Jusqu'à la fin de la partie ou à la mort du Pokémon choisi, les attaques de celui-ci infligent 10 dégâts de plus.");
    }




    //METHODES REDEFINIES

    /**
     * Utilise le pouvoir FerveurGuerriere.
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
        ArrayList<Pokemon> pokeAAmeliorer = new ArrayList<>();
        pokeAAmeliorer.addAll(terrain.getPokemonsJoueur(allie));
        Affichage.afficher("choisissez un pokemon a ameliorer : " + Affichage.selectionPokemon(pokeAAmeliorer));
        int pokemonAmeliorer = allie.selection(pokeAAmeliorer.size());
        terrain.getPokemonsJoueur(allie).get(pokemonAmeliorer ).modifAttaque(10);
        Terrain.getPouvoirsUtilises().put(this,terrain.getPokemonsJoueur(allie).get(pokemonAmeliorer ));
        this.m_utilise = true;
    }




    /**
     * Utilise le pouvoir FerveurGuerriere dans un test.
     *
     * @param terrain Le terrain de jeu.
     * @param allie Le joueur allié utilisant le pouvoir.
     * @param adversaire Le joueur adverse.
     * @param pokemon Le Pokémon utilisant le pouvoir.
     * @param intPokemon L'indice du Pokémon utilisant le pouvoir.
     * @param choix L'indice du Pokémon à améliorer.
     */
    @Override
    public void utilisertest(Terrain terrain, Joueur allie, Joueur adversaire, Pokemon pokemon, int intPokemon, int choix) {
        terrain.getPokemonsJoueur(allie).get(choix).modifAttaque(10);
        Terrain.getPouvoirsUtilises().put(this,terrain.getPokemonsJoueur(allie).get(choix));
        this.m_utilise = true;
        System.out.println("Le joueur a utilisé le pouvoir FerveurGuerriere");
        System.out.println(m_utilise);
    }




    /**
     * Annule l'effet du pouvoir FerveurGuerriere.
     *
     * @param terrain Le terrain de jeu.
     * @param allie Le joueur allié utilisant le pouvoir.
     * @param adversaire Le joueur adverse.
     * @param pokemon Le Pokémon utilisant le pouvoir.
     */
    @Override
    public void annulerPouvoir(Terrain terrain, Joueur allie, Joueur adversaire, Pokemon pokemon) {
        Terrain.getPouvoirsUtilises().get(this).modifAttaque(-10);
        Jeu.getPokemonAvecPouvoir().remove(pokemon);
        Terrain.getPouvoirsUtilises().remove(this);
    }
}