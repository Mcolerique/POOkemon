package pokemons.pouvoirs;

import jeu.Jeu;
import joueurs.Joueur;
import joueurs.Terrain;
import pokemons.Pokemon;

public class Confusion extends Pouvoir {

    //Constructeur
    public Confusion() {
        super("Confusion","Confusion, utilisable à chaque tour : le joueur adverse doit défausser toutes les cartes de sa main dans sa pioche, mélanger sa pioche et piocher 5 Pokémons.");
    }


    //Methodes redefinies
    @Override
    public void utiliser(Terrain terrain, Joueur allie, Joueur adversaire, Pokemon pokemon,int intPokemon) {
        System.out.println("Confusion !");
        //Vide la main de l'adversaire et la met dans la pioche de l'adversaire
        adversaire.viderMain();
        //Remplit la main de l'adversaire
        adversaire.remplirMain();
    }

    @Override
    public void utilisertest(Terrain terrain, Joueur allie, Joueur adversaire, Pokemon pokemon, int intPokemon, int choix) {
        System.out.println("Confusion !");
        //Vide la main de l'adversaire et la met dans la pioche de l'adversaire
        adversaire.viderMain();
        //Remplit la main de l'adversaire
        adversaire.remplirMain();
    }


    @Override
    public void annulerPouvoir(Terrain terrain, Joueur allie, Joueur adversaire, Pokemon pokemon) {
        Jeu.getPokemonAvecPouvoir().remove(pokemon);
    }
}
