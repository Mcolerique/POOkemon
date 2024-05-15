import java.util.Random;

public class Pokemon {
  private String m_nom;
  private int m_pvMax;
  private int m_pv;
  private int m_attaque;
  private int m_element; // Indice de l'élément du Pokémon
  private Jeu m_jeu;

  public Pokemon(String nom) {
    m_nom = nom;
    Random random = new Random();
    m_pvMax = (random.nextInt(11) + 10) * 10;
    m_pv = m_pvMax;
    m_attaque = (random.nextInt(5) + 3) * 10;
    m_element = random.nextInt(4); // Choix aléatoire de l'élément
  }

  public void attaquer(Pokemon pokemonCible) {
    int degats = m_attaque;
    int advantage = m_jeu.getTabElt()[this.m_element][pokemonCible.m_element];
    if (advantage == 1) {
      degats += 10; // Avantage d'affinité
    } else if (advantage == -1) {
      degats -= 10; // Désavantage d'affinité
    }

    pokemonCible.subirDegats(degats);
  }

  public void subirDegats(int degats) {
    m_pv -= degats;
  }

  public boolean estVivant() {
    return m_pv > 0;
  }

  public String getNom() {
    return m_nom;
  }

  public int getPv() {
    return m_pv;
  }

  public int getPvMax() {
    return m_pvMax;
  }

  public int getAttaque() {
    return m_attaque;
  }

  public int getElement() {
    return m_element;
  }
}
