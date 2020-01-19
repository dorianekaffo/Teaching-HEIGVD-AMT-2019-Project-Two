## Test avec JMeter
JMeter est un outil open source  développé par la fondation Apache. Il est très populaire pour tester effectuer des tests de montée en charge et de performance sur les applications.

## Comment lancer les tests JMeter
- Lancer le application en exécutant le script __run.sh__
	
	`./run.sh`

- Ensuite aller dans le répertoire __jmeter__ et lancer le script __run_tests.sh__

	`cd jmeter`
	`./run_tests.sh`

Ceci va exécuter les tests en mode __non GUI__.

## Configuration de JMeter
Pour effectuer les tests sur notre application, nous avons configuré JMeter comme suit:

- Nous avons pris 1000 utilisateurs
- L'avons envoyé les requêtes vers la route `auth/login` car étant la seule route ne nécessitant pas une authentification par token.

## Résultats obtenus

## Observations et critiques
