import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Pioche {
    private List<Pokemon> m_pioche;

    public Pioche(List<String> listeNomPokemon) {
        m_pioche = new ArrayList<>();
        // Génération des Pokémon dans la pioche en utilisant la liste de noms de Pokémon
        genererPokemon(listeNomPokemon);
        // Mélange aléatoire de la pioche
        melangerPioche();
    }

    private void genererPokemon(List<String> listeNomPokemon) {
        for (int i = 0; i < 20; i++) { // Générer 20 Pokémon dans la pioche
            String nomPokemon = listeNomPokemon.get(i % listeNomPokemon.size());
            Pokemon pokemon = new Pokemon(nomPokemon);
            m_pioche.add(pokemon);
        }
    }

    private void melangerPioche() {
        Collections.shuffle(m_pioche);
    }

    public void piocherMain(Main main) {
        if (!m_pioche.isEmpty()) {
            Pokemon pokemon = m_pioche.remove(0);
            main.ajouterPokemon(pokemon);
        } else {
            System.out.println("La pioche est vide !");
        }
    }
}
