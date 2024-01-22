package project;

import java.util.ArrayList;
import java.util.List;

public class Score {
    // empty constructor of Card
    public Score() {}

    // ignore played cards in a melee that have the same value
    public List<Card> ignoreCards(List<Card> cards) {
        List<Card> remainingCards = new ArrayList<>();
        List<Integer> duplicatedValues = new ArrayList<>();
        // remove duplicated values of the cards
        for (int i = 0; i < cards.size(); i++) {
            int cardValue = cards.get(i).getValue();
            boolean isValueUnique = true;
            for (int j = i + 1; j < cards.size(); j++) {
                if (cardValue == cards.get(j).getValue()) {
                    isValueUnique = false;
                }
            }
            if (isValueUnique) {
                remainingCards.add(cards.get(i));
            } else {
                duplicatedValues.add(cards.get(i).getValue());
            }
        }
        // loop again to check if all cards in remaining cards have unique values
        for (int i = 0; i < remainingCards.size(); i++) {
            if (duplicatedValues.contains(remainingCards.get(i).getValue())) {
                remainingCards.remove(i);
                i--;
            }
        }
        return remainingCards;
    }

    // get loser of a melee
    public Player getLoser(List<Card> cards) {
        // if there is no remaining card, there is no loser in this melee
        if (cards.isEmpty()) {
            return null;
        } else {
            int lowestValue = cards.get(0).getValue();
            Card lowestValuedCard = cards.get(0);
            // find the lowest value in the cards
            for (int i = 0; i < cards.size(); i++) {
                int currentValue = cards.get(i).getValue();
                if (currentValue < lowestValue) {
                    lowestValue = currentValue;
                    lowestValuedCard = cards.get(i);
                }
            }
            return lowestValuedCard.getCardOwner();
        }
    }

    // get loser's player id
    public int getLoserPlayerId(Player[] players, Player loser) {
        for (int i = 0; i < players.length; i++) {
            if (players[i] == loser) {
                return i;
            }
        }
        return -1;
    }

    // get winners for a round
    public List<Player> getWinners(Player[] players) {
        int highestHealthPoints = players[0].getHealthPoints();
        List<Player> winners = new ArrayList<>();
        for (int i = 0; i < players.length; i++) {
            int currentHealthPoints = players[i].getHealthPoints();
            if (currentHealthPoints > highestHealthPoints) {
                highestHealthPoints = currentHealthPoints;
            }
        }
        // if the highest health points <= 0, there's no winner in this round
        if (highestHealthPoints > 0) {
            // return all winners that have the same highest health points
            for (int i = 0; i < players.length; i++) {
                if (players[i].getHealthPoints() == highestHealthPoints) {
                    winners.add(players[i]);
                }
            }
        }
        return winners;
    }
}
