import java.util.List;

public class Terrain {
    private List<Pokemon> m_pokemonsJoueur1;
    private List<Pokemon> m_pokemonsJoueur2;

    public Terrain() {
        // Initialisation des listes de Pokemons pour les deux joueurs
    }

    public void placerPokemons(Joueur joueur, List<Pokemon> pokemons) {
        if (joueur.getNom().equals("Joueur 1")) {
            m_pokemonsJoueur1 = pokemons;
        } else {
            m_pokemonsJoueur2 = pokemons;
        }
    }


}
