package joueurs;

import pokemon.Pokemon;

import java.util.ArrayList;
import java.util.List;

public class Defausse {
    private List<Pokemon> m_defausse;

    public Defausse() {
        m_defausse = new ArrayList<>();
    }

    public void defausser(Pokemon pokemon) {
        m_defausse.add(pokemon);
    }

    // Méthode supplémentaire pour récupérer tous les Pokémon dans la défausse
    public List<Pokemon> getDefausse() {
        return m_defausse;
    }
}
