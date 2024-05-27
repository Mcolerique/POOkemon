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
  private final Elements m_element; //Indice de l'élément du Pokémon
  private final Pouvoir m_pouvoir;
  private static ArrayList<String> m_nomsDisponibles = new ArrayList<String>(Arrays.asList(
          "Pikachu", "Bulbizarre", "Carapuce", "Salamèche", "Herbizarre",
          "Dracaufeu", "Tortank", "Raichu", "Carabaffe", "Reptincel",
          "Aquali", "Florizarre", "Mewtwo", "Miaouss", "Ronflex",
          "Magicarpe", "Roucool", "Rattata", "Nidoran", "Abra",
          "Machoc", "Ptitard", "Kokiyas", "Hypotrempe", "Mimitoss",
          "Voltorbe", "Otaria", "Porygon", "Tadmorv", "Amonita",
          "Kabuto", "Natu", "Hoothoot", "Cornèbre", "Soporifik",
          "Mysdibule", "Girafarig", "Wattouat", "Toudoudou", "Tarsal", "Evoli"));
  private static ArrayList<String> m_nomsUtilises = new ArrayList<String>();
  private static ArrayList<Pouvoir> m_nomsPouvoirs = new ArrayList<>(Arrays.asList(new ExtensionTerritoire(), new Kamikaze(), new SoinDeZone(), new FerveurGuerriere()));
  private static ArrayList<Pouvoir> m_pouvoirsUtilises = new ArrayList<>();

  //Constructeur
  public Pokemon() {
    this.m_nom = Pokemon.AttribuerNom();
    Random random = new Random();
    this.m_pvMax = (random.nextInt(11) + 10) * 10;
    this.m_pv = this.m_pvMax;
    this.m_attaque = (random.nextInt(5) + 3) * 10;
    Elements[] tabAffinite = {Elements.AIR, Elements.EAU, Elements.FEU, Elements.TERRE};
    this.m_element = tabAffinite[random.nextInt(4)];
    this.m_pouvoir = Pokemon.AttribuerPouvoir();
    if(this.m_pouvoir != null){
      Jeu.getPokemonAvecPouvoir().put(this,this.m_pouvoir);
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
    return this.m_element.getAvantage().equals(pokemon.m_element.toString());
  }

  public void soigner(int soin){
    if(this.m_pv+soin > this.m_pvMax){
      this.m_pv = this.m_pvMax;
    }
    else {
      this.m_pv+=soin;
    }
  }

  public void boostAttaque(int boost) {
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
    return m_element.name();
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
  public static int getIndexAleatoire() {
    if (m_nomsDisponibles.isEmpty()) {
      throw new IllegalStateException("Il n'y a plus de noms de Pokémon disponibles.");
    }
    Random random = new Random();
    return random.nextInt(m_nomsDisponibles.size());
  }

  public static String AttribuerNom() {
    int index = getIndexAleatoire();
    String nom = m_nomsDisponibles.get(index);
    m_nomsUtilises.add(nom);
    m_nomsDisponibles.remove(index);
    return nom;
  }

  public static ArrayList<String> getM_nomsDisponibles() {
    return m_nomsDisponibles;
  }

  public static ArrayList<String> getM_nomsUtilises() {
    return m_nomsUtilises;
  }
  public static int getIndexPouvoirAleatoire() {
    if (m_nomsPouvoirs.isEmpty()) {
      return -1;
    }
    Random random = new Random();
    int index = random.nextInt(m_nomsPouvoirs.size())+m_nomsPouvoirs.size();
    try {
      m_nomsPouvoirs.get(index);
    }
    catch (IndexOutOfBoundsException e) {
      return -1;
    }
    return index;
  }

  public static Pouvoir AttribuerPouvoir() {
    int index = getIndexPouvoirAleatoire();
    if(index == -1)
    {
      return null;
    }
    else {
      Pouvoir pouvoir = m_nomsPouvoirs.get(index);
      m_pouvoirsUtilises.add(pouvoir);
      m_nomsPouvoirs.remove(index);
      return pouvoir;
    }
  }

  public static ArrayList<Pouvoir> getM_nomsPouvoirs() {
    return m_nomsPouvoirs;
  }

  public static ArrayList<Pouvoir> getM_pouvoirsUtilises() {
    return m_pouvoirsUtilises;
  }
}
