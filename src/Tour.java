

public class Tour {
    private final Jeu jeu;
    private int m_nbTour;

    public Tour(Jeu jeu) {
        this.jeu = jeu;
        this.m_nbTour = 1;
    }
    public void nouveauTour(){
        this.m_nbTour++;
    }
    public int getM_nbTour(){
        return this.m_nbTour;
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