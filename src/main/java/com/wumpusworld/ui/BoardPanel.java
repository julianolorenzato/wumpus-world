package com.wumpusworld.ui;

import com.wumpusworld.game.Game;
import com.wumpusworld.game.Tile;

import javax.swing.*;
import java.awt.*;

public class BoardPanel extends JPanel {
    Game game;
    int lines, columns;
    BoardPanel(Game game) {
        this.game = game;
        this.lines = game.getLines();
        this.columns = game.getColumns();
        this.setVisible(true);
        this.setSize(670, 670);
        this.setLayout(new GridLayout(lines, columns, 3, 3));
        this.setBackground(Color.lightGray);

        for (int i = 0; i < lines; i++) {
            for (int j = 0; j < columns; j++) {
                JPanel tile = new JPanel();
                tile.setBackground(Color.darkGray);
                this.add(tile);
            }
        }
    }
}