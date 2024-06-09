package jeu;

/**
 * La classe Start contient la méthode main pour lancer le jeu Pokémon.
 */
public class Start {

    /**
     * La méthode main pour lancer le jeu.
     *
     * @param args Les arguments de la ligne de commande.
     */
    public static void main(String[] args) {
        Jeu jeu = new Jeu();
        jeu.initialisationJeu();
    }
}