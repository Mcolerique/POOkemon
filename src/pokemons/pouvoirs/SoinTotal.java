package pokemons.pouvoirs;

import affichage.Affichage;
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
    public void utiliser(Terrain terrain, Joueur allie, Joueur adversaire, Pokemon pokemon) {
        ArrayList<Pokemon> pokeSoigner = new ArrayList<>();
        pokeSoigner.addAll(terrain.getM_pokemonsJoueur(allie));
        System.out.print("choisissez un pokemon a soigner");
        for (int j = 0; j<pokeSoigner.size();j++)
        {
            if (j != pokeSoigner.size()-1)
            {
                System.out.print((j+1)+"."+pokeSoigner.get(j).getNom() + "/");
            }
            else {System.out.println((j+1)+"."+pokeSoigner.get(j).getNom()+")");}
        }
        Affichage.affichePokemon(terrain.getM_pokemonsJoueur(allie));
        int pokemonSoigner;
        Scanner scanner = new Scanner(System.in);
        pokemonSoigner = scanner.nextInt() - 1;
        pokeSoigner.get(pokemonSoigner).soigner(pokeSoigner.get(pokemonSoigner).getPvMax());
        this.m_utilise = true;
    }

    @Override
    public void annulerPouvoir(Terrain terrain, Joueur allie, Joueur adversaire, Pokemon pokemon) {
    }
}
