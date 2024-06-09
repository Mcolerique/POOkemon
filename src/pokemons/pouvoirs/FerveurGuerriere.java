package pokemons.pouvoirs;

import affichage.Affichage;
import jeu.Jeu;
import joueurs.Joueur;
import joueurs.Terrain;
import pokemons.Pokemon;

import java.util.ArrayList;

public class FerveurGuerriere extends Pouvoir {

    //Constructeur
    public FerveurGuerriere(){
        super("Ferv guer","Ferveur guerrière, à utilisation unique : le Pokémon choisit un Pokémon de son camp (éventuellement lui-même). Jusqu'à la fin de la partie ou à la mort du Pokémon choisi, les attaques de celui-ci infligent 10 dégâts de plus.");
    }


    //Methodes redefinies
    @Override
    public void utiliser(Terrain terrain, Joueur allie, Joueur adversaire, Pokemon pokemon, int intPokemon) {
        ArrayList<Pokemon> pokeAAmeliorer = new ArrayList<>();
        pokeAAmeliorer.addAll(terrain.getPokemonsJoueur(allie));
        Affichage.afficher("choisissez un pokemon a ameliorer : " + Affichage.selectionPokemon(pokeAAmeliorer));
        int pokemonAmeliorer = allie.selection(pokeAAmeliorer.size());
        terrain.getPokemonsJoueur(allie).get(pokemonAmeliorer ).modifAttaque(10);
        Terrain.getPouvoirsUtilises().put(this,terrain.getPokemonsJoueur(allie).get(pokemonAmeliorer ));
        this.m_utilise = true;
    }
    public void utilisertest(Terrain terrain, Joueur allie, Joueur adversaire, Pokemon pokemon, int intPokemon, int choix) {
        terrain.getPokemonsJoueur(allie).get(choix).modifAttaque(10);
        Terrain.getPouvoirsUtilises().put(this,terrain.getPokemonsJoueur(allie).get(choix));
        this.m_utilise = true;
        System.out.println("Le joueur a utilisé le pouvoir FerveurGuerriere");
        System.out.println(m_utilise);
    }


    @Override
    public void annulerPouvoir(Terrain terrain, Joueur allie, Joueur adversaire, Pokemon pokemon) {
        Terrain.getPouvoirsUtilises().get(this).modifAttaque(-10);
        Jeu.getPokemonAvecPouvoir().remove(pokemon);
        Terrain.getPouvoirsUtilises().remove(this);
    }
}
