package affichage;

import java.util.List;
import joueurs.*;
import pokemons.*;

/**
 * La classe Affichage contient des méthodes statiques pour afficher divers éléments de jeu dans le terminal.
 */
public class Affichage {

    static int largeurTerminal = 140;

    //METHODES

    /**
     * Affiche un titre d'accueil stylisé et centré dans le terminal.
     *
     * <p>La méthode génère un titre d'accueil en ASCII art et l'affiche centré dans le terminal.
     * Le texte est coloré en jaune (code couleur ANSI 33m).</p>
     *
     * @see #afficherTexteCentre(String, int)
     * @see #mettreEnCouleur(String, String)
     */
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




    /**
     * Affiche un message de fin de partie indiquant le gagnant et l'état de ses Pokémons selon si le joueur est humain ou non.
     *
     * @param gagnant Le joueur gagnant de la partie.
     * @see #afficherTexteCentre(String, int)
     * @see #mettreEnGras(String)
     * @see #mettreEnCouleur(String, String)
     * @see #accueil()
     */
    public static void finDePartie(Joueur gagnant){
        try {
            Thread.sleep(2000);
            if (gagnant.getClass() == Humain.class) {
                afficherTexteCentre(mettreEnGras(mettreEnCouleur("Vous avez gagné !! "+(gagnant.getPioche().getPioche().size()+gagnant.getMain().getNbPokemon())+" de vos pokemons sont en pleine santé, vous la petez pas trop c'est pas dur", "[38;5;13m")), largeurTerminal);
            }
            else {
                afficherTexteCentre(mettreEnGras(mettreEnCouleur("Vous avez perdu !! "+(gagnant.getPioche().getPioche().size()+gagnant.getMain().getNbPokemon())+" de ses pokemons sont en pleine santé, vous êtes vraiment nul", "[38;5;13m")), largeurTerminal);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }




    /**
     * Génère une chaîne de caractères où chaque Pokémon est numéroté et listé avec des options de sélection.
     *
     * @param list La liste de Pokémons à afficher.
     * @return Une chaîne de caractères représentant les options de sélection des Pokémons.
     */
    public static String selectionPokemon(List<Pokemon> list){
        String txt = "";
        for (int j = 0; j<list.size();j++)
        {
            if (j != list.size()-1)
            {
                txt += j+1+"."+list.get(j).getNom() + "   ";
            }
            else {txt+=j+1+"."+list.get(j).getNom()+"   "+(j+2)+".Rien sélectionner";}
        }
        return txt;
    }




    /**
     * Affiche l'état actuel du terrain de jeu, y compris les cartes en main, les pioches et les défausses pour les deux joueurs.
     *
     * @param terrain Le terrain de jeu.
     * @param j1 Le premier joueur.
     * @param j2 Le deuxième joueur.
     * @see #afficherTexteCentre(String, int)
     * @see #mettreEnCouleur(String, String)
     * @see #mettreEnGras(String)
     * @see #afficheMainOrdinateur(List)
     * @see #afficheTerrainJoueur(List, int)
     * @see #affichePokemon(List)
     */
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
            afficherTexteCentre(mettreEnCouleur("\n+----- Pioche -----+            +---- Défausse ----+", "\u001B[32m"), largeurTerminal);
            afficherTexteCentre(mettreEnCouleur("|        " + piocheJ1 + "        |            |        " + defausseJ1 + "        |", "\u001B[32m"), largeurTerminal);
            afficherTexteCentre(mettreEnCouleur("+------------------+            +------------------+", "\u001B[32m"), largeurTerminal);
            afficherTexteCentre(mettreEnCouleur("\nMain du Joueur 1", "\u001B[36m"), largeurTerminal);
            afficheMainOrdinateur(j1.getMain().getListePokemon());
            afficheTerrainJoueur(terrain.getPokemonsJoueur(j1), j1.getTailleTerrain());
            afficherTexteCentre(mettreEnCouleur("+------------------------------------------------------------- TERRAIN JOUEUR 1 -------------------------------------------------------------+\n", "\u001B[31m"), largeurTerminal);
            System.out.println();
            afficherTexteCentre(mettreEnCouleur("+------------------------------------------------------------- TERRAIN JOUEUR 2 -------------------------------------------------------------+\n", "\u001B[31m"), largeurTerminal);
            afficheTerrainJoueur(terrain.getPokemonsJoueur(j2), j2.getTailleTerrain());
            affichePokemon(j2.getMain().getListePokemon());
            afficherTexteCentre(mettreEnCouleur("Main du Joueur 2", "\u001B[36m"), largeurTerminal);
            afficherTexteCentre(mettreEnCouleur("\n+----- Pioche -----+            +---- Défausse ----+", "\u001B[32m"), largeurTerminal);
            afficherTexteCentre(mettreEnCouleur("|        " + piocheJ2 + "        |            |        " + defausseJ2 + "        |", "\u001B[32m"), largeurTerminal);
            afficherTexteCentre(mettreEnCouleur("+------------------+            +------------------+\n","\u001B[32m"), largeurTerminal);
        }
        else {
            afficherTexteCentre(mettreEnCouleur("+----- Pioche -----+            +---- Défausse ----+", "\u001B[32m"), largeurTerminal);
            afficherTexteCentre(mettreEnCouleur("|        " + piocheJ2 + "        |            |        " + defausseJ2 + "        |", "\u001B[32m"), largeurTerminal);
            afficherTexteCentre(mettreEnCouleur("+------------------+            +------------------+\n","\u001B[32m"), largeurTerminal);
            afficherTexteCentre(mettreEnCouleur("\nMain du Joueur 2", "\u001B[36m"), largeurTerminal);
            afficheMainOrdinateur(j2.getMain().getListePokemon());
            afficheTerrainJoueur(terrain.getPokemonsJoueur(j2), j2.getTailleTerrain());
            afficherTexteCentre(mettreEnCouleur("+------------------------------------------------------------- TERRAIN JOUEUR 2 -------------------------------------------------------------+\n", "\u001B[31m"), largeurTerminal);
            System.out.println();
            afficherTexteCentre(mettreEnCouleur("+------------------------------------------------------------- TERRAIN JOUEUR 1 -------------------------------------------------------------+\n","\u001B[31m"), largeurTerminal);
            afficheTerrainJoueur(terrain.getPokemonsJoueur(j1), j1.getTailleTerrain());
            affichePokemon(j1.getMain().getListePokemon());
            afficherTexteCentre(mettreEnCouleur("Main du Joueur 1\n", "\u001B[36m"), largeurTerminal);
            afficherTexteCentre(mettreEnCouleur("+----- Pioche -----+            +---- Défausse ----+", "\u001B[32m"), largeurTerminal);
            afficherTexteCentre(mettreEnCouleur("|        " + piocheJ1 + "        |            |        " + defausseJ1 + "        |", "\u001B[32m"), largeurTerminal);
            afficherTexteCentre(mettreEnCouleur("+------------------+            +------------------+\n", "\u001B[32m"), largeurTerminal);
        }
    }




    /**
     * Applique une couleur ANSI à une chaîne de caractères.
     *
     * @param texte La chaîne de caractères à colorer.
     * @param couleur Le code de couleur ANSI à appliquer.
     * @return La chaîne de caractères colorée avec le code ANSI et réinitialisée à la fin.
     */
    public static String mettreEnCouleur(String texte, String couleur) {
        String ANSI_RESET = "\u001B[0m";
        return couleur + texte + ANSI_RESET;
    }




    /**
     * Applique un style gras à une chaîne de caractères en utilisant les codes ANSI.
     *
     * @param texte La chaîne de caractères à styliser en gras.
     * @return La chaîne de caractères stylisée en gras avec le code ANSI et réinitialisée à la fin.
     */
    public static String mettreEnGras(String texte) {
        String ANSI_BOLD = "\u001B[1m";
        String ANSI_RESET = "\u001B[0m";
        return ANSI_BOLD + texte + ANSI_RESET;
    }




    /**
     * Affiche un message sur la console.
     *
     * @param message La chaîne de caractères à afficher sur la console.
     */
    public static void afficher(String message){
        System.out.println(message);
    }




    /**
     * Affiche un texte centré dans le terminal.
     *
     * @param texte La chaîne de caractères à afficher centrée.
     * @param largeurTerminal La largeur du terminal utilisée pour centrer le texte.
     */
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




    /**
     * Affiche une représentation graphique des cartes en main de l'ordinateur.
     *
     * @param list La liste de Pokémons représentant la main de l'ordinateur.
     * @see #mettreEnCouleur(String, String)
     */
    public static void afficheMainOrdinateur(List<Pokemon> list){
        int nombrePokemons = list.size();

        System.out.print("    ");

        for (int i = 0; i < 5; i++) {
            System.out.print(mettreEnCouleur("  +----------------------+", "\u001B[36m"));
        }
        System.out.println();
        System.out.print("    ");

        for (int i = 0; i < 5; i++) {
            System.out.print(mettreEnCouleur("  |    ▕▔▔╲       ╱▔▔▏   |", "\u001B[36m"));
        }
        System.out.println();
        System.out.print("    ");

        for (int i = 0; i < 5; i++) {
            System.out.print(mettreEnCouleur("  |     ╲┈┈╲╱▔▔▔╲╱┈┈╱    |", "\u001B[36m"));
        }
        System.out.println();
        System.out.print("    ");

        for (int i = 0; i < 5; i++) {
            System.out.print(mettreEnCouleur("  |      ╲┈╭╮┈┈┈╭╮┈╱     |", "\u001B[36m"));
        }
        System.out.println();
        System.out.print("    ");

        for (int i = 0; i < 5; i++) {
            System.out.print(mettreEnCouleur("  |      ╱┈╰╯┈▂┈╰╯┈╲     |", "\u001B[36m"));
        }
        System.out.println();
        System.out.print("    ");

        for (int i = 0; i < 5; i++) {
            System.out.print(mettreEnCouleur("  |      ▏╭╮▕━┻━▏╭╮▕     |", "\u001B[36m"));
        }
        System.out.println();
        System.out.print("    ");

        for (int i = 0; i < 5; i++) {
            System.out.print(mettreEnCouleur("  |      ╲╰╯┈╲▂╱┈╰╯╱     |", "\u001B[36m"));
        }
        System.out.println();
        System.out.print("    ");

        for (int i = 0; i < 5; i++) {
            System.out.print(mettreEnCouleur("  +----------------------+", "\u001B[36m"));
        }
        System.out.println();
    }




    /**
     * Affiche une représentation graphique des Pokémons dans la main d'un joueur.
     *
     * @param list La liste de Pokémons à afficher.
     * @see #mettreEnCouleur(String, String)
     */
    public static void affichePokemon(List<Pokemon> list){
        int nombrePokemons = list.size();
        int largeurCase = 22;
        System.out.print("    ");
        for (int i = 0; i < 5; i++) {
            System.out.print(mettreEnCouleur("  +----------------------+", "\u001B[36m"));
        }
        System.out.println();
        System.out.print("      ");

        //Noms des Pokémons
        for (int i = 0; i < 5; i++) {
            try {
                String nom = list.get(i).getNom();
                int longueurNom = list.get(i).getNom().length();
                if (longueurNom % 2 == 1) {
                    nom = nom + " ";
                }
                int espaceDeChaqueCote = (largeurCase - longueurNom) / 2;
                System.out.print(mettreEnCouleur(String.format("|%" + espaceDeChaqueCote + "s%s%" + espaceDeChaqueCote + "s|  ", "", nom, ""), "\u001B[36m"));
            } catch (IndexOutOfBoundsException e) {
                System.out.print(mettreEnCouleur("|                      |  ", "\u001B[36m"));
            }
        }
        System.out.println();
        System.out.print("      ");

        //PV des Pokémons
        for (int i = 0; i < 5; i++) {
            try {
                String pv = list.get(i).getPv() + "/" + list.get(i).getPvMax();
                int longueurPV = pv.length();
                if (longueurPV % 2 == 0) {
                    pv = pv + " ";
                }
                int espaceDeChaqueCote = (largeurCase - longueurPV) / 2 - 2;
                System.out.print(mettreEnCouleur(String.format("| PV :%" + espaceDeChaqueCote + "s%s%" + espaceDeChaqueCote + "s|  ", "", pv, ""), "\u001B[36m"));
            } catch (IndexOutOfBoundsException e) {
                System.out.print(mettreEnCouleur("|                      |  ", "\u001B[36m"));
            }
        }
        System.out.println();
        System.out.print("      ");

        //Attaques des Pokémons
        for (int i = 0; i < 5; i++) {
            try {
                String attaque = String.valueOf(list.get(i).getAttaque());
                int longueurAtt = attaque.length();
                int espaceDeChaqueCote = (largeurCase - longueurAtt) / 2 - 5;
                System.out.print(mettreEnCouleur(String.format("| Attaque :%" + espaceDeChaqueCote + "s%s%" + espaceDeChaqueCote + "s|  ", "", attaque, ""), "\u001B[36m"));
            } catch (IndexOutOfBoundsException e) {
                System.out.print(mettreEnCouleur("|                      |  ", "\u001B[36m"));
            }
        }
        System.out.println();
        System.out.print("      ");

        //Types des Pokémons
        for (int i = 0; i < 5; i++) {
            try {
                String type = list.get(i).getElementString();
                int longueurType = type.length();
                int espaceDeChaqueCote = (largeurCase - longueurType) / 2 - 3;
                System.out.print(mettreEnCouleur(String.format("| Type :%" + espaceDeChaqueCote + "s%s%" + espaceDeChaqueCote + "s|  ", "", type, ""), "\u001B[36m"));
            } catch (IndexOutOfBoundsException e) {
                System.out.print(mettreEnCouleur("|                      |  ", "\u001B[36m"));
            }
        }
        System.out.println();
        System.out.print("      ");

        //Pouvoirs des Pokemons
        for (int i = 0; i < 5; i++) {
            try {
                String pouvoir = list.get(i).getNomPouvoir();
                int longueurPouvoir = pouvoir.length();
                if (longueurPouvoir % 2 == 1) {
                    pouvoir += " ";
                    longueurPouvoir = pouvoir.length();
                }
                int espaceDisponible = largeurCase - longueurPouvoir - 9;
                int espaceDeChaqueCote = espaceDisponible / 2;
                espaceDeChaqueCote = Math.max(espaceDeChaqueCote, 0);
                if (espaceDisponible < 0) {
                    pouvoir = pouvoir.substring(0, largeurCase - 9 - 1) + ".";
                    espaceDeChaqueCote = 0;
                }
                System.out.print(mettreEnCouleur(String.format("| Pouvoir :%" + espaceDeChaqueCote + "s%s%" + espaceDeChaqueCote + "s|  ", "", pouvoir, ""), "\u001B[36m"));
            } catch (IndexOutOfBoundsException e) {
                System.out.print(mettreEnCouleur("|                      |  ", "\u001B[36m"));
            }
        }
        System.out.println();
        System.out.print("    ");
        for (int i = 0; i < 5; i++) {
            System.out.print(mettreEnCouleur("  +----------------------+", "\u001B[36m"));
        }
        System.out.println();
    }




    /**
     * Affiche une représentation graphique des Pokémons sur le terrain d'un joueur.
     *
     * @param list La liste de Pokémons à afficher.
     * @param tailleTerrain La taille du terrain, définissant le nombre de cases à afficher.
     * @see #mettreEnCouleur(String, String)
     */
    public static void afficheTerrainJoueur(List<Pokemon> list, int tailleTerrain){
        int largeurCase = 22;
        System.out.print("                                ");
        for (int i = 0; i < tailleTerrain; i++) {
            System.out.print(mettreEnCouleur("+----------------------+  ", "\u001B[31m"));
        }
        System.out.println();
        System.out.print("                                ");

        //Noms des Pokémons
        for (int i = 0; i < tailleTerrain; i++) {
            try {
                String nom = list.get(i).getNom();
                int longueurNom = list.get(i).getNom().length();
                if (longueurNom % 2 == 1) {
                    nom = nom + " ";
                }
                int espaceDeChaqueCote = (largeurCase - longueurNom) / 2;
                System.out.print(mettreEnCouleur(String.format("|%" + espaceDeChaqueCote + "s%s%" + espaceDeChaqueCote + "s|  ", "", nom, ""), "\u001B[31m"));
            } catch (IndexOutOfBoundsException e) {
                System.out.print(mettreEnCouleur("|                      |  ", "\u001B[31m"));
            }
        }
        System.out.println();
        System.out.print("                                ");

        //PV des Pokémons
        for (int i = 0; i < tailleTerrain; i++) {
            try {
                String pv = list.get(i).getPv() + "/" + list.get(i).getPvMax();
                int longueurPV = pv.length();
                if (longueurPV % 2 == 0) {
                    pv = pv + " ";
                    int espaceDeChaqueCote = (largeurCase - longueurPV) / 2 - 3;
                    System.out.print(mettreEnCouleur(String.format("| PV :%" + espaceDeChaqueCote + "s%s%" + espaceDeChaqueCote + "s|  ", "", pv, ""), "\u001B[31m"));
                }
                else {
                    int espaceDeChaqueCote = (largeurCase - longueurPV) / 2 - 2;
                    System.out.print(mettreEnCouleur(String.format("| PV :%" + espaceDeChaqueCote + "s%s%" + espaceDeChaqueCote + "s|  ", "", pv, ""), "\u001B[31m"));
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.print(mettreEnCouleur("|                      |  ", "\u001B[31m"));
            }
        }
        System.out.println();
        System.out.print("                                ");

        //Attaques des Pokémons
        for (int i = 0; i < tailleTerrain; i++) {
            try {
                String attaque = String.valueOf(list.get(i).getAttaque());
                int longueurAtt = attaque.length();
                int espaceDeChaqueCote = (largeurCase - longueurAtt) / 2 - 5;
                System.out.print(mettreEnCouleur(String.format("| Attaque :%" + espaceDeChaqueCote + "s%s%" + espaceDeChaqueCote + "s|  ", "", attaque, ""), "\u001B[31m"));
            }
            catch (IndexOutOfBoundsException e) {
                System.out.print(mettreEnCouleur("|                      |  ", "\u001B[31m"));
            }
        }
        System.out.println();
        System.out.print("                                ");

        //Types des Pokémons
        for (int i = 0; i < tailleTerrain; i++) {
            try {
            String type = list.get(i).getElementString();
            int longueurType = type.length();
            int espaceDeChaqueCote = (largeurCase - longueurType) / 2 - 3;
            System.out.print(mettreEnCouleur(String.format("| Type :%" + espaceDeChaqueCote + "s%s%" + espaceDeChaqueCote + "s|  ", "", type, ""), "\u001B[31m"));
            }
            catch (IndexOutOfBoundsException e) {
                System.out.print(mettreEnCouleur("|                      |  ", "\u001B[31m"));
            }
        }
        System.out.println();
        System.out.print("                                ");

        //Pouvoirs des Pokemons
        for (int i = 0; i < tailleTerrain; i++) {
            try {
                String pouvoir = list.get(i).getNomPouvoir();
                int longueurPouvoir = pouvoir.length();
                if (longueurPouvoir % 2 == 0) {
                    pouvoir += " ";
                    longueurPouvoir = pouvoir.length();
                }
                int espaceDisponible = largeurCase - longueurPouvoir - 10;
                int espaceDeChaqueCote = espaceDisponible / 2;
                espaceDeChaqueCote = Math.max(espaceDeChaqueCote, 0);
                if (espaceDisponible < 0) {
                    pouvoir = pouvoir.substring(0, largeurCase - 9 - 1) + ".";
                    espaceDeChaqueCote = 0;
                }
                System.out.print(mettreEnCouleur(String.format("| Pouvoir : %" + espaceDeChaqueCote + "s%s%" + espaceDeChaqueCote + "s|  ", "", pouvoir, ""), "\u001B[31m"));
            } catch (IndexOutOfBoundsException e) {
                System.out.print(mettreEnCouleur("|                      |  ", "\u001B[31m"));
            }
        }
        System.out.println();
        System.out.print("                                ");

        for (int i = 0; i < tailleTerrain; i++) {
            System.out.print(mettreEnCouleur("+----------------------+  ", "\u001B[31m"));
        }
        System.out.println();
    }




    /**
     * Affiche un message indiquant le numéro du tour en cours dans un cadre, coloré et centré dans le terminal.
     *
     * @param tour La chaîne de caractères représentant le numéro du tour à afficher.
     * @see #afficherTexteCentre(String, int)
     * @see #mettreEnCouleur(String, String)
     * @see #mettreEnGras(String)
     */
    public static void afficheNbTour(String tour) {
        int largeurCadre = tour.length() + 2;

        try {
            Thread.sleep(3000);
            String ligneSuperieure = "+" + "-".repeat(largeurCadre) + "+";
            String ligneTitre = String.format("| %-" + (largeurCadre - 2) + "s |", tour);
            String ligneInferieure = "+" + "-".repeat(largeurCadre) + "+";
            System.out.println("-".repeat(140));
            afficherTexteCentre(mettreEnGras(mettreEnCouleur(ligneSuperieure, "[38;5;13m")), largeurTerminal);
            afficherTexteCentre(mettreEnGras(mettreEnCouleur(ligneTitre, "[38;5;13m")), largeurTerminal);
            afficherTexteCentre(mettreEnGras(mettreEnCouleur(ligneInferieure, "[38;5;13m")), largeurTerminal);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }




    /**
     * Affiche la liste des Pokémons dans la défausse d'un joueur.
     *
     * @param joueur Le joueur dont la défausse est affichée.
     * @param defausse La liste des Pokémons dans la défausse du joueur.
     */
    public static void afficheDefausse(Joueur joueur, List<Pokemon> defausse) {
        System.out.println("Défausse de " + joueur.getNom() + " :");
        for (int i = 0; i < defausse.size(); i++) {
            System.out.println((i + 1) + ". " + defausse.get(i).getNom());
        }
    }
}