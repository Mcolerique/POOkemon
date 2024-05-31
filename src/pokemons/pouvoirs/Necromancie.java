package pokemons.pouvoirs;

import jeu.Jeu;
import joueurs.Joueur;
import joueurs.Terrain;
import affichage.Affichage;
import pokemons.Pokemon;

import java.util.List;

public class Necromancie extends Pouvoir {

    //Constructeur
    public Necromancie() {
        super("Necromancie");
    }

    //Methodes
    @Override
    public void utiliser(Terrain terrain, Joueur allie, Joueur adversaire, Pokemon pokemon, int intPokemon) {
        // affichage de la défausse du joueur
        List<Pokemon> defausse = allie.getDefausse().getDefausse();
        Affichage.afficheDefausse(allie, defausse);
        // choix du pokemon à ressusciter
        int choix = allie.selection(defausse);
        Pokemon pokemonARessusciter = defausse.get(choix);
        // tuer le pokemon avec le pouvoir
        terrain.retirerPokemon(allie, intPokemon);
        // ressusciter le pokemon choisi
        allie.getDefausse().retirerPokemon(choix);
        // ajouter le pokemon ressucité sur le terrain
        terrain.placerPokemons(allie, pokemonARessusciter);
    }

    @Override
    public void annulerPouvoir(Terrain terrain, Joueur allie, Joueur adversaire, Pokemon pokemon) {
        Jeu.getPokemonAvecPouvoir().remove(pokemon);
    }
}
