package joueurs;

import pokemons.Pokemon;
import pokemons.pouvoirs.Pouvoir;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class Terrain {
    private final List<Pokemon> m_pokemonsJoueur1;
    private final List<Pokemon> m_pokemonsJoueur2;
    private final Hashtable<Pouvoir,Pokemon> m_pouvoirUtiliser;

    public Terrain() {
        // Initialisation des listes de Pokemons pour les deux joueurs
        this.m_pokemonsJoueur1 = new ArrayList<>();
        this.m_pokemonsJoueur2 = new ArrayList<>();
        this.m_pouvoirUtiliser = new Hashtable<>();
    }

    public void placerPokemons(Joueur joueur, int pokemon) {
        if (joueur.getNom().equals("joueurs.Joueur 1")) {
            this.m_pokemonsJoueur1.add(joueur.getPokemon(pokemon));
        } else {
            this.m_pokemonsJoueur2.add(joueur.getPokemon(pokemon));
        }
    }
    public Pokemon getPokemon(Joueur j, int pokemon) {
        if (j.getNom().equals("joueurs.Joueur 1")) {
            return m_pokemonsJoueur1.get(pokemon);
        } else {
            return m_pokemonsJoueur2.get(pokemon);
        }
    }

    public int getNbPokemonsJoueur(Joueur mJ1) {
        if (mJ1.getNom().equals("joueurs.Joueur 1")) {
            return  this.m_pokemonsJoueur1.size();
        } else {
            return  this.m_pokemonsJoueur2.size();
        }
    }


    public List<Pokemon> getM_pokemonsJoueur(Joueur joueur) {
        if (joueur.getNom().equals("joueurs.Joueur 1"))
        {
            return this.m_pokemonsJoueur1;
        }
        else{
            return this.m_pokemonsJoueur2;
        }
    }
    public void retirerPokemon(Joueur joueur, int pokemon){
        if (joueur.getNom().equals("joueurs.Joueur 1"))
        {
            this.m_pokemonsJoueur1.remove(pokemon);
        }
        else{
            this.m_pokemonsJoueur2.remove(pokemon);
        }
    }
    public boolean estVide(Joueur joueur){
        if (joueur.getNom().equals("joueurs.Joueur 1"))
        {
            return this.m_pokemonsJoueur1.isEmpty();
        }
        else{
            return this.m_pokemonsJoueur2.isEmpty();
        }
    }
}
