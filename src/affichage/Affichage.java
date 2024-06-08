package affichage;

import java.util.List;
import joueurs.*;
import pokemons.*;

public class Affichage {

    static int largeurTerminal = 140;


    public static String mettreEnCouleur(String texte, String couleur) {
        String ANSI_RESET = "\u001B[0m";
        return couleur + texte + ANSI_RESET;
    }


    public static void afficherTexteCentre(String texte, int largeurTerminal) {
        String[] lignes = texte.split("\n");
        for (String ligne : lignes) {
            // Nettoyer la ligne de ses tabulations initiales mais conserver les séquences ANSI
            String ligneNettoyee = ligne.replaceAll("\t", "");

            // Calculer la longueur sans les séquences ANSI
            int longueurTexte = ligneNettoyee.replaceAll("\u001B\\[[;\\d]*m", "").length();
            int espacesAvant = (largeurTerminal - longueurTexte) / 2;

            if (espacesAvant > 0) {
                System.out.print(" ".repeat(espacesAvant));
            }

            System.out.println(ligneNettoyee);
        }
    }

    public static String mettreEnGras(String texte) {
        String ANSI_BOLD = "\u001B[1m";
        String ANSI_RESET = "\u001B[0m";
        return ANSI_BOLD + texte + ANSI_RESET;
    }


    //Methodes
    public static void accueil(){
        String titre =
                "_.----._       ____         ,'  _\\   ___    ___     ____\n" +
                "\t\t\t\t   _,/         \\     |    |  /`.   \\,-'    |   \\  /   |   |    \\  |`.\n" +
                "\t\t\t\t   \\      __    \\    '-.  | /   `.  ___    |    \\/    |   '-.   \\ |  |\n" +
                "\t\t\t\t    \\.    \\ \\    | __  |  |/    ,','_  `.  |          | __  |    \\|  |\n" +
                "\t\t\t\t      \\    \\/   /,' _`.|      ,' / / / /   |          ,' _`.|     |  |\n" +
                "\t\t\t\t       \\     ,-'/  / \\ \\   ,'   |  \\/ / ,`.|         /  / \\ \\  |     |\n" +
                "\t\t\t\t        \\    \\ |   \\_/  |   `-.  \\    `'  /|  |    ||   \\_/  | |\\    |\n" +
                "\t\t\t\t         \\    \\ \\      /       `-.`.___,-' |  |\\  /| \\      /  | |   |\n" +
                "\t\t\t\t          \\    \\ `.__,'|  |`-._    `|      |__| \\/ |  `.__,'|  | |   |\n" +
                "\t\t\t\t\t           \\_.-'       |__|    `-._ |              '-.|     '-.| |   |\n" +
                "\t\t\t\t\t\t\t\t                                                                 '-._|\n";
        afficherTexteCentre(mettreEnCouleur(titre,"\u001B[33m"),largeurTerminal);
    }

    public static void afficher(String message){
        System.out.println(message);
    }

    public static void terrain(Terrain terrain, Joueur j1, Joueur j2){
        String piocheJ1 = Integer.toString(j1.getPioche().getPioche().size());
        String defausseJ1 = Integer.toString(j1.getDefausse().getDefausse().size());
        String piocheJ2 = Integer.toString(j2.getPioche().getPioche().size());
        String defausseJ2 = Integer.toString(j2.getDefausse().getDefausse().size());

        if (j1.getPioche().getPioche().size() < 10) {
            piocheJ1 = "0" + piocheJ1;
        }
        if (j1.getDefausse().getDefausse().size() < 10) {
            defausseJ1 = "0" + defausseJ1;
        }
        if (j2.getPioche().getPioche().size() < 10) {
            piocheJ2 = "0" + piocheJ2;
        }
        if (j2.getDefausse().getDefausse().size() < 10) {
            defausseJ2 = "0" + defausseJ2;
        }

        if (j1.getClass().getSimpleName().equals("Ordinateur")) {
            afficherTexteCentre("+----- Pioche -----+            +---- Défausse ----+", largeurTerminal);
            afficherTexteCentre("|        " + piocheJ1 + "        |            |        " + defausseJ1 + "        |", largeurTerminal);
            afficherTexteCentre("+------------------+            +------------------+", largeurTerminal);
            afficherTexteCentre("\nMain du Joueur 1", largeurTerminal);
            afficheMainOrdinateur(j1.getMain().getListePokemon());
            afficheTerrainJoueur(terrain.getPokemonsJoueur(j1), j1.getTailleTerrain());
            afficherTexteCentre("+------------------------------------------------------------- TERRAIN JOUEUR 1 -------------------------------------------------------------+\n", largeurTerminal);
            System.out.println();
            afficherTexteCentre("+------------------------------------------------------------- TERRAIN JOUEUR 2 -------------------------------------------------------------+\n", largeurTerminal);
            afficheTerrainJoueur(terrain.getPokemonsJoueur(j2), j2.getTailleTerrain());
            affichePokemon(j2.getMain().getListePokemon());
            afficherTexteCentre("Main du Joueur 2", largeurTerminal);
            afficherTexteCentre("\n+----- Pioche -----+            +---- Défausse ----+", largeurTerminal);
            afficherTexteCentre("|        " + piocheJ2 + "        |            |        " + defausseJ2 + "        |", largeurTerminal);
            afficherTexteCentre("+------------------+            +------------------+", largeurTerminal);
        }
        else {
            afficherTexteCentre("+----- Pioche -----+            +---- Défausse ----+", largeurTerminal);
            afficherTexteCentre("|        " + piocheJ2 + "        |            |        " + defausseJ2 + "        |", largeurTerminal);
            afficherTexteCentre("+------------------+            +------------------+", largeurTerminal);
            afficherTexteCentre("Main du Joueur 2", largeurTerminal);
            afficheMainOrdinateur(j2.getMain().getListePokemon());
            afficheTerrainJoueur(terrain.getPokemonsJoueur(j2), j2.getTailleTerrain());
            afficherTexteCentre("+------------------------------------------------------------- TERRAIN JOUEUR 2 -------------------------------------------------------------+\n", largeurTerminal);
            System.out.println();
            afficherTexteCentre("+------------------------------------------------------------- TERRAIN JOUEUR 1 -------------------------------------------------------------+\n", largeurTerminal);
            afficheTerrainJoueur(terrain.getPokemonsJoueur(j1), j1.getTailleTerrain());
            affichePokemon(j1.getMain().getListePokemon());
            afficherTexteCentre("Main du Joueur 1\n", largeurTerminal);

            afficherTexteCentre("\n+----- Pioche -----+            +---- Défausse ----+", largeurTerminal);
            afficherTexteCentre("|        " + piocheJ1 + "        |            |        " + defausseJ1 + "        |", largeurTerminal);
            afficherTexteCentre("+------------------+            +------------------+", largeurTerminal);
        }
    }

    public static void afficheMainOrdinateur(List<Pokemon> list){
        int nombrePokemons = list.size();

        System.out.print("    ");

        for (int i = 0; i < nombrePokemons; i++) {
            System.out.print("  +----------------------+");
        }
        System.out.println();
        System.out.print("    ");

        for (int i = 0; i < nombrePokemons; i++) {
            System.out.print("  |    ▕▔▔╲       ╱▔▔▏   |");
        }
        System.out.println();
        System.out.print("    ");

        for (int i = 0; i < nombrePokemons; i++) {
            System.out.print("  |     ╲┈┈╲╱▔▔▔╲╱┈┈╱    |");
        }
        System.out.println();
        System.out.print("    ");

        for (int i = 0; i < nombrePokemons; i++) {
            System.out.print("  |      ╲┈╭╮┈┈┈╭╮┈╱     |");
        }
        System.out.println();
        System.out.print("    ");

        for (int i = 0; i < nombrePokemons; i++) {
            System.out.print("  |      ╱┈╰╯┈▂┈╰╯┈╲     |");
        }
        System.out.println();
        System.out.print("    ");

        for (int i = 0; i < nombrePokemons; i++) {
            System.out.print("  |      ▏╭╮▕━┻━▏╭╮▕     |");
        }
        System.out.println();
        System.out.print("    ");

        for (int i = 0; i < nombrePokemons; i++) {
            System.out.print("  |      ╲╰╯┈╲▂╱┈╰╯╱     |");
        }
        System.out.println();
        System.out.print("    ");

        for (int i = 0; i < nombrePokemons; i++) {
            System.out.print("  +----------------------+");
        }
        System.out.println();
    }

    public static void affichePokemon(List<Pokemon> list){
        int nombrePokemons = list.size();
        int largeurCase = 22; //Largeur de chaque case

        System.out.print("    ");

        for (int i = 0; i < nombrePokemons; i++) {
            System.out.print("  +----------------------+");
        }
        System.out.println();
        System.out.print("    ");

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
        System.out.print("    ");

        //Affichage.Affichage des PV des Pokémon
        for (int i = 0; i < nombrePokemons; i++) {
            String pv = list.get(i).getPv() + "/" + list.get(i).getPvMax();
            int longueurPV = pv.length();
            int espaceDeChaqueCote = (largeurCase - longueurPV) / 2 - 2;
            System.out.print(String.format("  | PV :%" + espaceDeChaqueCote + "s%s%" + espaceDeChaqueCote + "s|", "", pv, ""));
        }
        System.out.println();
        System.out.print("    ");

        //Affichage.Affichage des attaques des Pokémon
        for (int i = 0; i < nombrePokemons; i++) {
            String attaque = String.valueOf(list.get(i).getAttaque());
            int longueurAtt = attaque.length();
            int espaceDeChaqueCote = (largeurCase - longueurAtt) / 2 - 5;
            System.out.print(String.format("  | Attaque :%" + espaceDeChaqueCote + "s%s%" + espaceDeChaqueCote + "s|", "", attaque, ""));
        }
        System.out.println();
        System.out.print("    ");

        //Affichage.Affichage des types des Pokémon
        for (int i = 0; i < nombrePokemons; i++) {
            String type = list.get(i).getElementString();
            int longueurType = type.length();
            int espaceDeChaqueCote = (largeurCase - longueurType) / 2 - 3;
            System.out.print(String.format("  | Type :%" + espaceDeChaqueCote + "s%s%" + espaceDeChaqueCote + "s|", "", type, ""));
        }
        System.out.println();
        System.out.print("    ");

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
        System.out.print("    ");

        for (int i = 0; i < nombrePokemons; i++) {
            System.out.print("  +----------------------+");
        }
        System.out.println();
    }

    public static void afficheTerrainJoueur(List<Pokemon> list, int tailleTerrain){
        int largeurCase = 22; //Largeur de chaque case
        System.out.print("                                ");

        for (int i = 0; i < tailleTerrain; i++) {
            System.out.print("+----------------------+  ");
        }
        System.out.println();
        System.out.print("                                ");

        //Affichage.Affichage des noms des Pokémon
        for (int i = 0; i < tailleTerrain; i++) {
            try {
                String nom = list.get(i).getNom();
                int longueurNom = list.get(i).getNom().length();
                if (longueurNom % 2 == 1) {
                    nom = nom + " ";
                }
                int espaceDeChaqueCote = (largeurCase - longueurNom) / 2;
                System.out.print(String.format("|%" + espaceDeChaqueCote + "s%s%" + espaceDeChaqueCote + "s|", "", nom, ""));
            } catch (IndexOutOfBoundsException e) {
                System.out.print("|                      |  ");
            }
        }
        System.out.println();
        System.out.print("                                ");

        //Affichage.Affichage des PV des Pokémon
        for (int i = 0; i < tailleTerrain; i++) {
            try {
                String pv = list.get(i).getPv() + "/" + list.get(i).getPvMax();
                int longueurPV = pv.length();
                int espaceDeChaqueCote = (largeurCase - longueurPV) / 2 - 2;
                System.out.print(String.format("| PV :%" + espaceDeChaqueCote + "s%s%" + espaceDeChaqueCote + "s|", "", pv, ""));
            } catch (IndexOutOfBoundsException e) {
                System.out.print("|                      |  ");
            }
        }
        System.out.println();
        System.out.print("                                ");

        //Affichage.Affichage des attaques des Pokémon
        for (int i = 0; i < tailleTerrain; i++) {
            try {
                String attaque = String.valueOf(list.get(i).getAttaque());
                int longueurAtt = attaque.length();
                int espaceDeChaqueCote = (largeurCase - longueurAtt) / 2 - 5;
                System.out.print(String.format("| Attaque :%" + espaceDeChaqueCote + "s%s%" + espaceDeChaqueCote + "s|", "", attaque, ""));
            }
            catch (IndexOutOfBoundsException e) {
                System.out.print("|                      |  ");
            }
        }
        System.out.println();
        System.out.print("                                ");

        //Affichage.Affichage des types des Pokémon
        for (int i = 0; i < tailleTerrain; i++) {
            try {
            String type = list.get(i).getElementString();
            int longueurType = type.length();
            int espaceDeChaqueCote = (largeurCase - longueurType) / 2 - 3;
            System.out.print(String.format("| Type :%" + espaceDeChaqueCote + "s%s%" + espaceDeChaqueCote + "s|", "", type, ""));
            }
            catch (IndexOutOfBoundsException e) {
                System.out.print("|                      |  ");
            }
        }
        System.out.println();
        System.out.print("                                ");

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
                System.out.print(String.format("| Pouvoir : %" + espaceDeChaqueCote + "s%s%" + espaceDeChaqueCote + "s|", "", pouvoir, ""));
            } catch (IndexOutOfBoundsException e) {
                System.out.print("|                      |  ");
            }
        }
        System.out.println();
        System.out.print("                                ");

        for (int i = 0; i < tailleTerrain; i++) {
            System.out.print("+----------------------+  ");
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
        accueil();
    }

    public static String selectionPokemon(List<Pokemon> list){
        String txt = "";
        for (int j = 0; j<list.size();j++)
        {
            if (j != list.size()-1)
            {
                txt += j+1+"."+list.get(j).getNom() + " / ";
            }
            else {txt+=j+1+"."+list.get(j).getNom()+" / "+(j+2)+".Rien sélectionner";}
        }
        return txt;
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
