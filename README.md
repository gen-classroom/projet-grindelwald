# Arancia

Ce projet est géré dans le cadre du cours de _Génie logiciel_ donné à
l'[HEIG-VD][HEIG-VD].

## Description fonctionnelle du projet
Ce projet met en place la gestion d'un site statique d'un utilisateur.
L'utilisateur a à disposition les commandes suivantes :
- ___build___ : Cette commande permet de compiler le site statique. Elle
convertit le contenu de chaque page du site en fichier HTML et le met dans le
sous-dossier _build_. En revanche, les fichiers de configuration n'y
figureront pas.
- ___init___ : Cette commande va réinitialiser le site. Elle va supprimer le
sous-dossier _build_ créé par la première commande, s'il existe.
- ___serve___ : Cette commande va afficher le résultat de la commande ___build___
dans un navigateur Web.
- ___-V___,___--version___ : Cette commande va afficher la version du site.

En outre de toutes ces commandes, nous avons ajouté une étape effectuant la
compilation dans la CI. Cette étape empêche le *merge* d'une branche de
développement dans la branche principale avec un état instable.
 
## Execution et déploiement

L'application peut être lancée à l'aide de la commande suivante :

```
$ mvn compile exec:java
```

Un jar autonome peut être géneré à l'aide de la commande suivante :

```
$ mvn package
```

Le fichier sera disponible sous `target/arancia-*-jar-with-dependencies.jar`.

Ce jar peut être exécuté ensuite à l'aide de la commande suivante :

```
$ java -jar target/arancia-*-jar-with-dependencies.jar
```

---

### Nom du groupe

Le nom de notre groupe est inspiré d'un [petit village][Grindelwald] dans le
canton de Berne, en Suisse, où le compositeur d'opéra [Richard Wagner][Wagner]
est supposé d'avoir séjourné pendant quelques années lors de son exile en
Suisse.


[HEIG-VD]: https://heig-vd.ch/
[Grindelwald]: https://en.wikipedia.org/wiki/Grindelwald
[Wagner]: https://en.wikipedia.org/wiki/Richard_Wagner
