import java.util.ArrayList;
import java.util.List;

public class Jeu {
    private Joueur m_j1;
    private Joueur m_j2;
    private Terrain m_terrain;
    private int m_nomMaxPokemon;
    private int[][] m_tabElt;
    private List<String> m_listNomPokemon;
    private Pioche m_pioche;

    public Jeu() {
        m_terrain = new Terrain();
        m_tabElt = new int[][]{{0, 1, 0, 1}, {0, 0, 1, 0}, {1, 0, 0, 1}, {0, 1, 0, 0}};
        m_listNomPokemon = new ArrayList<>();
        m_listNomPokemon.add("Pikachu");
        m_listNomPokemon.add("Bulbizarre");
        m_listNomPokemon.add("Salamèche");
        m_listNomPokemon.add("Carapuce");
        m_listNomPokemon.add("Rattata");
        m_listNomPokemon.add("Abo");
        m_listNomPokemon.add("Piafabec");
        m_listNomPokemon.add("Ptitard");
        m_listNomPokemon.add("Miaouss");
        m_listNomPokemon.add("Racaillou");

        // Création de la pioche avec la liste de noms de Pokémon
        m_pioche = new Pioche(m_listNomPokemon);
        m_j1 = new Joueur("Joueur 1", m_pioche);
        m_j2 = new Joueur("Joueur 2", m_pioche);
        Pokemon.initialiserTabElt(m_tabElt);
    }

    public void jouer() {
        System.out.println("Le jeu commence !");

        // Décider aléatoirement du premier joueur
        Joueur premierJoueur = Math.random() < 0.5 ? m_j1 : m_j2;
        Joueur secondJoueur = premierJoueur == m_j1 ? m_j2 : m_j1;

        System.out.println("Le premier joueur est : " + premierJoueur.getNom());

        // Placer les Pokémons sur le terrain pour chaque joueur
        premierJoueur.jouer(secondJoueur, m_terrain);
        secondJoueur.jouer(premierJoueur, m_terrain);

        // Début de la partie
        while (!partieTerminee()) {
            premierJoueur.jouer(secondJoueur, m_terrain);
            if (partieTerminee()) break;
            secondJoueur.jouer(premierJoueur, m_terrain);
        }

        // Déterminer le gagnant
        Joueur gagnant = premierJoueur.aPerdu() ? secondJoueur : premierJoueur;
        System.out.println("Le joueur " + gagnant.getNom() + " a gagné !");
    }

    private boolean partieTerminee() {
        return m_j1.aPerdu() || m_j2.aPerdu();
    }

    public int getM_nomMaxPokemon() {
        return m_nomMaxPokemon;
    }

    public void setM_nomMaxPokemon(int m_nomMaxPokemon) {
        this.m_nomMaxPokemon = m_nomMaxPokemon;
    }

    public int[][] getM_tabElt() {
        return m_tabElt;
    }

    public List<String> getM_listNomPokemon() {
        return m_listNomPokemon;
    }

    public static void main(String[] args) {
        Jeu jeu = new Jeu();
        jeu.jouer();
    }
}
