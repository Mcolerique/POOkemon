import java.util.List;

public class Terrain {
    private List<Pokemon> m_pokemonsJoueur1;
    private List<Pokemon> m_pokemonsJoueur2;

    public Terrain() {
        // Initialisation des listes de Pokemons pour les deux joueurs
    }

    public void placerPokemons(Joueur joueur, int pokemon) {
        if (joueur.getNom().equals("Joueur 1")) {
            m_pokemonsJoueur1.add(joueur.getPokemon(pokemon));
        } else {
            m_pokemonsJoueur2.add(joueur.getPokemon(pokemon));
        }
    }
    public Pokemon getPokemon(Joueur adversaire) {
        if (adversaire.getNom().equals("Joueur 1")) {
            return m_pokemonsJoueur1.get(0);
        } else {
            return m_pokemonsJoueur2.get(0);
        }
    }
    public void printPokemon(Joueur joueur) {
        if (joueur.getNom().equals("Joueur 1")) {
            System.out.println("Pokémon du Joueur 1 : " + m_pokemonsJoueur1.get(0).getNom());
        } else {
            System.out.println("Pokémon du Joueur 2 : " + m_pokemonsJoueur2.get(0).getNom());
        }

    }

    public int getNbPokemonsJoueur(Joueur mJ1) {
        if (mJ1.getNom().equals("Joueur 1")) {
            return m_pokemonsJoueur1.size();
        } else {
            return m_pokemonsJoueur2.size();
        }
    }
}
