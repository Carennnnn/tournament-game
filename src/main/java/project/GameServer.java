package project;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class GameServer {
    private Integer numOfPlayers;
    private Player[] players;
    private List<Card> deck = new ArrayList<Card>();
    private int currentPlayerId = 0;
    Score score = new Score();

    public static void main(String args[]) {
        GameServer gameServer = new GameServer();
        gameServer.setNumOfPlayers();
        gameServer.setPlayers();
        gameServer.initializeHealthPoints();
        gameServer.gameLoop();
    }

    // set number of players
    public void setNumOfPlayers() {
        int numPlayers = 0;
        boolean isInValidInteger = true;
        String numPlayersStr = "";
        Scanner input = new Scanner(System.in);
        System.out.print("Please enter number of players(needs to be between 3 and 5): ");
        while (isInValidInteger) {
            numPlayersStr = input.next();
            try {
                numPlayers = Integer.parseInt(numPlayersStr);
                isInValidInteger = false;
            } catch (NumberFormatException e) {
                System.out.println("Please enter an integer");
            }
        }
        boolean isNumOfPlayersInRange = isNumOfPlayersInRange(numPlayers);
        while (!isNumOfPlayersInRange) {
            System.out.println("Number of players is out-of-range");
            System.out.println("Please enter number of players(needs to be between 3 and 5): ");
            numPlayersStr = input.next();
            numPlayers = Integer.parseInt(numPlayersStr);
            isNumOfPlayersInRange = isNumOfPlayersInRange(numPlayers);
        }
        this.numOfPlayers = Integer.valueOf(numPlayersStr);
    }

    // ask for player's name and set new players
    public void setPlayers() {
        players = new Player[numOfPlayers];
        Scanner input = new Scanner(System.in);
        for (int i = 0; i < numOfPlayers; i++) {
            int currentPlayerId = i + 1;
            boolean isInValidName = true;
            while (isInValidName) {
                System.out.print("Please enter player " + currentPlayerId + "'s name: ");
                String name = input.next();
                if (isPlayerNameValid(name)) {
                    players[i] = new Player(name);
                    isInValidName = false;
                } else {
                    System.out.println("Player name cannot be empty");
                }
            }
        }
    }

    // initialize health points for all players
    public void initializeHealthPoints() {
        Scanner input = new Scanner(System.in);
        boolean isValidHealthPoints = false;
        while (!isValidHealthPoints) {
            System.out.print("Please enter initial number(non-negative) of health points for all players: ");
            int initialHealthPoints = Integer.parseInt(input.next());
            if (isHealthPointsValid(initialHealthPoints)) {
                isValidHealthPoints = true;
                for (int i = 0; i < numOfPlayers; i++) {
                    players[i].setHealthPoints(initialHealthPoints);
                }
            } else {
                System.out.println("Health points cannot be negative");
            }
        }
    }

    // game loop of the game
    public void gameLoop() {
        boolean isGameRunning = true;
        boolean isRoundRunning = true;
        boolean isGameOver = false;
        int roundNum = 0;
        while (isGameRunning) {
            String suit = "";
            initializeCards();
            shuffleCards();
            dealCards();
            int numOfPersonPlayed = 0;
            int numOfMeleePlayed = 0;
            roundNum++;
            System.out.println();
            System.out.println("************ Round " + roundNum + " ************");
            // print initial hand of each player
            printInitialHand();
            isRoundRunning = true;
            // reset injury deck to empty for a new round
            resetInjuryDeck();
            // players play a round of the game
            while (isRoundRunning) {
                Player currentPlayer = players[currentPlayerId];
                // if suit is empty, then the first card determines the suit of this melee
                if (suit.isEmpty()) {
                    suit = currentPlayer.playMelee(suit);
                } else {
                    // if suit is already set, player just play this melee
                    currentPlayer.playMelee(suit);
                }
                currentPlayerId++;
                numOfPersonPlayed++;
                // if current player is the last player, set current player id to the first player
                if (currentPlayerId == numOfPlayers) {
                    currentPlayerId = 0;
                }
                // if one or more players reach 0 health points, the game is over
                if (numOfPersonPlayed != numOfPlayers) {
                    for (int i = 0; i < players.length; i++) {
                        if (players[i].getHealthPoints() <= 0) {
                            System.out.println("Game over");
                            // calculate each player's health points at the end of the game
                            for (int j = 0; j < players.length; j++) {
                                players[j].calculateHealthPoints();
                            }
                            gameOver();
                            isGameOver = true;
                            isRoundRunning = false;
                            isGameRunning = false;
                            break;
                        }
                    }
                }
                // Once all players have played a card for this melee, determine loser
                if (numOfPersonPlayed == numOfPlayers) {
                    numOfPersonPlayed = 0;
                    suit = "";
                    System.out.println("This melee ends");
                    Player loser = getLoser();
                    getInjuryPoints(loser);
                    numOfMeleePlayed++;
                    for (int i = 0; i < players.length; i++) {
                        if (players[i].getHealthPoints() <= 0) {
                            System.out.println("Game over");
                            // calculate each player's health points at the end of the game
                            for (int j = 0; j < players.length; j++) {
                                players[j].calculateHealthPoints();
                            }
                            gameOver();
                            isGameOver = true;
                            isRoundRunning = false;
                            isGameRunning = false;
                            break;
                        }
                    }
                    // Once all players have played 12 melees, a round ends
                    if (numOfMeleePlayed == Constants.MELEE_NUM && !isGameOver) {
                        // end a round
                        isRoundRunning = false;
                        System.out.println("This round ends");
                        // calculate each player's health points at the end of the round
                        for (int i = 0; i < players.length; i++) {
                            players[i].calculateHealthPoints();
                        }
                        for (int i = 0; i < players.length; i++) {
                            if (players[i].getHealthPoints() <= 0) {
                                System.out.println("Game over");
                                gameOver();
                                isGameOver = true;
                                isGameRunning = false;
                                break;
                            }
                        }
                    }
                }
            }
        }
    }

    // initialize all cards
    public void initializeCards() {
        List<Card> deckOfCards = new ArrayList<>();
        for (int i = 1; i < 16; i++) {
            deckOfCards.add(new Card(Constants.SWORDS, i));
        }
        for (int i = 1; i < 16; i++) {
            deckOfCards.add(new Card(Constants.ARROWS, i));
        }
        for (int i = 1; i < 16; i++) {
            deckOfCards.add(new Card(Constants.SORCERY, i));
        }
        for (int i = 1; i < 16; i++) {
            deckOfCards.add(new Card(Constants.DECEPTION, i));
        }
        for (int i = 1; i < 16; i++) {
            deckOfCards.add(new Card(Constants.ALCHEMY, i));
        }
        for (int i = 0; i < 3; i++) {
            deckOfCards.add(new Card(Constants.MERLIN, 0));
        }
        for (int i = 0; i < 2; i++) {
            deckOfCards.add(new Card(Constants.APPRENTICE, 0));
        }
        deck = deckOfCards;
    }

    // check if number of players is between 3 and 5
    public boolean isNumOfPlayersInRange(int numPlayers) {
        if (numPlayers >= 3 && numPlayers <= 5) {
            return true;
        }
        return false;
    }

    // check if player's name is empty or not
    public boolean isPlayerNameValid(String name) {
        if (name.isEmpty()) {
            return false;
        }
        return true;
    }

    // check if health points are non-negative
    public boolean isHealthPointsValid(int points) {
        if (points >= 0) {
            return true;
        }
        return false;
    }

    // shuffle all cards
    public void shuffleCards() {
        Collections.shuffle(deck);
    }

    // deal the cards
    public void dealCards() {
        for (int i = 0; i < players.length; i++) {
            List<Card> cards = deck.subList(0, 12);
            players[i].receiveCards(cards);
            removeCards(cards);
        }
    }

    // set players
    public void setPlayers(Player[] p) {
        this.players = p;
    }

    // get deck
    public List<Card> getDeck() {
        return this.deck;
    }

    // remove cards from deck
    public void removeCards(List<Card> removedCards) {
        List<Card> tempDeck = new ArrayList<>();
        tempDeck.addAll(deck);
        for (int i = 0; i < removedCards.size(); i++) {
            tempDeck.remove(removedCards.get(i));
        }
        deck = tempDeck;
    }

    // get all played cards from all players in this melee
    public List<Card> getAllPlayedCards() {
        List<Card> playedCards = new ArrayList<>();
        for (int i = 0; i < players.length; i++) {
            Card playedCard = players[i].getPlayedCard();
            if (playedCard != null) {
                playedCards.add(playedCard);
                playedCard.setCardOwner(players[i]);
            }
        }
        return playedCards;
    }

    // set current player id
    public void setCurrentPlayerId(int id) {
        this.currentPlayerId = id;
    }

    // get current player id
    public int getCurrentPlayerId() {
        return this.currentPlayerId;
    }

    // game is over when one or more players reach 0 health points
    public void gameOver() {
        // get winners
        List<Player> winners = score.getWinners(players);
        if (!winners.isEmpty()) {
            // print all winners
            System.out.println("Winners: ");
            for (int i = 0; i < winners.size(); i++) {
                System.out.println(winners.get(i).getName());
            }
        } else {
            System.out.println("There is no winner");
        }
    }

    // set the first card of four players
    public void rigCardsForFourPlayers(String card1, String card2, String card3, String card4) {
        String[] cards = new String[4];
        cards[0] = card1;
        cards[1] = card2;
        cards[2] = card3;
        cards[3] = card4;
        for (int i = 0; i < 4; i++) {
            Card newCard = getRiggedCard(cards[i]);
            players[i].setCard(newCard);
            System.out.println("New card: " + newCard.getCardInfo());
            System.out.println(players[i].printCards(players[i].getCardsOnHand()));
        }
    }

    // split card and its value, if cards are Me or Ap, set card value to 0
    public Card getRiggedCard(String card) {
        Card newCard;
        String cardName = card.substring(0, 2);
        String cardFullName = getCardFullName(cardName);
        if (cardName.equals(Constants.ME) || cardName.equals(Constants.AP)) {
            newCard = new Card(cardFullName, 0);
        } else {
            String cardValueStr = card.substring(2);
            int cardValue = Integer.parseInt(cardValueStr);
            newCard = new Card(cardFullName, cardValue);
        }
        return newCard;
    }

    // get card's full name
    public String getCardFullName(String cardName) {
        if (cardName.equals(Constants.SW)) {
            return Constants.SWORDS;
        } else if (cardName.equals(Constants.AR)) {
            return Constants.ARROWS;
        } else if (cardName.equals(Constants.SO)) {
            return Constants.SORCERY;
        } else if (cardName.equals(Constants.DE)) {
            return Constants.DECEPTION;
        } else if (cardName.equals(Constants.ME)) {
            return Constants.MERLIN;
        } else if (cardName.equals(Constants.AP)) {
            return Constants.APPRENTICE;
        } else if (cardName.equals(Constants.AL)) {
            return Constants.ALCHEMY;
        }
        return "";
    }

    // print initial hand
    public void printInitialHand() {
        for (int i = 0; i < players.length; i++) {
            System.out.println("Player " + players[i].getName() + "'s cards: ");
            System.out.print(players[i].printCards(players[i].getCardsOnHand()));
            System.out.println();
        }
    }

    // reset injury deck to empty for all players for a new round
    public void resetInjuryDeck() {
        for (int i = 0; i < players.length; i++) {
            players[i].resetInjuryDeck();
        }
    }

    // get all players
    public Player[] getPlayers() {
        return players;
    }

    // get number of players
    public int getNumOfPlayers() {
        return numOfPlayers;
    }

    // get loser for a melee
    public Player getLoser() {
        // get all played cards from all players in this melee
        List<Card> playedCards = getAllPlayedCards();
        List<Card> remainingCards = score.ignoreCards(playedCards);
        // get loser
        Player loser = score.getLoser(remainingCards);
        if (loser != null) {
            System.out.println("Loser: " + loser.getName());
            // let loser becomes the leader and reorganize the order of all players
            int id = score.getLoserPlayerId(players, loser);
            currentPlayerId = id;
        } else {
            System.out.println("There's no loser in this melee");
            System.out.println();
        }
        return loser;
    }

    // get injury points for a melee
    public int getInjuryPoints(Player loser) {
        // get all played cards from all players in this melee
        List<Card> playedCards = getAllPlayedCards();
        // add all weapon cards played in this melee to the loser's injury deck
        loser.addToInjuryDeck(playedCards);
        // get injury points accumulated in a melee of loser
        int injuryPoints = loser.getInjuryPointsInMelee(playedCards);
        System.out.println("Player " + loser.getName() + " suffered " + injuryPoints + " injury points in this melee");
        System.out.println();
        return injuryPoints;
    }

    // check if user needs to set suit and value, or only value
    public String instructionToUserInput(String card) {
        String cardName = card.substring(0, 2);
        if (card.length() > 4) {
            return Constants.SUIT_AND_VALUE;
        } else if (cardName.equals(Constants.ME) || cardName.equals(Constants.AP)) {
            return Constants.VALUE;
        }
        return "";
    }

    // get suit number in selection by suit name
    public int getSuitNum(String suit) {
        if (suit.equals(Constants.SW)) {
            return 1;
        } else if (suit.equals(Constants.AR)) {
            return 2;
        } else if (suit.equals(Constants.SO)) {
            return 3;
        } else {
            return 4;
        }
    }

    public void rigCardsForPlayer(int PlayerId, List<Card> newCards) {
        players[PlayerId].setCards(newCards);
    }
}