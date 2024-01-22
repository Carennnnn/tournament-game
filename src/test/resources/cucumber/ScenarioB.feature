Feature: ScenarioB - game ends before end of 1st round due to shaming

    Scenario Outline: a game that ends before end of 1st round due to shaming
        Given setup the game
        And set the number of players to 3
        And set the player names to P1, P2, P3
        And set initial health points to 5
        And initialize deck
        And shuffle cards
        And deal 12 cards to each player
        And rig the cards for P1 scenario B
        And rig the cards for P2 scenario B
        And rig the cards for P3 scenario B
        And setup round
        And player plays card 2
        And player plays card 2
        And player plays card 3
        And loser is "P1"
        And injury points is 15
        And player plays card 2
        And player plays card 1
        And player plays card 1
        And loser is "P2"
        And injury points is 15
        And player plays card 1
        And player plays card 3
        When player plays card 1
        Then game ends due to shaming