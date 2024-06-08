package joueurs;

import affichage.Affichage;
import pokemons.Pokemon;
import java.util.List;

public abstract class Joueur {

    //Attributs
    protected String m_nom;
    protected Pioche m_pioche;
    protected Main m_main;
    protected Defausse m_defausse;
    protected int m_tailleTerrain;


    //Constructeur
    public Joueur(String nom, int taillePioche) {
        this.m_nom = nom;
        this.m_pioche = new Pioche(taillePioche);
        this.m_main = new Main();
        this.m_defausse = new Defausse();
        this.m_tailleTerrain = 3;
    }


    //Methodes abstraites
    public abstract boolean attaquer(Terrain terrain, Joueur adversaire);

    public abstract boolean utiliserPouvoir(Terrain terrain, Joueur adversaire);

    public abstract int selection(int borne);

    public abstract void placerPokemon(Terrain terrain);


    //Methodes
    public void piocherPokemon() {
        while (this.m_main.getNbPokemon() < 5 && !this.m_pioche.getPioche().isEmpty()) {
            this.m_pioche.piocherMain(this.m_main);
        }
    }

    public void defausser(Pokemon pokemon) {
        m_defausse.defausser(pokemon);
    }

    public boolean mort(Terrain terrain, int attaque) {
        if (!(terrain.getPokemon(this, attaque)).estVivant()) {
            Affichage.afficher(Affichage.mettreEnGras(Affichage.mettreEnCouleur("\nLe pokemon " + (terrain.getPokemon(this, attaque)).getNom() + " est mort...\n", "\u001B[36m")));
            this.defausser(terrain.getPokemon(this, attaque));
            //Retire le pokemon du terrain
            terrain.retirerPokemon(this, attaque);
            return terrain.estVide(this);
        }
        return false;
    }

    public boolean mort(Terrain terrain) {
        for (int i = 0; i < this.m_tailleTerrain; i++) {
            if (!(terrain.getPokemon(this, i)).estVivant()) {
                System.out.println("Le pokemon " + (terrain.getPokemon(this, i)).getNom() + " est mort");
                this.defausser(terrain.getPokemon(this, i));
                //Retire le pokemon du terrain
                terrain.retirerPokemon(this, i);
                if (terrain.estVide(this)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void viderMain()
    {
        //Vide la main pour la remettre dans la pioche
        for (int i = 0; i < this.m_main.getListePokemon().size(); i++)
        {
            //Met le pokemon de la main dans la pioche
            this.m_pioche.getPioche().add(this.m_main.getListePokemon().get(i));
        }
        melangerPioche();
    }

    public void remplirMain() {
        //Remplit la main avec la pioche
        while (this.m_main.getNbPokemon() < 5 && !this.m_pioche.getPioche().isEmpty()) {
            this.m_pioche.piocherMain(this.m_main);
        }
    }

    public void melangerPioche() {
        for (int i = 0; i < this.m_pioche.getPioche().size(); i++)
        {
            int random = (int) (Math.random() * this.m_pioche.getPioche().size());
            Pokemon temp = this.m_pioche.getPioche().get(i);
            this.m_pioche.getPioche().set(i, this.m_pioche.getPioche().get(random));
            this.m_pioche.getPioche().set(random, temp);
        }
    }

    public void modifPlaceTerrain(int modif){
        this.m_tailleTerrain+=modif;
    }

    public boolean aPerdu() {
        return this.m_main.getListePokemon().isEmpty() && this.m_pioche.getPioche().isEmpty();
    }


    //Getters
    public String getNom() {
        return this.m_nom;
    }

    public Main getMain() {
        return this.m_main;
    }

    public Pioche getPioche() {
        return this.m_pioche;
    }

    public Defausse getDefausse() {
        return this.m_defausse;
    }

    public int getTailleTerrain(){
        return this.m_tailleTerrain;
    }

    public Pokemon getPokemon(int pokemonCible) {
        List<Pokemon> listePokemon = this.m_main.getListePokemon();
        if (pokemonCible >= 0 && pokemonCible < listePokemon.size()) {
            //Vérifie que l'index est valide avant d'accéder à la liste
            Pokemon pokemon = listePokemon.get(pokemonCible);
            //Supprime le pokemon de la main seulement s'il existe dans la liste
            this.m_main.retirerPokemon(pokemon);
            return pokemon;
        } else {
            return null;
        }
    }
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
