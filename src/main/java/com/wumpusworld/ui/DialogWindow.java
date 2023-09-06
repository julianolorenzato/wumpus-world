package com.wumpusworld.ui;

import javax.swing.*;

public class DialogWindow extends JFrame {
    JLabel label = new JLabel("Digite o numero de linhas e colunas do tabuleiro");
    DialogWindow() {
        this.add(this.label);
    }
}
