package poker;

import java.util.Collections;

public class PokerGame {

    public static final String PLAYER_2_WIN = "player2 win";
    public static final String PLAYER_1_WIN = "player1 win";
    public static final String DRAW = "draw";
    public static final int HIGH_CARD = 0;
    public static final int A_PAIR = 1;
    public static final int TWO_PAIR = 2;
    public static final int THREE_OF_A_KIND = 3;
    public static final int STRAIGHT = 4;
    public static final int FLUSH = 5;
    public static final int FULL_HOUSE = 6;
    public static final int FOUR_OF_A_KIND = 7;
    public static final int STRAIGHT_FLUSH = 8;

    private String[] pokers;
    private PlayerPokers player1Pokers = new PlayerPokers();
    private PlayerPokers player2Pokers = new PlayerPokers();

    public PokerGame(String[] pokers) {
        this.pokers = pokers;
        this.addPlayerPoker(pokers);
    }

    public String compare() {
        String result = "";
        if (player1Pokers.getStatus() > player2Pokers.getStatus()) {
            result = PLAYER_1_WIN;
        } else if (player1Pokers.getStatus() < player2Pokers.getStatus()) {
            result = PLAYER_2_WIN;
        } else {
            switch (player1Pokers.getStatus()) {
                case 0:
                    result = compareHighCard();
                    break;
                case 1:
                case 2:
                case 5:
                    result = comparePair();
                    break;
                case 3:
                    result = compareThreeOfAKind();
                    break;
                case 4:
                    result = compareStraight();
                    break;
                case 6:
                    result = compareFullHouse();
                    break;
                case 7:
                    result = compareFourOfAKind();
                    break;
                case 8:
                    result = compareStraightFlush();
                    break;
            }
        }
        return result;
    }

    public String compareHighCard() {
        for (int i = player1Pokers.getPokers().size() - 1; i >= 0; i--) {
            if (compareNumber(i, i) == 1) {
                return PLAYER_1_WIN;
            } else if (compareNumber(i, i) == -1) {
                return PLAYER_2_WIN;
            }
        }
        return DRAW;
    }

    public String comparePair() {
        for (int i = 0; i < player1Pokers.getPokers().size() - 1; i++) {
            if (player1Pokers.getPokers().get(i).getNumber() == player1Pokers.getPokers().get(i+1).getNumber()) {
                for (int j = 0; j < player2Pokers.getPokers().size() - 1; j++) {
                    if (player2Pokers.getPokers().get(j).getNumber() == player2Pokers.getPokers().get(j+1).getNumber()) {
                        if (compareNumber(i, j) == 1) {
                            return PLAYER_1_WIN;
                        } else if (compareNumber(i, j) == -1) {
                            return PLAYER_2_WIN;
                        }
                    }
                }
            }
        }
        return compareHighCard();
    }

    public String compareThreeOfAKind() {
        for (int i = 0; i < 3; i++) {
            boolean isOneAndTwoSame = player1Pokers.getPokers().get(i).getNumber() == player1Pokers.getPokers().get(i+1).getNumber();
            boolean isOneAndThreeSame = player1Pokers.getPokers().get(i).getNumber() == player1Pokers.getPokers().get(i+2).getNumber();
            if (isOneAndTwoSame && isOneAndThreeSame) {
                for (int j = 0; j < 3; j++) {
                    boolean isOneAndTwoSame2 = player2Pokers.getPokers().get(j).getNumber() == player2Pokers.getPokers().get(j+1).getNumber();
                    boolean isOneAndThreeSame2 = player2Pokers.getPokers().get(j).getNumber() == player2Pokers.getPokers().get(j+2).getNumber();
                    if (isOneAndTwoSame2 && isOneAndThreeSame2) {
                        if (compareNumber(i, j) == 1) {
                            return PLAYER_1_WIN;
                        } else if (compareNumber(i, j) == -1) {
                            return PLAYER_2_WIN;
                        }
                    }
                }
            }
        }
        return compareHighCard();
    }

    public String compareStraight() {
        if (player1Pokers.getPokers().get(0).getNumber() > player2Pokers.getPokers().get(0).getNumber()) {
            return PLAYER_1_WIN;
        } else if (player1Pokers.getPokers().get(0).getNumber() < player2Pokers.getPokers().get(0).getNumber()) {
            return PLAYER_2_WIN;
        }
        return DRAW;
    }

    public String compareFullHouse() {
        for (int i = 0; i < 3; i++) {
            boolean isTwoSameOne = doesNumberEqual(player1Pokers, i, i+1);
            boolean isThreeSameOne = doesNumberEqual(player1Pokers, i, i+2);
            if (isTwoSameOne && isThreeSameOne) {
                for (int j = 0; j < 3; j++) {
                    boolean isTwoSameOne2 = doesNumberEqual(player2Pokers, j, j+1);
                    boolean isThreeSameOne2 = doesNumberEqual(player2Pokers, j, j+2);
                    if (isTwoSameOne2 && isThreeSameOne2) {
                        if (compareNumber(i, j) == 1) {
                            return PLAYER_1_WIN;
                        } else if (compareNumber(i, j) == -1) {
                            return PLAYER_2_WIN;
                        } else {
                            return compareFullHousePair(i, j);
                        }
                    }
                }
            }
        }
        return compareHighCard();
    }

    public String compareFullHousePair(int i, int j) {
        int result = 0;
        if (i == 0 && j == 0) {
            result = compareNumber(i + 3, j + 3);
        } else if (i == 0 && j == 2) {
            result = compareNumber(i + 3, j - 2);
        } else if (i == 2 && j == 0) {
            result = compareNumber(i - 2, j + 3);
        } else if (i == 2 && j == 2) {
            result = compareNumber(i - 2, j - 2);
        }

        if (result == 1) {
            return PLAYER_1_WIN;
        } else if (result == -1) {
            return PLAYER_2_WIN;
        } else {
            return DRAW;
        }
    }

    public String compareFourOfAKind() {
        for (int i = 0; i < 2; i++) {
            boolean isTwoSameOne = doesNumberEqual(player1Pokers, i, i+1);
            boolean isThreeSameOne = doesNumberEqual(player1Pokers, i, i+2);
            boolean isFourSameOne = doesNumberEqual(player1Pokers, i, i+3);
            if (isTwoSameOne && isThreeSameOne && isFourSameOne) {
                for (int j = 0; j < 2; j++) {
                    boolean isTwoSameOne2 = doesNumberEqual(player2Pokers, j, j+1);
                    boolean isThreeSameOne2 = doesNumberEqual(player2Pokers, j, j+2);
                    boolean isFourSameOne2 = doesNumberEqual(player2Pokers, j, j+3);
                    if (isTwoSameOne2 && isThreeSameOne2 && isFourSameOne2) {
                        if (compareNumber(i, j) == 1) {
                            return PLAYER_1_WIN;
                        } else if (compareNumber(i, j) == -1) {
                            return PLAYER_2_WIN;
                        }
                    }
                }
            }
        }
        return compareHighCard();
    }

    public String compareStraightFlush() {
        if (compareNumber(4, 4) == 1) {
            return PLAYER_1_WIN;
        } else if (compareNumber(4, 4) == -1) {
            return PLAYER_2_WIN;
        } else {
            return DRAW;
        }
    }

    public void isPair(PlayerPokers playerPokers) {
        int count = 0;
        for (int i = 0; i < playerPokers.getPokers().size() - 1; i++) {
            if (playerPokers.getPokers().get(i).getNumber() == playerPokers.getPokers().get(i+1).getNumber()) {
                count++;
            }
        }
        if (count == 1) {
            playerPokers.setStatus(A_PAIR);
        } else if (count == 2){
            playerPokers.setStatus(TWO_PAIR);
        }
    }

    public void isThreeOfAKind(PlayerPokers playerPokers) {
        for (int i = 0; i < 3; i++) {
            boolean isOneAndTwoSame = playerPokers.getPokers().get(i).getNumber() == playerPokers.getPokers().get(i+1).getNumber();
            boolean isOneAndThreeSame = playerPokers.getPokers().get(i).getNumber() == playerPokers.getPokers().get(i+2).getNumber();
            if (isOneAndTwoSame && isOneAndThreeSame) {
                playerPokers.setStatus(THREE_OF_A_KIND);
            }
        }
    }

    public void isStraight(PlayerPokers playerPokers) {
        boolean isTwoMoreThanOne1 = playerPokers.getPokers().get(0).getNumber() + 1 == playerPokers.getPokers().get(1).getNumber();
        boolean isThreeMoreThanOne1 = playerPokers.getPokers().get(0).getNumber() + 2 == playerPokers.getPokers().get(2).getNumber();
        boolean isFouroMoreThanOne1 = playerPokers.getPokers().get(0).getNumber() + 3 == playerPokers.getPokers().get(3).getNumber();
        boolean isFiveMoreThanOne1 = playerPokers.getPokers().get(0).getNumber() + 4 == playerPokers.getPokers().get(4).getNumber();
        if (isTwoMoreThanOne1 && isThreeMoreThanOne1 && isFouroMoreThanOne1 && isFiveMoreThanOne1) {
            playerPokers.setStatus(STRAIGHT);
        }
    }

    public void isFlush(PlayerPokers playerPokers) {
        boolean isTwoSameOne = playerPokers.getPokers().get(0).getPokerColor().equals(playerPokers.getPokers().get(1).getPokerColor());
        boolean isThreeSameOne = playerPokers.getPokers().get(0).getPokerColor().equals(playerPokers.getPokers().get(2).getPokerColor());
        boolean isFourSameOne = playerPokers.getPokers().get(0).getPokerColor().equals(playerPokers.getPokers().get(3).getPokerColor());
        boolean isFiveSameOne = playerPokers.getPokers().get(0).getPokerColor().equals(playerPokers.getPokers().get(4).getPokerColor());
        if (isTwoSameOne && isThreeSameOne && isFourSameOne && isFiveSameOne) {
            playerPokers.setStatus(FLUSH);
        }
    }

    public void isFullHouse(PlayerPokers playerPokers) {
        for (int i = 0; i < 3; i++) {
            boolean isTwoSameOne = doesNumberEqual(playerPokers, i, i+1);
            boolean isThreeSameOne = doesNumberEqual(playerPokers, i, i+2);

            boolean isFourSameFive = false;
            if (i == 0) {
                isFourSameFive = doesNumberEqual(playerPokers, i + 3, i + 4);
            } else if (i == 2){
                isFourSameFive = doesNumberEqual(playerPokers, i - 1, i - 2);
            }
            if (isTwoSameOne && isThreeSameOne && isFourSameFive) {
                playerPokers.setStatus(FULL_HOUSE);
            }
        }
    }

    public void isFourOfAKind(PlayerPokers playerPokers) {
        for (int i = 0; i < 2; i++) {
            boolean isTwoSameOne = doesNumberEqual(playerPokers, i, i+1);
            boolean isThreeSameOne = doesNumberEqual(playerPokers, i, i+2);
            boolean isFourSameOne = doesNumberEqual(playerPokers, i, i+3);
            if (isTwoSameOne && isThreeSameOne && isFourSameOne) {
                playerPokers.setStatus(FOUR_OF_A_KIND);
            }
        }
    }

    public void isStraightFlush(PlayerPokers playerPokers) {
        isStraight(playerPokers);
        boolean isStraightStatus = playerPokers.getStatus() == STRAIGHT;
        isFlush(playerPokers);
        boolean isFlushStatus = playerPokers.getStatus() == FLUSH;

        if (isStraightStatus && isFlushStatus) {
            playerPokers.setStatus(STRAIGHT_FLUSH);
        }
    }

    public boolean doesNumberEqual(PlayerPokers playerPokers, int i , int j) {
        return playerPokers.getPokers().get(i).getNumber() == playerPokers.getPokers().get(j).getNumber();
    }

    public int compareNumber(int i, int j) {
        return player1Pokers.getPokers().get(i).compareNumber(player2Pokers.getPokers().get(j).getNumber());
    }

    public void addPlayerPoker(String[] pokers) {
        for (int i = 0; i < pokers.length; i++) {
            if (i < 5) {
                player1Pokers.getPokers().add(new Poker(pokers[i]));
            } else {
                player2Pokers.getPokers().add(new Poker(pokers[i]));
            }
        }
        Collections.sort(player1Pokers.getPokers());
        Collections.sort(player2Pokers.getPokers());

        judgeType();
    }

    public void judgeType() {
        isPair(player1Pokers);
        isPair(player2Pokers);

        isThreeOfAKind(player1Pokers);
        isThreeOfAKind(player2Pokers);

        isStraight(player1Pokers);
        isStraight(player2Pokers);

        isFlush(player1Pokers);
        isFlush(player2Pokers);

        isFullHouse(player1Pokers);
        isFullHouse(player2Pokers);

        isFourOfAKind(player1Pokers);
        isFourOfAKind(player2Pokers);

        isStraightFlush(player1Pokers);
        isStraightFlush(player2Pokers);
    }
}
