Feature: Test des fonctionnalités exclusives à l'administrateur

  Background:
    Given J'ai un serveur
    And J'ai l'adresse email "doriane.tedongmokaffo@heig-vd.ch" et le mot de passe "administrator"
    And Je fais un POST vers le chemin /auth/login
    And Je reçois une réponse de code 200
    And Je reçois un token

  Scenario: Création de l'utilisateur
    Given Je veux créer un utilisateur d'identifiant "renedescartes@heig-vd.ch", de mot de passe "renedescartes", de prénom "René", de nom "Descartes" et de role "NORMAL"
    When Je fais un POST vers le chemin /users
    And Je reçois une réponse de code 201
    And J'ai l'adresse email "renedescartes@heig-vd.ch" et le mot de passe "renedescartes"
    And Je fais un POST vers le chemin /auth/login
    Then Je reçois une réponse de code 200
    And Je reçois un token

  Scenario: Bloquer un utilisateur
    Given Je veux créer un utilisateur d'identifiant "jacquesbernoulli@heig-vd.ch", de mot de passe "jacquesbernoulli", de prénom "Jacques", de nom "Bernoulli" et de role "NORMAL"
    When Je fais un POST vers le chemin /users
    And Je reçois une réponse de code 201
    And J'ai l'adresse email "jacquesbernoulli@heig-vd.ch" et le mot de passe "jacquesbernoulli"
    And Je fais un POST vers le chemin /auth/login
    Then Je reçois une réponse de code 200
    Given J'ai un utilisateur d'identifiant "jacquesbernoulli@heig-vd.ch"
    When Je fais un PUT vers le chemin /users/block/ avec email
    And Je reçois une réponse de code 200
    And J'ai l'adresse email "jacquesbernoulli@heig-vd.ch" et le mot de passe "jacquesbernoulli"
    And Je fais un POST vers le chemin /auth/login
    Then Je reçois une réponse de code 403

  Scenario: Débloquer un utilisateur bloqué
    Given Je veux créer un utilisateur d'identifiant "georgcantor@heig-vd.ch", de mot de passe "georgcantor", de prénom "Georg", de nom "Cantor" et de role "NORMAL"
    When Je fais un POST vers le chemin /users
    And Je reçois une réponse de code 201
    Given J'ai un utilisateur d'identifiant "georgcantor@heig-vd.ch"
    When Je fais un PUT vers le chemin /users/block/ avec email
    Then Je reçois une réponse de code 200
    And J'ai l'adresse email "georgcantor@heig-vd.ch" et le mot de passe "georgcantor"
    And Je fais un POST vers le chemin /auth/login
    Then Je reçois une réponse de code 403
    Given J'ai un utilisateur d'identifiant "georgcantor@heig-vd.ch"
    When Je fais un DELETE vers le chemin /users/block/ avec email
    Then Je reçois une réponse de code 200
    And J'ai l'adresse email "georgcantor@heig-vd.ch" et le mot de passe "georgcantor"
    And Je fais un POST vers le chemin /auth/login
    Then Je reçois une réponse de code 200
    And Je reçois un token

  Scenario: Restriction de la creation d'un utilisateur à l'Administrateur
    Given Je veux créer un utilisateur d'identifiant "alberteinstein@heig-vd.ch", de mot de passe "alberteinstein", de prénom "Albert", de nom "Einstein" et de role "NORMAL"
    When Je fais un POST vers le chemin /users
    And Je reçois une réponse de code 201
    Given J'ai l'adresse email "alberteinstein@heig-vd.ch" et le mot de passe "alberteinstein"
    And Je fais un POST vers le chemin /auth/login
    And Je reçois une réponse de code 200
    And Je reçois un token
    Given Je veux créer un utilisateur d'identifiant "henripoincare@heig-vd.ch", de mot de passe "henripoincare", de prénom "Henri", de nom "Poincaré" et de role "NORMAL"
    When Je fais un POST vers le chemin /users
    And Je reçois une réponse de code 403

  Scenario: Restriction de la possibilité de blocage d'un utilisateur qu'à l'Administrateur
    Given Je veux créer un utilisateur d'identifiant "isaacnewton@heig-vd.ch", de mot de passe "isaacnewton", de prénom "Isaac", de nom "Newton" et de role "NORMAL"
    When Je fais un POST vers le chemin /users
    And Je reçois une réponse de code 201
    Given Je veux créer un utilisateur d'identifiant "alanturing@heig-vd.ch", de mot de passe "alanturing", de prénom "Alan", de nom "Turing" et de role "NORMAL"
    When Je fais un POST vers le chemin /users
    And Je reçois une réponse de code 201
    Given J'ai l'adresse email "isaacnewton@heig-vd.ch" et le mot de passe "isaacnewton"
    When Je fais un POST vers le chemin /auth/login
    And Je reçois une réponse de code 200
    And Je reçois un token
    And J'ai un utilisateur d'identifiant "alanturing@heig-vd.ch"
    And Je fais un PUT vers le chemin /users/block/ avec email
    Then Je reçois une réponse de code 403

  Scenario: Restriction de la possibilité de déblocage un utilisateur qu'à l'Administrateur
    Given Je veux créer un utilisateur d'identifiant "leonardofibonacci@heig-vd.ch", de mot de passe "leonardofibonacci", de prénom "Leonardo", de nom "Fibonacci" et de role "NORMAL"
    When Je fais un POST vers le chemin /users
    And Je reçois une réponse de code 201
    Given Je veux créer un utilisateur d'identifiant "leonhardeuler@heig-vd.ch", de mot de passe "leonhardeuler", de prénom "leonhard", de nom "euler" et de role "NORMAL"
    When Je fais un POST vers le chemin /users
    And Je reçois une réponse de code 201
    When J'ai l'adresse email "leonardofibonacci@heig-vd.ch" et le mot de passe "leonardofibonacci"
    And Je fais un POST vers le chemin /auth/login
    And Je reçois une réponse de code 200
    And Je reçois un token
    And J'ai un utilisateur d'identifiant "leonhardeuler@heig-vd.ch"
    And Je fais un DELETE vers le chemin /users/block/ avec email
    Then Je reçois une réponse de code 403



