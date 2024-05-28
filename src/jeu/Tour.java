package jeu;

import affichage.Affichage;
import joueurs.Joueur;

public class Tour {

    //Attributs
    private final Jeu m_jeu;
    private int m_nbTour;


    //Constructeur
    public Tour(Jeu m_jeu) {
        this.m_jeu = m_jeu;
        this.m_nbTour = 1;
    }


    //Methodes
    public void nouveauTour(){
        this.m_nbTour++;
        while (!this.m_jeu.partieTerminee()) {
            System.out.println("jeu.Tour n°"+this.m_nbTour+" :\n\n");
            Affichage.terrain(this.m_jeu.getTerrain(),this.m_jeu.getJoueur1(), this.m_jeu.getJoueur2());
            m_jeu.jouer(m_jeu.getJoueur1(), m_jeu.getJoueur2());
            m_jeu.jouer(m_jeu.getJoueur2(), m_jeu.getJoueur1());
        }

    }

    public boolean attaquer(Joueur joueur, Joueur adversaire){
        Affichage.afficher((joueur.getNom()+" attaque :"));
        if(joueur.attaquer(this.m_jeu.getTerrain(), adversaire)){
            this.m_jeu.partieTerminee();
        }
        return false;
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