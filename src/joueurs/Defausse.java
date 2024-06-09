package joueurs;

import pokemons.Pokemon;
import java.util.ArrayList;
import java.util.List;

public class Defausse {

    //Attributs
    private List<Pokemon> m_defausse;

    //Constructeur
    public Defausse() {
        m_defausse = new ArrayList<>();
    }

    //Methodes
    public void defausser(Pokemon pokemon) {m_defausse.add(pokemon);}

    //Getters
    public List<Pokemon> getDefausse() {return m_defausse;}

    public void retirerPokemon(int choix) {
        if(choix < m_defausse.size())
            m_defausse.remove(choix);
        else
            System.out.println("Ce pokemon n'existe pas dans la defausse");
    }

    public void ajouterPokemon(Pokemon pokemon2) {
        m_defausse.add(pokemon2);
    }
}
