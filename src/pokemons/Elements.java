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
    Elements(String element, String avantage, String desavantage){
        this.m_element = element;
        this.m_avantage = avantage;
        this.m_desavantage = desavantage;
    }


    //Getters
    public int getAttaqueAffinite(Elements elementAttaquant, Elements elementEnnemi, Pokemon pokemon) {
        if(elementAttaquant.getAvantage().equals(elementEnnemi.toString()))
            return pokemon.getAttaque()+10;
        else if(elementAttaquant.getDesavantage().equals(elementEnnemi.toString()))
            return pokemon.getAttaque()-10;
        else
            return pokemon.getAttaque();
    }

    public String getElement() { return this.m_element; }

    public String getAvantage(){
        return new String(m_avantage);
    }

    public String getDesavantage(){
        return new String(m_desavantage);
    }
}