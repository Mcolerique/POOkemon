package pokemons;

enum Elements {
    EAU("eau","feu","terre"),
    FEU("feu","air","eau"),
    TERRE("terre","eau","air"),
    AIR("air","terre","feu");

    //Attributs
    private String m_element;
    private String m_avantage;
    private String m_desavantage;

    //Constructeurs
    private Elements(String element, String avantage, String desavantage){
        m_element = element;
        m_avantage = avantage;
        m_desavantage = desavantage;
    }

    //Methodes
    int getAttaqueAffinite(Elements elementAttaquant, Elements elementEnnemi, Pokemon pokemon) {
        if(elementAttaquant.getAvantage().equals(elementEnnemi.toString()))
            return pokemon.getAttaque()+10;
        else if(elementAttaquant.getDesavantage().equals(elementEnnemi.toString()))
            return pokemon.getAttaque()-10;
        else
            return pokemon.getAttaque();
    }

    //Getters
    String getAvantage(){
        return new String(m_avantage);
    }

    String getDesavantage(){
        return new String(m_desavantage);
    }

    //Affichage
    @Override
    public String toString(){
        return m_element;
    }
}