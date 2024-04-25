import java.util.Random;
public class Pokemon
{
  private String m_nom;
  private int m_pvMax;
  private int m_pv;
  private int m_attaque;
  private int m_element;
  public Pokemon(){
    Random random = new Random();
    int randomNumber = random.nextInt(0) + m_nomMaxPokemon;
    m_nom = m_tabPokemon.get(randomNumber);
    m_nomMaxPokemon.remove(randomNumber);
    m_nomMaxPokemon -=1;
    randomNumber = random.nextInt(11) + 10;
    m_pvMax = randomNumber *= 10;
    m_pv = m_pvMax;
    randomNumber = random.nextInt(0) + 4;
    m_attaque = randomNumber *= 10;
    randomNumber = random.nextInt(1) + 4;
    m_element = randomNumber;
  }
  public void attaquer(Pokemon pokemon){
    pokemon.subirDegats(this.m_attaque+=m_tabElt[pokemon.m_element,this.m_element]);
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
