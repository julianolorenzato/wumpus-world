package com.wumpusworld.ui;

import com.wumpusworld.game.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GameWindow extends JFrame implements ActionListener {
    Game game;
    BoardPanel boardPanel;
    InfoPanel infoPanel;
    ButtonPanel buttonPanel;
    Ending ending;

    public GameWindow(int lines, int columns) {
        this.setVisible(true);
        this.setSize(1280, 720);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.getContentPane().setLayout(null);
        game = new Game(lines, columns);
        this.ending = new Ending(game, this);
        boardPanel = new BoardPanel(game);
        infoPanel = new InfoPanel(game);
        buttonPanel = new ButtonPanel(game, boardPanel, infoPanel, ending);
        createWindow();
    }

    public void createWindow() {
        this.add(boardPanel);
        this.boardPanel.setLocation(5, 5);

        this.add(infoPanel);
        infoPanel.setLocation(720, 5);

        this.add(buttonPanel);
        buttonPanel.setLocation(720, 270);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
            throw new UnsupportedOperationException("Not supported yet.");
    }
}
