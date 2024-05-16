import java.util.ArrayList;
import java.util.List;

public class Terrain {
    private List<Pokemon> m_pokemonsJoueur1;
    private List<Pokemon> m_pokemonsJoueur2;

    public Terrain() {
        // Initialisation des listes de Pokemons pour les deux joueurs
        this.m_pokemonsJoueur1 = new ArrayList<>();
        this.m_pokemonsJoueur2 = new ArrayList<>();
    }

    public void placerPokemons(Joueur joueur, int pokemon) {
        if (joueur.getNom().equals("Joueur 1")) {
            this.m_pokemonsJoueur1.add(joueur.getPokemon(pokemon));
        } else {
            this.m_pokemonsJoueur2.add(joueur.getPokemon(pokemon));
        }
    }
    public Pokemon getPokemon(Joueur adversaire) {
        if (adversaire.getNom().equals("Joueur 1")) {
            return  this.m_pokemonsJoueur1.get(0);
        } else {
            return  this.m_pokemonsJoueur2.get(0);
        }
    }
    public void printPokemon(Joueur joueur) {
        if (joueur.getNom().equals("Joueur 1")) {
            System.out.println("Pokémon du Joueur 1 :    " +  this.m_pokemonsJoueur1.get(0).getNom() + "                 "+ this.m_pokemonsJoueur1.get(1).getNom()+ "                 "+ this.m_pokemonsJoueur1.get(2).getNom());
            System.out.println("                  pv : "+ this.m_pokemonsJoueur1.get(0).getPv()+"/"+ this.m_pokemonsJoueur1.get(0).getPvMax()+"               pv : "+ this.m_pokemonsJoueur1.get(1).getPv()+"/"+ this.m_pokemonsJoueur1.get(1).getPvMax()+"               pv : "+ this.m_pokemonsJoueur1.get(2).getPv()+"/"+ this.m_pokemonsJoueur1.get(2).getPvMax());
            System.out.println("             attaque : "+ this.m_pokemonsJoueur1.get(0).getAttaque()+"             attaque : "+ this.m_pokemonsJoueur1.get(1).getAttaque()+"             attaque : "+ this.m_pokemonsJoueur1.get(2).getAttaque());
            System.out.println("                type : "+ this.m_pokemonsJoueur1.get(0).getElementString()+"                type : "+ this.m_pokemonsJoueur1.get(1).getElementString()+"                type : "+ this.m_pokemonsJoueur1.get(2).getElementString());
        } else {
            System.out.println("Pokémon du Joueur 2 :    " +  this.m_pokemonsJoueur2.get(0).getNom() + "                 "+ this.m_pokemonsJoueur2.get(1).getNom()+ "                 "+ this.m_pokemonsJoueur2.get(2).getNom());
            System.out.println("                  pv : "+ this.m_pokemonsJoueur2.get(0).getPv()+"/"+ this.m_pokemonsJoueur2.get(0).getPvMax()+"               pv : "+ this.m_pokemonsJoueur2.get(1).getPv()+"/"+ this.m_pokemonsJoueur2.get(1).getPvMax()+"               pv : "+ this.m_pokemonsJoueur2.get(2).getPv()+"/"+ this.m_pokemonsJoueur2.get(2).getPvMax());
            System.out.println("             attaque : "+ this.m_pokemonsJoueur2.get(0).getAttaque()+"             attaque : "+ this.m_pokemonsJoueur2.get(1).getAttaque()+"             attaque : "+ this.m_pokemonsJoueur2.get(2).getAttaque());
            System.out.println("                type : "+ this.m_pokemonsJoueur2.get(0).getElementString()+"                type : "+ this.m_pokemonsJoueur2.get(1).getElementString()+"                type : "+ this.m_pokemonsJoueur2.get(2).getElementString());
        }

    }

    public int getNbPokemonsJoueur(Joueur mJ1) {
        if (mJ1.getNom().equals("Joueur 1")) {
            return  this.m_pokemonsJoueur1.size();
        } else {
            return  this.m_pokemonsJoueur2.size();
        }
    }
    public void afficheterrain(){
    System.out.println(" Pokémon du Joueur 1 : " +  this.m_pokemonsJoueur1.get(0).getNom() + "                 "+ this.m_pokemonsJoueur1.get(1).getNom()+ "                 "+ this.m_pokemonsJoueur1.get(2).getNom());
    System.out.println("                  pv : "+ this.m_pokemonsJoueur1.get(0).getPv()+"/"+ this.m_pokemonsJoueur1.get(0).getPvMax()+"               pv : "+ this.m_pokemonsJoueur1.get(1).getPv()+"/"+ this.m_pokemonsJoueur1.get(1).getPvMax()+"               pv : "+ this.m_pokemonsJoueur1.get(2).getPv()+"/"+ this.m_pokemonsJoueur1.get(2).getPvMax());
    System.out.println("             attaque : "+ this.m_pokemonsJoueur1.get(0).getAttaque()+"             attaque : "+ this.m_pokemonsJoueur1.get(1).getAttaque()+"             attaque : "+ this.m_pokemonsJoueur1.get(2).getAttaque());
    System.out.println("                type : "+ this.m_pokemonsJoueur1.get(0).getElementString()+"                type : "+ this.m_pokemonsJoueur1.get(1).getElementString()+"                type : "+ this.m_pokemonsJoueur1.get(2).getElementString());

    System.out.println("\n --------------------------------------------------------------------------------------\n");

    System.out.println("Pokémon du Joueur 2 :    " +  this.m_pokemonsJoueur2.get(0).getNom() + "                 "+ this.m_pokemonsJoueur2.get(1).getNom()+ "                 "+ this.m_pokemonsJoueur2.get(2).getNom());
    System.out.println("                  pv : "+ this.m_pokemonsJoueur2.get(0).getPv()+"/"+ this.m_pokemonsJoueur2.get(0).getPvMax()+"               pv : "+ this.m_pokemonsJoueur2.get(1).getPv()+"/"+ this.m_pokemonsJoueur2.get(1).getPvMax()+"               pv : "+ this.m_pokemonsJoueur2.get(2).getPv()+"/"+ this.m_pokemonsJoueur2.get(2).getPvMax());
    System.out.println("             attaque : "+ this.m_pokemonsJoueur2.get(0).getAttaque()+"             attaque : "+ this.m_pokemonsJoueur2.get(1).getAttaque()+"             attaque : "+ this.m_pokemonsJoueur2.get(2).getAttaque());
    System.out.println("                type : "+ this.m_pokemonsJoueur2.get(0).getElementString()+"                type : "+ this.m_pokemonsJoueur2.get(1).getElementString()+"                type : "+ this.m_pokemonsJoueur2.get(2).getElementString());
}
}
