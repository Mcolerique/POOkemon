public class Joueur {
    private String m_nom;
    private Pioche m_pioche;
    private Main m_main;

    public Joueur(String nom, Pioche pioche) {
        m_nom = nom;
        m_pioche = pioche;
        m_main = new Main();
    }

    public void jouer(Joueur adversaire, Terrain terrain) {
        piocher();
        placerPokemons(terrain);
        attaquer(adversaire.getMain().getPokemon(), terrain);
    }

    private void piocher() {
        m_pioche.piocherMain(m_main);
    }

    private void placerPokemons(Terrain terrain) {
        terrain.placerPokemons(this, m_main.getListePokemon());
    }

    private void attaquer(Pokemon adversaire, Terrain terrain) {
        // Implement the attack logic here
        Pokemon attaquant = m_main.getPokemon();
        if (attaquant != null && adversaire != null) {
            attaquant.attaquer(adversaire);
        } else {
            System.out.println("Le joueur " + m_nom + " n'a pas de Pokemon pour attaquer.");
        }
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
}
