package com.wumpusworld.ui;

import com.wumpusworld.game.Game;

import javax.swing.*;
import java.awt.*;

public class Ending extends JFrame {
    private Game game;
    private JLabel text;
    GameWindow gameWindow;

    public Ending(Game game, GameWindow gameWindow) {
        this.game = game;
        this.gameWindow = gameWindow;
        this.setSize(300,150);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Fim de Jogo");
        this.setLayout(new FlowLayout());
        updateWindow();
        this.setVisible(false);
    }

    public void updateWindow() {
        text = new JLabel();
        if (this.game.isGameOver() == true) {
            gameWindow.dispose();
            text.setText("Você perdeu!");
            this.setVisible(true);
            this.add(text);
        }

        if (this.game.isWin() == true) {
            gameWindow.dispose();
            text.setText("Você ganhou!");
            this.setVisible(true);
            this.add(text);
        }
    }
}
