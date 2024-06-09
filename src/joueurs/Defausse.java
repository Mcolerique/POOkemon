package joueurs;

import pokemons.Pokemon;
import java.util.ArrayList;
import java.util.List;

/**
 * La classe Defausse représente la défausse de Pokémons d'un joueur.
 */
public class Defausse {

    //ATTRIBUTS
    private List<Pokemon> m_defausse;


    //CONSTRUCTEUR

    /**
     * Constructeur de la classe Defausse, qui initialise une nouvelle liste vide pour stocker les Pokémons défaussés.
     */
    public Defausse() {
        m_defausse = new ArrayList<>();
    }


    //METHODES

    /**
     * Ajoute un Pokémon à la défausse.
     *
     * @param pokemon2 Le Pokémon à ajouter à la défausse.
     */
    public void ajouterPokemon(Pokemon pokemon2) {
        m_defausse.add(pokemon2);
    }



    /**
     * Retire un Pokémon de la défausse.
     *
     * @param choix L'indice du Pokémon à retirer de la défausse.
     */
    public void retirerPokemon(int choix) {
        if(choix < m_defausse.size())
            m_defausse.remove(choix);
        else
            System.out.println("Ce pokemon n'existe pas dans la defausse");
    }



    /**
     * Ajoute un Pokémon à la défausse.
     *
     * @param pokemon Le Pokémon à défausser.
     */
    public void defausser(Pokemon pokemon) {m_defausse.add(pokemon);}



    //GETTERS

    /**
     * Retourne la liste des Pokémons dans la défausse.
     *
     * @return La liste des Pokémons défaussés.
     */
    public List<Pokemon> getDefausse() {return m_defausse;}
}