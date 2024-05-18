import java.util.ArrayList;
import java.util.List;

public class Terrain {
    private final List<Pokemon> m_pokemonsJoueur1;
    private final List<Pokemon> m_pokemonsJoueur2;
    private final int m_taille;

    public Terrain() {
        // Initialisation des listes de Pokemons pour les deux joueurs
        this.m_pokemonsJoueur1 = new ArrayList<>();
        this.m_pokemonsJoueur2 = new ArrayList<>();
        m_taille = 3;
    }

    public void placerPokemons(Joueur joueur, int pokemon) {
        if (joueur.getNom().equals("Joueur 1")) {
            this.m_pokemonsJoueur1.add(joueur.getPokemon(pokemon));
        } else {
            this.m_pokemonsJoueur2.add(joueur.getPokemon(pokemon));
        }
    }
    public Pokemon getPokemon(Joueur j, int pokemon) {
        if (j.getNom().equals("Joueur 1")) {
            return m_pokemonsJoueur1.get(pokemon);
        } else {
            return m_pokemonsJoueur2.get(pokemon);
        }
    }
    public void printPokemon(Joueur joueur) {
        if (joueur.getNom().equals("Joueur 1")) {
            int nombrePokemons = this.m_pokemonsJoueur1.size();
            int largeurCase = 20; // Largeur de chaque case

            // Affichage des noms des Pokémon
            System.out.print("Pokémon du Joueur 1 : ");
            for (int i = 0; i < nombrePokemons; i++) {
                String nom = this.m_pokemonsJoueur1.get(i).getNom();
                System.out.print(String.format("%-" + largeurCase + "s", nom));
            }
            System.out.println();

            // Affichage des PV des Pokémon
            System.out.print("                  pv : ");
            for (int i = 0; i < nombrePokemons; i++) {
                String pv = this.m_pokemonsJoueur1.get(i).getPv() + "/" + this.m_pokemonsJoueur1.get(i).getPvMax();
                System.out.print(String.format("%-" + largeurCase + "s", pv));
            }
            System.out.println();

            // Affichage des attaques des Pokémon
            System.out.print("             attaque : ");
            for (int i = 0; i < nombrePokemons; i++) {
                String attaque = String.valueOf(this.m_pokemonsJoueur1.get(i).getAttaque());
                System.out.print(String.format("%-" + largeurCase + "s", attaque));
            }
            System.out.println();

            // Affichage des types des Pokémon
            System.out.print("                type : ");
            for (int i = 0; i < nombrePokemons; i++) {
                String type = this.m_pokemonsJoueur1.get(i).getElementString();
                System.out.print(String.format("%-" + largeurCase + "s", type));
            }
            System.out.println();
        }

        else {
            int nombrePokemons = this.m_pokemonsJoueur2.size();
            int largeurCase = 20; // Largeur de chaque case

            // Affichage des noms des Pokémon
            System.out.print("Pokémon du Joueur 2 : ");
            for (int i = 0; i < nombrePokemons; i++) {
                String nom = this.m_pokemonsJoueur2.get(i).getNom();
                System.out.print(String.format("%-" + largeurCase + "s", nom));
            }
            System.out.println();

            // Affichage des PV des Pokémon
            System.out.print("                  pv : ");
            for (int i = 0; i < nombrePokemons; i++) {
                String pv = this.m_pokemonsJoueur2.get(i).getPv() + "/" + this.m_pokemonsJoueur2.get(i).getPvMax();
                System.out.print(String.format("%-" + largeurCase + "s", pv));
            }
            System.out.println();

            // Affichage des attaques des Pokémon
            System.out.print("             attaque : ");
            for (int i = 0; i < nombrePokemons; i++) {
                String attaque = String.valueOf(this.m_pokemonsJoueur2.get(i).getAttaque());
                System.out.print(String.format("%-" + largeurCase + "s", attaque));
            }
            System.out.println();

            // Affichage des types des Pokémon
            System.out.print("                type : ");
            for (int i = 0; i < nombrePokemons; i++) {
                String type = this.m_pokemonsJoueur2.get(i).getElementString();
                System.out.print(String.format("%-" + largeurCase + "s", type));
            }
            System.out.println();
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

        System.out.println("Pokémon du Joueur 2 : " +  this.m_pokemonsJoueur2.get(0).getNom() + "                 "+ this.m_pokemonsJoueur2.get(1).getNom()+ "                 "+ this.m_pokemonsJoueur2.get(2).getNom());
        System.out.println("                 pv : "+ this.m_pokemonsJoueur2.get(0).getPv()+"/"+ this.m_pokemonsJoueur2.get(0).getPvMax()+"               pv : "+ this.m_pokemonsJoueur2.get(1).getPv()+"/"+ this.m_pokemonsJoueur2.get(1).getPvMax()+"               pv : "+ this.m_pokemonsJoueur2.get(2).getPv()+"/"+ this.m_pokemonsJoueur2.get(2).getPvMax());
        System.out.println("            attaque : "+ this.m_pokemonsJoueur2.get(0).getAttaque()+"             attaque : "+ this.m_pokemonsJoueur2.get(1).getAttaque()+"             attaque : "+ this.m_pokemonsJoueur2.get(2).getAttaque());
        System.out.println("               type : "+ this.m_pokemonsJoueur2.get(0).getElementString()+"                type : "+ this.m_pokemonsJoueur2.get(1).getElementString()+"                type : "+ this.m_pokemonsJoueur2.get(2).getElementString());
    }
    public int getM_taille(){
        return this.m_taille;
    }

    public List<Pokemon> getM_pokemonsJoueur(Joueur joueur) {
        if (joueur.getNom().equals("Joueur 1"))
        {
            return this.m_pokemonsJoueur1;
        }
        else{
            return this.m_pokemonsJoueur2;
        }
    }
    public void retirerPokemon(Joueur joueur, int pokemon){
        if (joueur.getNom().equals("Joueur 1"))
        {
            this.m_pokemonsJoueur1.remove(pokemon);
        }
        else{
            this.m_pokemonsJoueur2.remove(pokemon);
        }
    }
    public boolean estVide(Joueur joueur){
        if (joueur.getNom().equals("Joueur 1"))
        {
            return this.m_pokemonsJoueur1.isEmpty();
        }
        else{
            return this.m_pokemonsJoueur2.isEmpty();
        }
    }
}
