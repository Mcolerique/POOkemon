package pokemons;

import pokemons.pouvoirs.Pouvoir;

import java.util.Random;

public class Pokemon {
  private final String m_nom;
  private final int m_pvMax;
  private int m_pv;
  private final int m_attaque;
  private final Elements m_element; // Indice de l'élément du Pokémon
  private final Pouvoir m_pouvoir;

  public Pokemon() {
    this.m_nom = ListePokemons.getNom();
    Random random = new Random();
    this.m_pvMax = (random.nextInt(11) + 10) * 10;
    this.m_pv = this.m_pvMax;
    this.m_attaque = (random.nextInt(5) + 3) * 10;
    Elements[] tabAffinite = {Elements.AIR, Elements.EAU, Elements.FEU, Elements.TERRE};
    this.m_element = tabAffinite[random.nextInt(4)];
    this.m_pouvoir = ListePouvoirs.getPouvoir();
  }

  public void attaquer(Pokemon pokemonCible) {
    int attaqueAffinite = m_element.getAttaqueAffinite(m_element, pokemonCible.m_element, this);
    pokemonCible.subirDegats(attaqueAffinite);
  }

  public void subirDegats(int degats) {
    this.m_pv -= degats;
  }

  public boolean estVivant() {
    return this.m_pv>0;
  }

  public String getNom() {
    return this.m_nom;
  }

  public int getPv() {
    return this.m_pv;
  }

  public int getPvMax() {
    return this.m_pvMax;
  }

  public int getAttaque() {
    return this.m_attaque;
  }

  public String getElementString(){
    return m_element.name();
  }

  public boolean avantageSur(Pokemon pokemon) {
    return this.m_element.getAvantage().equals(pokemon.m_element.toString());
  }
  public String getNomPouvoir(){
    if(this.m_pouvoir == null){
      return "Aucun";
    }
    else {
      return this.m_pouvoir.getM_nom();
    }
  }
  public Pouvoir getM_pouvoir(){
    return this.m_pouvoir;
  }
  public void soigner(int soin){
    if(this.m_pv+soin > this.m_pvMax){
      this.m_pv = this.m_pvMax;
    }
    else {
      this.m_pv+=soin;
    }
  }
}
