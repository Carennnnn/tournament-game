/*
package project;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AcceptanceTest {

    // Test for all invalid scenarios
    @Test
    @DisplayName("A-TEST 001: Test for all invalid scenarios")
    public void testInvalidScenarios() {
        GameServer gameServer = new GameServer();
        int numPlayers1 = -1;
        int numPlayers2 = 2;
        int numPlayers3 = 6;
        assertFalse(gameServer.isNumOfPlayersInRange(numPlayers1));
        assertFalse(gameServer.isNumOfPlayersInRange(numPlayers2));
        assertFalse(gameServer.isNumOfPlayersInRange(numPlayers3));

        String name1 = "";
        assertFalse(gameServer.isPlayerNameValid(name1));

        int healthPoints1 = -1;
        int healthPoints2 = -50;
        int healthPoints3 = -400;
        assertFalse(gameServer.isHealthPointsValid(healthPoints1));
        assertFalse(gameServer.isHealthPointsValid(healthPoints2));
        assertFalse(gameServer.isHealthPointsValid(healthPoints3));

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

        Player player = new Player("1");
        int suit1 = -1;
        int suit2 = 0;
        int suit3 = 5;
        assertFalse(player.isSuitNumValid(suit1));
        assertFalse(player.isSuitNumValid(suit2));
        assertFalse(player.isSuitNumValid(suit3));

        int value1 = -1;
        int value2 = 0;
        int value3 = 16;
        assertFalse(player.isValueValid(value1));
        assertFalse(player.isValueValid(value2));
        assertFalse(player.isValueValid(value3));
    }

    // Test for all valid scenarios
    @Test
    @DisplayName("A-TEST 002: Test for all valid scenarios")
    public void testValidScenarios() {
        GameServer gameServer = new GameServer();
        int numPlayers1 = 3;
        int numPlayers2 = 4;
        int numPlayers3 = 5;
        assertTrue(gameServer.isNumOfPlayersInRange(numPlayers1));
        assertTrue(gameServer.isNumOfPlayersInRange(numPlayers2));
        assertTrue(gameServer.isNumOfPlayersInRange(numPlayers3));

        String name1 = "a";
        String name2 = "1";
        String name3 = "&Tom";
        String name4 = ";";
        assertTrue(gameServer.isPlayerNameValid(name1));
        assertTrue(gameServer.isPlayerNameValid(name2));
        assertTrue(gameServer.isPlayerNameValid(name3));
        assertTrue(gameServer.isPlayerNameValid(name4));

        int healthPoints1 = 0;
        int healthPoints2 = 1;
        int healthPoints3 = 50;
        int healthPoints4 = 400;
        assertTrue(gameServer.isHealthPointsValid(healthPoints1));
        assertTrue(gameServer.isHealthPointsValid(healthPoints2));
        assertTrue(gameServer.isHealthPointsValid(healthPoints3));
        assertTrue(gameServer.isHealthPointsValid(healthPoints4));

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

        Player player = new Player("1");
        int suit1 = 1;
        int suit2 = 2;
        int suit3 = 4;
        assertTrue(player.isSuitNumValid(suit1));
        assertTrue(player.isSuitNumValid(suit2));
        assertTrue(player.isSuitNumValid(suit3));

        int value1 = 1;
        int value2 = 7;
        int value3 = 15;
        assertTrue(player.isValueValid(value1));
        assertTrue(player.isValueValid(value2));
        assertTrue(player.isValueValid(value3));
    }

    // Test for all calculations
    @Test
    @DisplayName("A-TEST 003: Test for all calculations")
    public void testAllCalculations() {
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

        Player loser = new Player("1");
        List<Card> playedCards1 = new ArrayList<>();
        playedCards1.add(new Card(Constants.SWORDS, 9));
        playedCards1.add(new Card(Constants.SWORDS, 3));
        playedCards1.add(new Card(Constants.ALCHEMY, 3));
        playedCards1.add(new Card(Constants.MERLIN, 3));
        int injuryPoints1 = loser.getInjuryPointsInMelee(playedCards1);
        assertEquals(45, injuryPoints1);

        Player player = new Player("1");
        List<Card> injuryDeck1 = new ArrayList<>();
        injuryDeck1.add(new Card(Constants.SWORDS, 9));
        injuryDeck1.add(new Card(Constants.SWORDS, 3));
        injuryDeck1.add(new Card(Constants.ALCHEMY, 3));
        injuryDeck1.add(new Card(Constants.MERLIN, 3));
        player.addToInjuryDeck(injuryDeck1);
        int totalInjuryPoints = player.calculateTotalInjuryPoints();
        assertEquals(45, totalInjuryPoints);

        player.setHealthPoints(50);
        int healthPoints = player.getHealthPoints();
        healthPoints -= 5;
        player.setHealthPoints(healthPoints);
        assertEquals(45, player.getHealthPoints());
    }

    // Test for all initialization
    @Test
    @DisplayName("A-TEST 004: Test for all initialization")
    public void testInitialization() {
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

        List<Card> initialCards = gameServer.initializeCards();

        // make sure the order of cards in shuffled deck is different
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

    // Test for play a melee
    @Test
    @DisplayName("A-TEST 005: Test for play a melee")
    public void testPlayMelee() {
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

        Player player1 = new Player("1");

        cards1.clear();
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


        cards1.clear();
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
        playedCard1 = player1.getCardsOnHand().get(0);
        assertTrue(player1.isCardInvalid(playedCard1, "Ar"));
        playedCard2 = player1.getCardsOnHand().get(5);
        assertTrue(player1.isCardInvalid(playedCard2, "So"));

        Score score = new Score();
        players = new Player[4];
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

        players[0] = new Player("1");
        players[1] = new Player("2");
        players[2] = new Player("3");
        players[3] = new Player("4");
        playedCards1.clear();
        playedCards1.add(new Card(Constants.SWORDS, 2));
        playedCards1.add(new Card(Constants.ARROWS, 2));
        playedCards1.add(new Card(Constants.ALCHEMY, 5));
        playedCards1.add(new Card(Constants.MERLIN, 5));
        playedCards1.get(0).setCardOwner(players[0]);
        playedCards1.get(1).setCardOwner(players[1]);
        playedCards1.get(2).setCardOwner(players[2]);
        playedCards1.get(3).setCardOwner(players[3]);
        remainingCards1 = score.ignoreCards(playedCards1);
        loser1 = score.getLoser(remainingCards1);
        assertEquals(null, loser1);

        players[0] = new Player("1");
        players[1] = new Player("2");
        players[2] = new Player("3");
        Player loser = players[1];
        int currentPlayerId = score.getLoserPlayerId(players, loser);
        Player leaderOfNextMelee = players[currentPlayerId];
        assertEquals(players[1], leaderOfNextMelee);

        players[0] = new Player("1");
        players[1] = new Player("2");
        players[2] = new Player("3");
        currentPlayerId = 1;
        currentPlayerId++;
        leaderOfNextMelee = players[currentPlayerId];
        assertEquals(players[2], leaderOfNextMelee);
    }

    // Test for game rules
    @Test
    @DisplayName("A-TEST 006: Test for game rules")
    public void testGameRule() {
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

        Player player2 = new Player("2");

        cards1.clear();
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

        cards1.clear();
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

        Player player = new Player("1");
        playedCard1 = new Card(Constants.ALCHEMY, 1);
        String suit = Constants.ANY;
        playedCard2 = new Card(Constants.ALCHEMY, 2);
        boolean isCardValid1 = !player.isCardInvalid(playedCard2, suit);
        assertTrue(isCardValid1);
        Card playedCard3 = new Card(Constants.ARROWS, 1);
        boolean isCardValid2 = !player.isCardInvalid(playedCard3, suit);
        assertTrue(isCardValid2);
        Card playedCard4 = new Card(Constants.MERLIN, 5);
        boolean isCardValid3 = !player.isCardInvalid(playedCard4, suit);
        assertTrue(isCardValid3);

        player = new Player("1");
        player.setHealthPoints(50);
        int healthPoints = player.getHealthPoints();
        healthPoints -= 5;
        player.setHealthPoints(healthPoints);
        assertEquals(45, player.getHealthPoints());
    }

    // Test play a round
    @Test
    @DisplayName("A-TEST 007: Test play a round")
    public void testPlayARound() {
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

        isGameOver = false;
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