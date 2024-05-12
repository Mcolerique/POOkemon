import java.util.ArrayList;
import java.util.List;

public class Main {
    private List<Pokemon> m_listePokemon;

    public Main() {
        m_listePokemon = new ArrayList<>();
    }

    public void ajouterPokemon(Pokemon pokemon) {
        m_listePokemon.add(pokemon);
    }

    public void retirerPokemon(Pokemon pokemon) {
        m_listePokemon.remove(pokemon);
    }

    public List<Pokemon> getListePokemon() {
        return m_listePokemon;
    }

    public boolean estVide() {
        return m_listePokemon.isEmpty();
    }
    public Pokemon getPokemon() {
        if (!m_listePokemon.isEmpty()) {
            return m_listePokemon.getFirst();
        } else {
            return null; // or throw an exception
        }
    }
}
