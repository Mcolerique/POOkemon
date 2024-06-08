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
        try {
            Thread.sleep(2000);
            while (terrain.getPokemonsJoueur(this).size() < this.m_tailleTerrain) {
                terrain.placerPokemons(this, this.getPokemon(0));
            }
            Affichage.afficher("Le joueur n°2 a rempli son terrain\n");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean attaquer(Terrain terrain, Joueur adversaire) {
        for (int i = 0; i < terrain.getNbPokemonsJoueur(this); i++) {
            List<Integer> pokemonAttaquer = trouverCiblesPotentielles(terrain, adversaire, i);
            int pokemonAttaque = choisirCibleEtAttaquer(pokemonAttaquer, i, terrain, adversaire);

            if (adversaire.mort(terrain, pokemonAttaque)) {
                return true;
            }
        }
        return false;
    }
    private List<Integer> trouverCiblesPotentielles(Terrain terrain, Joueur adversaire, int indexAttaquant) {
        List<Integer> pokemonAttaquer = new ArrayList<>();

        for (int j = 0; j < terrain.getNbPokemonsJoueur(adversaire); j++) {
            if (terrain.getPokemon(this, indexAttaquant).avantageSur(terrain.getPokemon(adversaire, j))) {
                pokemonAttaquer.add(j);
            }
        }

        if (pokemonAttaquer.isEmpty()) {
            for (int j = 0; j < terrain.getNbPokemonsJoueur(adversaire); j++) {
                pokemonAttaquer.add(j);
            }
        }

        return pokemonAttaquer;
    }
    private int choisirCibleEtAttaquer(List<Integer> pokemonAttaquer, int indexAttaquant, Terrain terrain, Joueur adversaire) {
        if (pokemonAttaquer.size() > 1) {
            pokemonAttaquer = filtrerCiblesParPv(pokemonAttaquer, terrain, adversaire);

            if (pokemonAttaquer.size() > 1) {
                int randomIndex = this.selection(pokemonAttaquer.size());
                return attaqueJoueur(randomIndex, indexAttaquant, terrain, adversaire, pokemonAttaquer);
            }
        }
        return attaqueJoueur(0, indexAttaquant, terrain, adversaire, pokemonAttaquer);
    }
    private List<Integer> filtrerCiblesParPv(List<Integer> pokemonAttaquer, Terrain terrain, Joueur adversaire) {
        for (int j = 0; j < pokemonAttaquer.size() - 1; j++) {
            int firstIndex = pokemonAttaquer.get(j);
            int secondIndex = pokemonAttaquer.get(j + 1);

            if (firstIndex < terrain.getNbPokemonsJoueur(adversaire) && secondIndex < terrain.getNbPokemonsJoueur(adversaire)) {
                if (terrain.getPokemon(adversaire, firstIndex).getPv() < terrain.getPokemon(adversaire, secondIndex).getPv()) {
                    pokemonAttaquer.remove(secondIndex);
                }
                else {
                    pokemonAttaquer.remove(firstIndex);
                }
            }
        }
        return pokemonAttaquer;
    }

    private int attaqueJoueur(int index, int i, Terrain terrain, Joueur adversaire, List<Integer> pokemonAttaquer) {
        int pokemonAttaque;
        pokemonAttaque = pokemonAttaquer.get(index);
        try {
            if (i < terrain.getNbPokemonsJoueur(this) && pokemonAttaque < terrain.getNbPokemonsJoueur(adversaire)) {
                Thread.sleep(1000);
                Affichage.afficher(terrain.getPokemon(this, i).getNom() + " a attaqué " + terrain.getPokemon(adversaire, pokemonAttaque).getNom());
                terrain.getPokemon(this, i).attaquer(terrain.getPokemon(adversaire, pokemonAttaque));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return pokemonAttaque;
    }


    @Override
    public boolean utiliserPouvoir(Terrain terrain, Joueur adversaire) {
        List<Pokemon> pokeQuiAttaque = new ArrayList<>();
        this.getPokePouvoir(terrain, pokeQuiAttaque);
        if(pokeQuiAttaque.isEmpty()){
            return false;
        }
        try {
            for (int i = 0; i<pokeQuiAttaque.size();i++){
                int pokemonAttaquant = selection(pokeQuiAttaque.size()+1);
                if (pokemonAttaquant >= 0 && pokemonAttaquant < pokeQuiAttaque.size()) {
                    pokeQuiAttaque.get(pokemonAttaquant).getPouvoir().utiliser(terrain, this, adversaire,pokeQuiAttaque.get(pokemonAttaquant),pokemonAttaquant);
                    Affichage.afficher(terrain.getPokemon(this,pokemonAttaquant).getNom()+" a utilisé "+terrain.getPokemon(this,pokemonAttaquant).getNomPouvoir());
                    if (this.mort(terrain)|| adversaire.mort(terrain)){
                        if(Jeu.getPokemonAvecPouvoir().get(pokeQuiAttaque.get(pokemonAttaquant)) != null){
                            pokeQuiAttaque.get(pokemonAttaquant).getPouvoir().annulerPouvoir(terrain, this, adversaire,pokeQuiAttaque.get(pokemonAttaquant));
                        }
                        return true;
                    }
                }
                pokeQuiAttaque.remove(pokemonAttaquant);
            }
        }
        catch (IndexOutOfBoundsException e){
            return false;
        }
        return false;
    }

    @Override
    public int selection(int borne) {
        Random random = new Random();
        return random.nextInt(borne);
    }
}
