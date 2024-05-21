# Rapport de Conception: Projet Pokémon
=======================================

## Introduction
Nous avons complètement amélioré le projet. En codant toutes les classes nous avons pris conscience des erreurs précédentes et nous avons commencé à tout corriger.
Pour le moment tout n'est pas prêt, il faut améliorer les classes et les méthodes pour que le jeu soit jouable et clair, maus nous sommes sur la bonne voix.

## Architecture du Projet
Le projet est organisé autour de plusieurs classes principales, chacune représentant un aspect spécifique du jeu. Voici une présentation détaillée de chaque classe :

### pokemon.Pokemon
La classe pokemon.Pokemon représente une créature Pokémon avec ses attributs tels que le nom, les points de vie, l'attaque et l'élément. Les méthodes incluent:
- **attaquer(pokemon.Pokemon pokemonCible)**: Permet à un Pokémon d'attaquer un autre Pokémon.
- **subirDegats(int degats)**: Fait subir des dégâts au Pokémon.
- **estVivant()**: Vérifie si le Pokémon est en vie.
- **getElementString()**: Renvoie le nom de l'élément du Pokémon.
- **estAvantager(pokemon.Pokemon pokemon)**: Vérifie si le Pokémon a un avantage élémentaire sur un autre Pokémon.

### joueurs.Joueur
La classe joueurs.Joueur modélise un joueur du jeu avec une main de Pokémon, une pioche et une défense. Les sous-classes joueurs.Humain et joueurs.Ordinateur définissent des comportements spécifiques pour les joueurs humains et l'ordinateur. Les méthodes incluent:
- **piocherPokemon()**: Permet au joueur de piocher de nouveaux Pokémon.
- **attaquer(joueurs.Terrain terrain, joueurs.Joueur adversaire)**: Méthode abstraite pour attaquer un autre joueur.
- **aPerdu()**: Vérifie si le joueur a perdu.
- **defausser(pokemon.Pokemon pokemon)**: Ajoute un Pokémon à la défense du joueur.

### joueurs.Pioche et joueurs.Main
La classe joueurs.Pioche gère la pioche de nouveaux Pokémon pour les joueurs, tandis que la classe joueurs.Main contient les Pokémon actuellement en jeu pour un joueur. Les méthodes incluent:
- **piocherMain(joueurs.Main main)**: joueurs.Pioche un Pokémon dans la pioche et l'ajoute à la main du joueur.
- **ajouterPokemon(pokemon.Pokemon pokemon)** et **retirerPokemon(pokemon.Pokemon pokemon)**: Gèrent l'ajout et la suppression de Pokémon dans la main du joueur.

### joueurs.Terrain
La classe joueurs.Terrain représente le terrain de jeu sur lequel les Pokémon sont placés. Les méthodes incluent:
- **placerPokemons(joueurs.Joueur joueur, int pokemon)**: Place un Pokémon sur le terrain pour un joueur donné.
- **getPokemon(joueurs.Joueur j, int pokemon)**: Renvoie le Pokémon d'un joueur donné sur le terrain.
- **retirerPokemon(joueurs.Joueur joueur, int pokemon)**: Retire un Pokémon du terrain pour un joueur donné.
- **estVide(joueurs.Joueur joueur)**: Vérifie si le terrain d'un joueur est vide.

### jeu.Jeu et jeu.Tour
La classe jeu.Jeu gère le déroulement du jeu, tandis que la classe jeu.Tour gère le déroulement d'un tour de jeu pour un joueur. Les méthodes incluent:
- **jouer()**: Lance le déroulement du jeu.
- **nouveauTour()**: Démarre un nouveau tour de jeu.

### Autres Classes
D'autres classes telles que joueurs.Defausse et pokemon.Elements sont utilisées pour gérer la défense des joueurs et définir les éléments des Pokémon respectivement.

## Justification des Méthodes
Tout n'est pas encore très distinct ou clair, mais nous procédons classe par classe, la suite arrivera bientôt.

## Conclusion
Le projet a progressé de manière significative depuis la semaine dernière, avec une implémentation plus avancée des classes et des méthodes. Nous continuons à travailler sur l'amélioration du jeu et à résoudre les problèmes de conception pour garantir un système fonctionnel et structuré.

