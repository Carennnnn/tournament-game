/*
package project;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UnitTest {

    // Test for in-range number of players
    @Test
    @DisplayName("U-TEST 001: Test for in-range number of players")
    public void testInRangeNumOfPlayers() {
        GameServer gameServer = new GameServer();
        int numPlayers1 = 3;
        int numPlayers2 = 4;
        int numPlayers3 = 5;
        assertTrue(gameServer.isNumOfPlayersInRange(numPlayers1));
        assertTrue(gameServer.isNumOfPlayersInRange(numPlayers2));
        assertTrue(gameServer.isNumOfPlayersInRange(numPlayers3));
    }

    // Test for out-of-range number of players
    @Test
    @DisplayName("U-TEST 002: Test for out-of-range number of players")
    public void testOutOfRangeNumOfPlayers() {
        GameServer gameServer = new GameServer();
        int numPlayers1 = -1;
        int numPlayers2 = 2;
        int numPlayers3 = 6;
        assertFalse(gameServer.isNumOfPlayersInRange(numPlayers1));
        assertFalse(gameServer.isNumOfPlayersInRange(numPlayers2));
        assertFalse(gameServer.isNumOfPlayersInRange(numPlayers3));
    }

    // Test for valid player name
    @Test
    @DisplayName("U-TEST 003: Test for valid player name")
    public void testValidPlayerName() {
        GameServer gameServer = new GameServer();
        String name1 = "a";
        String name2 = "1";
        String name3 = "&Tom";
        String name4 = ";";
        assertTrue(gameServer.isPlayerNameValid(name1));
        assertTrue(gameServer.isPlayerNameValid(name2));
        assertTrue(gameServer.isPlayerNameValid(name3));
        assertTrue(gameServer.isPlayerNameValid(name4));
    }

    // Test for empty player name
    @Test
    @DisplayName("U-TEST 004: Test for empty player name")
    public void testEmptyPlayerName() {
        GameServer gameServer = new GameServer();
        String name1 = "";
        assertFalse(gameServer.isPlayerNameValid(name1));
    }

    // Test for valid initial number of health points of the players
    @Test
    @DisplayName("U-TEST 005: Test for valid initial number of health points")
    public void testInitialHealthPoints() {
        GameServer gameServer = new GameServer();
        int healthPoints1 = 0;
        int healthPoints2 = 1;
        int healthPoints3 = 50;
        int healthPoints4 = 400;
        assertTrue(gameServer.isHealthPointsValid(healthPoints1));
        assertTrue(gameServer.isHealthPointsValid(healthPoints2));
        assertTrue(gameServer.isHealthPointsValid(healthPoints3));
        assertTrue(gameServer.isHealthPointsValid(healthPoints4));
    }

    // Test for invalid initial number of health points of the players
    @Test
    @DisplayName("U-TEST 006: Test for invalid initial number of health points")
    public void testInvalidInitialHealthPoints() {
        GameServer gameServer = new GameServer();
        int healthPoints1 = -1;
        int healthPoints2 = -50;
        int healthPoints3 = -400;
        assertFalse(gameServer.isHealthPointsValid(healthPoints1));
        assertFalse(gameServer.isHealthPointsValid(healthPoints2));
        assertFalse(gameServer.isHealthPointsValid(healthPoints3));
    }

    // Test for poisoned cards
    @Test
    @DisplayName("U-TEST 007: Test for poisoned cards")
    public void testPoisonedCards() {
        List<Card> poisonedCards = new ArrayList<>();
        Card card = new Card();
        poisonedCards.add(new Card(Constants.SWORDS, 6));
        poisonedCards.add(new Card(Constants.SWORDS, 7));
        poisonedCards.add(new Card(Constants.SWORDS, 8));
        poisonedCards.add(new Card(Constants.SWORDS, 9));
        poisonedCards.add(new Card(Constants.ARROWS, 8));
        poisonedCards.add(new Card(Constants.ARROWS, 9));
        poisonedCards.add(new Card(Constants.ARROWS, 10));
        poisonedCards.add(new Card(Constants.ARROWS, 11));
        poisonedCards.add(new Card(Constants.SORCERY, 5));
        poisonedCards.add(new Card(Constants.SORCERY, 6));
        poisonedCards.add(new Card(Constants.SORCERY, 11));
        poisonedCards.add(new Card(Constants.SORCERY, 12));
        poisonedCards.add(new Card(Constants.DECEPTION, 6));
        poisonedCards.add(new Card(Constants.DECEPTION, 7));
        poisonedCards.add(new Card(Constants.DECEPTION, 9));
        poisonedCards.add(new Card(Constants.DECEPTION, 10));

        for (Card poisonedCard : poisonedCards) {
            boolean isCardPoisoned = card.isPoisoned(poisonedCard.getWeaponType(), poisonedCard.getValue());
            assertTrue(isCardPoisoned);
        }
    }

    // Test for nonpoisonous cards
    @Test
    @DisplayName("U-TEST 008: Test for nonpoisonous cards")
    public void testNonpoisonousCards() {
        List<Card> cards = new ArrayList<>();
        Card card = new Card();
        cards.add(new Card(Constants.SWORDS, 1));
        cards.add(new Card(Constants.SWORDS, 15));
        cards.add(new Card(Constants.SWORDS, 5));
        cards.add(new Card(Constants.SWORDS, 10));
        cards.add(new Card(Constants.ARROWS, 2));
        cards.add(new Card(Constants.ARROWS, 14));
        cards.add(new Card(Constants.ARROWS, 7));
        cards.add(new Card(Constants.ARROWS, 12));
        cards.add(new Card(Constants.SORCERY, 7));
        cards.add(new Card(Constants.SORCERY, 8));
        cards.add(new Card(Constants.SORCERY, 9));
        cards.add(new Card(Constants.SORCERY, 10));
        cards.add(new Card(Constants.DECEPTION, 5));
        cards.add(new Card(Constants.DECEPTION, 8));
        cards.add(new Card(Constants.DECEPTION, 11));
        cards.add(new Card(Constants.DECEPTION, 12));

        for (Card poisonedCard : cards) {
            boolean isCardPoisoned = card.isPoisoned(poisonedCard.getWeaponType(), poisonedCard.getValue());
            assertFalse(isCardPoisoned);
        }
    }

    // Test if injury points are correct
    @Test
    @DisplayName("U-TEST 009: Test if injury points are calculated correct")
    public void testInjuryPoints() {
        // test injury points = 10
        List<Card> cards1 = new ArrayList<>();
        Card card = new Card();
        cards1.add(new Card(Constants.SWORDS, 6));
        cards1.add(new Card(Constants.ARROWS, 9));
        cards1.add(new Card(Constants.SORCERY, 6));
        cards1.add(new Card(Constants.DECEPTION, 7));

        for (Card card1 : cards1) {
            int injuryPoints = card.getInjuryPoints(card1.getWeaponType(), card1.getValue());
            assertEquals(10, injuryPoints);
        }

        // test injury points = 5
        List<Card> cards2 = new ArrayList<>();
        cards2.add(new Card(Constants.SWORDS, 10));
        cards2.add(new Card(Constants.ARROWS, 7));
        cards2.add(new Card(Constants.SORCERY, 8));
        cards2.add(new Card(Constants.DECEPTION, 11));
        cards2.add(new Card(Constants.ALCHEMY, 7));
        cards2.add(new Card(Constants.APPRENTICE, 2));

        for (Card card2 : cards2) {
            int injuryPoints = card.getInjuryPoints(card2.getWeaponType(), card2.getValue());
            assertEquals(5, injuryPoints);
        }

        // test injury points = 25
        List<Card> cards3 = new ArrayList<>();
        cards3.add(new Card(Constants.MERLIN, 1));
        cards3.add(new Card(Constants.MERLIN, 2));
        cards3.add(new Card(Constants.MERLIN, 15));

        for (Card card3 : cards3) {
            int injuryPoints = card.getInjuryPoints(card3.getWeaponType(), card3.getValue());
            assertEquals(25, injuryPoints);
        }
    }

    // Test if suits of cards are set correctly
    @Test
    @DisplayName("U-TEST 010: Test if suits of cards are set correctly")
    public void testSuits() {
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(Constants.SWORDS, 2));
        cards.add(new Card(Constants.ARROWS, 7));
        cards.add(new Card(Constants.SORCERY, 1));
        cards.add(new Card(Constants.DECEPTION, 14));

        String suit1 = cards.get(0).getSuit();
        assertEquals("Sw", suit1);
        String suit2 = cards.get(1).getSuit();
        assertEquals("Ar", suit2);
        String suit3 = cards.get(2).getSuit();
        assertEquals("So", suit3);
        String suit4 = cards.get(3).getSuit();
        assertEquals("De", suit4);
    }

    // Test for initializing cards
    @Test
    @DisplayName("U-TEST 011: Test for initializing cards")
    public void testInitializeCards() {
        GameServer gameServer = new GameServer();
        List<Card> initialDeck = gameServer.initializeCards();
        String initialDeckStr = "";

        for (Card card : initialDeck) {
            initialDeckStr += card.getCardInfo() + "\n";
        }

        String expectedInitialDeck = "Swords - value: 1, suit: Sw\n" +
                "Swords - value: 2, suit: Sw\n" +
                "Swords - value: 3, suit: Sw\n" +
                "Swords - value: 4, suit: Sw\n" +
                "Swords - value: 5, suit: Sw\n" +
                "Swords - value: 6, suit: Sw\n" +
                "Swords - value: 7, suit: Sw\n" +
                "Swords - value: 8, suit: Sw\n" +
                "Swords - value: 9, suit: Sw\n" +
                "Swords - value: 10, suit: Sw\n" +
                "Swords - value: 11, suit: Sw\n" +
                "Swords - value: 12, suit: Sw\n" +
                "Swords - value: 13, suit: Sw\n" +
                "Swords - value: 14, suit: Sw\n" +
                "Swords - value: 15, suit: Sw\n" +
                "Arrows - value: 1, suit: Ar\n" +
                "Arrows - value: 2, suit: Ar\n" +
                "Arrows - value: 3, suit: Ar\n" +
                "Arrows - value: 4, suit: Ar\n" +
                "Arrows - value: 5, suit: Ar\n" +
                "Arrows - value: 6, suit: Ar\n" +
                "Arrows - value: 7, suit: Ar\n" +
                "Arrows - value: 8, suit: Ar\n" +
                "Arrows - value: 9, suit: Ar\n" +
                "Arrows - value: 10, suit: Ar\n" +
                "Arrows - value: 11, suit: Ar\n" +
                "Arrows - value: 12, suit: Ar\n" +
                "Arrows - value: 13, suit: Ar\n" +
                "Arrows - value: 14, suit: Ar\n" +
                "Arrows - value: 15, suit: Ar\n" +
                "Sorcery - value: 1, suit: So\n" +
                "Sorcery - value: 2, suit: So\n" +
                "Sorcery - value: 3, suit: So\n" +
                "Sorcery - value: 4, suit: So\n" +
                "Sorcery - value: 5, suit: So\n" +
                "Sorcery - value: 6, suit: So\n" +
                "Sorcery - value: 7, suit: So\n" +
                "Sorcery - value: 8, suit: So\n" +
                "Sorcery - value: 9, suit: So\n" +
                "Sorcery - value: 10, suit: So\n" +
                "Sorcery - value: 11, suit: So\n" +
                "Sorcery - value: 12, suit: So\n" +
                "Sorcery - value: 13, suit: So\n" +
                "Sorcery - value: 14, suit: So\n" +
                "Sorcery - value: 15, suit: So\n" +
                "Deception - value: 1, suit: De\n" +
                "Deception - value: 2, suit: De\n" +
                "Deception - value: 3, suit: De\n" +
                "Deception - value: 4, suit: De\n" +
                "Deception - value: 5, suit: De\n" +
                "Deception - value: 6, suit: De\n" +
                "Deception - value: 7, suit: De\n" +
                "Deception - value: 8, suit: De\n" +
                "Deception - value: 9, suit: De\n" +
                "Deception - value: 10, suit: De\n" +
                "Deception - value: 11, suit: De\n" +
                "Deception - value: 12, suit: De\n" +
                "Deception - value: 13, suit: De\n" +
                "Deception - value: 14, suit: De\n" +
                "Deception - value: 15, suit: De\n" +
                "Alchemy - value: 1, suit: \n" +
                "Alchemy - value: 2, suit: \n" +
                "Alchemy - value: 3, suit: \n" +
                "Alchemy - value: 4, suit: \n" +
                "Alchemy - value: 5, suit: \n" +
                "Alchemy - value: 6, suit: \n" +
                "Alchemy - value: 7, suit: \n" +
                "Alchemy - value: 8, suit: \n" +
                "Alchemy - value: 9, suit: \n" +
                "Alchemy - value: 10, suit: \n" +
                "Alchemy - value: 11, suit: \n" +
                "Alchemy - value: 12, suit: \n" +
                "Alchemy - value: 13, suit: \n" +
                "Alchemy - value: 14, suit: \n" +
                "Alchemy - value: 15, suit: \n" +
                "Merlin - value: , suit: \n" +
                "Merlin - value: , suit: \n" +
                "Merlin - value: , suit: \n" +
                "Apprentice - value: , suit: \n" +
                "Apprentice - value: , suit: \n";

        assertEquals(expectedInitialDeck, initialDeckStr);
        assertEquals(80, initialDeck.size());
    }

    // Test for shuffle cards
    @Test
    @DisplayName("U-TEST 012: Test for shuffle cards")
    public void testShuffleCards() {
        GameServer gameServer = new GameServer();
        List<Card> initialCards = gameServer.initializeCards();

        // make sure the order of cards in shuffled deck is different
        String initialDeckStr = "";
        for (Card card : initialCards) {
            initialDeckStr += card.getCardInfo() + "\n";
        }

        List<Card> shuffledCards = gameServer.shuffleCards(initialCards);
        String shuffledDeckStr = "";
        for (Card card : shuffledCards) {
            shuffledDeckStr += card.getCardInfo() + "\n";
        }

        assertEquals(80, shuffledCards.size());
        assertNotEquals(initialDeckStr, shuffledDeckStr);
    }

    // Test each player receives 12 weapon cards from the deck for this round
    @Test
    @DisplayName("U-TEST 013: Test each player receives 12 weapon cards from the deck for this round")
    public void testDealCards() {
        GameServer gameServer = new GameServer();
        Player[] players = new Player[3];
        players[0] = new Player("1");
        players[1] = new Player("2");
        players[2] = new Player("3");
        gameServer.setPlayers(players);
        List<Card> initialCards = gameServer.initializeCards();
        List<Card> shuffledCards = gameServer.shuffleCards(initialCards);
        List<Card> expectedCards1 = shuffledCards.subList(0, 12);
        List<Card> expectedCards2 = shuffledCards.subList(12, 24);
        List<Card> expectedCards3 = shuffledCards.subList(24, 36);

        gameServer.dealCards();

        List<Card> player1Cards = players[0].getCardsOnHand();
        List<Card> player2Cards = players[1].getCardsOnHand();
        List<Card> player3Cards = players[2].getCardsOnHand();

        assertEquals(expectedCards1, player1Cards);
        assertEquals(expectedCards2, player2Cards);
        assertEquals(expectedCards3, player3Cards);
    }

    // Test remove cards
    @Test
    @DisplayName("U-TEST 014: Test remove cards")
    public void testRemoveCards() {
        GameServer gameServer = new GameServer();
        Player[] players = new Player[3];
        players[0] = new Player("1");
        players[1] = new Player("2");
        players[2] = new Player("3");
        gameServer.setPlayers(players);
        List<Card> initialCards = gameServer.initializeCards();
        List<Card> shuffledCards = gameServer.shuffleCards(initialCards);
        List<Card> expectedCards = shuffledCards.subList(36, 80);

        gameServer.dealCards();

        List<Card> remainingCards = gameServer.getDeck();
        assertEquals(expectedCards, remainingCards);
    }

    // Test initial hand of each player is printed correctly
    @Test
    @DisplayName("U-TEST 015: Test initial hand of each player is printed correctly")
    public void testPrintInitialHand() {
        GameServer gameServer = new GameServer();
        Player[] players = new Player[1];
        players[0] = new Player("1");
        gameServer.setPlayers(players);

        List<Card> player1Cards = new ArrayList<>();
        player1Cards.add(new Card(Constants.SWORDS, 1));
        player1Cards.add(new Card(Constants.SWORDS, 2));
        player1Cards.add(new Card(Constants.ARROWS, 7));
        player1Cards.add(new Card(Constants.SWORDS, 3));
        player1Cards.add(new Card(Constants.DECEPTION, 3));
        player1Cards.add(new Card(Constants.SWORDS, 4));
        player1Cards.add(new Card(Constants.ARROWS, 1));
        player1Cards.add(new Card(Constants.ARROWS, 2));
        player1Cards.add(new Card(Constants.SWORDS, 5));
        player1Cards.add(new Card(Constants.MERLIN, 0));
        player1Cards.add(new Card(Constants.SORCERY, 3));
        player1Cards.add(new Card(Constants.SORCERY, 4));

        players[0].receiveCards(player1Cards);
        String hand1 = "";
        hand1 += "Player " + players[0].getName() + "'s cards: \n";
        hand1 += players[0].printCards(players[0].getCardsOnHand());

        String expectedPrintMessage = "Player 1's cards: \n" +
                "1. Swords - value: 1, suit: Sw\n" +
                "2. Swords - value: 2, suit: Sw\n" +
                "3. Arrows - value: 7, suit: Ar\n" +
                "4. Swords - value: 3, suit: Sw\n" +
                "5. Deception - value: 3, suit: De\n" +
                "6. Swords - value: 4, suit: Sw\n" +
                "7. Arrows - value: 1, suit: Ar\n" +
                "8. Arrows - value: 2, suit: Ar\n" +
                "9. Swords - value: 5, suit: Sw\n" +
                "10. Merlin - value: , suit: \n" +
                "11. Sorcery - value: 3, suit: So\n" +
                "12. Sorcery - value: 4, suit: So\n";

        assertEquals(expectedPrintMessage, hand1);
    }

    // Test leader is the first player
    @Test
    @DisplayName("U-TEST 016: Test leader is the first player")
    public void testLeaderIsFirstPlayer() {
        GameServer gameServer = new GameServer();
        Player[] players = new Player[3];
        players[0] = new Player("1");
        players[1] = new Player("2");
        players[2] = new Player("3");
        gameServer.setPlayers(players);
        gameServer.setCurrentPlayerId(0);

        Player leader = players[0];
        Player player = players[gameServer.getCurrentPlayerId()];
        assertEquals(leader, player);
    }

    // Test for playing a valid first card of a melee
    @Test
    @DisplayName("U-TEST 017: Test for playing a valid first card of a melee")
    public void testFirstValidCard() {
        Player[] players = new Player[2];
        players[0] = new Player("1");
        players[1] = new Player("2");

        List<Card> cards1 = new ArrayList<>();
        cards1.add(new Card(Constants.DECEPTION, 1));
        cards1.add(new Card(Constants.ARROWS, 2));
        cards1.add(new Card(Constants.ARROWS, 7));
        cards1.add(new Card(Constants.SWORDS, 3));
        cards1.add(new Card(Constants.DECEPTION, 3));
        cards1.add(new Card(Constants.SWORDS, 4));
        cards1.add(new Card(Constants.ARROWS, 11));
        cards1.add(new Card(Constants.ARROWS, 9));
        cards1.add(new Card(Constants.SWORDS, 5));
        cards1.add(new Card(Constants.MERLIN, 0));
        cards1.add(new Card(Constants.SORCERY, 3));
        cards1.add(new Card(Constants.SORCERY, 4));

        players[0].receiveCards(cards1);
        Card firstCard1 = players[0].getCardsOnHand().get(3);
        assertFalse(players[0].isCardInvalid(firstCard1, ""));

        List<Card> cards2 = new ArrayList<>();
        cards2.add(new Card(Constants.ALCHEMY, 1));
        cards2.add(new Card(Constants.ALCHEMY, 2));
        players[1].receiveCards(cards2);
        Card firstCard2 = players[1].getCardsOnHand().get(0);
        assertFalse(players[1].isCardInvalid(firstCard2, ""));
    }

    // Test for playing an invalid first card of a melee
    @Test
    @DisplayName("U-TEST 018: Test for playing an invalid first card of a melee")
    public void testFirstInvalidCard() {
        Player[] players = new Player[1];
        players[0] = new Player("1");

        List<Card> cards1 = new ArrayList<>();
        cards1.add(new Card(Constants.DECEPTION, 1));
        cards1.add(new Card(Constants.ARROWS, 2));
        cards1.add(new Card(Constants.ALCHEMY, 7));

        players[0].receiveCards(cards1);
        Card firstCard = players[0].getCardsOnHand().get(2);
        assertTrue(players[0].isCardInvalid(firstCard, ""));
    }

    // Test for valid card number input
    @Test
    @DisplayName("U-TEST 019: Test for valid card number input")
    public void testValidCardNum() {
        Player[] players = new Player[1];
        players[0] = new Player("1");

        List<Card> cards1 = new ArrayList<>();
        cards1.add(new Card(Constants.DECEPTION, 1));
        cards1.add(new Card(Constants.ARROWS, 2));
        cards1.add(new Card(Constants.ALCHEMY, 7));
        cards1.add(new Card(Constants.SWORDS, 4));
        cards1.add(new Card(Constants.ARROWS, 1));
        cards1.add(new Card(Constants.ALCHEMY, 2));
        cards1.add(new Card(Constants.SWORDS, 5));
        cards1.add(new Card(Constants.MERLIN, 0));

        players[0].receiveCards(cards1);
        assertTrue(players[0].isCardNumValid(1));
        assertTrue(players[0].isCardNumValid(8));
    }

    // Test for invalid card number input
    @Test
    @DisplayName("U-TEST 020: Test for invalid card number input")
    public void testInvalidCardNum() {
        Player[] players = new Player[1];
        players[0] = new Player("1");

        List<Card> cards1 = new ArrayList<>();
        cards1.add(new Card(Constants.DECEPTION, 1));
        cards1.add(new Card(Constants.ARROWS, 2));
        cards1.add(new Card(Constants.ALCHEMY, 7));
        cards1.add(new Card(Constants.SWORDS, 4));
        cards1.add(new Card(Constants.ARROWS, 1));
        cards1.add(new Card(Constants.ALCHEMY, 2));
        cards1.add(new Card(Constants.SWORDS, 5));
        cards1.add(new Card(Constants.MERLIN, 0));

        players[0].receiveCards(cards1);
        assertFalse(players[0].isCardNumValid(-1));
        assertFalse(players[0].isCardNumValid(0));
        assertFalse(players[0].isCardNumValid(9));
    }

    // Test for valid suit number input
    @Test
    @DisplayName("U-TEST 021: Test for valid suit number input")
    public void testValidSuitNum() {
        Player player = new Player("1");
        int suit1 = 1;
        int suit2 = 2;
        int suit3 = 4;
        assertTrue(player.isSuitNumValid(suit1));
        assertTrue(player.isSuitNumValid(suit2));
        assertTrue(player.isSuitNumValid(suit3));
    }

    // Test for invalid suit number input
    @Test
    @DisplayName("U-TEST 022: Test for invalid suit number input")
    public void testinvalidSuitNum() {
        Player player = new Player("1");
        int suit1 = -1;
        int suit2 = 0;
        int suit3 = 5;
        assertFalse(player.isSuitNumValid(suit1));
        assertFalse(player.isSuitNumValid(suit2));
        assertFalse(player.isSuitNumValid(suit3));
    }

    // Test for valid value of Merlin or Apprentice
    @Test
    @DisplayName("U-TEST 023: Test for valid value of Merlin or Apprentice")
    public void testValidValue() {
        Player player = new Player("1");
        int value1 = 1;
        int value2 = 7;
        int value3 = 15;
        assertTrue(player.isValueValid(value1));
        assertTrue(player.isValueValid(value2));
        assertTrue(player.isValueValid(value3));
    }

    // Test for invalid value of Merlin or Apprentice
    @Test
    @DisplayName("U-TEST 024: Test for invalid value of Merlin or Apprentice")
    public void testInvalidValue() {
        Player player = new Player("1");
        int value1 = -1;
        int value2 = 0;
        int value3 = 16;
        assertFalse(player.isValueValid(value1));
        assertFalse(player.isValueValid(value2));
        assertFalse(player.isValueValid(value3));
    }

    // Test for play a card of the suit of this melee
    @Test
    @DisplayName("U-TEST 025: Test for play a card of the suit of this melee")
    public void testPlayCardOfSuitOfMelee() {
        Player player1 = new Player("1");

        List<Card> cards1 = new ArrayList<>();
        cards1.add(new Card(Constants.DECEPTION, 1));
        cards1.add(new Card(Constants.ARROWS, 2));
        cards1.add(new Card(Constants.MERLIN, 0));
        cards1.add(new Card(Constants.SWORDS, 3));
        cards1.add(new Card(Constants.DECEPTION, 3));
        cards1.add(new Card(Constants.SWORDS, 4));
        cards1.add(new Card(Constants.DECEPTION, 9));
        cards1.add(new Card(Constants.ARROWS, 4));
        cards1.add(new Card(Constants.SWORDS, 5));
        cards1.add(new Card(Constants.MERLIN, 0));
        cards1.add(new Card(Constants.SORCERY, 3));
        cards1.add(new Card(Constants.SORCERY, 4));

        player1.receiveCards(cards1);
        Card playedCard1 = player1.getCardsOnHand().get(1);
        assertFalse(player1.isCardInvalid(playedCard1, "Ar"));
        Card playedCard2 = player1.getCardsOnHand().get(3);
        assertFalse(player1.isCardInvalid(playedCard2, "Sw"));
        Card playedCard3 = player1.getCardsOnHand().get(2);
        assertFalse(player1.isCardInvalid(playedCard3, "De"));
    }

    // Test for play a card of not the suit of this melee
    @Test
    @DisplayName("U-TEST 026: Test for play a card of not the suit of this melee")
    public void testPlayCardOfNotSuitOfMelee() {
        Player player1 = new Player("1");

        List<Card> cards1 = new ArrayList<>();
        cards1.add(new Card(Constants.DECEPTION, 1));
        cards1.add(new Card(Constants.ARROWS, 2));
        cards1.add(new Card(Constants.MERLIN, 0));
        cards1.add(new Card(Constants.SWORDS, 3));
        cards1.add(new Card(Constants.DECEPTION, 3));
        cards1.add(new Card(Constants.SWORDS, 4));
        cards1.add(new Card(Constants.DECEPTION, 9));
        cards1.add(new Card(Constants.ARROWS, 4));
        cards1.add(new Card(Constants.SWORDS, 5));
        cards1.add(new Card(Constants.MERLIN, 0));
        cards1.add(new Card(Constants.SORCERY, 3));
        cards1.add(new Card(Constants.SORCERY, 4));

        player1.receiveCards(cards1);
        Card playedCard1 = player1.getCardsOnHand().get(0);
        assertTrue(player1.isCardInvalid(playedCard1, "Ar"));
        Card playedCard2 = player1.getCardsOnHand().get(5);
        assertTrue(player1.isCardInvalid(playedCard2, "So"));
    }

    // Test if player can play Alchemy card
    @Test
    @DisplayName("U-TEST 027: Test if player can play Alchemy card")
    public void testCanPlayAlchemy() {
        Player player1 = new Player("1");

        List<Card> cards1 = new ArrayList<>();
        cards1.add(new Card(Constants.ARROWS, 1));
        cards1.add(new Card(Constants.ARROWS, 2));
        cards1.add(new Card(Constants.ARROWS, 3));
        cards1.add(new Card(Constants.ARROWS, 4));
        cards1.add(new Card(Constants.ARROWS, 5));
        cards1.add(new Card(Constants.SORCERY, 4));
        cards1.add(new Card(Constants.SORCERY, 9));
        cards1.add(new Card(Constants.SORCERY, 3));
        cards1.add(new Card(Constants.SORCERY, 2));
        cards1.add(new Card(Constants.DECEPTION, 7));
        cards1.add(new Card(Constants.DECEPTION, 3));
        cards1.add(new Card(Constants.DECEPTION, 4));

        player1.receiveCards(cards1);
        assertTrue(player1.canPlayAlchemy("Sw"));
    }

    // Test if player cannot play Alchemy card
    @Test
    @DisplayName("U-TEST 028: Test if player cannot play Alchemy card")
    public void testCannotPlayAlchemy() {
        Player player1 = new Player("1");
        Player player2 = new Player("2");

        List<Card> cards1 = new ArrayList<>();
        cards1.add(new Card(Constants.ARROWS, 1));
        cards1.add(new Card(Constants.ARROWS, 2));
        cards1.add(new Card(Constants.ARROWS, 3));
        cards1.add(new Card(Constants.ARROWS, 4));
        cards1.add(new Card(Constants.ARROWS, 5));
        cards1.add(new Card(Constants.SORCERY, 4));
        cards1.add(new Card(Constants.SORCERY, 9));
        cards1.add(new Card(Constants.SORCERY, 3));
        cards1.add(new Card(Constants.SORCERY, 2));
        cards1.add(new Card(Constants.DECEPTION, 0));
        cards1.add(new Card(Constants.DECEPTION, 3));
        cards1.add(new Card(Constants.DECEPTION, 4));
        player1.receiveCards(cards1);
        assertFalse(player1.canPlayAlchemy("Ar"));

        List<Card> cards2 = new ArrayList<>();
        cards2.add(new Card(Constants.ARROWS, 1));
        cards2.add(new Card(Constants.APPRENTICE, 0));
        cards2.add(new Card(Constants.DECEPTION, 3));
        player2.receiveCards(cards2);
        assertFalse(player2.canPlayAlchemy("Sw"));
    }

    // Test for remove card from player's hand
    @Test
    @DisplayName("U-TEST 029: Test for remove card from player's hand")
    public void testRemoveCard() {
        Player player1 = new Player("1");
        List<Card> cards1 = new ArrayList<>();
        cards1.add(new Card(Constants.ARROWS, 1));
        cards1.add(new Card(Constants.ARROWS, 2));
        cards1.add(new Card(Constants.ARROWS, 3));
        cards1.add(new Card(Constants.ARROWS, 4));
        cards1.add(new Card(Constants.ARROWS, 5));
        cards1.add(new Card(Constants.SORCERY, 4));
        cards1.add(new Card(Constants.SORCERY, 9));
        cards1.add(new Card(Constants.SORCERY, 3));
        cards1.add(new Card(Constants.SORCERY, 2));
        cards1.add(new Card(Constants.DECEPTION, 0));
        cards1.add(new Card(Constants.DECEPTION, 3));
        cards1.add(new Card(Constants.DECEPTION, 4));

        List<Card> expectedCards1 = cards1.subList(0, 11);
        List<Card> expectedCards2 = cards1.subList(1, 11);
        player1.receiveCards(cards1);
        Card playedCard1 = player1.getCardsOnHand().get(11);
        player1.removeCard(playedCard1);
        assertEquals(expectedCards1, player1.getCardsOnHand());
        Card playedCard2 = player1.getCardsOnHand().get(0);
        player1.removeCard(playedCard2);
        assertEquals(expectedCards2, player1.getCardsOnHand());
    }

    // Test for card must be discarded
    @Test
    @DisplayName("U-TEST 030: Test for card must be discarded")
    public void testDiscardCard() {
        Player player1 = new Player("1");
        List<Card> cards1 = new ArrayList<>();
        cards1.add(new Card(Constants.ARROWS, 1));
        cards1.add(new Card(Constants.ARROWS, 2));
        cards1.add(new Card(Constants.ARROWS, 3));
        cards1.add(new Card(Constants.ARROWS, 4));
        cards1.add(new Card(Constants.ARROWS, 5));
        cards1.add(new Card(Constants.SORCERY, 4));
        cards1.add(new Card(Constants.SORCERY, 9));
        cards1.add(new Card(Constants.SORCERY, 3));
        cards1.add(new Card(Constants.SORCERY, 2));
        cards1.add(new Card(Constants.DECEPTION, 0));
        cards1.add(new Card(Constants.DECEPTION, 3));
        cards1.add(new Card(Constants.DECEPTION, 4));
        player1.receiveCards(cards1);
        assertTrue(player1.mustDiscardCard("Sw"));
    }

    // Test for card cannot be discarded
    @Test
    @DisplayName("U-TEST 031: Test for card cannot be discarded")
    public void testNotDiscardCard() {
        Player player1 = new Player("1");
        List<Card> cards1 = new ArrayList<>();
        cards1.add(new Card(Constants.ARROWS, 1));
        cards1.add(new Card(Constants.ARROWS, 2));
        cards1.add(new Card(Constants.ARROWS, 3));
        cards1.add(new Card(Constants.ARROWS, 4));
        cards1.add(new Card(Constants.ARROWS, 5));
        cards1.add(new Card(Constants.SORCERY, 4));
        cards1.add(new Card(Constants.SORCERY, 9));
        cards1.add(new Card(Constants.SORCERY, 3));
        cards1.add(new Card(Constants.SORCERY, 2));
        cards1.add(new Card(Constants.DECEPTION, 0));
        cards1.add(new Card(Constants.DECEPTION, 3));
        cards1.add(new Card(Constants.DECEPTION, 4));
        player1.receiveCards(cards1);
        assertFalse(player1.mustDiscardCard("Ar"));

        List<Card> cards2 = new ArrayList<>();
        cards2.add(new Card(Constants.MERLIN, 0));
        cards2.add(new Card(Constants.ARROWS, 2));
        player1.receiveCards(cards2);
        assertFalse(player1.mustDiscardCard("So"));
    }

    // Test for the suit of a melee is determined by the first card
    @Test
    @DisplayName("U-TEST 032: Test for the suit of a melee is determined by the first card")
    public void testSuitOfAMelee() {
        Player player = new Player("1");
        String suit = "";
        Card playedCard1 = new Card(Constants.SWORDS, 1);
        if (suit.isEmpty()) {
            suit = playedCard1.getSuit();
        }
        assertEquals("Sw", suit);

        Card playedCard2 = new Card(Constants.MERLIN, 0);
        int suitNum = 2;
        suit = player.getSuitName(suitNum);
        playedCard2.setSuit(suit);
        assertEquals("Ar", suit);
    }

    // Test for second and subsequent players plays in order
    @Test
    @DisplayName("U-TEST 033: Test for second and subsequent players plays in order")
    public void testPlayerOrder() {
        GameServer gameServer = new GameServer();
        Player[] players = new Player[3];
        players[0] = new Player("1");
        players[1] = new Player("2");
        players[2] = new Player("3");
        gameServer.setPlayers(players);

        int currentPlayerId = gameServer.getCurrentPlayerId();
        Player player1 = players[currentPlayerId];
        assertEquals(players[0], player1);
        currentPlayerId++;
        Player player2 = players[currentPlayerId];
        assertEquals(players[1], player2);
        currentPlayerId++;
        Player player3 = players[currentPlayerId];
        assertEquals(players[2], player3);
    }

    // Test for all played cards are collected in a melee
    @Test
    @DisplayName("U-TEST 034: Test for all played cards are collected in a melee")
    public void testGetAllPlayedCards() {
        GameServer gameServer = new GameServer();
        Player[] players = new Player[3];
        players[0] = new Player("1");
        players[1] = new Player("2");
        players[2] = new Player("3");
        gameServer.setPlayers(players);
        Card card1 = new Card(Constants.SWORDS, 1);
        Card card2 = new Card(Constants.ARROWS, 2);
        Card card3 = new Card(Constants.DECEPTION, 3);
        players[0].setPlayedCard(card1);
        players[1].setPlayedCard(card2);
        players[2].setPlayedCard(card3);
        List<Card> expectedCards = new ArrayList<>();
        expectedCards.add(card1);
        expectedCards.add(card2);
        expectedCards.add(card3);
        List<Card> allPlayedCards = gameServer.getAllPlayedCards();
        assertEquals(expectedCards, allPlayedCards);
        assertEquals(players[0], card1.getCardOwner());
        assertEquals(players[1], card2.getCardOwner());
        assertEquals(players[2], card3.getCardOwner());
    }

    // Test for which of the played cards of this melee have the same value and ignore them
    @Test
    @DisplayName("U-TEST 035: Test for which of the played cards of this melee have the same value and ignore them")
    public void testIgnoreCards() {
        Score score = new Score();
        List<Card> playedCards1 = new ArrayList<>();
        playedCards1.add(new Card(Constants.SWORDS, 9));
        playedCards1.add(new Card(Constants.SWORDS, 3));
        playedCards1.add(new Card(Constants.ALCHEMY, 3));
        playedCards1.add(new Card(Constants.MERLIN, 3));
        List<Card> expectedCards1 = new ArrayList<>();
        expectedCards1.add(playedCards1.get(0));
        List<Card> remainingCards1 = score.ignoreCards(playedCards1);
        assertEquals(expectedCards1, remainingCards1);

        List<Card> playedCards2 = new ArrayList<>();
        playedCards2.add(new Card(Constants.SWORDS, 1));
        playedCards2.add(new Card(Constants.DECEPTION, 2));
        playedCards2.add(new Card(Constants.ARROWS, 3));
        playedCards2.add(new Card(Constants.MERLIN, 4));
        List<Card> expectedCards2 = new ArrayList<>();
        expectedCards2.add(playedCards2.get(0));
        expectedCards2.add(playedCards2.get(1));
        expectedCards2.add(playedCards2.get(2));
        expectedCards2.add(playedCards2.get(3));
        List<Card> remainingCards2 = score.ignoreCards(playedCards2);
        assertEquals(expectedCards2, remainingCards2);

        List<Card> playedCards3 = new ArrayList<>();
        playedCards3.add(new Card(Constants.SWORDS, 5));
        playedCards3.add(new Card(Constants.DECEPTION, 5));
        playedCards3.add(new Card(Constants.ARROWS, 5));
        List<Card> expectedCards3 = new ArrayList<>();
        List<Card> remainingCards3 = score.ignoreCards(playedCards3);
        assertEquals(expectedCards3, remainingCards3);
    }

    // Test for determine loser for a melee
    @Test
    @DisplayName("U-TEST 036: Test for determine loser for a melee")
    public void testDetermineLoser() {
        Score score = new Score();
        Player[] players = new Player[4];
        players[0] = new Player("1");
        players[1] = new Player("2");
        players[2] = new Player("3");
        players[3] = new Player("4");
        List<Card> playedCards1 = new ArrayList<>();
        playedCards1.add(new Card(Constants.SWORDS, 9));
        playedCards1.add(new Card(Constants.SWORDS, 3));
        playedCards1.add(new Card(Constants.ALCHEMY, 3));
        playedCards1.add(new Card(Constants.MERLIN, 3));
        playedCards1.get(0).setCardOwner(players[0]);
        playedCards1.get(1).setCardOwner(players[1]);
        playedCards1.get(2).setCardOwner(players[2]);
        playedCards1.get(3).setCardOwner(players[3]);
        List<Card> remainingCards1 = score.ignoreCards(playedCards1);
        Player loser1 = score.getLoser(remainingCards1);
        assertEquals(players[0], loser1);
    }

    // Test for no loser in a melee
    @Test
    @DisplayName("U-TEST 037: Test for no loser in a melee")
    public void testNoLoser() {
        Score score = new Score();
        Player[] players = new Player[4];
        players[0] = new Player("1");
        players[1] = new Player("2");
        players[2] = new Player("3");
        players[3] = new Player("4");
        List<Card> playedCards1 = new ArrayList<>();
        playedCards1.add(new Card(Constants.SWORDS, 2));
        playedCards1.add(new Card(Constants.ARROWS, 2));
        playedCards1.add(new Card(Constants.ALCHEMY, 5));
        playedCards1.add(new Card(Constants.MERLIN, 5));
        playedCards1.get(0).setCardOwner(players[0]);
        playedCards1.get(1).setCardOwner(players[1]);
        playedCards1.get(2).setCardOwner(players[2]);
        playedCards1.get(3).setCardOwner(players[3]);
        List<Card> remainingCards1 = score.ignoreCards(playedCards1);
        Player loser1 = score.getLoser(remainingCards1);
        assertEquals(null, loser1);
    }

    // Test for add all weapon cards played in this melee to loser's injury deck
    @Test
    @DisplayName("U-TEST 038: Test for add all weapon cards played in this melee to loser's injury deck")
    public void testInjuryDeck() {
        Player loser = new Player("1");
        List<Card> playedCards1 = new ArrayList<>();
        playedCards1.add(new Card(Constants.SWORDS, 2));
        playedCards1.add(new Card(Constants.ARROWS, 2));
        playedCards1.add(new Card(Constants.ALCHEMY, 5));
        playedCards1.add(new Card(Constants.MERLIN, 5));
        loser.addToInjuryDeck(playedCards1);
        assertEquals(playedCards1, loser.getInjuryDeck());
    }

    // Test for calculate injury points accumulated in a melee of a loser
    @Test
    @DisplayName("U-TEST 039: Test for calculate injury points accumulated in a melee of a loser")
    public void testInjuryPointsInMelee() {
        Player loser = new Player("1");
        List<Card> playedCards1 = new ArrayList<>();
        playedCards1.add(new Card(Constants.SWORDS, 9));
        playedCards1.add(new Card(Constants.SWORDS, 3));
        playedCards1.add(new Card(Constants.ALCHEMY, 3));
        playedCards1.add(new Card(Constants.MERLIN, 3));
        int injuryPoints1 = loser.getInjuryPointsInMelee(playedCards1);
        assertEquals(45, injuryPoints1);
    }

    // Test for the loser of this melee becomes the leader for the next melee
    @Test
    @DisplayName("U-TEST 040: Test for the loser of this melee becomes the leader for the next melee")
    public void testLoserBecomesLeader() {
        Score score = new Score();
        Player[] players = new Player[3];
        players[0] = new Player("1");
        players[1] = new Player("2");
        players[2] = new Player("3");
        Player loser = players[1];
        int currentPlayerId = score.getLoserPlayerId(players, loser);
        Player leaderOfNextMelee = players[currentPlayerId];
        assertEquals(players[1], leaderOfNextMelee);
    }

    // Test the leader of the melee with no loser is the leader of the next melee
    @Test
    @DisplayName("U-TEST 041: Test the leader of the melee with no loser is the leader of the next melee")
    public void testLeaderWithNoLoser() {
        Player[] players = new Player[3];
        players[0] = new Player("1");
        players[1] = new Player("2");
        players[2] = new Player("3");
        int currentPlayerId = 1;
        currentPlayerId++;
        Player leaderOfNextMelee = players[currentPlayerId];
        assertEquals(players[2], leaderOfNextMelee);
    }

    // Test for the round ends after 12 melees
    @Test
    @DisplayName("U-TEST 042: Test for the round ends after 12 melees")
    public void testRoundEnds() {
        int numOfMeleePlayed = 0;
        boolean isRoundRunning = true;
        while (isRoundRunning) {
            numOfMeleePlayed++;
            if (numOfMeleePlayed == Constants.MELEE_NUM) {
                isRoundRunning = false;
            }
        }
        assertEquals(12,numOfMeleePlayed);
    }

    // Test for total injury points suffered by each player in a round
    @Test
    @DisplayName("U-TEST 043: Test for total injury points suffered by each player in a round")
    public void testTotalInjuryPoints() {
        Player player = new Player("1");
        List<Card> injuryDeck1 = new ArrayList<>();
        injuryDeck1.add(new Card(Constants.SWORDS, 9));
        injuryDeck1.add(new Card(Constants.SWORDS, 3));
        injuryDeck1.add(new Card(Constants.ALCHEMY, 3));
        injuryDeck1.add(new Card(Constants.MERLIN, 3));
        player.addToInjuryDeck(injuryDeck1);
        int totalInjuryPoints = player.calculateTotalInjuryPoints();
        assertEquals(45, totalInjuryPoints);
    }

    // Test for player's health points at the end of the round
    @Test
    @DisplayName("U-TEST 044: Test for player's health points at the end of the round")
    public void testFinalHealthPoints() {
        Player player = new Player("1");
        player.setHealthPoints(50);
        List<Card> injuryDeck1 = new ArrayList<>();
        injuryDeck1.add(new Card(Constants.SWORDS, 9));
        injuryDeck1.add(new Card(Constants.SWORDS, 3));
        injuryDeck1.add(new Card(Constants.ALCHEMY, 3));
        injuryDeck1.add(new Card(Constants.MERLIN, 3));
        player.addToInjuryDeck(injuryDeck1);
        int finalHealthPoints = player.calculateHealthPoints();
        assertEquals(5, finalHealthPoints);
    }

    // Test for one or several winners at the end of the round
    @Test
    @DisplayName("U-TEST 045: Test for one or several winners at the end of the round")
    public void testOneOrSeveralWinners() {
        Score score = new Score();
        // Test one winner
        Player[] players = new Player[3];
        players[0] = new Player("1");
        players[1] = new Player("2");
        players[2] = new Player("3");
        for (int i = 0; i < players.length; i++) {
            players[i].setHealthPoints(50);
        }
        List<Card> injuryDeck1 = new ArrayList<>();
        injuryDeck1.add(new Card(Constants.SWORDS, 6));
        injuryDeck1.add(new Card(Constants.SWORDS, 3));
        injuryDeck1.add(new Card(Constants.SORCERY, 1));
        players[0].addToInjuryDeck(injuryDeck1);
        players[0].calculateHealthPoints();
        List<Card> injuryDeck2 = new ArrayList<>();
        injuryDeck2.add(new Card(Constants.ARROWS, 1));
        injuryDeck2.add(new Card(Constants.SWORDS, 2));
        injuryDeck2.add(new Card(Constants.SORCERY, 3));
        players[1].addToInjuryDeck(injuryDeck2);
        players[1].calculateHealthPoints();
        List<Card> injuryDeck3 = new ArrayList<>();
        injuryDeck3.add(new Card(Constants.ARROWS, 1));
        injuryDeck3.add(new Card(Constants.SWORDS, 2));
        injuryDeck3.add(new Card(Constants.SORCERY, 3));
        injuryDeck3.add(new Card(Constants.MERLIN, 10));
        players[2].addToInjuryDeck(injuryDeck3);
        players[2].calculateHealthPoints();
        List<Player> winner1 = score.getWinners(players);
        List<Player> expectedWinner = new ArrayList<>();
        expectedWinner.add(players[1]);
        assertEquals(expectedWinner, winner1);

        // Test two winners
        for (int i = 0; i < players.length; i++) {
            players[i].setHealthPoints(50);
        }
        List<Card> injuryDeck4 = new ArrayList<>();
        injuryDeck4.add(new Card(Constants.DECEPTION, 1));
        players[1].addToInjuryDeck(injuryDeck4);
        players[0].calculateHealthPoints();
        players[1].calculateHealthPoints();
        players[2].calculateHealthPoints();
        List<Player> winners2 = score.getWinners(players);
        List<Player> expectedWinners = new ArrayList<>();
        expectedWinners.add(players[0]);
        expectedWinners.add(players[1]);
        assertEquals(expectedWinners, winners2);
    }

    // Test for no winner at the end of the round
    @Test
    @DisplayName("U-TEST 046: Test for no winner at the end of the round")
    public void testNoWinner() {
        Score score = new Score();
        // Test one winner
        Player[] players = new Player[3];
        players[0] = new Player("1");
        players[1] = new Player("2");
        players[2] = new Player("3");
        for (int i = 0; i < players.length; i++) {
            players[i].setHealthPoints(20);
        }
        List<Card> injuryDeck1 = new ArrayList<>();
        injuryDeck1.add(new Card(Constants.SWORDS, 6));
        injuryDeck1.add(new Card(Constants.SWORDS, 3));
        injuryDeck1.add(new Card(Constants.SORCERY, 1));
        players[0].addToInjuryDeck(injuryDeck1);
        players[0].calculateHealthPoints();
        List<Card> injuryDeck2 = new ArrayList<>();
        injuryDeck2.add(new Card(Constants.ARROWS, 8));
        injuryDeck2.add(new Card(Constants.SWORDS, 2));
        injuryDeck2.add(new Card(Constants.SORCERY, 3));
        players[1].addToInjuryDeck(injuryDeck2);
        players[1].calculateHealthPoints();
        List<Card> injuryDeck3 = new ArrayList<>();
        injuryDeck3.add(new Card(Constants.ARROWS, 1));
        injuryDeck3.add(new Card(Constants.SWORDS, 2));
        injuryDeck3.add(new Card(Constants.SORCERY, 3));
        injuryDeck3.add(new Card(Constants.MERLIN, 10));
        players[2].addToInjuryDeck(injuryDeck3);
        players[2].calculateHealthPoints();
        List<Player> winner1 = score.getWinners(players);
        List<Player> expectedWinner = new ArrayList<>();
        assertEquals(expectedWinner, winner1);
    }

    // Test for the leader of a melee start with an Alchemy card and other players can play any card
    @Test
    @DisplayName("U-TEST 047: Test for the leader of a melee start with an Alchemy card and other players can play any card")
    public void testPlayAnyCard() {
        Player player = new Player("1");
        Card playedCard1 = new Card(Constants.ALCHEMY, 1);
        String suit = Constants.ANY;
        Card playedCard2 = new Card(Constants.ALCHEMY, 2);
        boolean isCardValid1 = !player.isCardInvalid(playedCard2, suit);
        assertTrue(isCardValid1);
        Card playedCard3 = new Card(Constants.ARROWS, 1);
        boolean isCardValid2 = !player.isCardInvalid(playedCard3, suit);
        assertTrue(isCardValid2);
        Card playedCard4 = new Card(Constants.MERLIN, 5);
        boolean isCardValid3 = !player.isCardInvalid(playedCard4, suit);
        assertTrue(isCardValid3);
    }

    // Test for suffer immediately 5 injury points for the shame
    @Test
    @DisplayName("U-TEST 048: Test for suffer immediately 5 injury points for the shame")
    public void testSuffer5InjuryPoints() {
        Player player = new Player("1");
        player.setHealthPoints(50);
        int healthPoints = player.getHealthPoints();
        healthPoints -= 5;
        player.setHealthPoints(healthPoints);
        assertEquals(45, player.getHealthPoints());
    }

    // Test for player immediately reach 0 health points due to the shame
    @Test
    @DisplayName("U-TEST 049: Test for player immediately reach 0 health points due to the shame")
    public void testReach0HealthPoints() {
        Player player = new Player("1");
        player.setHealthPoints(10);
        int healthPoints = player.getHealthPoints();
        healthPoints -= 5;
        player.setHealthPoints(healthPoints);
        healthPoints = player.getHealthPoints();
        healthPoints -= 5;
        player.setHealthPoints(healthPoints);
        assertEquals(0, player.getHealthPoints());
    }

    // Test for the game is over when one or more players reach 0 health points
    @Test
    @DisplayName("U-TEST 050: Test for the game is over when one or more players reach 0 health points")
    public void testGameOverDueToShame() {
        boolean isGameOver = false;
        Player player = new Player("1");
        player.setHealthPoints(8);
        int healthPoints = player.getHealthPoints();
        healthPoints -= 5;
        player.setHealthPoints(healthPoints);
        healthPoints = player.getHealthPoints();
        healthPoints -= 5;
        player.setHealthPoints(healthPoints);
        if (player.getHealthPoints() <= 0) {
            isGameOver = true;
        }
        assertTrue(isGameOver);
    }

    // Test for the game is over at the end of a round
    @Test
    @DisplayName("U-TEST 051: Test for the game is over at the end of a round")
    public void testGameOverAtEndOfRound() {
        boolean isGameOver = false;
        Player[] players = new Player[3];
        players[0] = new Player("1");
        players[1] = new Player("2");
        players[2] = new Player("3");
        players[0].setHealthPoints(10);
        players[1].setHealthPoints(-5);
        players[2].setHealthPoints(15);
        for (int i = 0; i < players.length; i++) {
            if (players[i].getHealthPoints() <= 0) {
                isGameOver = true;
            }
        }
        assertTrue(isGameOver);
    }

    // Test for play several rounds until game is over
    @Test
    @DisplayName("U-TEST 052: Test for play several rounds until game is over")
    public void testSeveralRounds() {
        boolean isGameRunning = true;
        boolean isRoundRunning = true;
        int numOfMeleePlayed = 0;
        int roundNum = 0;
        while (isGameRunning) {
            isRoundRunning = true;
            roundNum++;
            while (isRoundRunning) {
                numOfMeleePlayed++;
                if (numOfMeleePlayed == 2) {
                    isRoundRunning = false;
                    if (roundNum == 2) {
                        isGameRunning = false;
                    }
                }
            }
        }
        assertFalse(isGameRunning);
        assertFalse(isRoundRunning);
    }

    // Test for injury deck is reset for each round
    @Test
    @DisplayName("U-TEST 053: Test for injury deck is reset for each round")
    public void testResetInjuryDeck() {
        Player loser = new Player("1");
        List<Card> playedCards1 = new ArrayList<>();
        playedCards1.add(new Card(Constants.SWORDS, 2));
        playedCards1.add(new Card(Constants.ARROWS, 2));
        playedCards1.add(new Card(Constants.ALCHEMY, 5));
        playedCards1.add(new Card(Constants.MERLIN, 5));
        loser.addToInjuryDeck(playedCards1);
        loser.resetInjuryDeck();
        List<Card> expectedDeck = new ArrayList<>();
        assertEquals(expectedDeck, loser.getInjuryDeck());
    }
}
*/