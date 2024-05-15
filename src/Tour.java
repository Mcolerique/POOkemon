import java.util.Scanner;

public class Tour {
    private final Jeu jeu;
    private Joueur joueur;

    public Tour(Jeu jeu, Joueur joueur) {
        this.jeu = jeu;
        this.joueur = joueur;
    }

    public void changerJoueur() {
        joueur = joueur == jeu.getJoueur1() ? jeu.getJoueur2() : jeu.getJoueur1();
    }

    public void piocher() {
        joueur.piocherPokemon();
    }

    public void defausser(Pokemon pokemon) {
        joueur.defausser(pokemon);
    }

}
