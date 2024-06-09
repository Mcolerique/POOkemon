package jeu;

import affichage.Affichage;
import affichage.Musique;
import joueurs.Humain;
import joueurs.Joueur;
import joueurs.Ordinateur;
import joueurs.Terrain;
import pokemons.Pokemon;
import pokemons.pouvoirs.Pouvoir;
import java.util.Hashtable;
import java.util.Random;
import java.util.Scanner;

/**
 * La classe Jeu gère le déroulement d'une partie de Pokémon, y compris l'initialisation, le tour par tour, la gestion des joueurs et du terrain.
 */
public class Jeu {

    //ATTRIBUTS
    private Joueur m_j1;
    private Joueur m_j2;
    private final Terrain m_terrain;
    private final Tour m_tour;
    private Musique m_musiqueDeFond;
    private Musique m_bruitage;
    private static Hashtable<Pokemon, Pouvoir> m_pokemonAvecPouvoir = new Hashtable<>();


    // CONSTRUCTEUR

    /**
     * Constructeur de la classe Jeu, qui initialise le terrain, le tour et la musique de fond.
     */
    public Jeu() {
        this.m_terrain = new Terrain();
        this.m_tour = new Tour(this);
        this.m_musiqueDeFond = new Musique("musiques/jeu.wav");
    }


    //METHODES

    /**
     * Initialise une nouvelle partie de jeu Pokémon.
     *
     * @see Affichage#accueil()
     * @see Affichage#terrain(Terrain, Joueur, Joueur)
     * @see Affichage#afficheNbTour(String)
     * @see Joueur#piocherPokemon()
     * @see Joueur#placerPokemon(Terrain)
     * @see Tour#nouveauTour()
     */
    public void initialisationJeu(){
        Scanner scan = new Scanner(System.in);
        Affichage.afficher("Nouvelle partie ?(o/n)");
        char reponse = scan.next().charAt(0);

        if (reponse == 'o'){
            Affichage.accueil();
            this.m_musiqueDeFond.loop();

            initialiserJoueur();

            m_bruitage = new Musique("musiques/bruitage/pioche.wav");
            m_bruitage.play();
            this.m_j1.piocherPokemon();
            this.m_j2.piocherPokemon();
            try {
                Thread.sleep(2000);
                //Chaque joueur pose 3 pokemons sur le terrain
                Affichage.terrain(this.m_terrain,this.m_j1,this.m_j2);
                Thread.sleep(2000);
                Affichage.afficheNbTour(this.m_tour.getNbTourString() + " tour :");
                Affichage.afficher("C'est au tour du joueur n°1 !");
                Thread.sleep(1000);
                this.m_j1.placerPokemon(this.m_terrain);
                Thread.sleep(1000);
                Affichage.terrain(this.m_terrain,this.m_j1,this.m_j2);
                Thread.sleep(2000);
                Affichage.afficher("C'est au tour du joueur n°2 !");
                Thread.sleep(2000);
                this.m_j2.placerPokemon(this.m_terrain);
                Thread.sleep(2000);
                Affichage.terrain(this.m_terrain,this.m_j1,this.m_j2);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.m_tour.nouveauTour();
        }
        else {
            System.out.println("Merci d'être venu sur notre jeu Pokemon");
            System.exit(0);
        }
    }



    /**
     * Initialise les joueurs pour une nouvelle partie de jeu Pokémon.
     *
     * @see Affichage#afficher(String)
     * @see Affichage#mettreEnGras(String)
     * @see Affichage#mettreEnCouleur(String, String)
     */
    public void initialiserJoueur() {
        try {
            Affichage.afficher("Tirage au sort des joueurs...");
            Thread.sleep(2000);
            Random random = new Random();
            int randomInt = random.nextInt(2);
            if (randomInt == 0) {
                Affichage.afficher(Affichage.mettreEnGras(Affichage.mettreEnCouleur("Vous êtes le joueur 1 !", "\u001B[38;5;13m")));
                this.m_j1 = new Humain("Joueur 1", 20);
                this.m_j2 = new Ordinateur("Joueur 2", 21);
                Thread.sleep(1000);
            } else {
                Affichage.afficher(Affichage.mettreEnGras(Affichage.mettreEnCouleur("Vous êtes le joueur 2 !", "\u001B[38;5;13m")));
                this.m_j1 = new Ordinateur("Joueur 1", 20);
                this.m_j2 = new Humain("Joueur 2", 21);
                Thread.sleep(1000);
            }
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



    /**
     * Gère le déroulement d'un tour de jeu pour les deux joueurs.
     *
     * @param j1 Le premier joueur.
     * @param j2 Le deuxième joueur.
     * @see Joueur#piocherPokemon()
     * @see Joueur#placerPokemon(Terrain)
     * @see Joueur#utiliserPouvoir(Terrain, Joueur)
     * @see Affichage#terrain(Terrain, Joueur, Joueur)
     * @see Affichage#finDePartie(Joueur)
     * @see Tour#attaquer(Joueur, Joueur)
     */
    public void jouer(Joueur j1, Joueur j2) {
        if (!partieTerminee()) {
            try {
                Thread.sleep(2000);
                System.out.println("\nC'est au tour de " + j1.getNom() + " !\n");
                Thread.sleep(2000);
                j1.piocherPokemon();
                m_bruitage = new Musique("musiques/bruitage/pioche.wav");
                m_bruitage.play();
                System.out.println("...la main s'est remplie\n");
                Thread.sleep(2000);
                Affichage.terrain(this.m_terrain,this.m_j1,this.m_j2);

                Thread.sleep(2000);
                j1.placerPokemon(this.m_terrain);
                Thread.sleep(2000);
                System.out.println("Terrain rempli : \n");
                Thread.sleep(1000);
                Affichage.terrain(this.m_terrain,this.m_j1,this.m_j2);

                Thread.sleep(2000);
                if (j1.utiliserPouvoir(this.m_terrain, j2)) {
                    partieTerminee();
                }
                Thread.sleep(2000);
                if (!partieTerminee()) {
                    m_tour.attaquer(j1, j2);
                    m_bruitage = new Musique("musiques/bruitage/attaque.wav");
                    m_bruitage.play();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        else if (partieTerminee()){
            Affichage.finDePartie(this.getVainqueur());
        }
    }



    /**
     * Vérifie si la partie est terminée.
     *
     * @return true si la partie est terminée (si l'un des joueurs a perdu), sinon false.
     * @see Joueur#aPerdu()
     */
    public boolean partieTerminee() {
        return this.m_j1.aPerdu(this.m_terrain) || this.m_j2.aPerdu(this.m_terrain);
    }



    /**
     * Associe un pouvoir à un Pokémon dans la table de hachage.
     *
     * @param pokemon Le Pokémon à qui ajouter un pouvoir.
     * @param pouvoir Le pouvoir à associer au Pokémon.
     */
    public static void ajouterPokeAPouvoir(Pokemon pokemon, Pouvoir pouvoir){
        m_pokemonAvecPouvoir.put(pokemon,pouvoir);
    }



    //GETTERS

    /**
     * Retourne le premier joueur.
     *
     * @return Le premier joueur.
     */
    public Joueur getJoueur1() {
        return this.m_j1;
    }


    /**
     * Retourne le deuxième joueur.
     *
     * @return Le deuxième joueur.
     */
    public Joueur getJoueur2() {
        return this.m_j2;
    }


    /**
     * Retourne le terrain de jeu.
     *
     * @return Le terrain de jeu.
     */
    public Terrain getTerrain() {
        return m_terrain;
    }


    /**
     * Retourne la table de hachage des Pokémons et leurs pouvoirs.
     *
     * @return La table de hachage des Pokémons et leurs pouvoirs.
     */
    public static Hashtable<Pokemon, Pouvoir> getPokemonAvecPouvoir() {
        return m_pokemonAvecPouvoir;
    }



    /**
     * Retourne le joueur gagnant.
     *
     * @return Le joueur gagnant.
     */
    public Joueur getVainqueur() {
        if (this.m_j1.aPerdu(this.m_terrain)) {
            return this.m_j2;
        } else {
            return this.m_j1;
        }
    }
}