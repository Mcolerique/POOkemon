import java.util.List;
import java.util.Scanner;

public class Affichage {
    static void accueil(){
        System.out.println("Nouvelle partie ?(o/n)");
        Scanner scanner = new Scanner(System.in);
        char reponse = scanner.next().charAt(0);
        if (reponse == 'o'){
            Jeu jeu = new Jeu();
            jeu.initialisationJeu();
        }
        else {
            System.out.println("Bah casse toi alors");
            System.exit(0);
        }
    }
    static void terrain(Terrain terrain, Joueur j1, Joueur j2){
        System.out.println(" Pokémon du Joueur 1 : ");
        Affichage.affichePokemon(terrain.getM_pokemonsJoueur(j1));
        System.out.println("pioche : "+j1.m_pioche.getM_pioche().size() + " cartes");
        System.out.println("défausse : "+j1.m_defausse.getDefausse().size()+" cartes");
        System.out.println("\n--------------------------------------------------------------------------------");
        System.out.println(" Pokémon du Joueur 2 : ");
        Affichage.affichePokemon(terrain.getM_pokemonsJoueur(j2));
        System.out.println("pioche : "+j2.m_pioche.getM_pioche().size() + " cartes");
        System.out.println("défausse : "+j2.m_defausse.getDefausse().size()+" cartes");

    }
    static void affichePokemon(List<Pokemon> list){
        int nombrePokemons = list.size();
        int largeurCase = 20; // Largeur de chaque case

        // Affichage des noms des Pokémon
        System.out.print("                       ");
        for (int i = 0; i < nombrePokemons; i++) {
            String nom = list.get(i).getNom();
            System.out.print(String.format("%-" + largeurCase + "s", nom));
        }
        System.out.println();

        // Affichage des PV des Pokémon
        System.out.print("                  pv : ");
        for (int i = 0; i < nombrePokemons; i++) {
            String pv = list.get(i).getPv() + "/" + list.get(i).getPvMax();
            System.out.print(String.format("%-" + largeurCase + "s", pv));
        }
        System.out.println();

        // Affichage des attaques des Pokémon
        System.out.print("             attaque : ");
        for (int i = 0; i < nombrePokemons; i++) {
            String attaque = String.valueOf(list.get(i).getAttaque());
            System.out.print(String.format("%-" + largeurCase + "s", attaque));
        }
        System.out.println();

        // Affichage des types des Pokémon
        System.out.print("                type : ");
        for (int i = 0; i < nombrePokemons; i++) {
            String type = list.get(i).getElementString();
            System.out.print(String.format("%-" + largeurCase + "s", type));
        }
        System.out.println();
    }
    static void finDePartie(Joueur gagnant){
        if (gagnant.getClass() == Humain.class) {
            System.out.println("Vous avez gagné !! "+gagnant.m_pioche.getM_pioche().size()+gagnant.m_main.getNbPokemon()+" de vos Pokemon sont en pleine santé, vous la petez pas trop c'est pas dur");
        }
        else {
            System.out.println("Vous avez perdu !! "+gagnant.m_pioche.getM_pioche().size()+gagnant.m_main.getNbPokemon()+" de ses Pokemon sont en pleine santé, vous êtes vraiment nul");
        }
        Affichage.accueil();
    }
}
