package pokemon;

import java.util.Random;

public class Pokemon implements Elements{
  private final String m_nom;
  private final int m_pvMax;
  private int m_pv;
  private final int m_attaque;
  private final int m_element; // Indice de l'élément du Pokémon

  public Pokemon() {
    this.m_nom = ListePokemons.getNom();
    Random random = new Random();
    this.m_pvMax = (random.nextInt(11) + 10) * 10;
    this.m_pv = this.m_pvMax;
    this.m_attaque = (random.nextInt(5) + 3) * 10;
    this.m_element = random.nextInt(4) + 1; // Choix aléatoire de l'élément
  }

  public void attaquer(Pokemon pokemonCible) {
    int degats = this.m_attaque + m_tabElt[this.m_element][pokemonCible.m_element];
    pokemonCible.subirDegats(degats);
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
    switch (this.m_element){
      case 1:
        return "Terre";
      case 2:
        return "Eau";
      case 3:
        return "Feu";
      case 4:
        return "Air";
    }
    return "??";
  }
  public  boolean estAvantager(Pokemon pokemon){
    if(m_tabElt[this.m_element][pokemon.m_element]>0){
      return true;
    }
    else {
      return false;
    }
  }
}
