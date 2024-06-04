package pokemons.pouvoirs;

import joueurs.Joueur;
import joueurs.Terrain;
import pokemons.Pokemon;

public abstract class Pouvoir {

    //Attributs
    private String m_nom;
    protected boolean m_utilise;
    private String m_desc;


    //Constucteur
    public Pouvoir(String nom, String desc) {
        this.m_nom = nom;
        this.m_desc = desc;
        this.m_utilise = false;
    }


    //Methodes abstraites
    public abstract void utiliser(Terrain terrain, Joueur allie, Joueur adversaire, Pokemon pokemon, int intPokemon);

    public abstract void annulerPouvoir(Terrain terrain, Joueur allie, Joueur adversaire, Pokemon pokemon);


    //Getters
    public String getNom(){
        return this.m_nom;
    }

    public boolean getUtilise(){
        return this.m_utilise;
    }
    public String getM_desc(){return this.m_desc;}
}
