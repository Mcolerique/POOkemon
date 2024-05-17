import java.util.List;

public class Joueur {
    private String m_nom;
    private Pioche m_pioche;
    private Main m_main;
    private Defausse m_defausse;

    public Joueur(String nom, Jeu jeu, int taillePioche) {
        m_nom = nom;
        m_pioche = new Pioche(jeu, taillePioche);
        m_main = new Main();
        m_defausse = new Defausse();
    }

    private void piocher() {
        m_pioche.piocherMain(m_main);
    }

    public void attaquer(Pokemon attaquant, Pokemon adversaire) {
        attaquant.attaquer(adversaire);
    }

    public boolean aPerdu() {
        return m_main.estVide();
    }

    public String getNom() {
        return m_nom;
    }

    public Main getMain() {
        return m_main;
    }

    public Pioche getPioche() {
        return m_pioche;
    }
    public Defausse getDefausse() {
        return m_defausse;
    }

    public void piocherPokemon() {
        m_pioche.piocherMain(m_main);
    }

    public void defausser(Pokemon pokemon) {
        m_defausse.defausser(pokemon);
    }

    public Pokemon getPokemon(int pokemonCible) {
        List<Pokemon> listePokemon = m_main.getListePokemon();
        if (pokemonCible >= 0 && pokemonCible < listePokemon.size()) {
            // Vérifiez que l'index est valide avant d'accéder à la liste
            Pokemon pokemon = listePokemon.get(pokemonCible);
            // Supprimez le pokemon de la main seulement s'il existe dans la liste
            m_main.retirerPokemon(pokemon);
            return pokemon;
        } else {
            // Gérez le cas où l'index est invalide
            System.out.println("L'index du Pokémon est invalide.");
            return null;
        }
    }


}
