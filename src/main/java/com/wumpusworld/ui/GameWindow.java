package com.wumpusworld.ui;

import com.wumpusworld.game.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GameWindow extends JFrame implements ActionListener {
    int lines, columns;
    Game game;
    BoardPanel boardPanel;

    public GameWindow(int lines, int columns) {
        this.setVisible(true);
        this.setSize(1280, 720);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        game = new Game(lines, columns);
        boardPanel = new BoardPanel(game);
        createWindow();
    }

    public void createWindow() {
        this.add(boardPanel);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
