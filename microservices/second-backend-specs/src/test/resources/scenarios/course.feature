Feature: Fonctionnalités CRUD sur l'entité "Course"

  Background: S'authentifier
    Given Il y a un serveur du premier backend
    And Il y a un serveur
    And Je m'authentifie avec l'email "david.hilbert@heig-vd.ch" et le mot de passe "davidhilbert"

  Scenario: Création d'un cours
    Given J'ai un cours intitulé "Premiers pas avec Cucumber JVM"
    When Je fais un POST vers le chemin /courses pour créer un cours
    And Je reçois une réponse de code 201
    And Je fais un GET pour récupérer mon nouveau cours
    Then Je reçois une réponse de code 200
    And Les cours correspondent

  Scenario: Récupèration de tous les cours
    When Je fais un GET vers le chemin /courses avec le numéro de page 1 et la taille de page 25
    Then Je reçois une réponse de code 200
    And Le résultat est sous forme paginée

  Scenario: Récupération d'un cours
    Given J'ai l'identifiant 1 d'une ressource
    When Je fais un GET vers le chemin /courses/1
    Then Je reçois une réponse de code 200

  Scenario: Mise à jour du cours
    Given J'ai l'identifiant 1 d'une ressource
    And J'ai un cours à mettre à jour
    When Je fais un PUT vers le chemin /courses/1 avec des données
    Then Je reçois une réponse de code 200
    When Je fais un GET vers le chemin /courses/1
    Then Je reçois une réponse de code 200

  Scenario: Suppression du cours
    Given J'ai l'identifiant 1 d'une ressource
    When Je fais un DELETE vers le chemin /courses/1 pour le supprimer
    And Je reçois une réponse de code 204
    When Je fais un GET vers le chemin /courses/1
    Then Je reçois une réponse de code 404
