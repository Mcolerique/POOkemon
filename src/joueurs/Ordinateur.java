package joueurs;

import affichage.*;
import jeu.Jeu;
import pokemons.Pokemon;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * La classe Ordinateur représente un joueur contrôlé par l'ordinateur dans le jeu Pokémon.
 */
public class Ordinateur extends Joueur {

    //CONSTRUCTEUR

    /**
     * Constructeur de la classe Ordinateur, qui nitialise un joueur ordinateur.
     *
     * @param nom Le nom du joueur.
     * @param taillePioche La taille de la pioche du joueur.
     */
    public Ordinateur(String nom, int taillePioche) {
        super(nom, taillePioche);
    }




    //METHODES

    /**
     * Trouve les cibles potentielles pour un Pokémon attaquant.
     *
     * @param terrain Le terrain de jeu.
     * @param adversaire Le joueur adverse.
     * @param indexAttaquant L'indice du Pokémon attaquant.
     * @return Une liste des indices des Pokémons adverses à attaquer.
     */
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



    /**
     * Choisit une cible parmi les cibles potentielles et attaque.
     *
     * @param pokemonAttaquer La liste des indices des Pokémons adverses à attaquer.
     * @param indexAttaquant L'indice du Pokémon attaquant.
     * @param terrain Le terrain de jeu.
     * @param adversaire Le joueur adverse.
     * @return L'indice du Pokémon attaqué.
     */
    private int choisirCibleEtAttaquer(List<Integer> pokemonAttaquer, int indexAttaquant, Terrain terrain, Joueur adversaire) {
        if (pokemonAttaquer.size() > 1) {
            // Filtrer les cibles par PV
            pokemonAttaquer = filtrerCiblesParPv(pokemonAttaquer, terrain, adversaire);
            // Si plusieurs cibles restent, choisir aléatoirement
            if (pokemonAttaquer.size() > 1) {
                int randomIndex = this.selection(pokemonAttaquer.size());
                return attaqueJoueur(randomIndex, indexAttaquant, terrain, adversaire, pokemonAttaquer);
            }
        }
        // Attaquer la seule cible restante
        return attaqueJoueur(0, indexAttaquant, terrain, adversaire, pokemonAttaquer);
    }



    /**
     * Filtre les cibles potentielles par PV.
     *
     * @param pokemonAttaquer La liste des indices des Pokémons adverses à attaquer.
     * @param terrain Le terrain de jeu.
     * @param adversaire Le joueur adverse.
     * @return La liste des indices des Pokémons adverses filtrée par PV.
     */
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



    /**
     * Exécute l'attaque d'un Pokémon attaquant contre un Pokémon adverse.
     *
     * @param index L'indice de la cible dans la liste des cibles potentielles.
     * @param i L'indice du Pokémon attaquant.
     * @param terrain Le terrain de jeu.
     * @param adversaire Le joueur adverse.
     * @param pokemonAttaquer La liste des indices des Pokémons adverses à attaquer.
     * @return L'indice du Pokémon attaqué.
     */
    private int attaqueJoueur(int index, int i, Terrain terrain, Joueur adversaire, List<Integer> pokemonAttaquer) {
        int pokemonAttaque = pokemonAttaquer.get(index);
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





    //METHODES REDEFINIES

    /**
     * Permet à l'ordinateur de placer des Pokémons sur le terrain.
     *
     * @param terrain Le terrain de jeu.
     * @see Terrain#placerPokemons(Joueur, Pokemon)
     */
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



    /**
     * Gère l'attaque de l'ordinateur contre un adversaire.
     *
     * @param terrain Le terrain de jeu.
     * @param adversaire Le joueur adverse.
     * @return true si l'attaque conduit à la fin de la partie, sinon false.
     * @see Terrain#getPokemonsJoueur(Joueur)
     * @see Pokemon#attaquer(Pokemon)
     * @see Joueur#mort(Terrain, int)
     */
    @Override
    public boolean attaquer(Terrain terrain, Joueur adversaire) {
        Musique m_bruitage = new Musique("musiques/bruitage/attaque.wav");
        m_bruitage.play();
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



    /**
     * Génère une sélection aléatoire pour l'ordinateur.
     *
     * @param borne La taille de la liste parmi laquelle l'ordinateur peut choisir.
     * @return L'indice de l'option sélectionnée aléatoirement.
     */
    @Override
    public int selection(int borne) {
        Random random = new Random();
        return random.nextInt(borne);
    }



    /**
     * Permet à l'ordinateur d'utiliser un pouvoir spécial de ses Pokémons.
     *
     * @param terrain Le terrain de jeu.
     * @param adversaire Le joueur adverse.
     * @return true si l'utilisation du pouvoir conduit à la fin de la partie, sinon false.
     * @see Pokemon#getPouvoir()
     */
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
}