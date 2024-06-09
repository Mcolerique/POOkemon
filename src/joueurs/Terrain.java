package joueurs;

import pokemons.Pokemon;
import pokemons.pouvoirs.Pouvoir;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

/**
 * La classe Terrain représente le terrain de jeu où les Pokémons des joueurs sont placés.
 */
public class Terrain {

    //ATTRIBUTS
    private final List<Pokemon> m_pokemonsJoueur1;
    private final List<Pokemon> m_pokemonsJoueur2;
    private static Hashtable<Pouvoir,Pokemon> m_pouvoirsUtilises;



    //CONSTRUCTEUR

    /**
     * Constructeur de la classe Terrain, qui initialise les listes de Pokémons pour les deux joueurs et la table des pouvoirs utilisés.
     */
    public Terrain() {
        this.m_pokemonsJoueur1 = new ArrayList<>();
        this.m_pokemonsJoueur2 = new ArrayList<>();
        this.m_pouvoirsUtilises = new Hashtable<>();
    }



    //METHODES

    /**
     * Place un Pokémon sur le terrain pour un joueur donné.
     *
     * @param joueur Le joueur qui place le Pokémon.
     * @param pokemon Le Pokémon à placer.
     */
    public void placerPokemons(Joueur joueur, Pokemon pokemon) {
        if (joueur.getNom().equals("Joueur 1")) {
            this.m_pokemonsJoueur1.add(pokemon);
        } else {
            this.m_pokemonsJoueur2.add(pokemon);
        }
    }


    /**
     * Retire un Pokémon du terrain pour un joueur donné.
     *
     * @param joueur Le joueur qui retire le Pokémon.
     * @param pokemon L'indice du Pokémon à retirer.
     */
    public void retirerPokemon(Joueur joueur, int pokemon){
        if (joueur.getNom().equals("Joueur 1"))
        {
            this.m_pokemonsJoueur1.remove(pokemon);
        }
        else{
            this.m_pokemonsJoueur2.remove(pokemon);
        }
    }


    /**
     * Vérifie si le terrain est vide pour un joueur donné.
     *
     * @param joueur Le joueur pour lequel vérifier le terrain.
     * @return true si le terrain est vide pour le joueur, sinon false.
     */
    public boolean estVide(Joueur joueur){
        if (joueur.getNom().equals("Joueur 1"))
        {
            return this.m_pokemonsJoueur1.isEmpty();
        }
        else{
            return this.m_pokemonsJoueur2.isEmpty();
        }
    }



    //GETTERS

    /**
     * Retourne le Pokémon spécifié pour un joueur donné.
     *
     * @param j Le joueur dont on veut obtenir le Pokémon.
     * @param pokemon L'indice du Pokémon à obtenir.
     * @return Le Pokémon spécifié pour le joueur donné.
     */
    public Pokemon getPokemon(Joueur j, int pokemon) {
        if (j.getNom().equals("Joueur 1")) {
            return m_pokemonsJoueur1.get(pokemon);
        } else {
            return m_pokemonsJoueur2.get(pokemon);
        }
    }


    /**
     * Retourne le nombre de Pokémons sur le terrain pour un joueur donné.
     *
     * @param mJ1 Le joueur dont on veut connaître le nombre de Pokémons sur le terrain.
     * @return Le nombre de Pokémons sur le terrain pour le joueur donné.
     */
    public int getNbPokemonsJoueur(Joueur mJ1) {
        if (mJ1.getNom().equals("Joueur 1")) {
            return  this.m_pokemonsJoueur1.size();
        } else {
            return  this.m_pokemonsJoueur2.size();
        }
    }


    /**
     * Retourne la liste des Pokémons sur le terrain pour un joueur donné.
     *
     * @param joueur Le joueur dont on veut obtenir la liste des Pokémons sur le terrain.
     * @return La liste des Pokémons sur le terrain pour le joueur donné.
     */
    public List<Pokemon> getPokemonsJoueur(Joueur joueur) {
        if (joueur.getNom().equals("Joueur 1"))
        {
            return this.m_pokemonsJoueur1;
        }
        else{
            return this.m_pokemonsJoueur2;
        }
    }


    /**
     * Retourne la table des pouvoirs utilisés.
     *
     * @return La table des pouvoirs utilisés.
     */
    public static Hashtable<Pouvoir, Pokemon> getPouvoirsUtilises() {
        return m_pouvoirsUtilises;
    }
}