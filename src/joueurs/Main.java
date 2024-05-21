package joueurs;

import pokemon.Pokemon;

import java.util.ArrayList;
import java.util.List;

public class Main {
    private final List<Pokemon> m_listePokemon;

    public Main() {
        this.m_listePokemon = new ArrayList<>();
    }

    public void ajouterPokemon(Pokemon pokemon) {
        this.m_listePokemon.add(pokemon);
    }

    public void retirerPokemon(Pokemon pokemon) {
        this.m_listePokemon.remove(pokemon);
    }

    public List<Pokemon> getListePokemon() {
        return this.m_listePokemon;
    }

    public Pokemon getPokemon() {
        if (!this.m_listePokemon.isEmpty()) {
            return this.m_listePokemon.getFirst();
        } else {
            return null; // or throw an exception
        }
    }

    public int getNbPokemon() {
        return this.m_listePokemon.size();
    }
}
