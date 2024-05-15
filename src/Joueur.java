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

    public void placerPokemons(Terrain terrain, int pokemon) {
        terrain.placerPokemons(this, pokemon);
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

    public Pokemon getPokemon(int pokemonAttaque) {
        return m_main.getListePokemon().get(pokemonAttaque);
    }
}
