package com.wumpusworld.ui;

import com.wumpusworld.game.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonPanel extends JPanel implements ActionListener {
    Game game;
    JButton up, down, left, right, flashlightUp, flashlightDown, flashlightLeft, flashlightRight, closePit, makeArrow, arrowUp, arrowDown, arrowLeft, arrowRight;
    JPanel movementPanel, actionPanel, pitPanel, arrowPanel;
    BoardPanel boardPanel;
    InfoPanel infoPanel;

    public ButtonPanel(Game game, BoardPanel boardPanel, InfoPanel infoPanel) {
        this.game = game;
        this.boardPanel = boardPanel;
        this.infoPanel = infoPanel;
        this.setBackground(Color.lightGray);
        this.setSize(500, 400);
        createPanel();
    }

    public void createPanel() {
        // movementPanel definition
        JPanel movementPanel = new JPanel();
        this.add(movementPanel);
        movementPanel.setLayout(new BorderLayout());
        movementPanel.setSize(200, 200);
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
        actionPanel.setSize(200, 200);
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

        // pit panel definition
        JPanel pitPanel = new JPanel();
        pitPanel.setLayout(new BorderLayout());
        this.add(pitPanel);
        closePit = new JButton("fechar poço próximo");
        makeArrow = new JButton("criar nova flecha");
        closePit.addActionListener(this);
        makeArrow.addActionListener(this);
        pitPanel.add(closePit, BorderLayout.EAST);
        pitPanel.add(makeArrow, BorderLayout.WEST);

        // arrow panel definition
        JPanel arrowPanel = new JPanel();
        arrowPanel.setLayout(new BorderLayout());
        this.add(arrowPanel);
        arrowUp = new JButton("flecha para cima");
        arrowDown = new JButton("flecha para baixo");
        arrowLeft = new JButton("flecha para esquerda");
        arrowRight = new JButton("flecha para direita");
        arrowUp.addActionListener(this);
        arrowDown.addActionListener(this);
        arrowLeft.addActionListener(this);
        arrowRight.addActionListener(this);
        arrowPanel.add(arrowUp, BorderLayout.NORTH);
        arrowPanel.add(arrowDown, BorderLayout.SOUTH);
        arrowPanel.add(arrowLeft, BorderLayout.WEST);
        arrowPanel.add(arrowRight, BorderLayout.EAST);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == up) {
            this.game.moveAgentUp();
            this.boardPanel.updatePanel();
            this.infoPanel.updatePanel();
        }

        if (e.getSource() == down) {
            this.game.moveAgentDown();
            this.boardPanel.updatePanel();
            this.infoPanel.updatePanel();
        }

        if (e.getSource() == left) {
            this.game.moveAgentLeft();
            this.boardPanel.updatePanel();
            this.infoPanel.updatePanel();
        }

        if (e.getSource() == right) {
            this.game.moveAgentRight();
            this.boardPanel.updatePanel();
            this.infoPanel.updatePanel();
        }

        if (e.getSource() == flashlightUp) {
            this.game.revealTilesUp();
            this.boardPanel.updatePanel();
            this.infoPanel.updatePanel();
        }

        if (e.getSource() == flashlightDown) {
            this.game.revealTilesDown();
            this.boardPanel.updatePanel();
            this.infoPanel.updatePanel();
        }

        if (e.getSource() == flashlightRight) {
            this.game.revealTilesRight();
            this.boardPanel.updatePanel();
            this.infoPanel.updatePanel();
        }

        if (e.getSource() == flashlightLeft) {
            this.game.revealTilesLeft();
            this.boardPanel.updatePanel();
            this.infoPanel.updatePanel();
        }

        if (e.getSource() == closePit) {
            this.game.closePit();
            this.boardPanel.updatePanel();
            this.infoPanel.updatePanel();
        }

        if (e.getSource() == makeArrow) {
            this.game.makeArrow();
            this.boardPanel.updatePanel();
            this.infoPanel.updatePanel();
        }

        if (e.getSource() == arrowUp) {
            this.game.throwArrow(0);
            this.boardPanel.updatePanel();
            this.infoPanel.updatePanel();
        }

        if (e.getSource() == arrowDown) {
            this.game.throwArrow(1);
            this.boardPanel.updatePanel();
            this.infoPanel.updatePanel();
        }

        if (e.getSource() == arrowLeft) {
            this.game.throwArrow(2);
            this.boardPanel.updatePanel();
            this.infoPanel.updatePanel();
        }

        if (e.getSource() == arrowRight) {
            this.game.throwArrow(3);
            this.boardPanel.updatePanel();
            this.infoPanel.updatePanel();
        }
    }
}
