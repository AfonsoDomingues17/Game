package com.pong.gui;

import com.pong.model.Position;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class LanternaGUITest {
    private Screen screen;
    private LanternaGUI gui;
    private TextGraphics tg;

    @BeforeEach
    void setUp() {
        screen = Mockito.mock(Screen.class);
        tg = Mockito.mock(TextGraphics.class);

        Mockito.when(screen.newTextGraphics()).thenReturn(tg);
        gui = new LanternaGUI(screen);
        LanternaGUI.setMode(0);
    }
    @Test
    void drawBall() {
        gui.drawBall(new Position(60,15));
        Mockito.verify(tg, Mockito.times(1)).setForegroundColor(TextColor.ANSI.WHITE);
        Mockito.verify(tg, Mockito.times(1)).putString(60, 15, "\u25CF");
    }

    @Test
    void drawText() {
        gui.drawText(new Position(60, 5), "Menu", "#FFFFFF");

        Mockito.verify(tg, Mockito.times(1)).setForegroundColor(new TextColor.RGB(255, 255, 255));
        Mockito.verify(tg, Mockito.times(1)).putString(60, 5, "Menu");
    }

    @Test
    void drawPaddle1() {
        gui.drawPaddle1(new Position(1, 10));
        TerminalPosition top_left = new TerminalPosition(1, 8);
        TerminalSize tsize = new TerminalSize(1, 4);

        Mockito.verify(tg, Mockito.times(1)).fillRectangle(top_left,tsize,new TextCharacter('\u2588', new TextColor.RGB(56,183,254), new TextColor.RGB(18, 18, 18)));
    }

    @Test
    void drawPaddle2() {
        gui.drawPaddle2(new Position(39, 10));
        TerminalPosition top_left = new TerminalPosition(38, 8);
        TerminalSize tsize = new TerminalSize(1, 4);

        Mockito.verify(tg, Mockito.times(1)).fillRectangle(top_left, tsize, new TextCharacter('\u2588', new TextColor.RGB(225,54,54), new TextColor.RGB(18, 18, 18)));
    }


    @Test
    void drawScoreboard() {

        gui.drawScoreBoard(new Position(20, 5),0,0);
        Mockito.verify(tg, Mockito.times(1)).putString(17,2,String.valueOf(0));
        Mockito.verify(tg, Mockito.times(1)).putString(23, 2,String.valueOf(0));


    }
}