package pokemons.pouvoirs;

import affichage.Musique;
import jeu.Jeu;
import joueurs.Joueur;
import joueurs.Terrain;
import pokemons.Pokemon;

import java.lang.management.MemoryUsage;

public class SoinDeZone extends Pouvoir {

    Musique m_bruitage = new Musique("musiques/bruitage/soin.wav");

    //Constructeur
    public SoinDeZone(){
        super("Soin zone","Soin de zone, utilisable à chaque tour : chaque Pokémon de son camp regagne 10 points de vie.");
    }


    //Methodes redefinies
    @Override
    public void utiliser(Terrain terrain, Joueur allie, Joueur adversaire, Pokemon pokemon, int intPokemon) {
        m_bruitage.play();
        for (int i = 0; i<terrain.getPokemonsJoueur(allie).size(); i++){
            terrain.getPokemon(allie,i).soigner(10);
        }
    }

    @Override
    public void utilisertest(Terrain terrain, Joueur allie, Joueur adversaire, Pokemon pokemon, int intPokemon, int choix) {
        for (int i = 0; i<terrain.getPokemonsJoueur(allie).size(); i++){
            terrain.getPokemon(allie,i).soigner(10);
        }
    }

    @Override
    public void annulerPouvoir(Terrain terrain, Joueur allie, Joueur adversaire, Pokemon pokemon) {
        Jeu.getPokemonAvecPouvoir().remove(pokemon);
    }
}
