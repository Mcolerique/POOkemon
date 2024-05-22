package jeu;

import affichage.Affichage;
import joueurs.Humain;
import joueurs.Joueur;
import joueurs.Ordinateur;
import joueurs.Terrain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Jeu {
    private Joueur m_j1;
    private Joueur m_j2;
    private final Terrain m_terrain;
    private final Tour m_tour;


    public Jeu() {
        m_terrain = new Terrain();
        m_tour = new Tour(this);
    }

    public void jouer(Joueur j1, Joueur j2) {
        // Fonctionnement d'un tour :
        // 1. le joueur place jusqu'à 3 pokemons sur le terrain, il faut qu'il y en ait 3
        // 2. le joueur choisit un pokemon avec lequel attaquer et 1 pokemon à attaquer
        // 3. le pokemon attaque l'autre pokemon
        // 4. le joueur pioche dans sa main jusqu'à avoir 5 pokemons dans la main


        // Boucle de jeu
        if (!partieTerminee()) {
            System.out.println(this.m_tour.getM_nbTourString()+" tour :");
            // joueurs.Joueur 1
            System.out.println("jeu.Tour de " + j1.getNom() + " :\n");
            // piocher
            j1.piocherPokemon();

            // tant qu'il n'y a pas 3 pokemons du joueur sur le terrain
            while (this.m_terrain.getNbPokemonsJoueur(j1) < 3 && j1.getMain().getNbPokemon() > 0){
                System.out.println("Placer des pokemons sur le terrain");
                j1.placerPokemon(this.m_terrain);
            }
            // attaquer
            if(j1.attaquer(this.m_terrain, j2)){
                partieTerminee();
            }
        }
        else if (partieTerminee()){
            Affichage.finDePartie(j1);
        }
    }
    public void initialisationJeu(){
        Random random = new Random();
        int randomInt = random.nextInt(2);
        // Initialisation des joueurs
        if(randomInt == 0){
            System.out.println("Vous êtes le joueur 1");
            this.m_j1 = new Humain("joueurs.Joueur 1", 20);
            this.m_j2 = new Ordinateur("joueurs.Joueur 2", 21);
        }
        else {
            System.out.println("Vous êtes le joueur 2");
            this.m_j1 = new Ordinateur("joueurs.Joueur 1", 20);
            this.m_j2 = new Humain("joueurs.Joueur 2", 21);
        }
        for (int i=0; i<5; i++) {
            this.m_j1.piocherPokemon();
            this.m_j2.piocherPokemon();
        }

        // Initlalisaiton de la partie, chaque joueur pose 3 pokemons sur le terrain
        System.out.println(this.m_tour.getM_nbTourString()+" tour :");
        System.out.println("jeu.Tour de " + this.m_j1.getNom());
        for (int i=0; i<3; i++) {
            this.m_j1.placerPokemon(this.m_terrain);
        }
        System.out.println("jeu.Tour de " + this.m_j2.getNom());
        for (int i=0; i<3; i++) {
            this.m_j2.placerPokemon(this.m_terrain);
        }
        //attaque j1
        System.out.println((this.m_j1.m_nom+" attaque :"));
        if(this.m_j1.attaquer(this.m_terrain, this.m_j2)){
            partieTerminee();
        }
        //attaque j2
        System.out.println((this.m_j2.m_nom+" attaque :"));
        if(this.m_j2.attaquer(this.m_terrain, this.m_j1)){
            partieTerminee();
        }
        Affichage.terrain(this.m_terrain,this.m_j1,this.m_j2);
        this.m_tour.nouveauTour();
    }

    private boolean partieTerminee() {
        return this.m_j1.aPerdu() || this.m_j2.aPerdu();
    }

    public Joueur getJoueur1() {
        return this.m_j1;
    }
    public Joueur getJoueur2() {
        return this.m_j2;
    }

    public Terrain getM_terrain() {
        return m_terrain;
    }
}