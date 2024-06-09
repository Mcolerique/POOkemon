package joueurs;

import affichage.Affichage;
import pokemons.Pokemon;
import java.util.List;

/**
 * La classe abstraite Joueur représente un joueur dans le jeu Pokémon.
 */
public abstract class Joueur {

    //ATTRIBUTS
    protected String m_nom;
    protected Pioche m_pioche;
    protected Main m_main;
    protected Defausse m_defausse;
    protected int m_tailleTerrain;



    //CONSTRUCTEUR

    /**
     * Constructeur de la classe Joueur, qui initialise un joueur.
     *
     * @param nom Le nom du joueur.
     * @param taillePioche La taille de la pioche du joueur.
     */
    public Joueur(String nom, int taillePioche) {
        this.m_nom = nom;
        this.m_pioche = new Pioche(taillePioche);
        this.m_main = new Main();
        this.m_defausse = new Defausse();
        this.m_tailleTerrain = 3;
    }



    //METHODES ABSTRAITES

    /**
     * Gère l'attaque du joueur contre un adversaire.
     *
     * @param terrain Le terrain de jeu.
     * @param adversaire Le joueur adverse.
     * @return true si l'attaque conduit à la fin de la partie, sinon false.
     */
    public abstract boolean attaquer(Terrain terrain, Joueur adversaire);


    /**
     * Permet au joueur d'utiliser un pouvoir de ses Pokémons.
     *
     * @param terrain Le terrain de jeu.
     * @param adversaire Le joueur adverse.
     * @return true si l'utilisation du pouvoir conduit à la fin de la partie, sinon false.
     */
    public abstract boolean utiliserPouvoir(Terrain terrain, Joueur adversaire);


    /**
     * Permet au joueur de faire une sélection parmi une liste d'options.
     *
     * @param borne La taille de la liste parmi laquelle l'utilisateur peut choisir.
     * @return L'indice de l'option sélectionnée par l'utilisateur.
     */
    public abstract int selection(int borne);


    /**
     * Permet au joueur de placer des Pokémons sur le terrain.
     *
     * @param terrain Le terrain de jeu.
     */
    public abstract void placerPokemon(Terrain terrain);




    //METHODES

    /**
     * Mélange la pioche du joueur.
     */
    public void melangerPioche() {
        for (int i = 0; i < this.m_pioche.getPioche().size(); i++)
        {
            int random = (int) (Math.random() * this.m_pioche.getPioche().size());
            Pokemon temp = this.m_pioche.getPioche().get(i);
            this.m_pioche.getPioche().set(i, this.m_pioche.getPioche().get(random));
            this.m_pioche.getPioche().set(random, temp);
        }
    }



    /**
     * Permet au joueur de piocher des Pokémons jusqu'à en avoir 5 en main.
     */
    public void piocherPokemon() {
        while (this.m_main.getNbPokemon() < 5 && !this.m_pioche.getPioche().isEmpty()) {
            this.m_pioche.piocherMain(this.m_main);
        }
    }



    /**
     * Ajoute un Pokémon à la défausse du joueur.
     *
     * @param pokemon Le Pokémon à défausser.
     */
    public void defausser(Pokemon pokemon) {
        m_defausse.defausser(pokemon);
    }



    /**
     * Vérifie si un Pokémon est mort et le retire du terrain.
     *
     * @param terrain Le terrain de jeu.
     * @param attaque L'indice du Pokémon attaqué.
     * @return true si le terrain du joueur est vide après la défausse, sinon false.
     */
    public boolean mort(Terrain terrain, int attaque) {
        if (!(terrain.getPokemon(this, attaque)).estVivant()) {
            Affichage.afficher(Affichage.mettreEnGras(Affichage.mettreEnCouleur("\nLe pokemon " + (terrain.getPokemon(this, attaque)).getNom() + " est mort...\n", "\u001B[36m")));
            this.defausser(terrain.getPokemon(this, attaque));
            terrain.retirerPokemon(this, attaque);
            return terrain.estVide(this);
        }
        return false;
    }



    /**
     * Vérifie si des Pokémons sur le terrain sont morts et les retire.
     *
     * @param terrain Le terrain de jeu.
     * @return true si le terrain du joueur est vide après la défausse, sinon false.
     */
    public boolean mort(Terrain terrain) {
        for (int i = 0; i < this.m_tailleTerrain; i++) {
            if (!(terrain.getPokemon(this, i)).estVivant()) {
                System.out.println("Le pokemon " + (terrain.getPokemon(this, i)).getNom() + " est mort");
                this.defausser(terrain.getPokemon(this, i));
                terrain.retirerPokemon(this, i);
                if (terrain.estVide(this)) {
                    return true;
                }
            }
        }
        return false;
    }



    /**
     * Vide la main du joueur et remet les cartes dans la pioche.
     */
    public void viderMain()
    {
        // Ajoute toutes les cartes de la main à la pioche
        m_pioche.getPioche().addAll(m_main.getListePokemon());
        // Vide la main du joueur
        m_main.getListePokemon().clear();
    }



    /**
     * Remplit la main du joueur en piochant des Pokémons jusqu'à en avoir 5 en main.
     */
    public void remplirMain() {
        while (this.m_main.getNbPokemon() < 5 && !this.m_pioche.getPioche().isEmpty()) {
            this.m_pioche.piocherMain(this.m_main);
        }
    }



    /**
     * Modifie la taille du terrain du joueur.
     *
     * @param modif La modification à apporter à la taille du terrain.
     */
    public void modifPlaceTerrain(int modif){
        this.m_tailleTerrain+=modif;
    }



    /**
     * Vérifie si le joueur a perdu.
     *
     * @return true si le joueur a perdu (sa main et sa pioche sont vides), sinon false.
     */
    public boolean aPerdu() {
        return this.m_main.getListePokemon().isEmpty() && this.m_pioche.getPioche().isEmpty();
    }




    //GETTERS

    /**
     * Retourne le nom du joueur.
     *
     * @return Le nom du joueur.
     */
    public String getNom() {
        return this.m_nom;
    }


    /**
     * Retourne la main du joueur.
     *
     * @return La main du joueur.
     */
    public Main getMain() {
        return this.m_main;
    }


    /**
     * Retourne la pioche du joueur.
     *
     * @return La pioche du joueur.
     */
    public Pioche getPioche() {
        return this.m_pioche;
    }


    /**
     * Retourne la défausse du joueur.
     *
     * @return La défausse du joueur.
     */
    public Defausse getDefausse() {
        return this.m_defausse;
    }


    /**
     * Retourne la taille du terrain du joueur.
     *
     * @return La taille du terrain du joueur.
     */
    public int getTailleTerrain(){
        return this.m_tailleTerrain;
    }


    /**
     * Retourne le Pokémon spécifié de la main du joueur.
     *
     * @param pokemonCible L'indice du Pokémon à obtenir.
     * @return Le Pokémon spécifié, ou null si l'indice est invalide.
     */
    public Pokemon getPokemon(int pokemonCible) {
        List<Pokemon> listePokemon = this.m_main.getListePokemon();
        if (pokemonCible >= 0 && pokemonCible < listePokemon.size()) {
            Pokemon pokemon = listePokemon.get(pokemonCible);
            this.m_main.retirerPokemon(pokemon);
            return pokemon;
        } else {
            return null;
        }
    }


    /**
     * Ajoute à la liste les Pokémons du joueur qui ont des pouvoirs non utilisés.
     *
     * @param terrain Le terrain de jeu.
     * @param list La liste à remplir avec les Pokémons ayant des pouvoirs non utilisés.
     */
    public void getPokePouvoir(Terrain terrain, List<Pokemon> list){
        for(int i =0; i<terrain.getNbPokemonsJoueur(this);i++)
        {
            if (!terrain.getPokemonsJoueur(this).isEmpty()){
                if (terrain.getPokemonsJoueur(this).get(i).getPouvoir()!=null && !terrain.getPokemonsJoueur(this).get(i).getPouvoir().getUtilise())
                {
                    list.add(terrain.getPokemonsJoueur(this).get(i));
                }
            }
        }
    }
}