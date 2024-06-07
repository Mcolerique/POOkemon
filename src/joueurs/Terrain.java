package joueurs;

import pokemons.Pokemon;
import pokemons.pouvoirs.Pouvoir;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class Terrain {

    //Attributs
    private final List<Pokemon> m_pokemonsJoueur1;
    private final List<Pokemon> m_pokemonsJoueur2;
    private static Hashtable<Pouvoir,Pokemon> m_pouvoirsUtilises;


    //Constructeur
    public Terrain() {
        //Initialisation des listes de Pokemons pour les deux joueurs
        this.m_pokemonsJoueur1 = new ArrayList<>();
        this.m_pokemonsJoueur2 = new ArrayList<>();
        this.m_pouvoirsUtilises = new Hashtable<>();
    }


    //Methodes
    public void placerPokemons(Joueur joueur, Pokemon pokemon) {
        if (joueur.getNom().equals("Joueur 1")) {
            this.m_pokemonsJoueur1.add(pokemon);
        } else {
            this.m_pokemonsJoueur2.add(pokemon);
        }
    }

    public void retirerPokemon(Joueur joueur, int pokemon){
        if (joueur.getNom().equals("Joueur 1"))
        {
            this.m_pokemonsJoueur1.remove(pokemon);
        }
        else{
            this.m_pokemonsJoueur2.remove(pokemon);
        }
    }

    public boolean estVide(Joueur joueur){
        if (joueur.getNom().equals("Joueur 1"))
        {
            return this.m_pokemonsJoueur1.isEmpty();
        }
        else{
            return this.m_pokemonsJoueur2.isEmpty();
        }
    }


    //Getters
    public Pokemon getPokemon(Joueur j, int pokemon) {
        if (j.getNom().equals("Joueur 1")) {
            return m_pokemonsJoueur1.get(pokemon);
        } else {
            return m_pokemonsJoueur2.get(pokemon);
        }
    }

    public int getNbPokemonsJoueur(Joueur mJ1) {
        if (mJ1.getNom().equals("Joueur 1")) {
            return  this.m_pokemonsJoueur1.size();
        } else {
            return  this.m_pokemonsJoueur2.size();
        }
    }

    public List<Pokemon> getPokemonsJoueur(Joueur joueur) {
        if (joueur.getNom().equals("Joueur 1"))
        {
            return this.m_pokemonsJoueur1;
        }
        else{
            return this.m_pokemonsJoueur2;
        }
    }

    public static Hashtable<Pouvoir, Pokemon> getPouvoirsUtilises() {
        return m_pouvoirsUtilises;
    }
}
