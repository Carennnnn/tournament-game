package project;

public class Card {
    private String weaponType;
    private int value;
    private int injuryPoints;
    private String suit;
    private Player cardOwner;

    // empty constructor of Card
    public Card() {

    }

    // constructor of Card
    public Card(String type, int number) {
        this.weaponType = type;
        this.value = number;
        this.injuryPoints = getInjuryPoints(type, number);
        this.suit = getSuit(type);
    }

    // check if current card is poisoned
    public boolean isPoisoned(String type, int number) {
        if (type.equals(Constants.SWORDS)) {
            if (number == 6 || number == 7 || number == 8 || number == 9) {
                return true;
            }
        } else if (type.equals(Constants.ARROWS)) {
            if (number == 8 || number == 9 || number == 10 || number == 11) {
                return true;
            }
        } else if (type.equals(Constants.SORCERY)) {
            if (number == 5 || number == 6 || number == 11 || number == 12) {
                return true;
            }
        } else if (type.equals(Constants.DECEPTION)) {
            if (number == 6 || number == 7 || number == 9 || number == 10) {
                return true;
            }
        }
        return false;
    }

    // get injury points of the card
    public int getInjuryPoints(String type, int number) {
        boolean isPoisoned = isPoisoned(type, number);
        if (isPoisoned) {
            return 10;
        } else if (type.equals(Constants.MERLIN)) {
            return 25;
        }
        return 5;
    }

    // get suit of the card
    public String getSuit(String type) {
        if (type.equals(Constants.SWORDS)) {
            return Constants.SW;
        } else if (type.equals(Constants.ARROWS)) {
            return Constants.AR;
        } else if (type.equals(Constants.SORCERY)) {
            return Constants.SO;
        } else if (type.equals(Constants.DECEPTION)) {
            return Constants.DE;
        }
        return "";
    }

    // get weapon type of card
    public String getWeaponType() {
        return this.weaponType;
    }

    // get value of a card
    public int getValue() {
        return this.value;
    }

    // get injury points of current card
    public int getInjuryPoints() {
        return this.injuryPoints;
    }

    // get suit of current card
    public String getSuit() {
        return this.suit;
    }

    // get card information
    public String getCardInfo() {
        String valueStr = String.valueOf(value);
        if (value == 0) {
            valueStr = "";
        }
        return weaponType + " - value: " + valueStr + ", suit: " + suit;
    }

    // set suit for the card
    public void setSuit(String suit) {
        this.suit = suit;
    }

    // set value for the card
    public void setValue(int value) {
        this.value = value;
    }

    // set played card's owner
    public void setCardOwner(Player owner) {
        this.cardOwner = owner;
    }

    // get played card's owner
    public Player getCardOwner() {
        return this.cardOwner;
    }


}
