package poker;

import java.util.ArrayList;
import java.util.List;

public class PlayerPokers {

    List<Poker> pokers = new ArrayList<>();
    int status;
    public static final int HIGH_CARD = 0;

    public PlayerPokers() {
        status = HIGH_CARD;
    }

    public List<Poker> getPokers() {
        return pokers;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
