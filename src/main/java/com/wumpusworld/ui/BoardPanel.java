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
        this.setSize(new Dimension(700, 700));
        this.setLayout(new GridLayout(lines, columns));
        this.setBackground(Color.BLUE);

        for (Tile[] tiles : game.getBoard()) {
            this.add(new JPanel());
        }
    }
}