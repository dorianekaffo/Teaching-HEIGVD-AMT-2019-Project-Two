Feature: Fonctionnalités CRUD sur l'entité "Employé"

  Background: S'authentifier
    Given Je m'authentifie avec l'email "doriane.tedongmokaffo@heig-vd.ch" et le mot de passe "administrator"

  Scenario: Création d'un étudiant
    Given Je veux créer un étudiant de prenom "Olivier" et de nom "Liechti"
    When Je fais un POST vers le chemin /students
    Then Je reçois une réponse de code 201

  Scenario: Récupèration de tous les étudiants
    When Je fais un GET vers le chemin /students?page=0&size=25
    Then Je reçois une réponse de code 200
    And Le résultat est sous forme paginée

  Scenario: Récupération de l'étudiant
    Given J'ai l'identifiant 1 d'une ressource
    When Je fais un GET vers le chemin /students/1
    Then Je reçois une réponse de code 200

  Scenario: Mise à jour d'un étudiant
    Given J'ai l'identifiant 1 d'une ressource
    And Je veux mettre à jour l'étudiant correspondant
    When Je fais un PUT vers le chemin /students/1
    Then Je reçois une réponse de code 200

  Scenario: Suppression d'un étudiant
    Given J'ai l'identifiant 1 d'une ressource
    When Je fais un DELETE vers le chemin /students/1
    Then Je reçois une réponse de code 204
