import java.util.Random;

public class Pokemon implements Elements{
  private String m_nom;
  private int m_pvMax;
  private int m_pv;
  private int m_attaque;
  private int m_element; // Indice de l'élément du Pokémon
  private Jeu m_jeu;

  public Pokemon(String nom, Jeu jeu) {
    m_nom = nom;
    Random random = new Random();
    m_pvMax = (random.nextInt(11) + 10) * 10;
    m_pv = m_pvMax;
    m_attaque = (random.nextInt(5) + 3) * 10;
    m_element = random.nextInt(4); // Choix aléatoire de l'élément
    m_jeu = jeu;
  }

  public void attaquer(Pokemon pokemonCible) {
    int degats = m_attaque + m_tabElt[this.m_element][pokemonCible.m_element];
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
}
