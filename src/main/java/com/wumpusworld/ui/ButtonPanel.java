package com.wumpusworld.ui;

import com.wumpusworld.game.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonPanel extends JPanel implements ActionListener {
    Game game;
    JButton up, down, left, right, flashlightUp, flashlightDown, flashlightLeft, flashlightRight;
    JPanel movementPanel, actionPanel;
    BoardPanel boardPanel;
    public ButtonPanel(Game game, BoardPanel boardPanel) {
        this.game = game;
        this.boardPanel = boardPanel;
        this.setBackground(Color.lightGray);
        this.setSize(500, 400);
        createPanel();
    }

    public void createPanel() {
        // movementPanel definition
        JPanel movementPanel = new JPanel();
        this.add(movementPanel);
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

        // action panel definition
        JPanel actionPanel = new JPanel();
        actionPanel.setLayout(new BorderLayout());
        actionPanel.setSize(200,200);
        this.add(actionPanel);
        flashlightUp = new JButton("usar lanterna para cima");
        flashlightDown = new JButton("usar lanterna para baixo");
        flashlightLeft = new JButton("usar lanterna para esquerda");
        flashlightRight = new JButton("usar lanterna para direita");
        flashlightUp.addActionListener(this);
        flashlightDown.addActionListener(this);
        flashlightLeft.addActionListener(this);
        flashlightRight.addActionListener(this);
        actionPanel.add(flashlightUp, BorderLayout.NORTH);
        actionPanel.add(flashlightDown, BorderLayout.SOUTH);
        actionPanel.add(flashlightLeft, BorderLayout.WEST);
        actionPanel.add(flashlightRight, BorderLayout.EAST);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == up) {
            this.game.moveAgentUp();
            this.boardPanel.updateWindow();
        }

        if (e.getSource() == down) {
            this.game.moveAgentDown();
            this.boardPanel.updateWindow();
        }

        if (e.getSource() == left) {
            this.game.moveAgentLeft();
            this.boardPanel.updateWindow();
        }

        if (e.getSource() == right) {
            this.game.moveAgentRight();
            this.boardPanel.updateWindow();
        }

        if (e.getSource() == flashlightUp) {
            this.game.revealTilesUp();
            this.boardPanel.updateWindow();
        }

        if (e.getSource() == flashlightDown) {
            this.game.revealTilesDown();
            this.boardPanel.updateWindow();
        }

        if (e.getSource() == flashlightRight) {
            this.game.revealTilesRight();
            this.boardPanel.updateWindow();
        }

        if (e.getSource() == flashlightLeft) {
            this.game.revealTilesLeft();
            this.boardPanel.updateWindow();
        }
    }
}
