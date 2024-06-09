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

public class Jeu {

    //Attributs
    private static Hashtable<Pokemon, Pouvoir> m_pokemonAvecPouvoir = new Hashtable<>();
    private Joueur m_j1;
    private Joueur m_j2;
    private final Terrain m_terrain;
    private final Tour m_tour;
    private Musique m_musiqueDeFond;
    private Musique m_bruitage;

    //Constructeur
    public Jeu() {
        this.m_terrain = new Terrain();
        this.m_tour = new Tour(this);
        this.m_musiqueDeFond = new Musique("musiques/jeu.wav");
    }

    //Methodes
    public void initialisationJeu(){
        Scanner scan = new Scanner(System.in);
        Affichage.afficher("Nouvelle partie ?(o/n)");
        char reponse = scan.next().charAt(0);

        if (reponse == 'o'){
            Affichage.accueil();
            this.m_musiqueDeFond.loop();

            //Initialisation des joueurs
            initialiserJoueur();

            // remplissage des mains des joueurs
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

    public void jouer(Joueur j1, Joueur j2) {
        //Boucle de jeu
        if (!partieTerminee()) {
            //joueurs.Joueur 1
            try {
                Thread.sleep(2000);
                System.out.println("\nC'est au tour de " + j1.getNom() + " !\n");
                //Piocher
                Thread.sleep(2000);
                j1.piocherPokemon();
                System.out.println("...la main s'est remplie\n");
                Thread.sleep(2000);
                Affichage.terrain(this.m_terrain,this.m_j1,this.m_j2);

                Thread.sleep(2000);
                //Tant qu'il n'y a pas 3 pokemons du joueur sur le terrain
                j1.placerPokemon(this.m_terrain);
                Thread.sleep(2000);
                System.out.println("Terrain rempli : \n");
                Thread.sleep(1000);
                Affichage.terrain(this.m_terrain,this.m_j1,this.m_j2);


                //Utilisation des pouvoir
                Thread.sleep(2000);
                if (j1.utiliserPouvoir(this.m_terrain, j2)) {
                    partieTerminee();
                }
                //Attaquer
                Thread.sleep(2000);
                if (!partieTerminee()) {
                    m_tour.attaquer(j1, j2);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        else if (partieTerminee()){
            Affichage.finDePartie(this.getVainqueur());
        }
    }


    public boolean partieTerminee() {
        return this.m_j1.aPerdu() || this.m_j2.aPerdu();
    }

    public static void ajouterPokeAPouvoir(Pokemon pokemon, Pouvoir pouvoir){
        m_pokemonAvecPouvoir.put(pokemon,pouvoir);
    }

    //Getters
    public Joueur getJoueur1() {
        return this.m_j1;
    }

    public Joueur getJoueur2() {
        return this.m_j2;
    }

    public Terrain getTerrain() {
        return m_terrain;
    }

    public static Hashtable<Pokemon, Pouvoir> getPokemonAvecPouvoir() {
        return m_pokemonAvecPouvoir;
    }

    public Joueur getVainqueur() {
        if (this.m_j1.aPerdu()) {
            return this.m_j2;
        } else {
            return this.m_j1;
        }
    }
}