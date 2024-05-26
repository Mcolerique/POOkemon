package affichage;

import java.util.List;
import java.util.Scanner;
import jeu.*;
import joueurs.*;
import pokemons.*;

public class Affichage {

    //Methodes
    public static void accueil(){
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
        int largeurCase = 20; // Largeur de chaque case

        // affichage.Affichage des noms des Pokémon
        System.out.print("                       ");
        for (int i = 0; i < nombrePokemons; i++) {
            String nom = list.get(i).getNom();
            System.out.print(String.format("%-" + largeurCase + "s", nom));
        }
        System.out.println();

        // affichage.Affichage des PV des Pokémon
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
                System.out.print((j+1)+"."+list.get(j).getNom() + "/");
            }
            else {System.out.println((j+1)+"."+list.get(j).getNom()+")");}
        }
    }
}
