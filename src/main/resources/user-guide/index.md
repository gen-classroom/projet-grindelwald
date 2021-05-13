title: Manuel utilisateur
author: project grindelwald
date: 2021-03-10
---
# Arancia

Ce projet est géré dans le cadre du cours *Génie logiciel* de la HEIG-VD[¹]
d'Yverdon-les-Bains.

[¹]: Haute École d'Ingénierie et de Gestion du Canton de Vaud

# Comment utiliser ce programme

Voici les différentes informations permettants l'utlisation du logiciel produit
pendant le cours.

## Saisie de données structurées

L'utlisateur devra saisir les différentes données structurées telles que la
configuration, les métadonnées, etc, dans un ficihier `config.yaml`.

## Saisie de contenu

L'utilisateur pourra saisir du contenu dans un fichier Markdown qui sera par la
suite compilé en HTML.

## Format des pages

L'utilisateur pourra créer des pages ressemblant au style suivant :

```markdown
titre: Mon premier article
auteur: Bertil Chapuis
date: 2021-03-10
---
# Mon premier article

## Mon sous-titre
Le contenu de mon article.
![Une image](./image.png)
```

Il y aura donc besoin d'un titre, d'un auteur ainsi que d'une date dans les
métadonnées. Le reste sera du contenu écrit en Markdown.

## Les commandes disponibles

### Affichage de la version

L'utlisateur pourra afficher la version du programmea avec la commande

```bash
arancia --version
```

ou

```bash
arancia -V
```

### Initialisation du site statique

Pour initialiser un site statique, l'utlisateur devra exécuter la commande suivante

```bash
arancia new chemin/le/dossier/du/site
```

### Compiler le site statique

Pour compiler le site statique, l'utlisateur devra exécuter la commande

```bash
arancia build chemin/vers/le/dossier/du/site
```

Cela aura pour effet de créer un sous dossier `build` dans le dossier du site
contenant le contenu des documents Markdown en HTML.

### Tester le site statique

En exécutant la commande

```bash
arancia serve chemin/vers/le/dossier/du/site
```

l'utilisateur pourra tester ce que donnerait le site statique comme il
apparaîtra une fois qu'il sera en ligne. Il est cependant de la responsabilité
de l'utlisateur de mettre son site statique sur un serveur Web qu'il aura
préalablement configurer, le programme Arancia ne mettant pas à disposition un
serveur Web de qualité production.

### Nettoyer le site statique

Pour nettoyer le site statique, il faudra exécuter la commande

```bash
arancia clean chemin/vers/le/dossier/du/site
```

Cela aura pour effet de supprimer le sous dossier `build` créé lors de la
compilation.