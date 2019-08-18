package poker;

public class Poker implements Comparable<Poker>{

    private String cardName;
    private int number;
    private String pokerColor;

    public Poker(String cardName) {
        this.cardName = cardName;
        this.number = this.stringToNumber(cardName);
        this.pokerColor = cardName.substring(1, 2);
    }

    public String getCardName() {
        return cardName;
    }

    public int getNumber() {
        return number;
    }

    public String getPokerColor() {
        return pokerColor;
    }

    public int stringToNumber(String cardName) {
        if (cardName.charAt(0) >= '0' && cardName.charAt(0) <= '9') {
            return Integer.parseInt(cardName.substring(0, 1));
        } else if (cardName.charAt(0) == 'T')  {
            return 10;
        } else if (cardName.charAt(0) == 'J') {
            return 11;
        } else if (cardName.charAt(0) == 'Q') {
            return 12;
        } else if (cardName.charAt(0) == 'K') {
            return 13;
        } else if (cardName.charAt(0) == 'A') {
            return 14;
        }
        return 0;
    }

    public int compareNumber(int number2) {
        if (this.number > number2) {
            return 1;
        } else if (this.number == number2) {
            return 0;
        } else {
            return -1;
        }
    }


    @Override
    public int compareTo(Poker o) {
        if (this.number >= o.getNumber())
            return 1;
        else
            return -1;
    }
}
