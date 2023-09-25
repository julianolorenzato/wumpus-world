package com.wumpusworld.ui;

import com.wumpusworld.game.Game;
import com.wumpusworld.game.Tile;

import javax.swing.*;
import java.awt.*;

public class InfoPanel extends JPanel {
    Game game;
    JLabel welcome, life, items, breeze, badSmell;

    public InfoPanel(Game game) {
        this.game = game;
        this.setLayout(new GridLayout(0, 1, 10, 10));
        this.setSize(500, 200);
        this.setBackground(Color.lightGray);
        updatePanel();

    }

    void updatePanel() {
        this.removeAll();

        welcome = new JLabel("Mundo de Wumpus");
        life = new JLabel("Vida: " + game.getAgent().getLife());
        items = new JLabel("Itens: " + game.getAgent().getItems());
        breeze = new JLabel("O agente está sentindo brisa");
        badSmell = new JLabel("O agente está sentindo fedor");

        Tile agentTile = this.game.getBoard()[this.game.getAgent().getPosition().getLine()][this.game.getAgent().getPosition().getColumn()];
        breeze.setVisible(agentTile.isBreeze());
        badSmell.setVisible(agentTile.isBadSmell());

        this.add(welcome);
        this.add(life);
        this.add(items);
        this.add(breeze);
        this.add(badSmell);

        this.revalidate();
        this.repaint();
    }
}
