package com.wumpusworld.ui;

import com.wumpusworld.game.Game;

import javax.swing.*;
import java.awt.*;

public class InfoPanel extends JPanel {
    Game game;
    JLabel welcome, life, items;

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
        this.add(welcome);
        this.add(life);
        this.add(items);

        this.revalidate();
        this.repaint();
    }
}
