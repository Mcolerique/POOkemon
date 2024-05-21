package jeu;

import affichage.Affichage;

public class Tour {
    private final Jeu jeu;
    private int m_nbTour;

    public Tour(Jeu jeu) {
        this.jeu = jeu;
        this.m_nbTour = 1;
    }
    public void nouveauTour(){
        this.m_nbTour++;
        System.out.println("jeu.Tour n°"+this.m_nbTour+" :\n\n");
        Affichage.terrain(this.jeu.getM_terrain(),this.jeu.getJoueur1(), this.jeu.getJoueur2());
        jeu.jouer(jeu.getJoueur1(), jeu.getJoueur2());
        jeu.jouer(jeu.getJoueur2(), jeu.getJoueur1());
        this.nouveauTour();
    }
    public String getM_nbTourString(){
        if(this.m_nbTour == 1){
            return this.m_nbTour+"er";
        }
        else {
            return this.m_nbTour+"eme";
        }
    }
}