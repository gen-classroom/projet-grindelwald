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

Le projet est une application simple qui affiche "Hello World". La création du
projet est basée sur le cours de RES. Ce projet nous permet de familiariser avec
les commandes de <p><a href="https://maven.apache.org/" title="maven">Maven</a><
/p>
, <p><a href="https://picocli.info/" title="picocli">Picocli</a></p> et
notamment de travailler en groupe au travers de <p><a href="https://github.com/"
title="github">GitHub</a></p>

Pour lancer le programme, il faut lancer la commande maven dans le
terminal :
  `mvn clean package test`
Cette commande permet de compiler l'application qui peut donc être exécuté à
l'aide de la commande suivante :
  `java -cp target/lab2-1.0-SNAPSHOT.jar ch.heig.gen.App`
Le message _Hello world!_ s'affiche à l'éran.
