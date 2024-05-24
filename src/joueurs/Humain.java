package joueurs;

import affichage.Affichage;
import pokemons.Pokemon;
import jeu.Jeu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Humain extends Joueur{
    public Humain(String nom, int taillePioche){
        super(nom, taillePioche);
    }
    public void placerPokemon(Terrain terrain){
        System.out.println("Choisissez un pokemon à placer sur le terrain");
        for (int j = 0; j<this.m_main.getListePokemon().size();j++)
        {
            if (j != this.m_main.getListePokemon().size()-1)
            {
                System.out.print((j+1)+"."+this.m_main.getListePokemon().get(j).getNom() + " / ");
            }
            else {System.out.println((j+1)+"."+this.m_main.getListePokemon().get(j).getNom());}
        }
        Affichage.affichePokemon(this.m_main.getListePokemon());
        int pokemonaplacer;
        Scanner scanner = new Scanner(System.in);
        pokemonaplacer = scanner.nextInt() - 1;
        terrain.placerPokemons(this, pokemonaplacer);
        // afficher le terrain
        Affichage.affichePokemon(terrain.getM_pokemonsJoueur(this));
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
            Affichage.affichePokemon(terrain.getM_pokemonsJoueur(this));
            int pokemonAttaquant;
            Scanner scanner = new Scanner(System.in);
            pokemonAttaquant = scanner.nextInt() - 1;
            pokeQuiAttaque.remove(pokemonAttaquant);
            System.out.println("Choisissez un pokemon à attaquer");
            Affichage.affichePokemon(terrain.getM_pokemonsJoueur(adversaire));
            int pokemonAttaque;
            Scanner scanner2 = new Scanner(System.in);
            pokemonAttaque = scanner2.nextInt() - 1;
            terrain.getPokemon(this,pokemonAttaquant).attaquer(terrain.getPokemon(adversaire,pokemonAttaque));
            System.out.println(terrain.getPokemon(this,i).getNom()+" a attaquer "+terrain.getPokemon(adversaire,pokemonAttaque).getNom());
            // si le pokemon attaqué est mort, le défausser
            if(adversaire.mort(terrain, pokemonAttaque)){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean utiliserPouvoir(Terrain terrain, Joueur adversaire) {
        List<Pokemon> pokeQuiAttaque = new ArrayList<>();
        for(int i =0; i<terrain.getNbPokemonsJoueur(this);i++)
        {
            if (terrain.getM_pokemonsJoueur(this).get(i).getNomPouvoir()!="Aucun")
            {
                pokeQuiAttaque.add(terrain.getM_pokemonsJoueur(this).get(i));
            }
        }
        for (int i = 0; i<terrain.getNbPokemonsJoueur(this);i++)
        {
            System.out.print("Choisissez un pouvoir a utiliser (");
            for (int j = 0; j<pokeQuiAttaque.size();j++)
            {
                if (j != pokeQuiAttaque.size()-1)
                {
                    System.out.print((j+1)+"."+pokeQuiAttaque.get(j).getNom() + "/");
                }
                else {System.out.println((j+1)+"."+pokeQuiAttaque.get(j).getNom()+")");}
            }
            Affichage.affichePokemon(terrain.getM_pokemonsJoueur(this));
            int pokemonAttaquant;
            Scanner scanner = new Scanner(System.in);
            pokemonAttaquant = scanner.nextInt() - 1;
            pokeQuiAttaque.remove(pokemonAttaquant);
            pokeQuiAttaque.get(pokemonAttaquant).getM_pouvoir().utiliser(terrain, this, adversaire,pokeQuiAttaque.get(pokemonAttaquant));
            if (this.mort(terrain)|| adversaire.mort(terrain)){
                return true;
            }
        }
        return false;
    }
}
