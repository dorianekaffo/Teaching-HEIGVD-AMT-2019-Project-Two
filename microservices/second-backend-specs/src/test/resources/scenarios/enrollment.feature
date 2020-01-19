#Feature: Fonctionnalités sur la gestion des enrôlements
#
#  Background: S'authentifier
#    Given Il y a un serveur du premier backend
#    And Il y a un serveur
#    And Je m'authentifie avec l'email "david.hilbert@heig-vd.ch" et le mot de passe "davidhilbert"
#
#  Scenario: Enrôler un étudiant à un cours
#    Given J'ai un étudiant de prenom "Blaise" et de nom "Pascal"
#    And Je fais un POST vers le chemin /students
#    And Je reçois une réponse de code 201
#    And Je récupère le nouvel étudiant
#    And J'ai un cours intitulé "Algèbre Linéaire"
#    And Je fais un POST vers le chemin /courses pour créer un cours
#    And Je reçois une réponse de code 201
#    And Je recupère le nouveau cours
#    And J'ai un enrôlement fait avec l'étudiant et le cours donné
#    When Je fais un POST vers le chemin /enrollments
#    And Je reçois une réponse de code 201
#    And Je fais un GET vers le chemin /enrollments?page=0&size=20
#    Then Je reçois une réponse de code 200
#    And Le nombre d'enrollments n'est pas zéro
#
#  Scenario: Retrait de l'enrollement d'un étudiant à un cours
#    Given J'ai l'identifiant 1 d'une ressource
#    When Je fais un DELETE vers le chemin /enrollments/1
#    Then Je reçois une réponse de code 204