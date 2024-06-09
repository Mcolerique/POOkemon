package pokemons.pouvoirs;

import affichage.Musique;
import jeu.Jeu;
import joueurs.Joueur;
import joueurs.Terrain;
import affichage.Affichage;
import pokemons.Pokemon;

import java.util.List;

public class Necromancie extends Pouvoir {

    Musique m_bruitage = new Musique("musiques/bruitage/necromancie.wav");

    //Constructeur
    public Necromancie() {
        super("Necrom","Nécromancie, à utilisation unique : le Pokémon choisit un Pokémon mort dans sa défausse. Le Pokémon meurt et est remplacé par le Pokémon choisi dans la défausse.");
    }

    //Methodes
    @Override
    public void utiliser(Terrain terrain, Joueur allie, Joueur adversaire, Pokemon pokemon, int intPokemon) {
        m_bruitage.play();
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

            // Redonner toute sa vie au pokemon choisi
            pokemonARessusciter.soigner(pokemonARessusciter.getPvMax());

            // Debug: Afficher l'état avant de retirer le Pokémon
            System.out.println("Avant de retirer le Pokémon avec pouvoir:");
            System.out.println("Terrain: " + terrain.getPokemonsJoueur(allie));
            System.out.println("Défausse: " + allie.getDefausse().getDefausse());

            // Tuer le pokemon avec le pouvoir
            terrain.retirerPokemon(allie, intPokemon);

            // Ajouter le pokemon sacrifié à la défausse
            allie.getDefausse().ajouterPokemon(pokemon);

            // Debug: Afficher l'état après avoir retiré le Pokémon
            System.out.println("Après avoir retiré le Pokémon avec pouvoir:");
            System.out.println("Terrain: " + terrain.getPokemonsJoueur(allie));
            System.out.println("Défausse: " + allie.getDefausse().getDefausse());

            // Ressusciter le pokemon choisi
            allie.getDefausse().retirerPokemon(choix);

            // Ajouter le pokemon ressuscité sur le terrain
            terrain.placerPokemons(allie, pokemonARessusciter);

            // Debug: Afficher l'état final
            System.out.println("Après avoir ressuscité et placé le Pokémon choisi:");
            System.out.println("Terrain: " + terrain.getPokemonsJoueur(allie));
            System.out.println("Défausse: " + allie.getDefausse().getDefausse());
        }
    }

    @Override
    public void annulerPouvoir(Terrain terrain, Joueur allie, Joueur adversaire, Pokemon pokemon) {
        Jeu.getPokemonAvecPouvoir().remove(pokemon);
    }
}
