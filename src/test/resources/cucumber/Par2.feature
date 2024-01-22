Feature: Part2 - Robustness

    Scenario Outline: attempting to break the rules
        Given setup the game
        And enter invalid number of players 2, then enter 3 as number of players
        And enter blank name, then enter valid name
        And input initial health points as -10, then set initial health points of players at 50
        And initialize deck
        And shuffle cards
        And deal 12 cards to each player
        And rig the cards for Fred
        And rig the cards for Joe
        And rig the cards for Paul
        And setup round
        And Fred plays Alchemy and So11 in melee 1
        And Joe plays Sword, Deception, Arrow, Alchemy and So6 in melee 1
        And Paul plays So7 in melee 1
        And loser is "Joe"
        And injury points is 25
        And Joe plays Ar8 in melee 2
        And Paul plays Me and assigns 9 to it in melee 2
        And Fred plays Ap and assigns 10 to it in melee 2
        And loser is "Joe"
        And injury points is 40
        And Joe plays Sw9 in melee 3
        And Paul plays Alchemy and Sw7 in melee 3
        And Fred plays Sw3 in melee 3
        And loser is "Fred"
        And injury points is 25
        And Fred plays De9 in melee 4
        And Joe plays Alchemy and De6 in melee 4
        When Paul plays De1 in melee 4
        Then actual loser is "Paul"
        And actual injury points is 25
        And total injury points for Joe in this round is 65
        And total injury points for Fred in this round is 25
        And total injury points for Paul in this round is 25

