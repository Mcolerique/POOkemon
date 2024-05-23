package pokemons.pouvoirs;

import joueurs.Joueur;
import joueurs.Terrain;
import pokemons.Pokemon;

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
    public String getM_nom(){
        return this.m_nom;
    }
    public abstract void utiliser(Terrain terrain, Joueur allie, Joueur adversaire, Pokemon pokemon);
}
