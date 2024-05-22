package pokemons;

import java.util.Random;

public class Pokemon {
  private final String m_nom;
  private final int m_pvMax;
  private int m_pv;
  private final int m_attaque;
  private final Elements m_element; // Indice de l'élément du Pokémon

  public Pokemon(String nom) {
    this.m_nom = nom;
    Random random = new Random();
    this.m_pvMax = (random.nextInt(11) + 10) * 10;
    this.m_pv = this.m_pvMax;
    this.m_attaque = (random.nextInt(5) + 3) * 10;
    Elements[] tabAffinite = {Elements.AIR, Elements.EAU, Elements.FEU, Elements.TERRE};
    this.m_element = tabAffinite[random.nextInt(4)];  }

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
}
