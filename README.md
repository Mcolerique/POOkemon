# 🐉 POO(kemon) Project

## 📘 À propos du projet

Ce projet a été mené durant ma **première année de BUT Informatique** à l’**IUT Robert Schuman**, sous la supervision des enseignants du module de **Programmation Orientée Objet (POO)**.  
Il s’inscrit dans un cadre académique et vise à appliquer les principes de la POO à travers la conception et le développement complet d’un **jeu de cartes inspiré de Pokémon**, jouable dans un terminal Java.

> 📄 Le README officiel fourni par les enseignants peut être trouvé [ici](./README_OLD.md).

---

## 🧩 Organisation

- 👥 Travail en **binôme**, avec **[Gaétan H.](https://github.com/charnateg)**
- 🕓 **Durée :** 5 semaines
- 💻 **Langage principal :** Java
- 🎯 **Objectif :** Développer un jeu fonctionnel tout en respectant les bonnes pratiques de conception orientée objet (UML, modularité, héritage, polymorphisme).

---

## 🎮 Description du jeu

Le **POOkemon Project** est un jeu **tour par tour** dans lequel un joueur humain affronte un **ordinateur**.  
Chaque joueur dispose d’un ensemble de **Pokémons** qu’il peut placer, attaquer et gérer à l’aide de mécaniques inspirées du jeu de cartes Pokémon.

### ⚔️ Principe du jeu
- Deux joueurs : **le joueur humain** et **l’ordinateur (IA)**.
- Chaque joueur possède :
    - Un **terrain** (3 Pokémons maximum)
    - Une **main** (5 Pokémons maximum)
    - Une **pioche**
    - Une **défausse**
- Le but est simple : **éliminer tous les Pokémons adverses**.

### 🌍 Les affinités élémentaires

| Élément | Avantage sur | Désavantage contre |
|:--------|:--------------|:-------------------|
| 🌱 Terre | Eau | Air |
| 💧 Eau | Feu | Terre |
| 🔥 Feu | Air | Eau |
| 🌬️ Air | Terre | Feu |

- **Avantage :** +10 dégâts
- **Désavantage :** −10 dégâts

### 🤖 IA stratégique
L’intelligence artificielle choisit sa cible selon :
1. L’avantage d’affinité
2. Le plus faible nombre de points de vie adverses
3. Un choix aléatoire en cas d’égalité

---

## 🔮 Phase 2 — Les pouvoirs

En phase 2, les Pokémons peuvent obtenir des **pouvoirs spéciaux** aléatoires.  
Ces pouvoirs modifient le cours des combats et ajoutent une **profondeur tactique** au jeu.

### ✨ Exemples de pouvoirs
- 💪 **Berserk** : double la force d’attaque pour un tour
- ❤️ **Soin simple** : rend 30 PV à un allié chaque tour
- ☠️ **Kamikaze** : élimine le Pokémon attaquant et sa cible
- 🧊 **Protection** : immunise un Pokémon contre les attaques pendant un tour
- ⚗️ **Empoisonnement** : fait perdre 10 PV à un adversaire à chaque tour
- 🔁 **Déjà-vu** : permet à un Pokémon de rejouer immédiatement

---

## 🛠️ Technologies utilisées

- ☕ **Java** — Langage principal du projet
- 🧱 **Programmation Orientée Objet (POO)** — Héritage, encapsulation, polymorphisme
- 📊 **UML (PlantUML)** — Modélisation du code et des relations de classes
- 🧪 **Tests unitaires** — Validation du comportement des attaques et pouvoirs

---

## ⚙️ Comment exécuter le projet

### 🔧 Prérequis
- **Java JDK 8+** installé
- Un IDE compatible Java (ex : **IntelliJ IDEA**, **Eclipse**, **VS Code**)

---

### 💾 Installation

Clonez le dépôt sur votre machine :

```bash
git clone https://github.com/Mcolerique/POOkemon.git
```

### ▶️ Exécution via IntelliJ IDEA

1. Ouvrez le projet dans IntelliJ IDEA

2. Naviguez vers src/Start.java

3. Faites clic droit → Run 'Main'

Le jeu se lance dans la console intégrée

### Compilation et exécution manuelle

```bash
# Compiler tous les fichiers Java
javac -d out $(find src -name "*.java")
```

```bash
# Exécuter le jeu
java -cp out Start
```
