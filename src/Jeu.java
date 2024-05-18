import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Jeu {
    private Joueur m_j1;
    private Joueur m_j2;
    private final Terrain m_terrain;
    private final Tour m_tour;

    private final List<String> m_listNomPokemon;

    public Jeu() {
        m_terrain = new Terrain();
        m_listNomPokemon = new ArrayList<>();
        m_tour = new Tour(this);
    }

    public void jouer(Joueur j1, Joueur j2) {
        // Fonctionnement d'un tour :
        // 1. le joueur place jusqu'à 3 pokemons sur le terrain, il faut qu'il y en ait 3
        // 2. le joueur choisit un pokemon avec lequel attaquer et 1 pokemon à attaquer
        // 3. le pokemon attaque l'autre pokemon
        // 4. le joueur pioche dans sa main jusqu'à avoir 5 pokemons dans la main


        // Boucle de jeu
        if (!partieTerminee()) {
            System.out.println(this.m_tour.getM_nbTourString()+" tour :");
            // Joueur 1
            System.out.println("Tour de " + j1.getNom() + " :\n");
            // piocher
            j1.piocherPokemon();

            // tant qu'il n'y a pas 3 pokemons du joueur sur le terrain
            while (this.m_terrain.getNbPokemonsJoueur(j1) < 3 && j1.getMain().getNbPokemon() > 0){
                System.out.println("Placer des pokemons sur le terrain");
                j1.placerPokemon(this.m_terrain);
            }
            // attaquer
            if(j1.attaquer(this.m_terrain, j2)){
                partieTerminee();
            }
        }
        else if (partieTerminee()){
            Affichage.finDePartie(j1);
        }
    }
    public void initialisationJeu(){
        this.genererListNomPokemon();
        Random random = new Random();
        int randomInt = random.nextInt(2);
        // Initialisation des joueurs
        if(randomInt == 0){
            System.out.println("Vous êtes le joueur 1");
            this.m_j1 = new Humain("Joueur 1", this, 20);
            this.m_j2 = new Ordinateur("Joueur 2", this, 21);
        }
        else {
            System.out.println("Vous êtes le joueur 2");
            this.m_j1 = new Ordinateur("Joueur 1", this, 20);
            this.m_j2 = new Humain("Joueur 2", this, 21);
        }
        for (int i=0; i<5; i++) {
            this.m_j1.piocherPokemon();
            this.m_j2.piocherPokemon();
        }

        // Initlalisaiton de la partie, chaque joueur pose 3 pokemons sur le terrain
        System.out.println(this.m_tour.getM_nbTourString()+" tour :");
        System.out.println("Tour de " + this.m_j1.getNom());
        for (int i=0; i<3; i++) {
            this.m_j1.placerPokemon(this.m_terrain);
        }
        System.out.println("Tour de " + this.m_j2.getNom());
        for (int i=0; i<3; i++) {
            this.m_j2.placerPokemon(this.m_terrain);
        }
        //attaque j1
        if(this.m_j1.attaquer(this.m_terrain, this.m_j2)){
            partieTerminee();
        }
        //attaque j2
        if(this.m_j2.attaquer(this.m_terrain, this.m_j1)){
            partieTerminee();
        }
        Affichage.terrain(this.m_terrain,this.m_j1,this.m_j2);
        this.m_tour.nouveauTour();
    }

    private boolean partieTerminee() {
        return this.m_j1.aPerdu() || this.m_j2.aPerdu();
    }

    public List<String> getM_listNomPokemon() {
        return this.m_listNomPokemon;
    }

    public Joueur getJoueur1() {
        return this.m_j1;
    }
    public Joueur getJoueur2() {
        return this.m_j2;
    }

    public List<String> getListNomPokemon() {
        return this.m_listNomPokemon;
    }

    public Terrain getM_terrain() {
        return m_terrain;
    }

    public void genererListNomPokemon(){
        this.m_listNomPokemon.add("Pikachu");
        this.m_listNomPokemon.add("Bulbizarre");
        this.m_listNomPokemon.add("Salamèche");
        this.m_listNomPokemon.add("Carapuce");
        this.m_listNomPokemon.add("Rattata");
        this.m_listNomPokemon.add("Abo");
        this.m_listNomPokemon.add("Piafabec");
        this.m_listNomPokemon.add("Ptitard");
        this.m_listNomPokemon.add("Miaouss");
        this.m_listNomPokemon.add("Racaillou");
        this.m_listNomPokemon.add("Nosferapti");
        this.m_listNomPokemon.add("Machoc");
        this.m_listNomPokemon.add("Mystherbe");
        this.m_listNomPokemon.add("Tentacool");
        this.m_listNomPokemon.add("Racaillou");
        this.m_listNomPokemon.add("Rapasdepic");
        this.m_listNomPokemon.add("Amonita");
        this.m_listNomPokemon.add("Porygon");
        this.m_listNomPokemon.add("Amonistar");
        this.m_listNomPokemon.add("Kabuto");
        this.m_listNomPokemon.add("Kabutops");
        this.m_listNomPokemon.add("Artikodin");
        this.m_listNomPokemon.add("Electhor");
        this.m_listNomPokemon.add("Sulfura");
        this.m_listNomPokemon.add("Minidraco");
        this.m_listNomPokemon.add("Draco");
        this.m_listNomPokemon.add("Dracolosse");
        this.m_listNomPokemon.add("Mewtwo");
        this.m_listNomPokemon.add("Mew");
        this.m_listNomPokemon.add("Germignon");
        this.m_listNomPokemon.add("Héricendre");
        this.m_listNomPokemon.add("Kaiminus");
        this.m_listNomPokemon.add("Méganium");
        this.m_listNomPokemon.add("Typhlosion");
        this.m_listNomPokemon.add("Feraligatr");
        this.m_listNomPokemon.add("Cizayox");
        this.m_listNomPokemon.add("roucarnage");
        this.m_listNomPokemon.add("Feurisson");
        this.m_listNomPokemon.add("Insolourdo");
        this.m_listNomPokemon.add("Ronflex");
        this.m_listNomPokemon.add("morphéo");
        this.m_listNomPokemon.add("Zarbi");
    }

}