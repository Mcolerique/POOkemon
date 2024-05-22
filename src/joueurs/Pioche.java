package joueurs;

import pokemon.Pokemon;
import jeu.Jeu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Pioche {
    private final List<Pokemon> m_pioche;

    public Pioche(int taillePioche) {
        this.m_pioche = new ArrayList<>();
        for (int i = 0; i < taillePioche; i++) {
            this.m_pioche.add(new Pokemon());
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
