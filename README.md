# Arancia

Ce projet est géré dans le cadre du cours de _Génie logiciel_ donné à
l'[HEIG-VD][HEIG-VD].


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
