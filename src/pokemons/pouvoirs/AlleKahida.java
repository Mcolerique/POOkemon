package pokemons.pouvoirs;

import affichage.Affichage;
import jeu.Jeu;
import joueurs.Joueur;
import joueurs.Terrain;
import pokemons.Pokemon;

import java.util.ArrayList;
import java.util.List;

public class AlleKahida extends Pouvoir {

    //Constructeur
    public AlleKahida() {
        super("AllKahida","AlleKahida, utilisable à chaque tour : Permet de kamikaze un pokemon allié ou soit même");
    }

    //Methodes redefinies
    @Override
    public void utiliser(Terrain terrain, Joueur allie, Joueur adversaire, Pokemon pokemon, int intPokemon) {
        try {
            List<Pokemon> pokeAttaquant = new ArrayList<>();
            pokeAttaquant.addAll(terrain.getPokemonsJoueur(allie));
            Affichage.afficher("Choisissez un pokemon à sacrifier"+Affichage.selectionPokemon(pokeAttaquant));
            int pokemonAttaquant = allie.selection(pokeAttaquant.size());

            List<Pokemon> pokeAttaquer = new ArrayList<>();
            pokeAttaquer.addAll(terrain.getPokemonsJoueur(adversaire));
            Affichage.afficher("Choisissez un pokemon à attaquer"+Affichage.selectionPokemon(pokeAttaquer));
            int pokemonAttaque = allie.selection(pokeAttaquer.size());

            //Défausser les 2 pokemons avec retirerPokemon()
            terrain.retirerPokemon(allie, pokemonAttaquant);
            terrain.retirerPokemon(adversaire, pokemonAttaque);
        }
        catch (IndexOutOfBoundsException e){

        }
    }
    @Override
    public void utilisertest(Terrain terrain, Joueur allie, Joueur adversaire, Pokemon pokemon, int intPokemon, int choix) {
        try {
            //Défausser les 2 pokemons avec retirerPokemon()
            terrain.retirerPokemon(allie, choix);
            terrain.retirerPokemon(adversaire, choix);
        }
        catch (IndexOutOfBoundsException e){

        }
    }

    @Override
    public void annulerPouvoir(Terrain terrain, Joueur allie, Joueur adversaire, Pokemon pokemon) {
        Jeu.getPokemonAvecPouvoir().remove(pokemon);
    }
}
