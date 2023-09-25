package com.wumpusworld.ui;

import com.wumpusworld.game.Game;
import com.wumpusworld.game.Position;
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
        updatePanel();
    }

    public void updatePanel() {
        this.removeAll();

        Position agent = game.getAgent().getPosition();

        for (int i = 0; i < lines; i++) {
            for (int j = 0; j < columns; j++) {
                JPanel tile = new JPanel();

                Tile currTile = this.game.getBoard()[i][j];

                if (i == agent.getLine() && j == agent.getColumn()) {
                    tile.setBackground(Color.blue);
                } else {
                    if (currTile.isVisible()) {
                        int wumpusLine = game.getWumpus().getPosition().getLine();
                        int wumpusCol = game.getWumpus().getPosition().getColumn();
                        int fastWumpusLine = game.getFastWumpus().getPosition().getLine();
                        int fastWumpusCol = game.getFastWumpus().getPosition().getColumn();

                        if (wumpusLine == i && wumpusCol == j && !game.getWumpus().isDead()) {
                            tile.setBackground(Color.red);
                        } else if (fastWumpusLine == i && fastWumpusCol == j && !game.getFastWumpus().isDead()) {
                            tile.setBackground(Color.red);
                        } else if (currTile.getGold() != null) {
                            tile.setBackground(Color.yellow);
                        } else if (currTile.getWood() != null) {
                            tile.setBackground(Color.magenta);
                        } else if (currTile.getPit() != null && currTile.getPit().isOpen()) {
                            tile.setBackground(Color.green);
                        } else {
                            tile.setBackground(Color.lightGray);
                        }
                    } else {
                        tile.setBackground(Color.darkGray);
                    }
                }

                this.add(tile);
            }
        }

        this.revalidate();
        this.repaint();
    }
}