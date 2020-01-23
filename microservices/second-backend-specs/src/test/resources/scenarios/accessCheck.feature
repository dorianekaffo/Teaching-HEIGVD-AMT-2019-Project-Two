Feature: Vérification des droits utilisateurs

  Background: S'authentifier
    Given Il y a un serveur
    And Je m'authentifie en tant qu'utilisateur avec l'email "david.hilbert@heig-vd.ch" et le rôle "NORMAL"

  Scenario: Accès à un cours restrient qu'à son owner
    Given J'ai un cours intitulé "Théorie des ensembles"
    When Je fais un POST vers le chemin /courses
    Then Je reçois une réponse de code 201
    And Je m'assure que le owner est "david.hilbert@heig-vd.ch"
    And Je récupère le nouveau cours
    When Je m'authentifie en tant qu'utilisateur avec l'email "bernhard.riemann@heig-vd.ch" et le rôle "NORMAL"
    And Je fais un GET vers le chemin /courses avec le nouveau cours
    Then Je reçois une réponse de code 403
    When Je fais un PUT vers le chemin /courses avec le nouveau cours avec son nouveau titre "Somme de Riemann"
    Then Je reçois une réponse de code 403
    When Je fais un DELETE vers le chemin /courses avec le nouveau cours
    Then Je reçois une réponse de code 403

  Scenario: Accès à un cours restrient qu'à son owner
    Given J'ai un étudiant de prenom "Carl Friedrich" et de nom "Gauss"
    And Je fais un POST vers le chemin /students
    And Je reçois une réponse de code 201
    And Je m'assure que le owner est "david.hilbert@heig-vd.ch"
    And Je récupère le nouvel étudiant
    When Je m'authentifie en tant qu'utilisateur avec l'email "bernhard.riemann@heig-vd.ch" et le rôle "NORMAL"
    And Je fais un GET vers le chemin /students avec le nouvel étudiant
    Then Je reçois une réponse de code 403
    When Je fais un PUT vers le chemin /students avec le nouvel étudiant avec son nouveau nom "Chamberlain"
    Then Je reçois une réponse de code 403
    When Je fais un DELETE vers le chemin /students avec le nouvel étudiant
    Then Je reçois une réponse de code 403

