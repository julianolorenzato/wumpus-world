package com.wumpusworld.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartMenu extends JFrame implements ActionListener {
    private JPanel title, buttons;
    private JLabel welcome;
    private JButton playButton, debugButton, exitButton;

    
    public StartMenu() {
        this.setSize(300,150);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Menu Inicial");
        this.setLayout(new GridLayout(2,1, 0, 10));
        createWindow();
        this.setVisible(true);
    }
    
    public void createWindow() {
        title = new JPanel();
        buttons = new JPanel();
        title.setBorder(BorderFactory.createEmptyBorder(15, 40, 5, 40));
        buttons.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));
        this.add(title);
        this.add(buttons);

        // welcomePanel definition
        welcome = new JLabel("Bem Vindo ao Mundo de Wumpus!");
        title.add(welcome);
        
        // buttonPanel definition
        buttons.setLayout(new FlowLayout(1, 10, 0));
        playButton = new JButton("Jogar");
        debugButton = new JButton("Debug");
        exitButton = new JButton("Sair");
        playButton.addActionListener(this);
        debugButton.addActionListener(this);
        exitButton.addActionListener(this);
        buttons.add(playButton);
        buttons.add(debugButton);
        buttons.add(exitButton);
    }

    public static void main(String[] args) {
        StartMenu startMenu = new StartMenu();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == playButton) {
            MatchSettings matchSettings = new MatchSettings(this);
            this.setVisible(false);
        }
        
        if (e.getSource() == debugButton) {
            throw new UnsupportedOperationException("Not supported yet.");
        }
        
        if (e.getSource() == exitButton) {
            super.dispose();
        }
    }
}
