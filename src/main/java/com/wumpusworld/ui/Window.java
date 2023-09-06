package com.wumpusworld.ui;

import com.wumpusworld.game.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Window extends JFrame implements ActionListener {
    Game game;
    DialogWindow dialogWindow = new DialogWindow();

    Window() {
        this.setVisible(true);
        this.setSize(1500, 900);
        this.setLayout(new FlowLayout());
        this.add(new BoardPanel(10, 10));
    }
    public static void main(String[] args) {
        Window window = new Window();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.dialogWindow) {
            this.add(this.dialogWindow);
        }
    }
}
