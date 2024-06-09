package pokemons;

import jeu.Jeu;
import pokemons.pouvoirs.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Pokemon {

  //Attributs
  private final String m_nom;
  private final int m_pvMax;
  private int m_pv;
  private int m_attaque;
  private final Affinite m_element; //Indice de l'élément du Pokémon
  private final Pouvoir m_pouvoir;
  private int m_defense;

  //Constructeur
  public Pokemon(String nom, Pouvoir pouvoir) {
    this.m_nom = nom;
    Random random = new Random();
    this.m_pvMax = (random.nextInt(11) + 10) * 10;
    this.m_pv = this.m_pvMax;
    this.m_attaque = (random.nextInt(5) + 3) * 10;
    Elements[] tabAffinite = {Elements.AIR, Elements.EAU, Elements.FEU, Elements.TERRE};
    this.m_element = new Affinite(tabAffinite[random.nextInt(4)]);
    this.m_pouvoir = pouvoir;
    this.m_defense=0;
    if(this.m_pouvoir != null){
      Jeu.ajouterPokeAPouvoir(this,this.m_pouvoir);
    }
  }


  //Methodes
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

  public boolean avantageSur(Pokemon pokemon) {
    return this.m_element.getAvantage().equals(pokemon.m_element.getElement());
  }

  public void soigner(int soin){
    if(this.m_pv+soin > this.m_pvMax){
      this.m_pv = this.m_pvMax;
    }
    else {
      this.m_pv+=soin;
    }
  }

  public void modifAttaque(int boost) {
    this.m_attaque += boost;
  }


  //Getters
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
    return this.m_element.getElement();
  }

  public String getNomPouvoir(){
    if(this.m_pouvoir == null){
      return "Aucun";
    }
    else {
      return this.m_pouvoir.getNom();
    }
  }

  public Pouvoir getPouvoir(){
    return this.m_pouvoir;
  }
  public void modifDefense(int defense){
    this.m_defense+=defense;
  }
  public int getDefense(){
    return this.m_defense;
  }
}
