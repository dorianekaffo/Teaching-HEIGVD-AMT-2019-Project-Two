Feature: Fonctionnalités CRUD sur l'entité "Student"

  Background: S'authentifier
    Given Il y a un serveur
    And Je m'authentifie en tant qu'utilisateur avec l'email "david.hilbert@heig-vd.ch" et le rôle "NORMAL"

  Scenario: Création d'un étudiant
    Given J'ai un étudiant de prenom "Henri" et de nom "Lebesgue"
    When Je fais un POST vers le chemin /students
    Then Je reçois une réponse de code 201
    And Je récupère le nouvel étudiant
    When Je fais un GET vers le chemin /students avec le nouvel étudiant
    Then Je reçois une réponse de code 200

  Scenario: Récupèration de tous les étudiants
    When Je fais un GET vers le chemin /students?page=0&size=25
    Then Je reçois une réponse de code 200
    And Le résultat est sous forme paginée

  Scenario: Récupération de l'étudiant
    Given J'ai un étudiant de prenom "Abraham" et de nom "De Moivre"
    When Je fais un POST vers le chemin /students
    Then Je reçois une réponse de code 201
    And Je récupère le nouvel étudiant
    When Je fais un GET vers le chemin /students avec le nouvel étudiant
    Then Je reçois une réponse de code 200

  Scenario: Mise à jour d'un étudiant
    Given J'ai un étudiant de prenom "Brook" et de nom "Taylor"
    When Je fais un POST vers le chemin /students
    Then Je reçois une réponse de code 201
    And Je récupère le nouvel étudiant
    When Je fais un PUT vers le chemin /students avec le nouvel étudiant avec son nouveau nom "Eric"
    Then Je reçois une réponse de code 200
    When Je fais un GET vers le chemin /students avec le nouvel étudiant
    Then Je reçois une réponse de code 200
    And L'étudiant est modifié

  Scenario: Suppression d'un étudiant
    Given J'ai un étudiant de prenom "Olivier" et de nom "Liechti"
    When Je fais un POST vers le chemin /students
    Then Je reçois une réponse de code 201
    And Je récupère le nouvel étudiant
    When Je fais un DELETE vers le chemin /students avec le nouvel étudiant
    Then Je reçois une réponse de code 204
    When Je fais un GET vers le chemin /students avec le nouvel étudiant
    Then Je reçois une réponse de code 404
