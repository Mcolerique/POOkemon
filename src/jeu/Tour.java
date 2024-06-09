package jeu;

import affichage.Affichage;
import joueurs.Joueur;

/**
 * La classe Tour gère le déroulement des tours dans le jeu.
 */
public class Tour {

    //ATTRIBUTS
    private final Jeu m_jeu;
    private int m_nbTour;


    //CONSTRUCTEUR

    /**
     * Constructeur de la classe Tour, qui initialise le jeu et le nombre de tours à 1.</p>
     *
     * @param jeu L'instance du jeu en cours.
     */
    public Tour(Jeu jeu) {
        this.m_jeu = jeu;
        this.m_nbTour = 1;
    }


    //METHODES

    /**
     * Commence un nouveau tour de jeu.
     *
     * @see Affichage#afficheNbTour(String)
     * @see Jeu#jouer(Joueur, Joueur)
     * @see Affichage#finDePartie(Joueur)
     */
    public void nouveauTour(){
        while (!this.m_jeu.partieTerminee()) {
            this.m_nbTour++;
            Affichage.afficheNbTour(this.getNbTourString() + " tour :");
            m_jeu.jouer(this.m_jeu.getJoueur1(), this.m_jeu.getJoueur2());
            m_jeu.jouer(this.m_jeu.getJoueur2(), this.m_jeu.getJoueur1());
        }
        Affichage.finDePartie(this.m_jeu.getVainqueur());
    }



    /**
     * Gère l'attaque d'un joueur contre un adversaire.
     *
     * @param joueur Le joueur qui attaque.
     * @param adversaire Le joueur adverse.
     * @return true si la partie est terminée après l'attaque, sinon false.
     */
    public boolean attaquer(Joueur joueur, Joueur adversaire){
        Affichage.afficher(Affichage.mettreEnGras(Affichage.mettreEnCouleur("Le " + joueur.getNom()+" attaque !", "\u001B[31m")));
        if(joueur.attaquer(this.m_jeu.getTerrain(), adversaire)){
            return this.m_jeu.partieTerminee();
        }
        return false;
    }



    //GETTERS

    /**
     * Retourne le numéro actuel du tour sous forme de chaîne de caractères.
     *
     * @return Le numéro actuel du tour.
     */
    public String getNbTourString(){
        if(this.m_nbTour == 1){
            return this.m_nbTour+"er";
        }
        else {
            return this.m_nbTour+"eme";
        }
    }
}