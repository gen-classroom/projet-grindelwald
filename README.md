Ce dépôt est géré dans le cadre du cours de _Génie logiciel_ donné à
l'[HEIG-VD][HEIG-VD].


### Nom du groupe

Le nom de notre groupe est inspiré d'un [petit village][Grindelwald] dans le
canton de Berne, en Suisse, où le compositeur d'opéra [Richard Wagner][Wagner]
est supposé d'avoir séjourné pendant quelques années lors de son exile en
Suisse.


[HEIG-VD]: https://heig-vd.ch/
[Grindelwald]: https://en.wikipedia.org/wiki/Grindelwald
[Wagner]: https://en.wikipedia.org/wiki/Richard_Wagner


### Projet Maven

Le projet est une application simple qui affiche _Hello World!_. La création du
projet est basée sur le cours de RES. Ce projet nous permet de familiariser avec
les commandes de [Maven](https://maven.apache.org/),
[Picocli](https://picocli.info/) et notamment de travailler en groupe au travers
 de [GitHub](https://github.com/).

Pour lancer le programme, il faut entrer la commande maven dans le
terminal :
  `mvn clean package test`
Cette commande permet de compiler l'application qui peut donc être exécuté à
l'aide de la commande suivante :
  `java -cp target/lab2-1.0-SNAPSHOT.jar ch.heig.gen.App`
Le message _Hello World!_ s'affiche à l'éran.
