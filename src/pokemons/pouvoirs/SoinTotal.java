package pokemons.pouvoirs;

import affichage.Affichage;
import jeu.Jeu;
import joueurs.Joueur;
import joueurs.Terrain;
import pokemons.Pokemon;

import java.util.ArrayList;
import java.util.Scanner;

public class SoinTotal extends Pouvoir{
    public SoinTotal(){
        super("Soin total");
    }

    @Override
    public void utiliser(Terrain terrain, Joueur allie, Joueur adversaire, Pokemon pokemon,int intPokemon) {
        ArrayList<Pokemon> pokeSoigner = new ArrayList<>();
        pokeSoigner.addAll(terrain.getM_pokemonsJoueur(allie));
        System.out.print("choisissez un pokemon a soigner");
        Affichage.selectionPokemon(pokeSoigner);
        Affichage.affichePokemon(terrain.getM_pokemonsJoueur(allie));
        int pokemonSoigne = allie.selection(pokeSoigner);
        pokeSoigner.get(pokemonSoigne).soigner(pokeSoigner.get(pokemonSoigne).getPvMax());
        this.m_utilise = true;
    }

    @Override
    public void annulerPouvoir(Terrain terrain, Joueur allie, Joueur adversaire, Pokemon pokemon) {
        Jeu.getM_pokemonAvecPouvoir().remove(pokemon);
    }
}
