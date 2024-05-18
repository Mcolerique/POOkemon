import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Pioche {
    private final List<Pokemon> m_pioche;

    public Pioche(Jeu jeu, int taillePioche) {
        this.m_pioche = new ArrayList<>();
        for (int i = 0; i < taillePioche; i++) {
            // prendre un pokemon aléatoire dans la liste des noms de pokemons
            String nomPokemon = jeu.getListNomPokemon().get((int) (Math.random() * jeu.getListNomPokemon().size()));
            this.m_pioche.add(new Pokemon(nomPokemon));
            // supprimer le pokemon de la liste des noms de pokemons
            jeu.getListNomPokemon().remove(nomPokemon);
        }
        Collections.shuffle(m_pioche);
    }

    public void piocherMain(Main Main) {
        // si la pioche n'est pas vide
        if (!this.m_pioche.isEmpty()) {
            // ajouter le premier pokemon de la pioche à la main
            Main.ajouterPokemon(this.m_pioche.getFirst());
            // retirer le premier pokemon de la pioche
            this.m_pioche.removeFirst();
        }
    }
    public List<Pokemon> getM_pioche(){
        return this.m_pioche;
    }

}
