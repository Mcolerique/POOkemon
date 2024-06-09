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
        super("Necrom","Nécromancie, à utilisation unique : le Pokémon choisit un Pokémon mort dans sa défausse. Le Pokémon meurt et est remplacé par le Pokémon choisi dans la défausse.");
    }

    //Methodes
    @Override
    public void utiliser(Terrain terrain, Joueur allie, Joueur adversaire, Pokemon pokemon, int intPokemon) {
        // affichage de la défausse du joueur
        List<Pokemon> defausse = allie.getDefausse().getDefausse();
        if(!defausse.isEmpty()) {
            Affichage.afficheDefausse(allie, defausse);
            // choix du pokemon à ressusciter
            int choix = allie.selection(defausse.size());
            Pokemon pokemonARessusciter = defausse.get(choix);
            // redonner toute sa vie au pokemon choisi
            pokemonARessusciter.soigner(pokemonARessusciter.getPvMax());
            // tuer le pokemon avec le pouvoir
            terrain.retirerPokemon(allie, intPokemon);
            // ressusciter le pokemon choisi
            allie.getDefausse().retirerPokemon(choix);
            // ajouter le pokemon ressucité sur le terrain
            terrain.placerPokemons(allie, pokemonARessusciter);
        }
        else {
            Affichage.afficher("Aucun pokemon dans la défausse");
        }
    }

    @Override
    public void utilisertest(Terrain terrain, Joueur allie, Joueur adversaire, Pokemon pokemon, int intPokemon, int choix) {
        // affichage de la défausse du joueur
        List<Pokemon> defausse = allie.getDefausse().getDefausse();
        if (!defausse.isEmpty()) {
            Pokemon pokemonARessusciter = defausse.get(choix);
            // redonner toute sa vie au pokemon choisi
            pokemonARessusciter.soigner(pokemonARessusciter.getPvMax());
            // tuer le pokemon avec le pouvoir
            terrain.retirerPokemon(allie, intPokemon);
            // ressusciter le pokemon choisi
            allie.getDefausse().retirerPokemon(choix);
            // ajouter le pokemon ressucité sur le terrain
            terrain.placerPokemons(allie, pokemonARessusciter);
        }
    }

    @Override
    public void annulerPouvoir(Terrain terrain, Joueur allie, Joueur adversaire, Pokemon pokemon) {
        Jeu.getPokemonAvecPouvoir().remove(pokemon);
    }
}
