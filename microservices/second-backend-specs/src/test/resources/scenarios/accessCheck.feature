#Feature: Vérification des droits utilisateurs
#
#  Background: Authentification et création d'un deuxième utilisateur
#    Given  Il y a un serveur du premier backend
#    And Il y a un serveur
#    And Je m'authentifie avec l'email "doriane.tedongmokaffo@heig-vd.ch" et le mot de passe "administrator"
#    And J'ai un utilisateur de prénom "David", de nom "Hilbert", d'email "david.hilbert@heig-vd.ch", de mot de passe "davidhilbert" et de role "NORMAL"
#    And Je fais un POST vers le chemin /users
#    And J'ai un utilisateur de prénom "Bernhard", de nom "Riemann", d'email "bernhard.riemann@heig-vd.ch", de mot de passe "bernhardriemann" et de role "NORMAL"
#    And Je fais un POST vers le chemin /users
#
#  Scenario: Modification d'un étudiant ou d'un enrôlement n'étant son owner
#    Given Je m'authentifie avec l'email "david.hilbert@heig-vd.ch" et le mot de passe "davidhilbert"
#    And J'ai un cours intitulé "Théorie des ensembles"
#    And J'ai un étudiant de prenom "Carl Friedrich" et de nom "Gauss"
#    When Je fais un POST vers le chemin /courses pour créer un cours
#    And Je reçois une réponse de code 201
#    And Je m'assure que le owner est "david.hilbert@heig-vd.ch"
#    And Je recupère le nouveau cours
#    When Je fais un POST vers le chemin /students
#    And Je reçois une réponse de code 201
#    And Je m'assure que le owner est "david.hilbert@heig-vd.ch"
#    And Je récupère le nouvel étudiant
#    When Je m'authentifie avec l'email "bernhard.riemann@heig-vd.ch" et le mot de passe "bernhardriemmann"
#    And Je fais un GET vers le chemin /students avec le nouvel étudiant
#    Then Je reçois une réponse de code 403
#    When Je fais un GET vers le chemin /courses avec le nouveau cours
#    Then Je reçois une réponse de code 403
#    And Je fais un PUT vers le chemin /students avec le nouvel étudiant avec son nouveau nom "Toto"
#    Then Je reçois une réponse de code 403
#    When Je fais un PUT vers le chemin /courses avec le nouveau cours avec son nouveau titre "Spring"
#    Then Je reçois une réponse de code 403
#    And Je fais un DELETE vers le chemin /students avec le nouvel étudiant
#    Then Je reçois une réponse de code 403
#    When Je fais un DELETE vers le chemin /courses avec le nouveau cours
#    Then Je reçois une réponse de code 403
#