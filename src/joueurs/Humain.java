package joueurs;

import affichage.*;
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
                Thread.sleep(1000);
                Affichage.afficher("Choisissez un pokemon à placer sur le terrain :         " + Affichage.selectionPokemon(this.m_main.getListePokemon()));
                int pokemonaplacer = selection(this.m_main.getListePokemon().size());
                Pokemon poke = this.getPokemon(pokemonaplacer);
                if (poke == null)
                {
                    Affichage.afficher("Vous devez placer un pokemon");
                }
                else {
                    terrain.placerPokemons(this, poke);
                }
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
        Musique m_bruitage = new Musique("musiques/bruitage/attaque.wav");
        m_bruitage.play();
        List<Pokemon> pokeQuiAttaque = new ArrayList<>();
        pokeQuiAttaque.addAll(terrain.getPokemonsJoueur(this));
        for (int i = 0; i<terrain.getNbPokemonsJoueur(this);i++)
        {
            try {
                Thread.sleep(1000);
                Affichage.afficher("Choisissez un pokemon avec lequel attaquer :       " + Affichage.selectionPokemon(pokeQuiAttaque));
                int pokemonAttaquant = selection(pokeQuiAttaque.size());
                Pokemon attaquant = pokeQuiAttaque.get(pokemonAttaquant);
                Affichage.afficher("Choisissez un pokemon à attaquer :       " + Affichage.selectionPokemon(terrain.getPokemonsJoueur(adversaire)));
                int pokemonAttaque = selection(pokeQuiAttaque.size());
                attaquant.attaquer(terrain.getPokemon(adversaire, pokemonAttaque));
                Thread.sleep(1000);
                Affichage.afficher(Affichage.mettreEnGras(Affichage.mettreEnCouleur(attaquant.getNom() + " a attaqué " + terrain.getPokemon(adversaire, pokemonAttaque).getNom(),"\u001B[31m")));
                pokeQuiAttaque.remove(pokemonAttaquant);
                // si le pokemon attaqué est mort, le défausser
                if (adversaire.mort(terrain, pokemonAttaque)) {
                    return true;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public boolean utiliserPouvoir(Terrain terrain, Joueur adversaire) {
        List<Pokemon> pokeQuiAttaque = new ArrayList<>();
        this.getPokePouvoir(terrain, pokeQuiAttaque);
        if(pokeQuiAttaque.isEmpty()){
            return false;
        }
        for (int i = 0; i<pokeQuiAttaque.size();i++)
        {
            descriptionPouvoir(pokeQuiAttaque);
            Affichage.afficher("Choisissez un pouvoir a utiliser :      " + Affichage.selectionPokemon(pokeQuiAttaque) + "\n");
            try {
                int pokemonAttaquant = selection(pokeQuiAttaque.size());
                pokeQuiAttaque.get(pokemonAttaquant).getPouvoir().utiliser(terrain, this, adversaire,pokeQuiAttaque.get(pokemonAttaquant),pokemonAttaquant);
                pokeQuiAttaque.remove(pokemonAttaquant);
                if (this.mort(terrain)|| adversaire.mort(terrain)){
                    if(Jeu.getPokemonAvecPouvoir().get(pokeQuiAttaque.get(pokemonAttaquant)) != null){
                        pokeQuiAttaque.get(pokemonAttaquant).getPouvoir().annulerPouvoir(terrain, this, adversaire,pokeQuiAttaque.get(pokemonAttaquant));
                    }
                    return true;
                }
            }
            catch (IndexOutOfBoundsException e)
            {
                return false;
            }
        }
        return false;
    }

    @Override
    public int selection(int borne) {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt() - 1;
    }
    public void descriptionPouvoir(List<Pokemon> list){
        Scanner scan = new Scanner(System.in);
        Affichage.afficher("Voulez vous la description d'un des pouvoirs ?   (o/n)");
        char reponse = scan.next().charAt(0);
        if (reponse == 'o'){
            Affichage.afficher("Quel description voulez vous lire ?      "+Affichage.selectionPokemon(list));
            int descriptionSelectionner = this.selection(list.size());
            Affichage.afficher(list.get(descriptionSelectionner).getPouvoir().getDesc() + "\n");
        }
    }
}
