package com.wumpusworld.ui;

import com.wumpusworld.game.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonPanel extends JPanel implements ActionListener {
    Game game;
    JButton up, down, left, right;
    JPanel movementPanel, actionPanel;
    public ButtonPanel(Game game) {
        this.game = game;
        this.setBackground(Color.lightGray);
        this.setSize(500, 400);
        createPanel();
    }

    public void createPanel() {
        JPanel movementPanel = new JPanel();
        this.add(movementPanel);

    /*
        JPanel actionPanel = new JPanel();
        actionPanel.setLayout(new GridLayout());
        this.add(actionPanel);
     */

        // movementPanel definition
        movementPanel.setLayout(new BorderLayout());
        movementPanel.setSize(200,200);
        up = new JButton("ir para cima");
        down = new JButton("ir para baixo");
        left = new JButton("ir para esquerda");
        right = new JButton("ir para direita");
        up.addActionListener(this);
        down.addActionListener(this);
        left.addActionListener(this);
        right.addActionListener(this);
        movementPanel.add(up, BorderLayout.NORTH);
        movementPanel.add(down, BorderLayout.SOUTH);
        movementPanel.add(left, BorderLayout.WEST);
        movementPanel.add(right, BorderLayout.EAST);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == up) {
            game.moveAgentUp();
        }

        if (e.getSource() == down) {
            game.moveAgentDown();
        }

        if (e.getSource() == left) {
            game.moveAgentLeft();
        }

        if (e.getSource() == right) {
            game.moveAgentRight();
        }
    }
}
