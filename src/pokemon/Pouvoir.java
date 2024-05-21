package pokemon;

public abstract class Pouvoir {
    private String m_nom;
    private boolean m_utilisableChaqueTour;
    private boolean m_utilise;
    private String m_desc;

    public Pouvoir(String nom, boolean utilisableChaqueTour) {
        this.m_nom = nom;
        this.m_utilisableChaqueTour = utilisableChaqueTour;
        this.m_utilise = false;
    }
}
