package pokemons;

/**
 * La classe Affinite représente l'affinité élémentaire d'un Pokémon.
 */
public class Affinite {


    //ATTRIBUTS
    private final String m_elements;
    private final String m_avantage;
    private final String m_desavantage;



    //CONSTRUCTEUR

    /**
     * Constructeur de la classe Affinite, qui initialise l'affinité élémentaire en fonction de l'élément spécifié.
     *
     * @param elements L'élément du Pokémon (eau, terre, air, feu).
     */
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



    //METHODES

    /**
     * Calcule l'attaque d'un Pokémon en fonction des affinités des éléments attaquant et ennemi.
     *
     * @param elementAttaquant L'affinité de l'élément attaquant.
     * @param elementEnnemi L'affinité de l'élément ennemi.
     * @param pokemon Le Pokémon dont l'attaque doit être calculée.
     * @return L'attaque du Pokémon ajustée en fonction des affinités.
     */

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




    //GETTERS

    /**
     * Obtient l'élément de cette affinité.
     *
     * @return Une chaîne de caractères représentant l'élément.
     */
    public String getElement() { return this.m_elements; }


    /**
     * Obtient l'élément sur lequel cette affinité a un avantage.
     *
     * @return Une chaîne de caractères représentant l'élément avantagé.
     */
    public String getAvantage(){
        return m_avantage;
    }


    /**
     * Obtient l'élément sur lequel cette affinité a un désavantage.
     *
     * @return Une chaîne de caractères représentant l'élément désavantagé.
     */
    public String getDesavantage(){
        return m_desavantage;
    }
}
