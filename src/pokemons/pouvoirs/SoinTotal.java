package pokemons.pouvoirs;

import affichage.Affichage;
import jeu.Jeu;
import joueurs.Joueur;
import joueurs.Terrain;
import pokemons.Pokemon;

import java.util.ArrayList;

public class SoinTotal extends Pouvoir {

    //Constructeur
    public SoinTotal(){
        super("Soin total","Soin total, à utilisation unique : le Pokémon choisit un Pokémon de son camp (éventuellement lui-même). Celui-ci regagne toute sa vie.");
    }


    //Methodes redefinies
    @Override
    public void utiliser(Terrain terrain, Joueur allie, Joueur adversaire, Pokemon pokemon,int intPokemon) {
        ArrayList<Pokemon> pokeSoigner = new ArrayList<>();
        pokeSoigner.addAll(terrain.getPokemonsJoueur(allie));
        try{
            System.out.print("choisissez un pokemon a soigner : "+Affichage.selectionPokemon(pokeSoigner));
            int pokemonSoigne = allie.selection(pokeSoigner.size());
            pokeSoigner.get(pokemonSoigne).soigner(pokeSoigner.get(pokemonSoigne).getPvMax());
            this.m_utilise = true;
        }
        catch (IndexOutOfBoundsException e){

        }
    }

    @Override
    public void annulerPouvoir(Terrain terrain, Joueur allie, Joueur adversaire, Pokemon pokemon) {
        Jeu.getPokemonAvecPouvoir().remove(pokemon);
    }
}
