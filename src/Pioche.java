import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Pioche {
    private List<Pokemon> m_pioche;

    public Pioche(Jeu jeu, int taillPioche) {
        m_pioche = new ArrayList<>();
        for (int i = 0; i < taillPioche; i++) {
            // prendre un pokemon aléatoire dans la liste des noms de pokemons
            String nomPokemon = jeu.getListNomPokemon().get((int) (Math.random() * jeu.getListNomPokemon().size()));
            m_pioche.add(new Pokemon(nomPokemon));
            // supprimer le pokemon de la liste des noms de pokemons
            jeu.getListNomPokemon().remove(nomPokemon);
        }
        Collections.shuffle(m_pioche);
    }

    public void piocherMain(Main mMain) {
        // si la pioche n'est pas vide
        if (!m_pioche.isEmpty()) {
            // ajouter le premier pokemon de la pioche à la main
            mMain.ajouterPokemon(m_pioche.get(0));
            // retirer le premier pokemon de la pioche
            m_pioche.remove(0);
        }
    }
}
