## AMT 2019 - Project Two

Ce projet contient le deuxième projet dans le cadre de l'AMT 2019. Nous avons les répertoires suivants:

- `docker-topologies` qui contient les configurations pour la topologie docker. Pour des informations sur ce la configuration docker vous pouvez consulter le fichier [docker.md](./doc/docker.md)

- `docker-images` qui contient tout le nécessaire pour la création d'image docker.

- `doc` qui contient toutes les documentations
	* [jmeter.md](./doc/jmeter.md) contient toutes les configurations qui a été faite ainsi que les résultats obtenus lors des tests de performance et de monté en charge.
	* [docker.md](./doc/docker.md) qui contient toutes les informations sur l'architecture docker ainsi déployé.
	* [rapport_final.md](./doc/rapport_final.md) qui est le rapport du projet
	* [first_backend.md](./doc/first_backend.md) qui contient les informations sur le premier backend.
	* [second_backend.md](./doc/second_backend.md) qui contient des informations sur le deuxième backend.
	
- `microservices` qui contient les différents services du projet. Nous avons:

	* Le serveur `first-backend` qui expose des APIs pour la __gestion des utilisateurs__
	* Le serveur `second-backend` qui expose des APIs pour la __gestion des enrollments à un cours par des étudiants__.
	* Le serveur `first-backend-specs` qui teste le serveur `first-backend` par l'approche __BDD__ (Behavior Driven Development).
	* Le serveur `second-backend-specs` qui teste le serveur `second-backend` par l'approche __BDD__ (Behavior Driven Development).

- `jmeter` contient le fichier de configuration pour réaliser des tests de performance sur l'application. Pour plus d'informations sur cette configuration vous pouvez consulter le ficher [jmeter.md](./doc/jmeter.md)

## Où trouver les spécifications ?
Les API qui sont fournies on été fait en utilisation la spécification OpenAPI. Nous trouvons les spécifications dans les répertoires suivants:

- Pour les serveurs __first-backend__ et __second-backend__

 `./microservices/nom_du_serveur/src/main/resources/specs/api-spec.yaml`
 
- Pour les serveurs __first-backend-specs__ et __second-backend-specs__

 `./microservices/nom_du_serveur/src/main/resources/api-spec.yaml`
 
>  *Avec __nom_du_serveur__ qui est le nom du serveur choisi*
 
Vous pouvez éditer ce fichier via l'éditeur Swagger, que vous pouvez lancer par Docker. Ecrivez la commande suivante et ouvrez votre navigateur à cette adresse `http://YOURDOCKERHOST:28080`.

```
docker run -p 28080:8080 swaggerapi/swagger-editor
```

## Comment construire l'application?

1. Contruisez toutes les images en exécutant le script **build.sh**

  `./build.sh`
  
- Il est possible de contruire une image docker d'un serveur particulier, pour cela vous procéder comme suit:

       Par exemple pour le premier backend *__(first-backend)__*

      `cd docker-images/first-backend/`
      `./build-docker-image.sh`


### Comment lancer le projet
1. Lancer la topologie docker. La topologie se lancera avec les jars construits lors du dernier *build*.

    `./run.sh`
    
2. Vérifiez que le serveur en cours d'exécution

Ouvrez un navigateur web [http://localhost:8090](http://localhost:8080/)


### Comment exécuter une spécification

1. Pour cela lancer la topologie docker comme expliqué précédemment
2. Ensuite vous lancer le script **test.sh**

Vous pouvez cependant tester individuellement chacun des serveur. Pour cela procéder comme suit:

1. Construisez l'image docker qui encapsule vous spécifications de test

    Pour le premier backend:

     `cd docker-images/first-backend-specs`
     `./build-docker-image.sh`
  
    Pour le second backend:

     `cd docker-images/second-backend-specs`
     `./build-docker-image.sh`
    
2. Exécutez l'image construite et vérifiez la console
  
  `./run-docker-image.sh`
  
### Comment lancer des tests de performance 
Vous aurez besoin d'installer jmeter. Pour la mise en place de jmeter veuillez consulter le fichier [jmeter.md](./doc/jmeter.md)

- Lancer Jmeter.
- Charger le fichier .jmx qui se trouve dans le répertoire `jmeter`
- Cliquez sur le bouton "Start" pour lancer le test
- Observez et interprétez

