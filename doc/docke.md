# Configuration de Docker
Pour le déploiement de notre backend, nous avons fait usage de **Docker**, via sa technologie **Compose**. Plus précisément, nous avons utilisé les docker files de version **3.7** puisque notre Docker Engine était de la version 19.08. 

Nous avons utilisé la topologie suivante pour déployer notre projet en dans des conteneurs Docker.

![enter image description here](file:///home/sammie/Pictures/Screenshot%20from%202019-11-13%2012-54-13.png)

ici, nous avons les conteneurs suivants:
* **Traefik** qui est un reverse proxy, qui redirige le traffic vers les conteneur sousjacents selon les règles prédéfinies pour le routage. Dans la suite, nous détaillons les règles de routage qui nous implémenté pour notre réseau.
* **first_backend** qui est le premier backend que nous avons utilisé. Il servira de SUT (Subject Under Test) pour notre project BDD, "users-specs". C'est un projet developpé à partir de Spring qui expose des API Rest pour l'authentification et la gestion des utilisateurs. Nous validons ces APIs avec CucumberJVM.
* **second_backend** qui est le second backedn que nous avons développé. Il servira aussi de SUT (Subject Under Test) pour notre deuxième projet BDD "employees-specs". 
* **first_server_db** et **second_server_db** sont des images docker contenant MySQL, le SGBD que nous avons choisi pour ce projet. Ces images sont respectivement connecté aux conteneurs **first_backend** et **second_backend**.

# Lancement du réseau docker
Pour lancer, allez dans le répertoire `docker/runtime` sur le terminal. Ensuite, exécuter la commande suivante:
`docker-compose up`
Cette commande utilisera le fichier `docker-compose.yml` qui batîra les différentrs images dockers pour les projets Spring si elles n'ont pas encore été créées (première exécution), ou alors utilisé un image déjà existante (après la première exécution"
