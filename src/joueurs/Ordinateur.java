package joueurs;

import affichage.Affichage;
import jeu.Jeu;
import pokemons.Pokemon;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Ordinateur extends Joueur {

    //Constructeur
    public Ordinateur(String nom, int taillePioche) {
        super(nom, taillePioche);
    }


    //Methodes refefinies
    @Override
    public void placerPokemon(Terrain terrain) {
        while (terrain.getPokemonsJoueur(this).size() < this.m_tailleTerrain) {
            terrain.placerPokemons(this, 0);
        }
    }

    @Override
    public boolean attaquer(Terrain terrain, Joueur adversaire) {
        List<Integer> pokemonAttaquer = new ArrayList<>();
        int pokemonAttaque;
        for (int i = 0; i < terrain.getNbPokemonsJoueur(this); i++) {
            for (int j = 0; j < terrain.getNbPokemonsJoueur(adversaire); j++) {
                if (terrain.getPokemon(this, i).avantageSur(terrain.getPokemon(adversaire, j))) {
                    pokemonAttaquer.add(j);
                }
            }

            if (pokemonAttaquer.isEmpty()) {
                for (int j = 0; j < terrain.getNbPokemonsJoueur(adversaire); j++) {
                    pokemonAttaquer.add(j);
                }
            }

            if (pokemonAttaquer.size() > 1) {
                for (int j = 0; j < pokemonAttaquer.size() - 1; j++) {
                    int firstIndex = pokemonAttaquer.get(j);
                    int secondIndex = pokemonAttaquer.get(j + 1);

                    if (firstIndex < terrain.getNbPokemonsJoueur(adversaire) && secondIndex < terrain.getNbPokemonsJoueur(adversaire)) {
                        if (terrain.getPokemon(adversaire, firstIndex).getPv() <= terrain.getPokemon(adversaire, secondIndex).getPv()) {
                            pokemonAttaquer.remove(j + 1);
                        }
                    }
                }

                if (pokemonAttaquer.size() > 1) {
                    Random random = new Random();
                    int randomIndex = random.nextInt(pokemonAttaquer.size());
                    pokemonAttaque = attaqueJoueur(randomIndex, i, terrain, adversaire, pokemonAttaquer);
                } else {
                    pokemonAttaque = attaqueJoueur(0, i, terrain, adversaire, pokemonAttaquer);
                }
            } else {
                pokemonAttaque = attaqueJoueur(0, i, terrain, adversaire, pokemonAttaquer);
            }

            // Si le pokemon attaqué est mort, le défausser
            if (adversaire.mort(terrain, pokemonAttaque)) {
                return true;
            }
            pokemonAttaquer.clear();
        }
        return false;
    }

    private int attaqueJoueur(int index, int i, Terrain terrain, Joueur adversaire, List<Integer> pokemonAttaquer) {
        int pokemonAttaque;
        pokemonAttaque = pokemonAttaquer.get(index);
        if (i < terrain.getNbPokemonsJoueur(this) && pokemonAttaque < terrain.getNbPokemonsJoueur(adversaire)) {
            Affichage.afficher(terrain.getPokemon(this, i).getNom() + " a attaqué " + terrain.getPokemon(adversaire, pokemonAttaque).getNom());
            terrain.getPokemon(this, i).attaquer(terrain.getPokemon(adversaire, pokemonAttaque));
        }
        return pokemonAttaque;
    }


    @Override
    public boolean utiliserPouvoir(Terrain terrain, Joueur adversaire) {
        List<Pokemon> pokeQuiAttaque = new ArrayList<>();
        for(int i =0; i<terrain.getNbPokemonsJoueur(this);i++)
        {
            if (!terrain.getPokemonsJoueur(this).isEmpty()){
                if (terrain.getPokemonsJoueur(this).get(i).getNomPouvoir()!="Aucun" && !terrain.getPokemonsJoueur(this).get(i).getPouvoir().getUtilise())
                {
                    pokeQuiAttaque.add(terrain.getPokemonsJoueur(this).get(i));
                }
            }
        }
        if(pokeQuiAttaque.isEmpty()){
            return false;
        }
        for (int i = 0; i<pokeQuiAttaque.size();i++){
            int pokemonAttaquant = selection(pokeQuiAttaque);
            pokeQuiAttaque.remove(pokemonAttaquant);
            pokeQuiAttaque.get(pokemonAttaquant).getPouvoir().utiliser(terrain, this, adversaire,pokeQuiAttaque.get(pokemonAttaquant),pokemonAttaquant);
            Affichage.afficher(terrain.getPokemon(this,i).getNom()+" a utilisé "+terrain.getPokemon(this,i).getNomPouvoir());
            if (this.mort(terrain)|| adversaire.mort(terrain)){
                if(Jeu.getPokemonAvecPouvoir().get(pokeQuiAttaque.get(pokemonAttaquant)) != null){
                    pokeQuiAttaque.get(pokemonAttaquant).getPouvoir().annulerPouvoir(terrain, this, adversaire,pokeQuiAttaque.get(pokemonAttaquant));
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public int selection(List<Pokemon> list) {
        Random random = new Random();
        return random.nextInt(list.size());
    }
}
