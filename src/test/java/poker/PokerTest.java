package poker;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class PokerTest {

    @Test
    public void highCard_should_return_negative_1_when_player1_is_3S_and_player2_is_7D () {
        //given
        Poker poker1 = new Poker("3S");
        Poker poker2 = new Poker("7D");

        //when
        int result = poker1.compareNumber(poker2.getNumber());

        //then
        assertEquals(-1, result);
    }

    @Test
    public void highCard_should_return_0_when_player1_is_2S_and_player2_is_2D () {
        //given
        Poker poker1 = new Poker("2S");
        Poker poker2 = new Poker("2D");

        //when
        int result = poker1.compareNumber(poker2.getNumber());

        //then
        assertEquals(0, result);
    }

    @Test
    public void highCard_should_return_player1_win_when_input_3S_4D_JH_6S_9S_6S_3C_TC_9C_5C () {
        //given
        String[] pokers = {"3S", "4D", "JH", "6S", "9S", "6S", "3C", "TC", "9C", "5C"};
        PokerGame pokerGame = new PokerGame(pokers);

        //when
        String result = pokerGame.compare();

        //then
        assertEquals("player1 win", result);
    }

    @Test
    public void highCard_should_return_player2_win_when_input_3S_4D_TH_6S_9S_6S_3C_TC_9C_5C () {
        //given
        String[] pokers = {"3S", "4D", "TH", "6S", "9S", "6S", "3C", "TC", "9C", "5C"};
        PokerGame pokerGame = new PokerGame(pokers);

        //when
        String result = pokerGame.compare();

        //then
        assertEquals("player2 win", result);
    }

    @Test
    public void highCard_should_return_draw_when_input_3S_4D_TH_5S_9S_3H_4C_TC_9C_5C () {
        //given
        String[] pokers = {"3S", "4D", "TH", "5S", "9S", "3H", "4C", "TC", "9C", "5C"};
        PokerGame pokerGame = new PokerGame(pokers);

        //when
        String result = pokerGame.compare();

        //then
        assertEquals("draw", result);
    }

    @Test
    public void highCard_compare_pair_should_return_player1_win_when_input_AS_AD_3H_6S_9S_2S_6C_TC_9C_5C () {
        //given
        String[] pokers = {"AS", "AD", "3H", "6S", "9S", "2S", "6C", "TC", "9C", "5C"};
        PokerGame pokerGame = new PokerGame(pokers);

        //when
        String result = pokerGame.compare();

        //then
        assertEquals("player1 win", result);
    }

    @Test
    public void pair_should_return_player2_win_when_input_3S_3D_4H_6S_9S_4S_4C_TC_9C_5C () {
        //given
        String[] pokers = {"3S", "3D", "4H", "6S", "9S", "4S", "4C", "TC", "9C", "5C"};
        PokerGame pokerGame = new PokerGame(pokers);

        //when
        String result = pokerGame.compare();

        //then
        assertEquals("player2 win", result);
    }

    @Test
    public void pair_should_return_player2_win_when_input_4H_4D_7H_6S_9S_4S_4C_TC_9C_5C () {
        //given
        String[] pokers = {"4H", "4D", "7H", "6S", "9S", "4S", "4C", "TC", "9C", "5C"};
        PokerGame pokerGame = new PokerGame(pokers);

        //when
        String result = pokerGame.compare();

        //then
        assertEquals("player2 win", result);
    }

    @Test
    public void two_pair_compare_pair_should_return_player1_win_when_input_6S_6D_3H_3S_9S_8S_8C_TC_9C_5C () {
        //given
        String[] pokers = {"6S", "6D", "3H", "3S", "9S", "8S", "8C", "TC", "9C", "5C"};
        PokerGame pokerGame = new PokerGame(pokers);

        //when
        String result = pokerGame.compare();

        //then
        assertEquals("player1 win", result);
    }

    @Test
    public void two_pair_should_return_player2_win_when_input_6S_6D_3H_3S_9S_8S_8C_TC_TC_5C () {
        //given
        String[] pokers = {"6S", "6D", "3H", "3S", "9S", "8S", "8C", "TC", "TC", "5C"};
        PokerGame pokerGame = new PokerGame(pokers);

        //when
        String result = pokerGame.compare();

        //then
        assertEquals("player2 win", result);
    }

    @Test
    public void three_compare_pair_should_return_player2_win_when_input_6S_6D_3H_4S_9S_8S_8C_TC_9C_8C () {
        //given
        String[] pokers = {"6S", "6D", "3H", "4S", "9S", "8S", "8C", "TC", "9C", "8C"};
        PokerGame pokerGame = new PokerGame(pokers);

        //when
        String result = pokerGame.compare();

        //then
        assertEquals("player2 win", result);
    }

    @Test
    public void three_should_return_player1_win_when_input_6S_9D_9H_4S_9S_8S_8C_TC_9C_8C () {
        //given
        String[] pokers = {"6S", "9D", "9H", "4S", "9S", "8S", "8C", "TC", "9C", "8C"};
        PokerGame pokerGame = new PokerGame(pokers);

        //when
        String result = pokerGame.compare();

        //then
        assertEquals("player1 win", result);
    }

    @Test
    public void three_compare_straight_should_return_player2_win_when_input_6S_9D_9H_4S_9S_3S_4C_5C_6C_7C () {
        //given
        String[] pokers = {"6S", "9D", "9H", "4S", "9S", "3S", "4C", "5C", "6C", "7C"};
        PokerGame pokerGame = new PokerGame(pokers);

        //when
        String result = pokerGame.compare();

        //then
        assertEquals("player2 win", result);
    }

    @Test
    public void straight_should_return_player1_win_when_input_4S_5D_6H_7S_8S_3S_4C_5C_6C_7C () {
        //given
        String[] pokers = {"4S", "5D", "6H", "7S", "8S", "3S", "4C", "5C", "6C", "7C"};
        PokerGame pokerGame = new PokerGame(pokers);

        //when
        String result = pokerGame.compare();

        //then
        assertEquals("player1 win", result);
    }

    @Test
    public void straight_compare_flush_should_return_player2_win_when_input_4S_5D_6H_7S_8S_3C_5C_7C_9C_KC () {
        //given
        String[] pokers = {"4S", "5D", "6H", "7S", "8S", "3C", "5C", "7C", "9C", "KC"};
        PokerGame pokerGame = new PokerGame(pokers);

        //when
        String result = pokerGame.compare();

        //then
        assertEquals("player2 win", result);
    }

    @Test
    public void flush_should_return_player1_win_when_input_8S_2S_3S_KS_5S_3C_5C_7C_9C_TC () {
        //given
        String[] pokers = {"8S", "2S", "3S", "KS", "5S", "3C", "5C", "7C", "9C", "TC"};
        PokerGame pokerGame = new PokerGame(pokers);

        //when
        String result = pokerGame.compare();

        //then
        assertEquals("player1 win", result);
    }

    @Test
    public void flush_compare_four_should_return_player2_win_when_input_8S_2S_3S_KS_5S_3C_3H_3D_3S_TC () {
        //given
        String[] pokers = {"8S", "2S", "3S", "KS", "5S", "3C", "3H", "3D", "3S", "TC"};
        PokerGame pokerGame = new PokerGame(pokers);

        //when
        String result = pokerGame.compare();

        //then
        assertEquals("player2 win", result);
    }

    @Test
    public void four_should_return_player1_win_when_input_4D_4S_4H_4C_5S_3C_3H_3D_3S_TC () {
        //given
        String[] pokers = {"4D", "4S", "4H", "4C", "5S", "3C", "3H", "3D", "3S", "TC"};
        PokerGame pokerGame = new PokerGame(pokers);

        //when
        String result = pokerGame.compare();

        //then
        assertEquals("player1 win", result);
    }

    @Test
    public void full_house_compare_four_should_return_player2_win_when_input_8C_8S_8H_5C_5S_3C_3H_3D_3S_TC () {
        //given
        String[] pokers = {"8C", "8S", "8H", "5C", "5S", "3C", "3H", "3D", "3S", "TC"};
        PokerGame pokerGame = new PokerGame(pokers);

        //when
        String result = pokerGame.compare();

        //then
        assertEquals("player2 win", result);
    }

    @Test
    public void full_house_should_return_player1_win_when_input_8C_8S_8H_5C_5S_3C_3H_3D_3S_TC () {
        //given
        String[] pokers = {"8C", "8S", "8H", "5C", "5S", "3C", "3H", "3D", "TS", "TC"};
        PokerGame pokerGame = new PokerGame(pokers);

        //when
        String result = pokerGame.compare();

        //then
        assertEquals("player1 win", result);
    }

    @Test
    public void four_compare_straight_flush_should_return_player2_win_when_input_4D_4S_4H_4C_5S_5C_6C_7C_8C_9C () {
        //given
        String[] pokers = {"4D", "4S", "4H", "4C", "5S", "5C", "6C", "7C", "8C", "9C"};
        PokerGame pokerGame = new PokerGame(pokers);

        //when
        String result = pokerGame.compare();

        //then
        assertEquals("player2 win", result);
    }

    @Test
    public void straight_flush_should_return_player1_win_when_input_6S_7S_8S_9S_TS_5C_6C_7C_8C_9C () {
        //given
        String[] pokers = {"6S", "7S", "8S", "9S", "TS", "5C", "6C", "7C", "8C", "9C"};
        PokerGame pokerGame = new PokerGame(pokers);

        //when
        String result = pokerGame.compare();

        //then
        assertEquals("player1 win", result);
    }

}
