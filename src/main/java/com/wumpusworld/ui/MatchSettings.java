package com.wumpusworld.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MatchSettings extends JFrame implements ActionListener {
    private JPanel infoPanel, linesPanel, columnsPanel, buttonPanel;
    private JLabel linesLabel, columnsLabel;
    private JTextField linesField, columnsField;
    private JButton start, cancel;
    private int lines, columns;
    private StartMenu startMenu;

    public MatchSettings(StartMenu startMenu) {
        this.setSize(250,200);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
        this.setTitle("Configuração da Partida");
        this.setLayout(new GridLayout(2, 1, 0, 20));
        createWindow();
        this.setVisible(true);
        this.startMenu = startMenu;
    }

    public void createWindow() {
        infoPanel = new JPanel();
        buttonPanel = new JPanel();
        this.add(infoPanel);
        this.add(buttonPanel);

        // infoPanel definition
        infoPanel.setLayout(new GridLayout(0, 2, 20, 10));
        infoPanel.setBorder(BorderFactory.createEmptyBorder(0, 40, 0, 40));
        linesPanel = new JPanel();
        columnsPanel = new JPanel();
        infoPanel.add(linesPanel);
        infoPanel.add(columnsPanel);
        linesLabel = new JLabel("Linhas:");
        linesField = new JTextField("15");
        columnsLabel = new JLabel("Colunas:");
        columnsField = new JTextField("15");
        linesField.setEditable(true);
        columnsField.setEditable(true);
        infoPanel.add(linesLabel);
        infoPanel.add(columnsLabel);
        infoPanel.add(linesField);
        infoPanel.add(columnsField);

        // buttonPanel definition
        buttonPanel.setLayout(new FlowLayout(1, 20, 0));
        start = new JButton("Iniciar");
        cancel = new JButton("Cancelar");
        start.addActionListener(this);
        cancel.addActionListener(this);
        buttonPanel.add(start);
        buttonPanel.add(cancel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == start) {
            lines = Integer.parseInt(linesField.getText());
            columns = Integer.parseInt(columnsField.getText());
            /*
            TODO code logic;
            */
            super.dispose();
        }

        if (e.getSource() == cancel) {
            startMenu.setVisible(true);
            super.dispose();
        }
    }
}
