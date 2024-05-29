package affichage;

import java.util.List;
import joueurs.*;
import pokemons.*;
import jeu.Tour;

public class Affichage {

    //Methodes
    public static void accueil(){
        System.out.println("\t\t\t\t\t\t\t\t                  ,'\\\n" +
                    "\t\t\t\t    _.----._       ____         ,'  _\\   ___    ___     ____\n" +
                    "\t\t\t\t_,/         \\     |    |  /`.   \\,-'    |   \\  /   |   |    \\  |`.\n" +
                    "\t\t\t\t\\      __    \\    '-.  | /   `.  ___    |    \\/    |   '-.   \\ |  |\n" +
                    "\t\t\t\t \\.    \\ \\    | __  |  |/    ,','_  `.  |          | __  |    \\|  |\n" +
                    "\t\t\t\t   \\    \\/   /,' _`.|      ,' / / / /   |          ,' _`.|     |  |\n" +
                    "\t\t\t\t    \\     ,-'/  / \\ \\   ,'   |  \\/ / ,`.|         /  / \\ \\  |     |\n" +
                    "\t\t\t\t     \\    \\ |   \\_/  |   `-.  \\    `'  /|  |    ||   \\_/  | |\\    |\n" +
                    "\t\t\t\t      \\    \\ \\      /       `-.`.___,-' |  |\\  /| \\      /  | |   |\n" +
                    "\t\t\t\t       \\    \\ `.__,'|  |`-._    `|      |__| \\/ |  `.__,'|  | |   |\n" +
                    "\t\t\t\t\t    \\_.-'       |__|    `-._ |              '-.|     '-.| |   |\n" +
                    "\t\t\t\t\t\t\t\t                                              '-._|\n");
    }

    public static void afficher(String message){
        System.out.println(message);
    }

    public static void terrain(Terrain terrain, Joueur j1, Joueur j2){
        System.out.println(" \nPokémon du Joueur 1 : ");
        Affichage.affichePokemon(terrain.getPokemonsJoueur(j1));
        System.out.println("pioche : "+j1.getPioche().getPioche().size() + " cartes");
        System.out.println("défausse : "+j1.getDefausse().getDefausse().size()+" cartes");
        System.out.println("\n--------------------------------------------------------------------------------\n");
        System.out.println(" Pokémon du Joueur 2 : ");
        Affichage.affichePokemon(terrain.getPokemonsJoueur(j2));
        System.out.println("pioche : "+j2.getPioche().getPioche().size() + " cartes");
        System.out.println("défausse : "+j2.getDefausse().getDefausse().size()+" cartes\n");
    }

    public static void affichePokemon(List<Pokemon> list){
        int nombrePokemons = list.size();
        int largeurCase = 20; //Largeur de chaque case

        //Affichage.Affichage des noms des Pokémon
        System.out.print("                       ");
        for (int i = 0; i < nombrePokemons; i++) {
            String nom = list.get(i).getNom();
            System.out.print(String.format("%-" + largeurCase + "s", nom));
        }
        System.out.println();

        //Affichage.Affichage des PV des Pokémon
        System.out.print("                  pv : ");
        for (int i = 0; i < nombrePokemons; i++) {
            String pv = list.get(i).getPv() + "/" + list.get(i).getPvMax();
            System.out.print(String.format("%-" + largeurCase + "s", pv));
        }
        System.out.println();

        //Affichage.Affichage des attaques des Pokémon
        System.out.print("             attaque : ");
        for (int i = 0; i < nombrePokemons; i++) {
            String attaque = String.valueOf(list.get(i).getAttaque());
            System.out.print(String.format("%-" + largeurCase + "s", attaque));
        }
        System.out.println();

        //Affichage.Affichage des types des Pokémon
        System.out.print("                type : ");
        for (int i = 0; i < nombrePokemons; i++) {
            String type = list.get(i).getElementString();
            System.out.print(String.format("%-" + largeurCase + "s", type));
        }
        System.out.println();

        //Affichage.Affichage des pouvoirs des pokemons
        System.out.print("             pouvoir : ");
        for (int i = 0; i < nombrePokemons; i++) {
            String type = list.get(i).getNomPouvoir();
            System.out.print(String.format("%-" + largeurCase + "s", type));
        }
        System.out.println();
    }

    public static void finDePartie(Joueur gagnant){
        if (gagnant.getClass() == Humain.class) {
            System.out.println("Vous avez gagné !! "+gagnant.getPioche().getPioche().size()+gagnant.getMain().getNbPokemon()+" de vos pokemon.Pokemon sont en pleine santé, vous la petez pas trop c'est pas dur");
        }
        else {
            System.out.println("Vous avez perdu !! "+gagnant.getPioche().getPioche().size()+gagnant.getMain().getNbPokemon()+" de ses pokemon.Pokemon sont en pleine santé, vous êtes vraiment nul");
        }
        Affichage.accueil();
    }

    public static void selectionPokemon(List<Pokemon> list){
        for (int j = 0; j<list.size();j++)
        {
            if (j != list.size()-1)
            {
                System.out.print((j+1)+"."+list.get(j).getNom() + " / ");
            }
            else {System.out.println((j+1)+"."+list.get(j).getNom()+"");}
        }
    }

    public static void afficheNbTour(String tour) {
        int largeurCadre = tour.length() + 2;

        String ligneSuperieure = "+" + "-".repeat(largeurCadre) + "+";
        String ligneTitre = String.format("| %-" + (largeurCadre - 2) + "s |", tour);
        String ligneInferieure = "+" + "-".repeat(largeurCadre) + "+";
        System.out.println(ligneSuperieure);
        System.out.println(ligneTitre);
        System.out.println(ligneInferieure);
    }
}
