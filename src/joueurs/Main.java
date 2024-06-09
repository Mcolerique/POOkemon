package joueurs;

import pokemons.Pokemon;
import java.util.ArrayList;
import java.util.List;

/**
 * La classe Main représente la main d'un joueur dans le jeu Pokémon.
 */
public class Main {

    //ATTRIBUTS
    private final List<Pokemon> m_listePokemon;



    //CONSTRUCTEUR

    /**
     * Constructeur de la classe Main, qui initialise une nouvelle main vide pour le joueur.
     */
    public Main() {
        this.m_listePokemon = new ArrayList<>();
    }



    //METHODES

    /**
     * Ajoute un Pokémon à la main du joueur.
     *
     * @param pokemon Le Pokémon à ajouter.
     */
    public void ajouterPokemon(Pokemon pokemon) {
        this.m_listePokemon.add(pokemon);
    }


    /**
     * Retire un Pokémon de la main du joueur.
     *
     * @param pokemon Le Pokémon à retirer.
     */
    public void retirerPokemon(Pokemon pokemon) {
        this.m_listePokemon.remove(pokemon);
    }



    //GETTERS

    /**
     * Retourne la liste des Pokémons dans la main du joueur.
     *
     * @return La liste des Pokémons en main.
     */
    public List<Pokemon> getListePokemon() {
        return this.m_listePokemon;
    }


    /**
     * Retourne le premier Pokémon dans la main du joueur.
     *
     * @return Le premier Pokémon en main, ou null si la main est vide.
     */
    public Pokemon getPokemon() {
        if (!this.m_listePokemon.isEmpty()) {
            return this.m_listePokemon.getFirst();
        } else {
            return null;
        }
    }


    /**
     * Retourne le nombre de Pokémons dans la main du joueur.
     *
     * @return Le nombre de Pokémons en main.
     */
    public int getNbPokemon() {
        return this.m_listePokemon.size();
    }
}
