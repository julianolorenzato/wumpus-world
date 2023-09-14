package com.wumpusworld.ui;

import com.wumpusworld.game.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Window extends JFrame implements ActionListener {
    Game game;

    Window() {
        this.setVisible(true);
        this.setSize(1280, 720);
        this.setLocationRelativeTo(null);
        this.setLayout(new FlowLayout());
    }
    
    public static void main(String[] args) {
        Window window = new Window();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        StartMenu startMenu = new StartMenu(window);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
