package pokemons.pouvoirs;

import joueurs.Joueur;
import joueurs.Terrain;
import pokemons.Pokemon;

public abstract class Pouvoir {
    private String m_nom;
    protected boolean m_utilise;
    private String m_desc;

    public Pouvoir(String nom) {
        this.m_nom = nom;
        this.m_utilise = false;
    }
    public String getM_nom(){
        return this.m_nom;
    }
    public abstract void utiliser(Terrain terrain, Joueur allie, Joueur adversaire, Pokemon pokemon, int intPokemon);
    public abstract void annulerPouvoir(Terrain terrain, Joueur allie, Joueur adversaire, Pokemon pokemon);
    public boolean getM_utilise(){
        return this.m_utilise;
    }
}
