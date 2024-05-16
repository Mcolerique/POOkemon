import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Jeu {
    private Joueur m_j1;
    private Joueur m_j2;
    private Terrain m_terrain;

    private List<String> m_listNomPokemon;

    public Jeu() {
        m_terrain = new Terrain();
        m_listNomPokemon = new ArrayList<>();
    }

    public void jouer() {
        // fonctionnement d'un tour :
        // 1. le joueur place jusqu'à 3 pokemons sur le terrain, il faut qu'il y en ait 3
        // 2. le joueur choisit un pokemon avec lequel attaquer et 1 pokemon à attaquer
        // 3. le pokemon attaque l'autre pokemon
        // 4. le joueur pioche dans sa main jusqu'à avoir 5 pokemons dans la main

        // Initialisation des joueurs
        this.genererListNomPokemon();
        m_j1 = new Joueur("Joueur 1", this, 20);
        m_j2 = new Joueur("Joueur 2", this, 21);
        for (int i=0; i<5; i++) {
            m_j1.piocherPokemon();
            m_j2.piocherPokemon();
        }

        // Initlalisaiton de la partie, chaque joueur pose 3 pokemons sur le terrain
        System.out.println("Tour de " + m_j1.getNom());
        for (int i=0; i<3; i++) {
            m_j1.getMain().afficher();
            System.out.println("Choisissez un pokemon à placer sur le terrain");
            int pokemonaplacer;
            try {
                pokemonaplacer = System.in.read();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            m_j1.placerPokemons(m_terrain, pokemonaplacer);
        }
        System.out.println("Tour de " + m_j2.getNom());
        for (int i=0; i<3; i++) {
            m_j2.getMain().afficher();
            System.out.println("Choisissez un pokemon à placer sur le terrain");
            int pokemonaplacer;
            try {
                pokemonaplacer = System.in.read();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            m_j2.placerPokemons(m_terrain, pokemonaplacer);
        }

        // Boucle de jeu
        while (!partieTerminee()) {
            // Joueur 1
            System.out.println("Tour de " + m_j1.getNom());
            m_terrain.printPokemon(m_j1);
            System.out.println("Placer des pokemons sur le terrain");
            // tant qu'il n'y a pas 3 pokemons du joueur sur le terrain
            while (m_terrain.getNbPokemonsJoueur(m_j1) < 3) {
                // afficher la main du joueur
                m_j1.getMain().afficher();
                int pokemonaplacer;
                try {
                    pokemonaplacer = System.in.read();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                m_j1.placerPokemons(m_terrain, pokemonaplacer);
            }
            // attaquer
            System.out.println("Choisissez un pokemon avec lequel attaquer");
            m_terrain.printPokemon(m_j1);
            int pokemonAttaquant;
            try {
                pokemonAttaquant = System.in.read();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Choisissez un pokemon à attaquer");
            m_terrain.printPokemon(m_j2);
            int pokemonAttaque;
            try {
                pokemonAttaque = System.in.read();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            m_j1.attaquer((Pokemon) m_terrain.getPokemon(m_j2), (Pokemon) m_terrain.getPokemon(m_j1));
            // si le pokemon attaqué est mort, le défausser
            if (((Pokemon) m_terrain.getPokemon(m_j2)).getPv()<=0) {
                m_j2.defausser((Pokemon) m_terrain.getPokemon(m_j2));
            }
            // piocher
            while (m_j1.getMain().getNbPokemon() < 5) {
                m_j1.piocherPokemon();
            }

            // Joueur 2
            System.out.println("Tour de " + m_j2.getNom());
            m_terrain.printPokemon(m_j2);
            System.out.println("Placer des pokemons sur le terrain");
            // tant qu'il n'y a pas 3 pokemons du joueur sur le terrain
            while (m_terrain.getNbPokemonsJoueur(m_j2) < 3) {
                // afficher la main du joueur
                m_j2.getMain().afficher();
                int pokemonaplacer;
                try {
                    pokemonaplacer = System.in.read();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                m_j2.placerPokemons(m_terrain, pokemonaplacer);
            }
            // attaquer
            System.out.println("Choisissez un pokemon avec lequel attaquer");
            m_terrain.printPokemon(m_j2);
            int pokemonAttaquant2;
            try {
                pokemonAttaquant2 = System.in.read();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Choisissez un pokemon à attaquer");
            m_terrain.printPokemon(m_j1);
            int pokemonAttaque2;
            try {
                pokemonAttaque2 = System.in.read();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            m_j2.attaquer((Pokemon) m_terrain.getPokemon(m_j1), (Pokemon) m_terrain.getPokemon(m_j2));
            // si le pokemon attaqué est mort, le défausser
            if (((Pokemon) m_terrain.getPokemon(m_j1)).getPv()<=0) {
                m_j1.defausser((Pokemon) m_terrain.getPokemon(m_j1));
            }
            // piocher
            while (m_j2.getMain().getNbPokemon() < 5) {
                m_j2.piocherPokemon();
            }
            partieTerminee();
        }
    }

    private boolean partieTerminee() {
        return m_j1.aPerdu() || m_j2.aPerdu();
    }

    public List<String> getM_listNomPokemon() {
        return m_listNomPokemon;
    }

    public static void main(String[] args) {
        Jeu jeu = new Jeu();
        jeu.jouer();
    }

    public Joueur getJoueur1() {
        return m_j1;
    }
    public Joueur getJoueur2() {
        return m_j2;
    }

    public List<String> getListNomPokemon() {
        return m_listNomPokemon;
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