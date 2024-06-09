package joueurs;

import affichage.Affichage;
import jeu.Jeu;
import pokemons.Pokemon;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Ordinateur extends Joueur {

    // Constructeur
    public Ordinateur(String nom, int taillePioche) {
        super(nom, taillePioche);
    }

    // Méthodes redéfinies
    @Override
    public void placerPokemon(Terrain terrain) {
        try {
            // Attendre 2 secondes
            Thread.sleep(2000);
            // Tant que le terrain n'est pas rempli avec des Pokémon
            while (terrain.getPokemonsJoueur(this).size() < this.m_tailleTerrain) {
                // Placer un Pokémon sur le terrain
                terrain.placerPokemons(this, this.getPokemon(0));
            }
            Affichage.afficher("Le joueur n°2 a rempli son terrain\n");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean attaquer(Terrain terrain, Joueur adversaire) {
        // Pour chaque Pokémon de l'ordinateur sur le terrain
        for (int i = 0; i < terrain.getNbPokemonsJoueur(this); i++) {
            // Trouver les cibles potentielles
            List<Integer> pokemonAttaquer = trouverCiblesPotentielles(terrain, adversaire, i);
            // Choisir une cible et attaquer
            int pokemonAttaque = choisirCibleEtAttaquer(pokemonAttaquer, i, terrain, adversaire);

            // Vérifier si l'adversaire est mort après l'attaque
            if (adversaire.mort(terrain, pokemonAttaque)) {
                return true;
            }
        }
        return false;
    }

    private List<Integer> trouverCiblesPotentielles(Terrain terrain, Joueur adversaire, int indexAttaquant) {
        List<Integer> pokemonAttaquer = new ArrayList<>();

        // Ajouter les cibles avec avantage
        for (int j = 0; j < terrain.getNbPokemonsJoueur(adversaire); j++) {
            if (terrain.getPokemon(this, indexAttaquant).avantageSur(terrain.getPokemon(adversaire, j))) {
                pokemonAttaquer.add(j);
            }
        }

        // Si pas de cibles avec avantage, ajouter toutes les cibles
        if (pokemonAttaquer.isEmpty()) {
            for (int j = 0; j < terrain.getNbPokemonsJoueur(adversaire); j++) {
                pokemonAttaquer.add(j);
            }
        }

        return pokemonAttaquer;
    }

    private int choisirCibleEtAttaquer(List<Integer> pokemonAttaquer, int indexAttaquant, Terrain terrain, Joueur adversaire) {
        if (pokemonAttaquer.size() > 1) {
            // Filtrer les cibles par PV
            pokemonAttaquer = filtrerCiblesParPv(pokemonAttaquer, terrain, adversaire);

            // Si plusieurs cibles restent, choisir une aléatoirement
            if (pokemonAttaquer.size() > 1) {
                int randomIndex = this.selection(pokemonAttaquer.size());
                return attaqueJoueur(randomIndex, indexAttaquant, terrain, adversaire, pokemonAttaquer);
            }
        }
        // Attaquer la seule cible restante
        return attaqueJoueur(0, indexAttaquant, terrain, adversaire, pokemonAttaquer);
    }

    private List<Integer> filtrerCiblesParPv(List<Integer> pokemonAttaquer, Terrain terrain, Joueur adversaire) {
        // Copier la liste originale pour éviter de modifier la liste en cours d'itération
        List<Integer> result = new ArrayList<>(pokemonAttaquer);

        // Utiliser une boucle classique pour éviter les problèmes d'index
        for (int i = 0; i < result.size() - 1; i++) {
            int firstIndex = result.get(i);
            int secondIndex = result.get(i + 1);

            if (firstIndex < terrain.getNbPokemonsJoueur(adversaire) && secondIndex < terrain.getNbPokemonsJoueur(adversaire)) {
                if (terrain.getPokemon(adversaire, firstIndex).getPv() < terrain.getPokemon(adversaire, secondIndex).getPv()) {
                    result.remove(i + 1);
                } else {
                    result.remove(i);
                }
                i--; // Réajuster l'index après suppression
            }
        }
        return result;
    }

    private int attaqueJoueur(int index, int i, Terrain terrain, Joueur adversaire, List<Integer> pokemonAttaquer) {
        int pokemonAttaque = pokemonAttaquer.get(index);
        try {
            if (i < terrain.getNbPokemonsJoueur(this) && pokemonAttaque < terrain.getNbPokemonsJoueur(adversaire)) {
                // Attendre 1 seconde
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

        if (pokeQuiAttaque.isEmpty()) {
            return false;
        }

        try {
            for (int i = 0; i < pokeQuiAttaque.size(); i++) {
                int pokemonAttaquant = selection(pokeQuiAttaque.size() + 1);

                if (pokemonAttaquant >= 0 && pokemonAttaquant < pokeQuiAttaque.size()) {
                    // Utiliser le pouvoir du Pokémon
                    pokeQuiAttaque.get(pokemonAttaquant).getPouvoir().utiliser(terrain, this, adversaire, pokeQuiAttaque.get(pokemonAttaquant), pokemonAttaquant);
                    Affichage.afficher(terrain.getPokemon(this, pokemonAttaquant).getNom() + " a utilisé " + terrain.getPokemon(this, pokemonAttaquant).getNomPouvoir());

                    // Vérifier si l'un des joueurs est mort après l'utilisation du pouvoir
                    if (this.mort(terrain) || adversaire.mort(terrain)) {
                        if (Jeu.getPokemonAvecPouvoir().get(pokeQuiAttaque.get(pokemonAttaquant)) != null) {
                            pokeQuiAttaque.get(pokemonAttaquant).getPouvoir().annulerPouvoir(terrain, this, adversaire, pokeQuiAttaque.get(pokemonAttaquant));
                        }
                        return true;
                    }
                }
                pokeQuiAttaque.remove(pokemonAttaquant);
            }
        } catch (IndexOutOfBoundsException e) {
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
