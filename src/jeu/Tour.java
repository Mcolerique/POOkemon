package jeu;

import affichage.Affichage;

public class Tour {

    //Attributs
    private final Jeu jeu;
    private int m_nbTour;


    //Constructeur
    public Tour(Jeu jeu) {
        this.jeu = jeu;
        this.m_nbTour = 1;
    }


    //Methodes
    public void nouveauTour(){
        this.m_nbTour++;
        System.out.println("jeu.Tour n°"+this.m_nbTour+" :\n\n");
        Affichage.terrain(this.jeu.getTerrain(),this.jeu.getJoueur1(), this.jeu.getJoueur2());
        jeu.jouer(jeu.getJoueur1(), jeu.getJoueur2());
        jeu.jouer(jeu.getJoueur2(), jeu.getJoueur1());
        this.nouveauTour();
    }


    //Getters
    public String getNbTourString(){
        if(this.m_nbTour == 1){
            return this.m_nbTour+"er";
        }
        else {
            return this.m_nbTour+"eme";
        }
    }
}