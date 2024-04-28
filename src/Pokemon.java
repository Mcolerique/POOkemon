import java.util.Random;
public class Pokemon
{
  private String m_nom;
  private int m_pvMax;
  private int m_pv;
  private int m_attaque;
  private int m_element;
  public Pokemon(Jeu jeu){
    Random random = new Random();
    int randomNumber = random.nextInt(jeu.m_nomMaxPokemon);
    m_nom = jeu.m_listNomPokemon.get(randomNumber);
    jeu.m_listNomPokemon.remove(randomNumber);
    jeu.m_nomMaxPokemon -=1;
    randomNumber = (random.nextInt(11) + 10)*10;
    m_pvMax = randomNumber;
    m_pv = m_pvMax;
    randomNumber = (random.nextInt(5) + 3)*10;
    m_attaque = randomNumber;
    randomNumber = random.nextInt(jeu.m_tabElt.length)+1;
    m_element = randomNumber;
  }
  public void attaquer(Pokemon pokemon, Jeu jeu){
    pokemon.subirDegats(this.m_attaque+=jeu.m_tabElt[pokemon.m_element][this.m_element]);
  }
  public void subirDegats(int degats){
    this.m_pv -= degats;
  }
  public Boolean estVivant(){
    if(this.m_pv<=0){
      return false;
    }
    else {
      return true;
    }
  }
}
