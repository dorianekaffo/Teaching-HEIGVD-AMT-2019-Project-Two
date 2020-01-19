Feature: Test des fonctionnalités exclusives à l'administrateur

  Background:
    Given J'ai un serveur

  Scenario: Création de l'utilisateur
    Given J'ai l'adresse email "doriane.tedongmokaffo@heig-vd.ch" et le mot de passe "administrator"
    And Je fais un POST vers le chemin /auth/login
    And Je reçois une réponse de code 200
    And Je reçois un token
    Given Je veux créer un utilisateur d'identifiant "renedescartes@heig-vd.ch", de mot de passe "renedescartes", de prénom "René", de nom "Descartes" et de role "NORMAL"
    When Je fais un POST vers le chemin /users
    And Je reçois une réponse de code 201
    And J'ai l'adresse email "renedescartes@heig-vd.ch" et le mot de passe "renedescartes"
    And Je fais un POST vers le chemin /auth/login
    Then Je reçois une réponse de code 200
    And Je reçois un token

  Scenario: Bloquer un l'utilisateur
    Given J'ai l'adresse email "doriane.tedongmokaffo@heig-vd.ch" et le mot de passe "administrator"
    And Je fais un POST vers le chemin /auth/login
    And Je reçois une réponse de code 200
    And Je reçois un token
    Given J'ai un utilisateur d'identifiant "renedescartes@heig-vd.ch"
    When Je fais un PUT vers le chemin /users/block/ avec email
    And Je reçois une réponse de code 200
    And J'ai l'adresse email "renedescartes@heig-vd.ch" et le mot de passe "renedescartes"
    And Je fais un POST vers le chemin /auth/login
    Then Je reçois une réponse de code 403
    And Je reçois un token

  Scenario: Débloquer l'utilisateur d'identifiant "renedescartes@heigvd.ch"
    Given J'ai l'adresse email "doriane.tedongmokaffo@heig-vd.ch" et le mot de passe "administrator"
    And Je fais un POST vers le chemin /auth/login
    And Je reçois une réponse de code 200
    And Je reçois un token
    Given J'ai un utilisateur d'identifiant "renedescartes@heig-vd.ch"
    When Je fais un DELETE vers le chemin /users/block/ avec email
    Then Je reçois une réponse de code 200
    And J'ai l'adresse email "renedescartes@heig-vd.ch" et le mot de passe "renedescartes"
    And Je fais un POST vers le chemin /auth/login
    Then Je reçois une réponse de code 200
    And Je reçois un token

  Scenario: Restriction de la creation à l'Administrateur
    Given J'ai l'adresse email "renedescartes@heig-vd.ch" et le mot de passe "renedescartes"
    And Je fais un POST vers le chemin /auth/login
    And Je reçois une réponse de code 200
    And Je reçois un token
    Given Je veux créer un utilisateur d'identifiant "henripoincare@heig-vd.ch", de mot de passe "henripoincare", de prénom "Henri", de nom "Poincaré" et de role "NORMAL"
    When Je fais un POST vers le chemin /users
    And Je reçois une réponse de code 403

  Scenario: Restriction de la possibilité de blocage un utilisateur qu'à l'Administrateur
    Given J'ai l'adresse email "renedescartes@heig-vd.ch" et le mot de passe "renedescartes"
    When Je fais un POST vers le chemin /auth/login
    And Je reçois une réponse de code 200
    And Je reçois un token
    And J'ai un utilisateur d'identifiant "doriane.tedongmokaffo@heig-vd.ch"
    And Je fais un PUT vers le chemin /users/block/ avec email
    Then Je reçois une réponse de code 403

  Scenario: Restriction de la possibilité de déblocage un utilisateur qu'à l'Administrateur
    Given J'ai l'adresse email "renedescartes@heig-vd.ch" et le mot de passe "renedescartes"
    And Je fais un POST vers le chemin /auth/login
    And Je reçois une réponse de code 200
    And Je reçois un token
    And J'ai un utilisateur d'identifiant "doriane.tedongmokaffo@heig-vd.ch"
    And Je fais un DELETE vers le chemin /users/block/ avec email
    Then Je reçois une réponse de code 403



