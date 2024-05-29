package jeu;

import affichage.Affichage;
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
    private static Hashtable<Pokemon, Pouvoir> m_pokemonAvecPouvoir;
    private Joueur m_j1;
    private Joueur m_j2;
    private final Terrain m_terrain;
    private final Tour m_tour;


    //Constructeur
    public Jeu() {
        this.m_terrain = new Terrain();
        this.m_tour = new Tour(this);
    }


    //Methodes
    public void initialisationJeu(){
        Scanner scan = new Scanner(System.in);
        Affichage.afficher("Nouvelle partie ?(o/n)");
        char reponse = scan.next().charAt(0);

        if (reponse == 'o'){
            Affichage.accueil();

            //Initialisation des joueurs
            initialiserJoueur();

            // remplissage des mains des joueurs
            m_j1.piocherPokemon();
            m_j2.piocherPokemon();
            try {
                Thread.sleep(2000);
                //Chaque joueur pose 3 pokemons sur le terrain
                Affichage.afficheNbTour(this.m_tour.getNbTourString() + " tour :");
                Affichage.afficher("C'est au tour du joueur n°1 !");
                this.m_j1.placerPokemon(this.m_terrain);

                Affichage.afficher("C'est au tour du joueur n°2 !");
                this.m_j2.placerPokemon(this.m_terrain);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }

            //Attaque j1
            Affichage.afficher(this.m_j1.getNom()+ " attaque : ");
            if(this.m_j1.attaquer(this.m_terrain, this.m_j2)){
                partieTerminee();
            }
            //Attaque j2
            System.out.println((this.m_j2.getNom()+" attaque :"));
            if(this.m_j2.attaquer(this.m_terrain, this.m_j1)){
                partieTerminee();
            }
            Affichage.terrain(this.m_terrain,this.m_j1,this.m_j2);
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
                Affichage.afficher("Vous êtes le joueur 1");
                this.m_j1 = new Humain("Joueur 1", 20);
                this.m_j2 = new Ordinateur("Joueur 2", 21);
            } else {
                Affichage.afficher("Vous êtes le joueur 2");
                this.m_j1 = new Ordinateur("Joueur 1", 20);
                this.m_j2 = new Humain("Joueur 2", 21);
            }
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



    public void jouer(Joueur j1, Joueur j2) {
        //Boucle de jeu
        if (!partieTerminee()) {
            System.out.println(this.m_tour.getNbTourString()+" tour :");
            //joueurs.Joueur 1
            System.out.println("Tour de " + j1.getNom() + " :\n");
            //Piocher
            j1.piocherPokemon();

            //Tant qu'il n'y a pas 3 pokemons du joueur sur le terrain
                j1.placerPokemon(this.m_terrain);
            //Utilisation des pouvoir
            if(j1.utiliserPouvoir(this.m_terrain,j2)){
                partieTerminee();
            }
            //Attaquer
            if (!partieTerminee()) {
                m_tour.attaquer(j1,j2);
            }
        }
        else if (partieTerminee()){
            Affichage.finDePartie(this.getVainqueur());
        }
    }





    public boolean partieTerminee() {
        return this.m_j1.aPerdu() || this.m_j2.aPerdu();
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
    public static void reinitialiserNom() {
        Pokemon.getM_nomsDisponibles().addAll(Pokemon.getM_nomsUtilises());
        Pokemon.getM_nomsUtilises().clear();
    }
    public static void reinitialiserPouvoir() {
        Pokemon.getM_nomsPouvoirs().addAll(Pokemon.getM_pouvoirsUtilises());
        Pokemon.getM_pouvoirsUtilises().clear();
    }

    public Joueur getVainqueur() {
        if (this.m_j1.aPerdu()) {
            return this.m_j2;
        } else {
            return this.m_j1;
        }
    }
}