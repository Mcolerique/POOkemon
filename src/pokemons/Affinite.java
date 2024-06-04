package pokemons;

import pokemons.pouvoirs.Pouvoir;

public class Affinite {
    private final String m_elements;
    private final String m_avantage;
    private final String m_desavantage;
    public Affinite(Elements elements){
        if(elements == Elements.EAU){
            this.m_elements = "eau";
            this.m_avantage = "feu";
            this.m_desavantage = "terre";
        }
        else if(elements == Elements.TERRE){
            this.m_elements = "terre";
            this.m_avantage = "eau";
            this.m_desavantage = "air";
        }
        else if(elements == Elements.AIR){
            this.m_elements = "air";
            this.m_avantage = "terre";
            this.m_desavantage = "feu";
        }
        else {
            this.m_elements = "feu";
            this.m_avantage = "air";
            this.m_desavantage = "eau";
        }
    }

    public int getAttaqueAffinite(Affinite elementAttaquant, Affinite elementEnnemi, Pokemon pokemon) {
        if(elementAttaquant.getAvantage() == elementEnnemi.getElement())
        {
            return pokemon.getAttaque()+10;
        }
        else if(elementAttaquant.getDesavantage() == elementEnnemi.getElement())
        {
            return pokemon.getAttaque()-10;
        }
        else
        {
            return pokemon.getAttaque();
        }
    }

    public String getElement() { return this.m_elements; }

    public String getAvantage(){
        return m_avantage;
    }

    public String getDesavantage(){
        return m_desavantage;
    }
}
