import java.util.List;

public abstract class Joueur {
    protected String m_nom;
    protected Pioche m_pioche;
    protected Main m_main;
    protected Defausse m_defausse;

    public Joueur(String nom, Jeu jeu, int taillePioche) {
        m_nom = nom;
        m_pioche = new Pioche(jeu, taillePioche);
        m_main = new Main();
        m_defausse = new Defausse();
    }

    private void piocher() {
        m_pioche.piocherMain(m_main);
    }

    public abstract Boolean attaquer(Terrain terrain, Joueur adversaire);

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
        while (this.m_main.getNbPokemon() < 5 && !this.m_pioche.getM_pioche().isEmpty()) {
            this.m_pioche.piocherMain(this.m_main);
        }
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
    public abstract void placerPokemon(Terrain terrain);

}
