Feature: Fonctionnalités CRUD sur l'entité "Course"

  Background: S'authentifier
    Given Il y a un serveur
    And Je m'authentifie en tant qu'utilisateur avec l'email "david.hilbert@heig-vd.ch" et le rôle "NORMAL"

  Scenario: Création d'un cours
    Given J'ai un cours intitulé "Premiers pas avec Cucumber JVM"
    When Je fais un POST vers le chemin /courses
    And Je reçois une réponse de code 201
    And Je récupère le nouveau cours
    And Je fais un GET vers le chemin /courses avec le nouveau cours
    Then Je reçois une réponse de code 200

  Scenario: Récupèration de tous les cours
    When Je fais un GET vers le chemin /courses avec le numéro de page 1 et la taille de page 25
    Then Je reçois une réponse de code 200
    And Le résultat est sous forme paginée

  Scenario: Récupération d'un cours
    Given J'ai un cours intitulé "Programmation Orientée Aspect"
    And Je fais un POST vers le chemin /courses
    And Je reçois une réponse de code 201
    And Je récupère le nouveau cours
    When Je fais un GET vers le chemin /courses avec le nouveau cours
    Then Je reçois une réponse de code 200

  Scenario: Mise à jour du cours
    Given J'ai un cours intitulé "Faire des tests avec JMeter"
    And Je fais un POST vers le chemin /courses
    And Je reçois une réponse de code 201
    And Je récupère le nouveau cours
    When Je fais un PUT vers le chemin /courses avec le nouveau cours avec son nouveau titre "Test de performance avec JMeter"
    Then Je reçois une réponse de code 200
    When Je fais un GET vers le chemin /courses avec le nouveau cours
    Then Je reçois une réponse de code 200
    And Le cours est modifié

  Scenario: Suppression du cours
    Given J'ai un cours intitulé "Gestion des transactions avec Spring Data"
    And Je fais un POST vers le chemin /courses
    And Je reçois une réponse de code 201
    And Je récupère le nouveau cours
    When Je fais un DELETE vers le chemin /courses avec le nouveau cours
    And Je reçois une réponse de code 204
    When Je fais un GET vers le chemin /courses avec le nouveau cours
    Then Je reçois une réponse de code 404
