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
    public void jouer(Joueur j1, Joueur j2) {
        //Boucle de jeu
        if (!partieTerminee()) {
            System.out.println(this.m_tour.getNbTourString()+" tour :");
            //joueurs.Joueur 1
            System.out.println("Tour de " + j1.getNom() + " :\n");
            //Piocher
            j1.piocherPokemon();

            //Tant qu'il n'y a pas 3 pokemons du joueur sur le terrain
            while (this.m_terrain.getNbPokemonsJoueur(j1) < j1.getTailleTerrain() && j1.getMain().getNbPokemon() > 0){
                j1.placerPokemon(this.m_terrain);
            }
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

    public void initialisationJeu(){
        Scanner scan = new Scanner(System.in);
        Affichage.affichage("Nouvelle partie ?(o/n)");
        char reponse = scan.next().charAt(0);
        if (reponse == 'o'){
            Affichage.accueil();
            //Initialisation des joueurs
            initialiserJoueur();

            // remplissage des mains des joueurs
            m_j1.piocherPokemon();
            m_j2.piocherPokemon();

            //Initlalisaiton de la partie, chaque joueur pose 3 pokemons sur le terrain
            System.out.println(this.m_tour.getNbTourString()+" tour :");
            System.out.println("Tour de " + this.m_j1.getNom());
            this.m_j1.placerPokemon(this.m_terrain);

            System.out.println("Tour de " + this.m_j2.getNom());
            this.m_j2.placerPokemon(this.m_terrain);

            //Attaque j1
            System.out.println((this.m_j1.getNom()+" attaque :"));
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
        Random random = new Random();
        int randomInt = random.nextInt(2);
        if(randomInt == 0){
            System.out.println("Vous êtes le joueur 1");
            this.m_j1 = new Humain("Joueur 1", 20);
            this.m_j2 = new Ordinateur("Joueur 2", 21);
        }
        else {
            System.out.println("Vous êtes le joueur 2");
            this.m_j1 = new Ordinateur("Joueur 1", 20);
            this.m_j2 = new Humain("Joueur 2", 21);
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