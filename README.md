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




### Projet de Maven

Le projet est une application simple qui affiche "Hello World". La création du
projet est basée sur le cours de RES. Ce projet nous permet de familiariser avec
les commandes de [maven], [picocli] et notamment de travailler en groupe au
travers de [GitHub]

Pour lancer le programme, il faut commencer la commande de maven dans le
terminal :
  `mvn clean package test`
Cette commande permet de compiler l'application. Nous pouvons dès maintenant
l'exécuter avec la commande suivante:
  `java -cp target/lab2-1.0-SNAPSHOT.jar ch.heig.gen.App`
Nous voyons donc [Hello World!] affiché au terminal
