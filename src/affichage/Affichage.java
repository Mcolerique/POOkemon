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
        Affichage.affichePokemon(j1.getMain().getListePokemon());
        Affichage.afficheTerrainJoueur(terrain.getPokemonsJoueur(j1),j1.getTailleTerrain());
        System.out.println("pioche : "+j1.getPioche().getPioche().size() + " cartes");
        System.out.println("défausse : "+j1.getDefausse().getDefausse().size()+" cartes");
        System.out.println("\n--------------------------------------------------------------------------------\n");
        System.out.println(" Pokémon du Joueur 2 : ");
        Affichage.afficheTerrainJoueur(terrain.getPokemonsJoueur(j2), j2.getTailleTerrain());
        Affichage.affichePokemon(j2.getMain().getListePokemon());
        System.out.println("pioche : "+j2.getPioche().getPioche().size() + " cartes");
        System.out.println("défausse : "+j2.getDefausse().getDefausse().size()+" cartes\n");
    }

    public static void affichePokemon(List<Pokemon> list){
        int nombrePokemons = list.size();
        int largeurCase = 22; //Largeur de chaque case

        for (int i = 0; i < nombrePokemons; i++) {
            System.out.print("  +----------------------+");
        }
        System.out.println();


        //Affichage.Affichage des noms des Pokémon
        for (int i = 0; i < nombrePokemons; i++) {
            String nom = list.get(i).getNom();
            int longueurNom = list.get(i).getNom().length();
            if (longueurNom % 2 == 1) {
                nom = nom + " ";
            }
            int espaceDeChaqueCote = (largeurCase - longueurNom) / 2;
            System.out.print(String.format("  |%" + espaceDeChaqueCote + "s%s%" + espaceDeChaqueCote + "s|", "", nom, ""));
        }
        System.out.println();

        //Affichage.Affichage des PV des Pokémon
        for (int i = 0; i < nombrePokemons; i++) {
            String pv = list.get(i).getPv() + "/" + list.get(i).getPvMax();
            int longueurPV = pv.length();
            int espaceDeChaqueCote = (largeurCase - longueurPV) / 2 - 2;
            System.out.print(String.format("  | PV :%" + espaceDeChaqueCote + "s%s%" + espaceDeChaqueCote + "s|", "", pv, ""));
        }
        System.out.println();

        //Affichage.Affichage des attaques des Pokémon
        for (int i = 0; i < nombrePokemons; i++) {
            String attaque = String.valueOf(list.get(i).getAttaque());
            int longueurAtt = attaque.length();
            int espaceDeChaqueCote = (largeurCase - longueurAtt) / 2 - 5;
            System.out.print(String.format("  | Attaque :%" + espaceDeChaqueCote + "s%s%" + espaceDeChaqueCote + "s|", "", attaque, ""));
        }
        System.out.println();

        //Affichage.Affichage des types des Pokémon
        for (int i = 0; i < nombrePokemons; i++) {
            String type = list.get(i).getElementString();
            int longueurType = type.length();
            int espaceDeChaqueCote = (largeurCase - longueurType) / 2 - 3;
            System.out.print(String.format("  | Type :%" + espaceDeChaqueCote + "s%s%" + espaceDeChaqueCote + "s|", "", type, ""));
        }
        System.out.println();

        //Affichage.Affichage des pouvoirs des pokemons
        for (int i = 0; i < nombrePokemons; i++) {
            String pouvoir = list.get(i).getNomPouvoir();
            int longueurPouvoir = pouvoir.length();
            if (longueurPouvoir % 2 == 1) {
                pouvoir += " ";
                longueurPouvoir = pouvoir.length();
            }
            int espaceDisponible = largeurCase - longueurPouvoir - 9; // 9 est la longueur de " Pouvoir : "
            int espaceDeChaqueCote = espaceDisponible / 2;
            espaceDeChaqueCote = Math.max(espaceDeChaqueCote, 0); // S'assurer que l'espace est au moins 0

            // Gestion du cas où l'espace est insuffisant
            if (espaceDisponible < 0) {
                pouvoir = pouvoir.substring(0, largeurCase - 9 - 1) + ".";
                espaceDeChaqueCote = 0;
            }

            System.out.print(String.format("  | Pouvoir :%" + espaceDeChaqueCote + "s%s%" + espaceDeChaqueCote + "s|", "", pouvoir, ""));
        }
        System.out.println();
        for (int i = 0; i < nombrePokemons; i++) {
            System.out.print("  +----------------------+");
        }
        System.out.println();
    }

    public static void afficheTerrainJoueur(List<Pokemon> list, int tailleTerrain){
        int largeurCase = 22; //Largeur de chaque case

        for (int i = 0; i < tailleTerrain; i++) {
            System.out.print("  +----------------------+  ");
        }
        System.out.println();


        //Affichage.Affichage des noms des Pokémon
        for (int i = 0; i < tailleTerrain; i++) {
            try {
                String nom = list.get(i).getNom();
                int longueurNom = list.get(i).getNom().length();
                if (longueurNom % 2 == 1) {
                    nom = nom + " ";
                }
                int espaceDeChaqueCote = (largeurCase - longueurNom) / 2;
                System.out.print(String.format("  |%" + espaceDeChaqueCote + "s%s%" + espaceDeChaqueCote + "s|", "", nom, ""));
            } catch (IndexOutOfBoundsException e) {
                System.out.print("  |                      |  ");
            }
        }
        System.out.println();

        //Affichage.Affichage des PV des Pokémon
        for (int i = 0; i < tailleTerrain; i++) {
            try {
                String pv = list.get(i).getPv() + "/" + list.get(i).getPvMax();
                int longueurPV = pv.length();
                int espaceDeChaqueCote = (largeurCase - longueurPV) / 2 - 2;
                System.out.print(String.format("  | PV :%" + espaceDeChaqueCote + "s%s%" + espaceDeChaqueCote + "s|", "", pv, ""));
            } catch (IndexOutOfBoundsException e) {
                System.out.print("  |                      |  ");
            }
        }
        System.out.println();

        //Affichage.Affichage des attaques des Pokémon
        for (int i = 0; i < tailleTerrain; i++) {
            try {
                String attaque = String.valueOf(list.get(i).getAttaque());
                int longueurAtt = attaque.length();
                int espaceDeChaqueCote = (largeurCase - longueurAtt) / 2 - 5;
                System.out.print(String.format("  | Attaque :%" + espaceDeChaqueCote + "s%s%" + espaceDeChaqueCote + "s|", "", attaque, ""));
            }
            catch (IndexOutOfBoundsException e) {
                System.out.print("  |                      |  ");
            }
        }
        System.out.println();

        //Affichage.Affichage des types des Pokémon
        for (int i = 0; i < tailleTerrain; i++) {
            try {
            String type = list.get(i).getElementString();
            int longueurType = type.length();
            int espaceDeChaqueCote = (largeurCase - longueurType) / 2 - 3;
            System.out.print(String.format("  | Type :%" + espaceDeChaqueCote + "s%s%" + espaceDeChaqueCote + "s|", "", type, ""));
            }
            catch (IndexOutOfBoundsException e) {
                System.out.print("  |                      |  ");
            }
        }
        System.out.println();

        //Affichage.Affichage des pouvoirs des pokemons
        for (int i = 0; i < tailleTerrain; i++) {
            try {
                String pouvoir = list.get(i).getNomPouvoir();
                int longueurPouvoir = pouvoir.length();
                if (longueurPouvoir % 2 == 1) {
                    pouvoir += " ";
                    longueurPouvoir = pouvoir.length();
                }
                int espaceDisponible = largeurCase - longueurPouvoir - 9; // 9 est la longueur de " Pouvoir : "
                int espaceDeChaqueCote = espaceDisponible / 2;
                espaceDeChaqueCote = Math.max(espaceDeChaqueCote, 0); // S'assurer que l'espace est au moins 0

                // Gestion du cas où l'espace est insuffisant
                if (espaceDisponible < 0) {
                    pouvoir = pouvoir.substring(0, largeurCase - 9 - 1) + ".";
                    espaceDeChaqueCote = 0;
                }
                System.out.print(String.format("  | Pouvoir : %" + espaceDeChaqueCote + "s%s%" + espaceDeChaqueCote + "s|", "", pouvoir, ""));
            } catch (IndexOutOfBoundsException e) {
                System.out.print("  |                      |  ");
            }
        }
        System.out.println();
        for (int i = 0; i < tailleTerrain; i++) {
            System.out.print("  +----------------------+  ");
        }
        System.out.println();
    }

    public static void finDePartie(Joueur gagnant){
        if (gagnant.getClass() == Humain.class) {
            System.out.println("Vous avez gagné !! "+gagnant.getPioche().getPioche().size()+gagnant.getMain().getNbPokemon()+" de vos pokemon.Pokemon sont en pleine santé, vous la petez pas trop c'est pas dur");
        }
        else {
            System.out.println("Vous avez perdu !! "+gagnant.getPioche().getPioche().size()+gagnant.getMain().getNbPokemon()+" de ses pokemon Pokemon sont en pleine santé, vous êtes vraiment nul");
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
            else {System.out.println((j+1)+"."+list.get(j).getNom()+" )");}
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
    public static void afficheDefausse(Joueur joueur, List<Pokemon> defausse) {
        System.out.println("Défausse de " + joueur.getNom() + " :");
        for (int i = 0; i < defausse.size(); i++) {
            System.out.println((i + 1) + ". " + defausse.get(i).getNom());
        }
    }
}
