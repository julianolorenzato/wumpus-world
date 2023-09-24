package com.wumpusworld.game;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Game {
    private int rounds = 0;
    private final int lines;
    private final int columns;
    private Tile[][] board;
    private Agent agent = new Agent();
    private Wumpus wumpus;
    private FastWumpus fastWumpus;
    private final String greetings = "Olá, seja bem vindo ao Mundo de Wumpus, selecione uma opção para começar a jogar!";

    public Game(int lines, int columns) {
        this.lines = lines;
        this.columns = columns;
        this.setupMonsters();
        this.agent = new Agent();
        this.board = fillBoard();
    }

    public int getLines() {
        return lines;
    }

    public int getColumns() {
        return columns;
    }

    public Tile[][] getBoard() {
        return board;
    }

    public Agent getAgent() {
        return agent;
    }

    public void printBoard() {
        for (Tile[] tilesLine : this.board){
            System.out.print("[");
            for (Tile tile : tilesLine) {
                System.out.print(tile.isBadSmell());
                System.out.print(",");
            }
            System.out.println("]");
        }
    }

    private Map<String, int[]> randomCoordinates() {
        Map<String, int[]> coordinates = new HashMap<>();
        coordinates.put("x", new int[8]);
        coordinates.put("y", new int[8]);
        for (int i = 0; i < 7; i++) {
            int line, column;

            do {
                line = new Random().nextInt(this.lines - 1);
                column = new Random().nextInt(this.columns - 1);

                // Avoid coordinate(14,0)
            } while (line == 14 && column == 0);

            coordinates.get("x")[i] = line;
            coordinates.get("y")[i] = column;
        }

        return coordinates;
    }
    private Tile[][] fillBoard() {
        Tile[][] tiles = new Tile[this.lines][this.columns];

        for (int i = 0; i <  this.lines; i++) {
            for (int j = 0; j < this.columns; j++) {
                tiles[i][j] = new Tile();
            }
        }

        return tiles;
    }

    private void setupMonsters() {
        Position wumpusPosition = Position.randomPosition(this.lines, this.columns);
        Position fastWumpusPosition = Position.randomPosition(this.lines, this.columns);

        do {
            wumpusPosition = Position.randomPosition(this.lines, this.columns);
            fastWumpusPosition = Position.randomPosition(this.lines, this.columns);
        } while (wumpusPosition.samePosition(fastWumpusPosition));

        this.wumpus = new Wumpus(wumpusPosition);
        this.fastWumpus = new FastWumpus(fastWumpusPosition);
    }

    boolean isGameOver() {
        Position agentPosition = agent.getPosition();
        Position wumpusPosition = wumpus.getPosition();
        Position fastWumpusPosition = fastWumpus.getPosition();

        return agentPosition.samePosition(wumpusPosition) || agentPosition.samePosition(fastWumpusPosition) || this.agent.life == 0;
    }

    public void moveAgentUp() {
        if (agent.getPosition().getLine() == 0) {
            throw new Error("O agente já se encontra no extremo norte do mapa");
        }

        this.agent.moveUp();
    }

    public void moveAgentDown() {
        if (agent.getPosition().getLine() == this.lines - 1) {
            throw new Error("O agente já se encontra no extremo sul do mapa");
        }

        this.agent.moveDown();
    }

    public void moveAgentRight() {
        if (agent.getPosition().getColumn() == this.columns - 1) {
            throw new Error("O agente já se encontra no extremo leste do mapa");
        }

        this.agent.moveRight();
    }

    public void moveAgentLeft() {
        if (agent.getPosition().getColumn() == 0) {
            throw new Error("O agente já se encontra no extremo oeste do sul");
        }

        this.agent.moveLeft();
    }

}
