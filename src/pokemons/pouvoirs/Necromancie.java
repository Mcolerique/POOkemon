package pokemons.pouvoirs;

import affichage.Musique;
import jeu.Jeu;
import joueurs.Joueur;
import joueurs.Terrain;
import affichage.Affichage;
import pokemons.Pokemon;

import java.util.List;

/**
 * La classe Necromancie représente un pouvoir spécial d'un Pokémon.
 */
public class Necromancie extends Pouvoir {

    Musique m_bruitage = new Musique("musiques/bruitage/necromancie.wav");


    //CONSTRUCTEUR

    /**
     * Constructeur de la classe Necromancie, qui initialise le pouvoir avec un nom et une description.
     */
    public Necromancie() {
        super("Necrom","Nécromancie, à utilisation unique : le Pokémon choisit un Pokémon mort dans sa défausse. Le Pokémon meurt et est remplacé par le Pokémon choisi dans la défausse.");
    }




    //METHODES REDEFINIES

    /**
     * Utilise le pouvoir Necromancie.
     *
     * @param terrain Le terrain de jeu.
     * @param allie Le joueur allié utilisant le pouvoir.
     * @param adversaire Le joueur adverse.
     * @param pokemon Le Pokémon utilisant le pouvoir.
     * @param intPokemon L'indice du Pokémon utilisant le pouvoir.
     */
    @Override
    public void utiliser(Terrain terrain, Joueur allie, Joueur adversaire, Pokemon pokemon, int intPokemon) {
        m_bruitage.play();
        List<Pokemon> defausse = allie.getDefausse().getDefausse();
        if(!defausse.isEmpty()) {
            Affichage.afficheDefausse(allie, defausse);
            //Choix du pokemon à ressusciter
            int choix = allie.selection(defausse.size());
            Pokemon pokemonARessusciter = defausse.get(choix);
            //Redonner toute sa vie au pokemon choisi
            pokemonARessusciter.soigner(pokemonARessusciter.getPvMax());
            //Tuer le pokemon avec le pouvoir
            terrain.retirerPokemon(allie, intPokemon);
            //Ressusciter le pokemon choisi
            allie.getDefausse().retirerPokemon(choix);
            //Ajouter le pokemon ressucité sur le terrain
            terrain.placerPokemons(allie, pokemonARessusciter);
        }
        else {
            Affichage.afficher("Aucun pokemon dans la défausse");
        }
    }



    /**
     * Utilise le pouvoir Necromancie dans un test.
     *
     * @param terrain Le terrain de jeu.
     * @param allie Le joueur allié utilisant le pouvoir.
     * @param adversaire Le joueur adverse.
     * @param pokemon Le Pokémon utilisant le pouvoir.
     * @param intPokemon L'indice du Pokémon utilisant le pouvoir.
     * @param choix L'indice du Pokémon à ressusciter dans la défausse.
     */
    @Override
    public void utilisertest(Terrain terrain, Joueur allie, Joueur adversaire, Pokemon pokemon, int intPokemon, int choix) {
        List<Pokemon> defausse = allie.getDefausse().getDefausse();
        if (!defausse.isEmpty()) {
            Pokemon pokemonARessusciter = defausse.get(choix);

            // Redonner toute sa vie au pokemon choisi
            pokemonARessusciter.soigner(pokemonARessusciter.getPvMax());

            System.out.println("Avant de retirer le Pokémon avec pouvoir:");
            System.out.println("Terrain: " + terrain.getPokemonsJoueur(allie));
            System.out.println("Défausse: " + allie.getDefausse().getDefausse());

            // Tuer le pokemon avec le pouvoir
            terrain.retirerPokemon(allie, intPokemon);

            // Ajouter le pokemon sacrifié à la défausse
            allie.getDefausse().ajouterPokemon(pokemon);

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



    /**
     * Annule l'effet du pouvoir Necromancie.
     *
     * @param terrain Le terrain de jeu.
     * @param allie Le joueur allié utilisant le pouvoir.
     * @param adversaire Le joueur adverse.
     * @param pokemon Le Pokémon utilisant le pouvoir.
     */
    @Override
    public void annulerPouvoir(Terrain terrain, Joueur allie, Joueur adversaire, Pokemon pokemon) {
        Jeu.getPokemonAvecPouvoir().remove(pokemon);
    }
}