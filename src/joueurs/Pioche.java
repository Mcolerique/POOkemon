package joueurs;

import jeu.Jeu;
import pokemons.GenerateurPokemon;
import pokemons.Pokemon;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Pioche {

    //Attributs
    private final List<Pokemon> m_pioche;


    //Constructeur
    public Pioche(int taillePioche) {
        this.m_pioche = new ArrayList<>();
        for (int i = 0; i < taillePioche; i++) {
            this.m_pioche.add(GenerateurPokemon.creePokemon());
        }
        Collections.shuffle(m_pioche);
    }


    //Methodes
    public void piocherMain(Main main) {
        //Si la pioche n'est pas vide
        if (!this.m_pioche.isEmpty()) {
            //Ajout du premier pokemon de la pioche dans la main
            main.ajouterPokemon(this.m_pioche.getFirst());
            //Retrait du premier pokemon de la pioche
            this.m_pioche.removeFirst();
        }
    }


    //Getters
    public List<Pokemon> getPioche(){
        return this.m_pioche;
    }
}
