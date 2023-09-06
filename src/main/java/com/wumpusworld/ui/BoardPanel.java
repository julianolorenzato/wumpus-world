package com.wumpusworld.ui;

import com.wumpusworld.game.Game;
import com.wumpusworld.game.Tile;

import javax.swing.*;
import java.awt.*;

public class BoardPanel extends JPanel {
    Game game;
    BoardPanel(int lines, int columns) {
        this.setVisible(true);
        this.setSize(1000, 800);
        this.setLayout(new GridLayout(game.getLines(), game.getColumns()));
        this.setBackground(Color.BLUE);
        this.setAlignmentX(10);

        this.game = new Game(lines, columns);

        for (Tile[] tiles : game.getBoard()) {
            this.add(new JPanel());
        }
    }
}
