package joueurs;

import pokemons.GenerateurPokemon;
import pokemons.Pokemon;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * La classe Pioche représente la pioche de cartes Pokémon d'un joueur.
 */
public class Pioche {

    //ATTRIBUTS
    private final List<Pokemon> m_pioche;



    //CONSTRUCTEUR

    /**
     * Constructeur de la classe Pioche, qui initialise une nouvelle pioche.
     *
     * @param taillePioche Le nombre de cartes Pokémon dans la pioche.
     */
    public Pioche(int taillePioche) {
        this.m_pioche = new ArrayList<>();
        for (int i = 0; i < taillePioche; i++) {
            this.m_pioche.add(GenerateurPokemon.creePokemon());
        }
        Collections.shuffle(m_pioche);
    }



    //METHODES

    /**
     * Pioche une carte Pokémon et l'ajoute à la main du joueur.
     *
     * @param main La main du joueur à laquelle ajouter la carte piochée.
     */
    public void piocherMain(Main main) {
        if (!this.m_pioche.isEmpty()) {
            main.ajouterPokemon(this.m_pioche.getFirst());
            this.m_pioche.removeFirst();
        }
    }



    //GETTERS

    /**
     * Retourne la liste des cartes Pokémon dans la pioche.
     *
     * @return La liste des cartes Pokémon dans la pioche.
     */
    public List<Pokemon> getPioche(){
        return this.m_pioche;
    }
}