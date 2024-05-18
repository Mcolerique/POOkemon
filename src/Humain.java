import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Humain extends Joueur{
    public Humain(String nom, Jeu jeu, int taillePioche){
        super(nom, jeu, taillePioche);
    }
    public void placerPokemon(Terrain terrain){
        this.m_main.afficher();
        System.out.println("Choisissez un pokemon à placer sur le terrain");
        int pokemonaplacer;
        Scanner scanner = new Scanner(System.in);
        pokemonaplacer = scanner.nextInt() - 1;
        terrain.placerPokemons(this, pokemonaplacer);
        // afficher le terrain
        terrain.printPokemon(this);
    }

    @Override
    public Boolean attaquer(Terrain terrain, Joueur adversaire)
    {
        List<Pokemon> pokeQuiAttaque = new ArrayList<>();
        pokeQuiAttaque.addAll(terrain.getM_pokemonsJoueur(this));
        for (int i = 0; i<terrain.getNbPokemonsJoueur(this);i++)
        {
            System.out.print("Choisissez un pokemon avec lequel attaquer (");
            for (int j = 0; j<pokeQuiAttaque.size();j++)
            {
                if (j != pokeQuiAttaque.size()-1)
                {
                    System.out.print((j+1)+"."+pokeQuiAttaque.get(j).getNom() + "/");
                }
                else {System.out.println((j+1)+"."+pokeQuiAttaque.get(j).getNom()+")");}
            }
            terrain.printPokemon(this);
            int pokemonAttaquant;
            Scanner scanner = new Scanner(System.in);
            pokemonAttaquant = scanner.nextInt() - 1;
            pokeQuiAttaque.remove(pokemonAttaquant);
            System.out.println("Choisissez un pokemon à attaquer");
            terrain.printPokemon(adversaire);
            int pokemonAttaque;
            Scanner scanner2 = new Scanner(System.in);
            pokemonAttaque = scanner2.nextInt() - 1;
            terrain.getPokemon(this,pokemonAttaquant).attaquer(terrain.getPokemon(adversaire,pokemonAttaque));
            System.out.println(terrain.getPokemon(this,i).getNom()+" a attaquer "+terrain.getPokemon(adversaire,pokemonAttaque).getNom());
            // si le pokemon attaqué est mort, le défausser
            if (!(terrain.getPokemon(adversaire, pokemonAttaque)).estVivant()) {
                System.out.println("Le pokemon " + (terrain.getPokemon(adversaire, pokemonAttaque)).getNom() + " est mort");
                adversaire.defausser(terrain.getPokemon(adversaire, pokemonAttaque));
                // retirer le pokemon du terrain
                terrain.retirerPokemon(adversaire, pokemonAttaque);
                if(terrain.estVide(adversaire)){
                    System.out.println("Le "+this.m_nom+" a gagné");
                    return true;
                }
            }
        }
        return false;
    }
}
