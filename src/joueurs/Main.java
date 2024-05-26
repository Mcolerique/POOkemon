package joueurs;

import pokemons.Pokemon;
import java.util.ArrayList;
import java.util.List;

public class Main {

    //Attributs
    private final List<Pokemon> m_listePokemon;


    //Constructeur
    public Main() {
        this.m_listePokemon = new ArrayList<>();
    }


    //Methodes
    public void ajouterPokemon(Pokemon pokemon) {
        this.m_listePokemon.add(pokemon);
    }

    public void retirerPokemon(Pokemon pokemon) {
        this.m_listePokemon.remove(pokemon);
    }


    //Getters
    public List<Pokemon> getListePokemon() {
        return this.m_listePokemon;
    }

    public Pokemon getPokemon() {
        if (!this.m_listePokemon.isEmpty()) {
            return this.m_listePokemon.getFirst();
        } else {
            return null;
        }
    }

    public int getNbPokemon() {
        return this.m_listePokemon.size();
    }
}
