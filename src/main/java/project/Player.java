package project;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Player {
    private String name;
    private Integer healthPoints;
    private Card playedCard = null;
    private List<Card> cardsOnHand = new ArrayList<>();
    private List<Card> injuryDeck = new ArrayList<>();
    public Player(String n) {
        this.name = n;
    }

    // player plays melee
    public String playMelee(String suit) {
        Scanner input = new Scanner(System.in);
        Card playedCard = null;
        int cardNum = 0;
        boolean isInputInvalid = true;
        boolean isCardInvalid = true;
        System.out.println("Player " + name + " has started his/her round");
        System.out.println("Player " + name + "'s cards: ");
        System.out.print(printCards(cardsOnHand));
        System.out.println("Current suit: " + suit);
        // check if card must be discarded
        boolean discardCard = mustDiscardCard(suit);
        if (discardCard) {
            boolean isDiscardNumValid = false;
            while (!isDiscardNumValid) {
                System.out.println("Please select a card to discard: ");
                cardNum = input.nextInt();
                isDiscardNumValid = isCardNumValid(cardNum);
            }
            int cardInd = cardNum - 1;
            Card discardedCard = cardsOnHand.get(cardInd);
            removeCard(discardedCard);
            System.out.println("Player " + name + " discarded card " + cardNum + ": " + discardedCard.getCardInfo());
            // suffer immediately 5 injury points for the shame of not being able to participate in the melee
            healthPoints -= 5;
            System.out.println("Player " + name + " suffered 5 injury points for the shame of not being able to participate in the melee");
        } else {
            // player choose a card to play
            // player enters valid card number
            while (isInputInvalid) {
                System.out.println("Please select a card to play: ");
                cardNum = input.nextInt();
                isInputInvalid = !isCardNumValid(cardNum);
            }
            // get played card
            int cardInd = cardNum - 1;
            playedCard = cardsOnHand.get(cardInd);
            isCardInvalid = isCardInvalid(playedCard, suit);
            while (isCardInvalid) {
                System.out.println("Please select a card to play: ");
                cardNum = input.nextInt();
                cardInd = cardNum - 1;
                playedCard = cardsOnHand.get(cardInd);
                isCardInvalid = isCardInvalid(playedCard, suit);
            }
            removeCard(playedCard);
            // set suit of Alchemy
            if (playedCard.getWeaponType().equals(Constants.ALCHEMY)) {
                // if Alchemy card is the first card played in this melee, set suit to any
                if (suit.isEmpty()) {
                    suit = Constants.ANY;
                } else if (!suit.equals(Constants.ANY)){
                    // if Alchemy card is not the first card, set suit of Alchemy card to the suit of this melee
                    playedCard.setSuit(suit);
                }
            }
            // set value and suit for Merlin or Apprentice card
            if (playedCard.getWeaponType().equals(Constants.MERLIN) ||
                    playedCard.getWeaponType().equals(Constants.APPRENTICE)
            ) {
                int specifiedValue = -1;
                boolean isValueValid = false;
                while (!isValueValid) {
                    System.out.println("Please specify a value of this card(1-15): ");
                    specifiedValue = input.nextInt();
                    isValueValid = isValueValid(specifiedValue);
                }
                playedCard.setValue(specifiedValue);

                // if Merlin or Apprentice is the first card in this melee, set the suit
                if (suit.isEmpty()) {
                    int suitNum = -1;
                    boolean isSuitNumValid = false;
                    while (!isSuitNumValid) {
                        System.out.println("Please specify a suit of this card: ");
                        System.out.println("1. Sw");
                        System.out.println("2. Ar");
                        System.out.println("3. So");
                        System.out.println("4. De");
                        suitNum = input.nextInt();
                        isSuitNumValid = isSuitNumValid(suitNum);
                    }
                    suit = getSuitName(suitNum);
                    playedCard.setSuit(suit);
                } else if (!suit.equals(Constants.ANY)){
                    // if suit is not any, and Merlin or Apprentice is not the first card, the suit is set to the suit of the current melee
                    playedCard.setSuit(suit);
                }
            } else {
                // if card is not Merlin or Apprentice, set the suit to the suit of current card
                if (suit.isEmpty()) {
                    suit = playedCard.getSuit();
                }
            }
            setPlayedCard(playedCard);
            System.out.println("Player " + name + " played card " + cardNum + ": " + playedCard.getCardInfo());
            System.out.println();
        }
        System.out.println("The resulting hand of player " + name + ": ");
        System.out.println(printCards(cardsOnHand));
        return suit;
    }

    // check if played card is valid
    public boolean isCardInvalid(Card currentCard, String suit) {
        // if the card is not the first card or suit is not any, check if played card is valid
        if (!suit.isEmpty() && !suit.equals(Constants.ANY)) {
            // if current card type is not Merlin or Apprentice, then check if the card suit is the suit of this melee
            if (!currentCard.getWeaponType().equals(Constants.MERLIN) &&
                    !currentCard.getWeaponType().equals(Constants.APPRENTICE) &&
                    !currentCard.getWeaponType().equals(Constants.ALCHEMY)
            ) {
                if (!currentCard.getSuit().equals(suit)) {
                    System.out.println("You must play a card of the suit of this melee - " + suit);
                    return true;
                }
            }
        }
        if (currentCard.getWeaponType().equals(Constants.ALCHEMY)) {
            return !canPlayAlchemy(suit);
        }
        return false;
    }

    // check if Alchemy card can be played
    public boolean canPlayAlchemy(String suit) {
        boolean firstCardIsAlchemy = true;
        boolean canPlayAlchemy = true;
        // if it is the first card of a melee, Alchemy card can only be played
        // if player has no Sw, Ar, So, De, Merlin or Apprentice card left in his hand
        if (suit.isEmpty()) {
            for (int i = 0; i < cardsOnHand.size(); i++) {
                if (cardsOnHand.get(i).getWeaponType().equals(Constants.SWORDS) ||
                        cardsOnHand.get(i).getWeaponType().equals(Constants.ARROWS) ||
                        cardsOnHand.get(i).getWeaponType().equals(Constants.SORCERY) ||
                        cardsOnHand.get(i).getWeaponType().equals(Constants.DECEPTION) ||
                        cardsOnHand.get(i).getWeaponType().equals(Constants.MERLIN) ||
                        cardsOnHand.get(i).getWeaponType().equals(Constants.APPRENTICE)
                ) {
                    firstCardIsAlchemy = false;
                }
            }
            if (!firstCardIsAlchemy) {
                System.out.println("First card cannot be Alchemy because you have");
                System.out.println("Sw, Ar, So, De, Merlin or Apprentice card in your hand");
            }
            return firstCardIsAlchemy;
        } else if (!suit.equals(Constants.ANY)){
            // if suit is not any, and there is no cards in the suit of this melee, or there is no Merlin or Apprentice card,
            // Alchemy card can be played
            for (int i = 0; i < cardsOnHand.size(); i++) {
                if (cardsOnHand.get(i).getSuit().equals(suit) ||
                        cardsOnHand.get(i).getWeaponType().equals(Constants.MERLIN) ||
                        cardsOnHand.get(i).getWeaponType().equals(Constants.APPRENTICE)
                ) {
                    canPlayAlchemy = false;
                }
            }
            if (!canPlayAlchemy) {
                System.out.println("You can't play Alchemy card because you have a weapon card ");
                System.out.println("of this suit or you have merlin or apprentice card");
            }
        }
        return canPlayAlchemy;
    }

    // check if input card number is valid
    public boolean isCardNumValid(int cardNum) {
        if (cardNum >= 1 && cardNum <= cardsOnHand.size()) {
            return true;
        }
        System.out.println("Card number is out-of-range");
        System.out.println("Please enter number between 1 and " + cardsOnHand.size());
        return false;
    }

    // check if suit number is valid
    public boolean isSuitNumValid(int suitNum) {
        if (suitNum >= 1 && suitNum <= 4) {
            return true;
        }
        System.out.println("Suit number is out-of-range");
        System.out.println("Please enter number between 1 and 4");
        return false;
    }

    // check if value is valid
    public boolean isValueValid(int value) {
        if (value >= 1 && value <= 15) {
            return true;
        }
        System.out.println("Value is out-of-range");
        System.out.println("Please enter value between 1 and 15");
        return false;
    }

    // player receives 12 cards from the deck
    public void receiveCards(List<Card> cards) {
        this.cardsOnHand = cards;
    }

    // print current player's cards
    public String printCards(List<Card> cards) {
        String cardsStr = "";
        for (int i = 0; i < cards.size(); i++) {
            cardsStr += (i + 1) + ". " + cards.get(i).getCardInfo() + "\n";
        }
        return cardsStr;
    }

    // set health points
    public void setHealthPoints(Integer p) {
        this.healthPoints = p;
    }

    // get cards on hand
    public List<Card> getCardsOnHand() {
        return cardsOnHand;
    }

    // get current player's name
    public String getName() {
        return this.name;
    }

    // get suit name by player's input suit number
    public String getSuitName(int suitNum) {
        if (suitNum == 1) {
            return "Sw";
        } else if (suitNum == 2) {
            return "Ar";
        } else if (suitNum == 3) {
            return "So";
        } else if (suitNum == 4) {
            return "De";
        }
        return "";
    }

    // remove played card from player's hand
    public void removeCard(Card playedCard) {
        List<Card> tempDeck = new ArrayList<>();
        tempDeck.addAll(cardsOnHand);
        tempDeck.remove(playedCard);
        cardsOnHand = tempDeck;
    }

    // If suit is not any, and there is no melee’s suit or a Merlin card or an apprentice card
    // or an Alchemy card, player must discard a card
    public boolean mustDiscardCard(String suit) {
        for (int i = 0; i < cardsOnHand.size(); i++) {
            if (suit.isEmpty() ||
                    cardsOnHand.get(i).getSuit().equals(suit) ||
                    cardsOnHand.get(i).getWeaponType().equals(Constants.MERLIN) ||
                    cardsOnHand.get(i).getWeaponType().equals(Constants.APPRENTICE) ||
                    cardsOnHand.get(i).getWeaponType().equals(Constants.ALCHEMY) ||
                    suit.equals(Constants.ANY)
            ) {
                return false;
            }
        }
        return true;
    }

    // set player's played card in a melee
    public void setPlayedCard(Card playedCard) {
        this.playedCard = playedCard;
    }

    // get player's played card in a melee
    public Card getPlayedCard() {
        return playedCard;
    }

    // add all weapon cards played in this melee to the loser's injury deck
    public void addToInjuryDeck(List<Card> injuryDeck) {
        this.injuryDeck.addAll(injuryDeck);
    }

    // get injury deck for player
    public List<Card> getInjuryDeck() {
        return this.injuryDeck;
    }

    // get injury points accumulated in a melee of loser
    public int getInjuryPointsInMelee(List<Card> injuryDeck) {
        int injuryPoints = 0;
        for (int i = 0; i < injuryDeck.size(); i++) {
            injuryPoints += injuryDeck.get(i).getInjuryPoints();
        }
        return injuryPoints;
    }

    // calculate total injury points suffered by a player in a round
    public int calculateTotalInjuryPoints() {
        int totalInjuryPoints = 0;
        for (int i = 0; i < injuryDeck.size(); i++) {
            totalInjuryPoints += injuryDeck.get(i).getInjuryPoints();
        }
        System.out.println("Player " + name + " suffered " + totalInjuryPoints + " injury points in this round");
        return totalInjuryPoints;
    }

    // calculate player's health points at the end of the round
    public int calculateHealthPoints() {
        int totalInjuryPoints = calculateTotalInjuryPoints();
        healthPoints = healthPoints - totalInjuryPoints;
        System.out.println("Player " + name + "'s remaining health points: " + healthPoints);
        return healthPoints;
    }

    // get player's health points
    public int getHealthPoints() {
        return this.healthPoints;
    }

    // reset injury deck to empty for a new round
    public void resetInjuryDeck() {
        injuryDeck.clear();
    }

    // set rigged card for testing for part 1
    public void setCard(Card newCard) {
        List<Card> cards = new ArrayList<>();
        cards.add(newCard);
        cardsOnHand = cards;
    }

    // set rigged cards for testing for part 2
    public void setCards(List<Card> newCards) {
        cardsOnHand = newCards;
    }
}
