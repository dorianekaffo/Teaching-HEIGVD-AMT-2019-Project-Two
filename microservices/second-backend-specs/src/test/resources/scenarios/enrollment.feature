Feature: Fonctionnalités sur la gestion des enrôlements

  Background: S'authentifier
    Given Il y a un serveur
    And Je m'authentifie en tant qu'utilisateur avec l'email "david.hilbert@heig-vd.ch" et le rôle "NORMAL"

  Scenario: Enrôler un étudiant à un cours
    Given J'ai un étudiant de prenom "Blaise" et de nom "Pascal"
    When Je fais un POST vers le chemin /students
    Then Je reçois une réponse de code 201
    And Je récupère le nouvel étudiant
    When J'ai un cours intitulé "Algèbre Linéaire"
    And Je fais un POST vers le chemin /courses
    Then Je reçois une réponse de code 201
    And Je récupère le nouveau cours
    When J'ai un enrôlement fait avec l'étudiant et le cours donné
    And Je fais un POST vers le chemin /enrollments
    Then Je reçois une réponse de code 201
    And Je récupère le nouvel enrollment
    And Je fais un GET vers le chemin /enrollments avec le nouvel enrôlement
    Then Je reçois une réponse de code 200

  Scenario: Retrait de l'enrollement d'un étudiant à un cours
    Given J'ai un étudiant de prenom "Blaise" et de nom "Pascal"
    When Je fais un POST vers le chemin /students
    Then Je reçois une réponse de code 201
    And Je récupère le nouvel étudiant
    When J'ai un cours intitulé "Algèbre Linéaire"
    And Je fais un POST vers le chemin /courses
    Then Je reçois une réponse de code 201
    And Je récupère le nouveau cours
    When J'ai un enrôlement fait avec l'étudiant et le cours donné
    And Je fais un POST vers le chemin /enrollments
    Then Je reçois une réponse de code 201
    And Je récupère le nouvel enrollment
    When Je fais un DELETE vers le chemin /enrollments avec le nouvel enrôlement
    Then Je reçois une réponse de code 204
    When Je fais un GET vers le chemin /enrollments avec le nouvel enrôlement
    Then Je reçois une réponse de code 404