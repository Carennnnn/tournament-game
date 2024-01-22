package cucumber;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import project.Card;
import project.Constants;
import project.GameServer;
import project.Player;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class StepDefinition {
    private GameServer gameServer;
    private InputStream inputStream = System.in;
    private int roundNum = 0;
    private int numOfPersonPlayed = 0;
    private int numOfPlayers = 0;
    private String suit = "";
    private Player currentPlayer;
    private int currentPlayerId = 0;
    private Player[] players;
    private Player loser;
    @Given("setup the game")
    public void setupGame() {
        gameServer = new GameServer();
    }

    @Given("set the number of players to {int}")
    public void setNumberOfPlayers(Integer num) {
        String userInput = num.toString() + "\n";
        ByteArrayInputStream in = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(in);
        gameServer.setNumOfPlayers();
        System.setIn(inputStream);
    }

    @Given("enter invalid number of players {int}, then enter {int} as number of players")
    public void tryInvalidNumberOfPlayers(Integer num1, Integer num2) {
        String userInput = num1.toString() + "\n" + num2.toString() + "\n";
        ByteArrayInputStream in = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(in);
        gameServer.setNumOfPlayers();
        System.setIn(inputStream);
    }

    @Given("set the player names to P1, P2, P3, P4")
    public void setPlayerNames() {
        String userInput = "P1\nP2\nP3\nP4\n";
        ByteArrayInputStream in = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(in);
        gameServer.setPlayers();
        System.setIn(inputStream);
    }

    @Given("set the player names to P1, P2, P3")
    public void set3PlayerNames() {
        String userInput = "P1\nP2\nP3\n";
        ByteArrayInputStream in = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(in);
        gameServer.setPlayers();
        System.setIn(inputStream);
    }

    @Given("enter blank name, then enter valid name")
    public void tryBlankPlayerNames() {
        String userInput = "\nFred\n\nJoe\n\nPaul\n";
        ByteArrayInputStream in = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(in);
        gameServer.setPlayers();
        System.setIn(inputStream);
    }

    @Given("set initial health points to {int}")
    public void setInitialHealthPoints(Integer num) {
        String userInput = num.toString() + "\n";
        ByteArrayInputStream in = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(in);
        gameServer.initializeHealthPoints();
        System.setIn(inputStream);
    }

    @Given("input initial health points as {int}, then set initial health points of players at {int}")
    public void tryInvalidInitialHealthPoints(Integer num1, Integer num2) {
        String userInput = num1.toString() + "\n" + num2.toString() + "\n";
        ByteArrayInputStream in = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(in);
        gameServer.initializeHealthPoints();
        System.setIn(inputStream);
    }

    @Given("initialize deck")
    public void initializeDeck() {
        gameServer.initializeCards();
    }

    @Given("shuffle cards")
    public void shuffleTheCards() {
        gameServer.shuffleCards();
    }

    @Given("deal 12 cards to each player")
    public void dealTheCards() {
        gameServer.dealCards();
    }

    @Given("rig the cards {string}, {string}, {string}, {string}")
    public void rigTheCards(String card1, String card2, String card3, String card4) {
        gameServer.rigCardsForFourPlayers(card1, card2, card3, card4);
    }

    @Given("rig the cards for Fred")
    public void rigTheCardsForFred() {
        List<Card> cardForFred = new ArrayList<>();
        cardForFred.add(new Card(Constants.ALCHEMY, 5));
        cardForFred.add(new Card(Constants.SWORDS, 3));
        cardForFred.add(new Card(Constants.SORCERY, 11));
        cardForFred.add(new Card(Constants.MERLIN, 0));
        cardForFred.add(new Card(Constants.APPRENTICE, 0));
        cardForFred.add(new Card(Constants.DECEPTION, 9));
        cardForFred.add(new Card(Constants.SWORDS, 2));
        cardForFred.add(new Card(Constants.SWORDS, 4));
        cardForFred.add(new Card(Constants.SORCERY, 10));
        cardForFred.add(new Card(Constants.SORCERY, 9));
        cardForFred.add(new Card(Constants.ARROWS, 1));
        cardForFred.add(new Card(Constants.ARROWS, 2));
        gameServer.rigCardsForPlayer(0, cardForFred);
    }

    @Given("rig the cards for Joe")
    public void rigTheCardsForJoe() {
        List<Card> cardForJoe = new ArrayList<>();
        cardForJoe.add(new Card(Constants.SORCERY, 2));
        cardForJoe.add(new Card(Constants.SWORDS, 1));
        cardForJoe.add(new Card(Constants.DECEPTION, 5));
        cardForJoe.add(new Card(Constants.ARROWS, 7));
        cardForJoe.add(new Card(Constants.ALCHEMY, 11));
        cardForJoe.add(new Card(Constants.SORCERY, 6));
        cardForJoe.add(new Card(Constants.ARROWS, 8));
        cardForJoe.add(new Card(Constants.SWORDS, 9));
        cardForJoe.add(new Card(Constants.DECEPTION, 6));
        cardForJoe.add(new Card(Constants.ALCHEMY, 12));
        cardForJoe.add(new Card(Constants.SORCERY, 3));
        cardForJoe.add(new Card(Constants.ARROWS, 4));
        gameServer.rigCardsForPlayer(1, cardForJoe);
    }

    @Given("rig the cards for Paul")
    public void rigTheCardsForPaul() {
        List<Card> cardForPaul = new ArrayList<>();
        cardForPaul.add(new Card(Constants.SORCERY, 7));
        cardForPaul.add(new Card(Constants.MERLIN, 0));
        cardForPaul.add(new Card(Constants.SWORDS, 7));
        cardForPaul.add(new Card(Constants.ALCHEMY, 1));
        cardForPaul.add(new Card(Constants.DECEPTION, 1));
        cardForPaul.add(new Card(Constants.SWORDS, 5));
        cardForPaul.add(new Card(Constants.SWORDS, 6));
        cardForPaul.add(new Card(Constants.SWORDS, 15));
        cardForPaul.add(new Card(Constants.DECEPTION, 2));
        cardForPaul.add(new Card(Constants.DECEPTION, 3));
        cardForPaul.add(new Card(Constants.ARROWS, 5));
        cardForPaul.add(new Card(Constants.ARROWS, 6));
        gameServer.rigCardsForPlayer(2, cardForPaul);
    }

    @Given("rig the cards for P1 scenario B")
    public void rigTheCardsForP1ScenarioB() {
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(Constants.ARROWS, 5));
        cards.add(new Card(Constants.SWORDS, 13));
        cards.add(new Card(Constants.SORCERY, 13));
        cards.add(new Card(Constants.ARROWS, 3));
        cards.add(new Card(Constants.ARROWS, 4));
        cards.add(new Card(Constants.SWORDS, 1));
        cards.add(new Card(Constants.SWORDS, 2));
        cards.add(new Card(Constants.SWORDS, 4));
        cards.add(new Card(Constants.SORCERY, 10));
        cards.add(new Card(Constants.SORCERY, 11));
        cards.add(new Card(Constants.ARROWS, 1));
        cards.add(new Card(Constants.ARROWS, 2));
        gameServer.rigCardsForPlayer(0, cards);
    }

    @Given("rig the cards for P2 scenario B")
    public void rigTheCardsForP2ScenarioB() {
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(Constants.SORCERY, 1));
        cards.add(new Card(Constants.SWORDS, 14));
        cards.add(new Card(Constants.DECEPTION, 5));
        cards.add(new Card(Constants.ARROWS, 7));
        cards.add(new Card(Constants.ALCHEMY, 11));
        cards.add(new Card(Constants.SORCERY, 6));
        cards.add(new Card(Constants.ARROWS, 8));
        cards.add(new Card(Constants.SWORDS, 9));
        cards.add(new Card(Constants.DECEPTION, 6));
        cards.add(new Card(Constants.ALCHEMY, 12));
        cards.add(new Card(Constants.SORCERY, 3));
        cards.add(new Card(Constants.ARROWS, 9));
        gameServer.rigCardsForPlayer(1, cards);
    }

    @Given("rig the cards for P3 scenario B")
    public void rigTheCardsForP3ScenarioB() {
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(Constants.SORCERY, 14));
        cards.add(new Card(Constants.MERLIN, 0));
        cards.add(new Card(Constants.SWORDS, 15));
        cards.add(new Card(Constants.ALCHEMY, 1));
        cards.add(new Card(Constants.DECEPTION, 1));
        cards.add(new Card(Constants.SWORDS, 5));
        cards.add(new Card(Constants.SWORDS, 6));
        cards.add(new Card(Constants.SWORDS, 15));
        cards.add(new Card(Constants.DECEPTION, 2));
        cards.add(new Card(Constants.DECEPTION, 3));
        cards.add(new Card(Constants.ARROWS, 12));
        cards.add(new Card(Constants.ARROWS, 6));
        gameServer.rigCardsForPlayer(2, cards);
    }

    @Given("setup round")
    public void setupRound() {
        roundNum++;
        gameServer.printInitialHand();
        gameServer.resetInjuryDeck();
        players = gameServer.getPlayers();
        numOfPlayers = gameServer.getNumOfPlayers();
    }

    @When("P1 plays {string}")
    public void P1PlaysCard(String card1) {
        currentPlayer = players[currentPlayerId];
        Integer cardNum = 1;
        String userInput;
        String instruction = gameServer.instructionToUserInput(card1);
        if (instruction.equals(Constants.VALUE)) {
            String cardValueStr = card1.substring(2);
            int cardValue = Integer.parseInt(cardValueStr);
            userInput = cardNum + "\n" + cardValue + "\n";
        } else if (instruction.equals(Constants.SUIT_AND_VALUE)) {
            String suitStr = card1.substring(2, 4);
            int suitNum = gameServer.getSuitNum(suitStr);
            String cardValueStr = card1.substring(4);
            int cardValue = Integer.parseInt(cardValueStr);
            userInput = cardNum + "\n" + cardValue + "\n" + suitNum + "\n";
        } else {
            userInput = cardNum + "\n";
        }
        ByteArrayInputStream in = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(in);
        if (suit.isEmpty()) {
            suit = currentPlayer.playMelee(suit);
        } else {
            currentPlayer.playMelee(suit);
        }
        System.setIn(inputStream);
        currentPlayerId++;
        numOfPersonPlayed++;
        // if current player is the last player, set current player id to the first player
        if (currentPlayerId == numOfPlayers) {
            currentPlayerId = 0;
        }
    }

    @When("P2 plays {string}")
    public void P2PlaysCard(String card2) {
        currentPlayer = players[currentPlayerId];
        Integer cardNum = 1;
        String userInput;
        String instruction = gameServer.instructionToUserInput(card2);
        if (instruction.equals(Constants.VALUE)) {
            String cardValueStr = card2.substring(2);
            int cardValue = Integer.parseInt(cardValueStr);
            userInput = cardNum + "\n" + cardValue + "\n";
        } else if (instruction.equals(Constants.SUIT_AND_VALUE)) {
            String suitStr = card2.substring(2, 4);
            int suitNum = gameServer.getSuitNum(suitStr);
            String cardValueStr = card2.substring(4);
            int cardValue = Integer.parseInt(cardValueStr);
            userInput = cardNum + "\n" + cardValue + "\n" + suitNum + "\n";
        } else {
            userInput = cardNum + "\n";
        }
        ByteArrayInputStream in = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(in);
        if (suit.isEmpty()) {
            suit = currentPlayer.playMelee(suit);
        } else {
            currentPlayer.playMelee(suit);
        }
        System.setIn(inputStream);
        currentPlayerId++;
        numOfPersonPlayed++;
        // if current player is the last player, set current player id to the first player
        if (currentPlayerId == numOfPlayers) {
            currentPlayerId = 0;
        }
    }

    @When("P3 plays {string}")
    public void P3PlaysCard(String card3) {
        currentPlayer = players[currentPlayerId];
        Integer cardNum = 1;
        String userInput;
        String instruction = gameServer.instructionToUserInput(card3);
        if (instruction.equals(Constants.VALUE)) {
            String cardValueStr = card3.substring(2);
            int cardValue = Integer.parseInt(cardValueStr);
            userInput = cardNum + "\n" + cardValue + "\n";
        } else if (instruction.equals(Constants.SUIT_AND_VALUE)) {
            String suitStr = card3.substring(2, 4);
            int suitNum = gameServer.getSuitNum(suitStr);
            String cardValueStr = card3.substring(4);
            int cardValue = Integer.parseInt(cardValueStr);
            userInput = cardNum + "\n" + cardValue + "\n" + suitNum + "\n";
        } else {
            userInput = cardNum + "\n";
        }
        ByteArrayInputStream in = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(in);
        if (suit.isEmpty()) {
            suit = currentPlayer.playMelee(suit);
        } else {
            currentPlayer.playMelee(suit);
        }
        System.setIn(inputStream);
        currentPlayerId++;
        numOfPersonPlayed++;
        // if current player is the last player, set current player id to the first player
        if (currentPlayerId == numOfPlayers) {
            currentPlayerId = 0;
        }
    }

    @When("P4 plays {string}")
    public void P4PlaysCard(String card4) {
        currentPlayer = players[currentPlayerId];
        Integer cardNum = 1;
        String userInput;
        String instruction = gameServer.instructionToUserInput(card4);
        if (instruction.equals(Constants.VALUE)) {
            String cardValueStr = card4.substring(2);
            int cardValue = Integer.parseInt(cardValueStr);
            userInput = cardNum + "\n" + cardValue + "\n";
        } else if (instruction.equals(Constants.SUIT_AND_VALUE)) {
            String suitStr = card4.substring(2, 4);
            int suitNum = gameServer.getSuitNum(suitStr);
            String cardValueStr = card4.substring(4);
            int cardValue = Integer.parseInt(cardValueStr);
            userInput = cardNum + "\n" + cardValue + "\n" + suitNum + "\n";
        } else {
            userInput = cardNum + "\n";
        }
        ByteArrayInputStream in = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(in);
        if (suit.isEmpty()) {
            suit = currentPlayer.playMelee(suit);
        } else {
            currentPlayer.playMelee(suit);
        }
        System.setIn(inputStream);
        currentPlayerId++;
        numOfPersonPlayed++;
        // if current player is the last player, set current player id to the first player
        if (currentPlayerId == numOfPlayers) {
            currentPlayerId = 0;
        }
    }

    @Given("player plays card {int}")
    public void playsCard(int cardNum) {
        currentPlayer = players[currentPlayerId];
        String userInput = cardNum + "\n";
        ByteArrayInputStream in = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(in);
        if (suit.isEmpty()) {
            suit = currentPlayer.playMelee(suit);
        } else {
            currentPlayer.playMelee(suit);
        }
        System.setIn(inputStream);
        currentPlayerId++;
        numOfPersonPlayed++;
        // if current player is the last player, set current player id to the first player
        if (currentPlayerId == numOfPlayers) {
            currentPlayerId = 0;
        }
    }

    @Given("Fred plays Alchemy and So11 in melee 1")
    public void FredPlaysMelee1() {
        currentPlayer = players[currentPlayerId];
        String userInput = 1 + "\n" + 3 + "\n";
        ByteArrayInputStream in = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(in);
        if (suit.isEmpty()) {
            suit = currentPlayer.playMelee(suit);
        } else {
            currentPlayer.playMelee(suit);
        }
        System.setIn(inputStream);
        currentPlayerId++;
        numOfPersonPlayed++;
        // if current player is the last player, set current player id to the first player
        if (currentPlayerId == numOfPlayers) {
            currentPlayerId = 0;
        }
    }

    @Given("Fred plays Ap and assigns 10 to it in melee 2")
    public void FredPlaysMelee2() {
        currentPlayer = players[currentPlayerId];
        String userInput = 4 + "\n" + 20 + "\n" + 10 + "\n";
        ByteArrayInputStream in = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(in);
        if (suit.isEmpty()) {
            suit = currentPlayer.playMelee(suit);
        } else {
            currentPlayer.playMelee(suit);
        }
        System.setIn(inputStream);
        currentPlayerId++;
        numOfPersonPlayed++;
        // if current player is the last player, set current player id to the first player
        if (currentPlayerId == numOfPlayers) {
            currentPlayerId = 0;
        }
    }

    @Given("Fred plays Sw3 in melee 3")
    public void FredPlaysMelee3() {
        currentPlayer = players[currentPlayerId];
        String userInput = 2 + "\n";
        ByteArrayInputStream in = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(in);
        if (suit.isEmpty()) {
            suit = currentPlayer.playMelee(suit);
        } else {
            currentPlayer.playMelee(suit);
        }
        System.setIn(inputStream);
        currentPlayerId++;
        numOfPersonPlayed++;
        // if current player is the last player, set current player id to the first player
        if (currentPlayerId == numOfPlayers) {
            currentPlayerId = 0;
        }
    }

    @Given("Fred plays De9 in melee 4")
    public void FredPlaysMelee4() {
        currentPlayer = players[currentPlayerId];
        String userInput = 3 + "\n";
        ByteArrayInputStream in = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(in);
        if (suit.isEmpty()) {
            suit = currentPlayer.playMelee(suit);
        } else {
            currentPlayer.playMelee(suit);
        }
        System.setIn(inputStream);
        currentPlayerId++;
        numOfPersonPlayed++;
        // if current player is the last player, set current player id to the first player
        if (currentPlayerId == numOfPlayers) {
            currentPlayerId = 0;
        }
    }

    @Given("Joe plays Sword, Deception, Arrow, Alchemy and So6 in melee 1")
    public void JoePlaysMelee1() {
        currentPlayer = players[currentPlayerId];
        String userInput = 2 + "\n" + 3 + "\n" + 4 + "\n" + 5 + "\n" + 6 + "\n";
        ByteArrayInputStream in = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(in);
        if (suit.isEmpty()) {
            suit = currentPlayer.playMelee(suit);
        } else {
            currentPlayer.playMelee(suit);
        }
        System.setIn(inputStream);
        currentPlayerId++;
        numOfPersonPlayed++;
        // if current player is the last player, set current player id to the first player
        if (currentPlayerId == numOfPlayers) {
            currentPlayerId = 0;
        }
    }

    @Given("Joe plays Ar8 in melee 2")
    public void JoePlaysMelee2() {
        currentPlayer = players[currentPlayerId];
        String userInput = 6 + "\n";
        ByteArrayInputStream in = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(in);
        if (suit.isEmpty()) {
            suit = currentPlayer.playMelee(suit);
        } else {
            currentPlayer.playMelee(suit);
        }
        System.setIn(inputStream);
        currentPlayerId++;
        numOfPersonPlayed++;
        // if current player is the last player, set current player id to the first player
        if (currentPlayerId == numOfPlayers) {
            currentPlayerId = 0;
        }
    }

    @Given("Joe plays Sw9 in melee 3")
    public void JoePlaysMelee3() {
        currentPlayer = players[currentPlayerId];
        String userInput = 6 + "\n";
        ByteArrayInputStream in = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(in);
        if (suit.isEmpty()) {
            suit = currentPlayer.playMelee(suit);
        } else {
            currentPlayer.playMelee(suit);
        }
        System.setIn(inputStream);
        currentPlayerId++;
        numOfPersonPlayed++;
        // if current player is the last player, set current player id to the first player
        if (currentPlayerId == numOfPlayers) {
            currentPlayerId = 0;
        }
    }

    @Given("Joe plays Alchemy and De6 in melee 4")
    public void JoePlaysMelee4() {
        currentPlayer = players[currentPlayerId];
        String userInput = 5 + "\n" + 6 + "\n";
        ByteArrayInputStream in = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(in);
        if (suit.isEmpty()) {
            suit = currentPlayer.playMelee(suit);
        } else {
            currentPlayer.playMelee(suit);
        }
        System.setIn(inputStream);
        currentPlayerId++;
        numOfPersonPlayed++;
        // if current player is the last player, set current player id to the first player
        if (currentPlayerId == numOfPlayers) {
            currentPlayerId = 0;
        }
    }

    @Given("Paul plays So7 in melee 1")
    public void paulPlaysMelee1() {
        currentPlayer = players[currentPlayerId];
        String userInput = 1 + "\n";
        ByteArrayInputStream in = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(in);
        if (suit.isEmpty()) {
            suit = currentPlayer.playMelee(suit);
        } else {
            currentPlayer.playMelee(suit);
        }
        System.setIn(inputStream);
        currentPlayerId++;
        numOfPersonPlayed++;
        // if current player is the last player, set current player id to the first player
        if (currentPlayerId == numOfPlayers) {
            currentPlayerId = 0;
        }
    }

    @Given("Paul plays Me and assigns 9 to it in melee 2")
    public void paulPlaysMelee2() {
        currentPlayer = players[currentPlayerId];
        String userInput = 1 + "\n" + 16 + "\n"+ 9 + "\n";
        ByteArrayInputStream in = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(in);
        if (suit.isEmpty()) {
            suit = currentPlayer.playMelee(suit);
        } else {
            currentPlayer.playMelee(suit);
        }
        System.setIn(inputStream);
        currentPlayerId++;
        numOfPersonPlayed++;
        // if current player is the last player, set current player id to the first player
        if (currentPlayerId == numOfPlayers) {
            currentPlayerId = 0;
        }
    }

    @Given("Paul plays Alchemy and Sw7 in melee 3")
    public void paulPlaysMelee3() {
        currentPlayer = players[currentPlayerId];
        String userInput = 2 + "\n" + 1 + "\n";
        ByteArrayInputStream in = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(in);
        if (suit.isEmpty()) {
            suit = currentPlayer.playMelee(suit);
        } else {
            currentPlayer.playMelee(suit);
        }
        System.setIn(inputStream);
        currentPlayerId++;
        numOfPersonPlayed++;
        // if current player is the last player, set current player id to the first player
        if (currentPlayerId == numOfPlayers) {
            currentPlayerId = 0;
        }
    }

    @When("Paul plays De1 in melee 4")
    public void paulPlaysMelee4() {
        currentPlayer = players[currentPlayerId];
        String userInput = 2 + "\n";
        ByteArrayInputStream in = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(in);
        if (suit.isEmpty()) {
            suit = currentPlayer.playMelee(suit);
        } else {
            currentPlayer.playMelee(suit);
        }
        System.setIn(inputStream);
        currentPlayerId++;
        numOfPersonPlayed++;
        // if current player is the last player, set current player id to the first player
        if (currentPlayerId == numOfPlayers) {
            currentPlayerId = 0;
        }
    }

    @Then("actual loser is {string}")
    public void verifyLoser(String expectedLoser) {
        if (numOfPersonPlayed == numOfPlayers) {
            String actualLoser;
            numOfPersonPlayed = 0;
            suit = "";
            System.out.println("This melee ends");
            loser = gameServer.getLoser();
            if (loser != null) {
                actualLoser = loser.getName();
                currentPlayerId = gameServer.getCurrentPlayerId();
            } else {
                actualLoser = "-";
            }
            assert actualLoser.equals(expectedLoser);
        }
    }

    @Given("loser is {string}")
    public void checkLoser(String expectedLoser) {
        if (numOfPersonPlayed == numOfPlayers) {
            String actualLoser;
            numOfPersonPlayed = 0;
            suit = "";
            System.out.println("This melee ends");
            loser = gameServer.getLoser();
            if (loser != null) {
                actualLoser = loser.getName();
                currentPlayerId = gameServer.getCurrentPlayerId();
            } else {
                actualLoser = "-";
            }
            assert actualLoser.equals(expectedLoser);
        }
    }

    @Then("actual injury points is {int}")
    public void verifyInjuryPoints(int expectedInjuryPoints) {
        int actualInjuryPoints;
        if (loser != null) {
            actualInjuryPoints = gameServer.getInjuryPoints(loser);
        } else {
            actualInjuryPoints = 0;
        }
        assert actualInjuryPoints == expectedInjuryPoints;
    }

    @Given("injury points is {int}")
    public void checkInjuryPoints(int expectedInjuryPoints) {
        int actualInjuryPoints;
        if (loser != null) {
            actualInjuryPoints = gameServer.getInjuryPoints(loser);
        } else {
            actualInjuryPoints = 0;
        }
        assert actualInjuryPoints == expectedInjuryPoints;
    }

    @Then("total injury points for Joe in this round is {int}")
    public void checkInjuryPointsForJoe(int expectedInjuryPoints) {
        int totalInjuryPoints = players[1].calculateTotalInjuryPoints();
        assert totalInjuryPoints == expectedInjuryPoints;
    }

    @Then("total injury points for Fred in this round is {int}")
    public void checkInjuryPointsForFred(int expectedInjuryPoints) {
        int totalInjuryPoints = players[0].calculateTotalInjuryPoints();
        assert totalInjuryPoints == expectedInjuryPoints;
    }

    @Then("total injury points for Paul in this round is {int}")
    public void checkInjuryPointsForPaul(int expectedInjuryPoints) {
        int totalInjuryPoints = players[2].calculateTotalInjuryPoints();
        assert totalInjuryPoints == expectedInjuryPoints;
    }

    @Then("game ends due to shaming")
    public void checkGameEnds() {
        boolean isGameOver = false;
        for (int i = 0; i < players.length; i++) {
            if (players[i].getHealthPoints() <= 0) {
                System.out.println("Game over");
                // calculate each player's health points at the end of the game
                for (int j = 0; j < players.length; j++) {
                    players[j].calculateHealthPoints();
                }
                isGameOver = true;
                gameServer.gameOver();
                break;
            }
        }
        assertTrue(isGameOver);
    }

}
