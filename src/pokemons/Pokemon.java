package pokemons;

import jeu.Jeu;
import pokemons.pouvoirs.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * La classe Pokemon représente un Pokémon avec ses attributs et ses capacités.
 */
public class Pokemon {

  //ATTRIBUTS
  private final String m_nom;
  private final int m_pvMax;
  private int m_pv;
  private int m_attaque;
  private final Affinite m_element;
  private final Pouvoir m_pouvoir;
  private int m_defense;




  //CONSTRUCTEUR

  /**
   * Constructeur de la classe Pokemon.
   *
   * @param nom Le nom du Pokémon.
   * @param pouvoir Le pouvoir du Pokémon.
   */
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




  //METHODES

  /**
   * Attaque un autre Pokémon en tenant compte des affinités.
   *
   * @param pokemonCible Le Pokémon cible de l'attaque.
   */
  public void attaquer(Pokemon pokemonCible) {
    int attaqueAffinite = m_element.getAttaqueAffinite(m_element, pokemonCible.m_element, this);
    pokemonCible.subirDegats(attaqueAffinite);
  }


  /**
   * Subit des dégâts en fonction de l'attaque reçue.
   *
   * @param degats Le montant des dégâts à subir.
   */
  public void subirDegats(int degats) {
    this.m_pv -= degats;
  }


  /**
   * Vérifie si le Pokémon est encore vivant.
   *
   * @return true si le Pokémon a encore des points de vie, false sinon.
   */
  public boolean estVivant() {
    return this.m_pv>0;
  }



  /**
   * Vérifie si ce Pokémon a un avantage d'élément sur un autre Pokémon.
   *
   * @param pokemon Le Pokémon à comparer.
   * @return true si ce Pokémon a un avantage d'élément, false sinon.
   */
  public boolean avantageSur(Pokemon pokemon) {
    return this.m_element.getAvantage().equals(pokemon.m_element.getElement());
  }



  /**
   * Soigne le Pokémon en ajoutant des points de vie.
   *
   * @param soin Le montant de soin à ajouter.
   */
  public void soigner(int soin){
    if(this.m_pv+soin > this.m_pvMax){
      this.m_pv = this.m_pvMax;
    }
    else {
      this.m_pv+=soin;
    }
  }



  /**
   * Modifie l'attaque du Pokémon.
   *
   * @param boost Le montant à ajouter à l'attaque.
   */
  public void modifAttaque(int boost) {
    this.m_attaque += boost;
  }


  /**
   * Modifie la défense du Pokémon.
   *
   * @param defense Le montant à ajouter à la défense.
   */
  public void modifDefense(int defense){
    this.m_defense+=defense;
  }





  //GETTERS

  /**
   * Obtient le nom du Pokémon.
   *
   * @return Le nom du Pokémon.
   */
  public String getNom() {
    return this.m_nom;
  }


  /**
   * Obtient les points de vie actuels du Pokémon.
   *
   * @return Les points de vie actuels du Pokémon.
   */
  public int getPv() {
    return this.m_pv;
  }


  /**
   * Obtient les points de vie maximum du Pokémon.
   *
   * @return Les points de vie maximum du Pokémon.
   */
  public int getPvMax() {
    return this.m_pvMax;
  }


  /**
   * Obtient l'attaque du Pokémon.
   *
   * @return L'attaque du Pokémon.
   */
  public int getAttaque() {
    return this.m_attaque;
  }


  /**
   * Obtient l'élément du Pokémon sous forme de chaîne de caractères.
   *
   * @return L'élément du Pokémon.
   */
  public String getElementString(){
    return this.m_element.getElement();
  }


  /**
   * Obtient le nom du pouvoir du Pokémon.
   *
   * @return Le nom du pouvoir du Pokémon ou "Aucun" s'il n'a pas de pouvoir.
   */
  public String getNomPouvoir(){
    if(this.m_pouvoir == null){
      return "Aucun";
    }
    else {
      return this.m_pouvoir.getNom();
    }
  }


  /**
   * Obtient le pouvoir du Pokémon.
   *
   * @return Le pouvoir du Pokémon.
   */
  public Pouvoir getPouvoir(){
    return this.m_pouvoir;
  }


  /**
   * Obtient la défense du Pokémon.
   *
   * @return La défense du Pokémon.
   */
  public int getDefense(){
    return this.m_defense;
  }
}
