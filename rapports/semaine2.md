# Rapport de Conception: Projet Pokémon
=======================================

## Introduction
Nous avons complètement amélioré le projet. En codant toutes les classes nous avons pris conscience des erreurs précédentes et nous avons commencé à tout corriger.
Pour le moment tout n'est pas prêt, il faut améliorer les classes et les méthodes pour que le jeu soit jouable et clair, maus nous sommes sur la bonne voix.

## Architecture du Projet
Le projet est organisé autour de plusieurs classes principales, chacune représentant un aspect spécifique du jeu. Voici une présentation détaillée de chaque classe :

### Pokemon
La classe Pokemon représente une créature Pokémon avec ses attributs tels que le nom, les points de vie, l'attaque et l'élément. Les méthodes incluent:
- **attaquer(Pokemon pokemonCible)**: Permet à un Pokémon d'attaquer un autre Pokémon.
- **subirDegats(int degats)**: Fait subir des dégâts au Pokémon.
- **estVivant()**: Vérifie si le Pokémon est en vie.
- **getElementString()**: Renvoie le nom de l'élément du Pokémon.
- **estAvantager(Pokemon pokemon)**: Vérifie si le Pokémon a un avantage élémentaire sur un autre Pokémon.

### Joueur
La classe Joueur modélise un joueur du jeu avec une main de Pokémon, une pioche et une défense. Les sous-classes Humain et Ordinateur définissent des comportements spécifiques pour les joueurs humains et l'ordinateur. Les méthodes incluent:
- **piocherPokemon()**: Permet au joueur de piocher de nouveaux Pokémon.
- **attaquer(Terrain terrain, Joueur adversaire)**: Méthode abstraite pour attaquer un autre joueur.
- **aPerdu()**: Vérifie si le joueur a perdu.
- **defausser(Pokemon pokemon)**: Ajoute un Pokémon à la défense du joueur.

### Pioche et Main
La classe Pioche gère la pioche de nouveaux Pokémon pour les joueurs, tandis que la classe Main contient les Pokémon actuellement en jeu pour un joueur. Les méthodes incluent:
- **piocherMain(Main main)**: Pioche un Pokémon dans la pioche et l'ajoute à la main du joueur.
- **ajouterPokemon(Pokemon pokemon)** et **retirerPokemon(Pokemon pokemon)**: Gèrent l'ajout et la suppression de Pokémon dans la main du joueur.

### Terrain
La classe Terrain représente le terrain de jeu sur lequel les Pokémon sont placés. Les méthodes incluent:
- **placerPokemons(Joueur joueur, int pokemon)**: Place un Pokémon sur le terrain pour un joueur donné.
- **getPokemon(Joueur j, int pokemon)**: Renvoie le Pokémon d'un joueur donné sur le terrain.
- **retirerPokemon(Joueur joueur, int pokemon)**: Retire un Pokémon du terrain pour un joueur donné.
- **estVide(Joueur joueur)**: Vérifie si le terrain d'un joueur est vide.

### Jeu et Tour
La classe Jeu gère le déroulement du jeu, tandis que la classe Tour gère le déroulement d'un tour de jeu pour un joueur. Les méthodes incluent:
- **jouer()**: Lance le déroulement du jeu.
- **nouveauTour()**: Démarre un nouveau tour de jeu.

### Autres Classes
D'autres classes telles que Defausse et Elements sont utilisées pour gérer la défense des joueurs et définir les éléments des Pokémon respectivement.

## Justification des Méthodes
Tout n'est pas encore très distinct ou clair, mais nous procédons classe par classe, la suite arrivera bientôt.

## Conclusion
Le projet a progressé de manière significative depuis la semaine dernière, avec une implémentation plus avancée des classes et des méthodes. Nous continuons à travailler sur l'amélioration du jeu et à résoudre les problèmes de conception pour garantir un système fonctionnel et structuré.

