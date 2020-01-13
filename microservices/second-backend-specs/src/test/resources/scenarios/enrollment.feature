Feature: Fonctionnalités sur l'état de membre d'un employé

  Background: S'authentifier
    Given Il y a un serveur d'authentification
    And Je m'authentifie avec l'email "doriane.tedongmokaffo@heig-vd.ch" et le mot de passe "administrator"


  Scenario: Enrôler un étudiant à un cours
    Given J'ai un étudiant
    And J'ai un cours
    When Je fais un POST vers le chemin /enrollments?page=0&size=25
    And Je reçois une réponse de code 201
    And Je fais un GET vers le chemin /enrollments
    Then Je reçois une réponse de code 200
    And le retour n'est pas vide

  Scenario: Dissocier un employé à un département
    Given J'ai l'identifiant 1 d'une ressource
    When Je fais un DELETE vers le chemin /enrollments/1
    Then Je reçois une réponse de code 204