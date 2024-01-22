Feature: Part1 - One-melee scenarios

    Scenario Outline: <description>
        Given setup the game
        And set the number of players to 4
        And set the player names to P1, P2, P3, P4
        And set initial health points to 50
        And initialize deck
        And shuffle cards
        And deal 12 cards to each player
        And rig the cards "<P1>", "<P2>", "<P3>", "<P4>"
        And setup round
        When P1 plays "<P1>"
        And P2 plays "<P2>"
        And P3 plays "<P3>"
        And P4 plays "<P4>"
        Then actual loser is "<Loser>"
        And actual injury points is <Injury points>

        Examples:
            | P1     | P2   | P3   | P4   | Loser | Injury points | description |
            | Ar13   | Ar5  | Ar12 | Ar7  | P2    | 20            | All arrows – none poisoned |
            | Sw6    | Sw7  | Sw15 | Sw13 | P1    | 30            | All swords – TWO poisoned |
            | So11   | So12 | So6  | So5  | P4    | 40            | All sorcery – all poisoned |
            | De9    | De14 | De1  | De5  | P3    | 25            | All decepMon – completes loser positions |
            | Ar13   | Ar8  | Me7  | Ar14 | P3    | 45            | Merlin loses |
            | Ar13   | Ar8  | Me15 | Al14 | P2    | 45            | Merlin is not loser – use Alchemy |
            | Ar13   | Ar8  | Ap7  | Ar14 | P3    | 25            | Apprentice loses |
            | Ar13   | Ar8  | Ap15 | Ar14 | P2    | 25            | Apprentice is not loser |
            | De13   | Me14 | Me14 | Me14 | P1    | 80            | 3-same card feint |
            | De8    | Me14 | De9  | Ap10 | P1    | 45            | Mix of Me and Ap |
            | Sw10   | Sw1  | Sw2  | Me1  | P3    | 40            | 1 feint 2 cards – feint lowest |
            | Sw10   | Ap10 | Sw15 | Me10 | P3    | 40            | 1 feint 3 disMnct cards |
            | Sw10   | Sw1  | Al2  | Me2  | P2    | 40            | 1 feint 2 cards – feint non lowest |
            | Al2    | De7  | Sw6  | Ar8  | P1    | 35            | Start with alchemy and lose |
            | Al6    | Me7  | Ap8  | So5  | P4    | 45            | Start with Al, not lose, using Me and Ap |
            | Al12   | De7  | Sw6  | Ar8  | P3    | 35            | Start with alchemy and not lose |
            | MeSw13 | Sw10 | Sw1  | Al2  | P3    | 40            | Merlin starts and is not loser |
            | ApSw13 | Sw10 | Sw1  | Sw2  | P3    | 20            | ApprenMce starts and is not loser |
            | MeSw13 | Sw10 | Al10 | Ap10 | P1    | 40            | Feint makes Me loses despite playing ﬁrst |
            | ApSw13 | Sw10 | Al10 | Ap10 | P1    | 20            | Feint makes Ap loses despite playing ﬁrst |
            | MeDe13 | De7  | Me14 | De10 | P2    | 70            | 2 merlins 2 nd one in suit |
            | MeDe13 | Ap7  | Me14 | De10 | P2    | 65            | 2 merlins 2 nd one in suit + Ap in suit |
            | Sw10   | Ap10 | Sw11 | Me11 | -     | 0             | 2 feints – no loser |
            | Sw10   | Ap10 | Al10 | Me10 | -     | 0             | Same value for all players – no loser |
