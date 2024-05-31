package joueurs;

import affichage.Affichage;
import pokemons.Pokemon;
import jeu.Jeu;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Humain extends Joueur{

    //Constructeur
    public Humain(String nom, int taillePioche){
        super(nom, taillePioche);
    }


    //Methodes
    public void placerPokemon(Terrain terrain){
        while (terrain.getPokemonsJoueur(this).size() < this.m_tailleTerrain){
            try {
                Thread.sleep(2000);
                Affichage.afficher("Choisissez un pokemon à placer sur le terrain");
                Affichage.selectionPokemon(this.m_main.getListePokemon());
                Affichage.affichePokemon(this.m_main.getListePokemon());
                int pokemonaplacer = selection(this.m_main.getListePokemon());
                terrain.placerPokemons(this, pokemonaplacer);
                //Afficher le terrain
                Affichage.affichePokemon(terrain.getPokemonsJoueur(this));
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    //Methodes redefinies
    @Override
    public boolean attaquer(Terrain terrain, Joueur adversaire)
    {
        List<Pokemon> pokeQuiAttaque = new ArrayList<>();
        pokeQuiAttaque.addAll(terrain.getPokemonsJoueur(this));
        for (int i = 0; i<terrain.getNbPokemonsJoueur(this);i++)
        {
            Affichage.afficher("Choisissez un pokemon avec lequel attaquer (");
            Affichage.selectionPokemon(pokeQuiAttaque);
            Affichage.affichePokemon(terrain.getPokemonsJoueur(this));
            int pokemonAttaquant = selection(pokeQuiAttaque);
            Affichage.afficher("Choisissez un pokemon à attaquer");
            Affichage.affichePokemon(terrain.getPokemonsJoueur(adversaire));
            int pokemonAttaque = selection(pokeQuiAttaque);
            pokeQuiAttaque.get(pokemonAttaquant).attaquer(terrain.getPokemon(adversaire,pokemonAttaque));
            Affichage.afficher(pokeQuiAttaque.get(pokemonAttaquant).getNom()+" a attaquer "+terrain.getPokemon(adversaire,pokemonAttaque).getNom());
            pokeQuiAttaque.remove(pokemonAttaquant);
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
            if (terrain.getPokemonsJoueur(this).get(i).getNomPouvoir()!="Aucun"&& !terrain.getPokemonsJoueur(this).get(i).getPouvoir().getUtilise())
            {
                pokeQuiAttaque.add(terrain.getPokemonsJoueur(this).get(i));
            }
        }
        if(pokeQuiAttaque.isEmpty()){
            return false;
        }
        for (int i = 0; i<pokeQuiAttaque.size();i++)
        {
            Affichage.afficher("Choisissez un pouvoir a utiliser (");
            Affichage.selectionPokemon(pokeQuiAttaque);
            Affichage.affichePokemon(pokeQuiAttaque);
            int pokemonAttaquant = selection(pokeQuiAttaque);
            pokeQuiAttaque.get(pokemonAttaquant).getPouvoir().utiliser(terrain, this, adversaire,pokeQuiAttaque.get(pokemonAttaquant),pokemonAttaquant);
            pokeQuiAttaque.remove(pokemonAttaquant);
            if (this.mort(terrain)|| adversaire.mort(terrain)){
                if(Jeu.getPokemonAvecPouvoir().get(pokeQuiAttaque.get(pokemonAttaquant)) != null){
                    pokeQuiAttaque.get(pokemonAttaquant).getPouvoir().annulerPouvoir(terrain, this, adversaire,pokeQuiAttaque.get(pokemonAttaquant));
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public int selection(List<Pokemon> list) {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt() - 1;
    }
}
