package com.wumpusworld.game;

import java.util.*;

public class Game {
    // private int rounds = 0;
    private final int lines;
    private final int columns;
    private Tile[][] board;
    private Agent agent = new Agent();
    private Wumpus wumpus;
    private FastWumpus fastWumpus;

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

    //    public void nextRound() {
    //        this.rounds++;
    //    }

    public void printBoard() {
        for (Tile[] tilesLine : this.board) {
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
        for (int i = 0; i < 8; i++) {
            int line, column;
            boolean alreadyExists = false;

            do {
                line = new Random().nextInt(this.lines - 1);
                column = new Random().nextInt(this.columns - 1);

                for (int j = 0; j <= i; j++) {
                    if (line == coordinates.get("x")[j] && column == coordinates.get("y")[j]) {
                        alreadyExists = true;
                    }
                }

                // Avoid coordinate(14,0)
            } while ((line == 14 && column == 0) || alreadyExists);

            coordinates.get("x")[i] = line;
            coordinates.get("y")[i] = column;
        }

        return coordinates;
    }

    private Tile[][] fillBoard() {
        Tile[][] tiles = new Tile[this.lines][this.columns];
        Map<String, int[]> coordinates = randomCoordinates();

        for (int i = 0; i < this.lines; i++) {
            for (int j = 0; j < this.columns; j++) {

                tiles[i][j] = new Tile();

                for (int k = 0; k < 8; k++) {
                    if (k < 5) {
                        if (i == coordinates.get("x")[k] && j == coordinates.get("y")[k]) {
                            tiles[i][j] = new Tile(new Pit());
                        }
                    } else if (k < 7) {
                        if (i == coordinates.get("x")[k] && j == coordinates.get("y")[k]) {
                            tiles[i][j] = new Tile(new Wood());
                        }
                    } else {
                        if (i == coordinates.get("x")[k] && j == coordinates.get("y")[k]) {
                            tiles[i][j] = new Tile(new Gold());
                        }
                    }
                }
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
        this.tryToPick();
        this.getBoard()[this.agent.getPosition().getLine()][this.agent.getPosition().getColumn()].setVisible(true);
    }

    public void moveAgentDown() {
        if (agent.getPosition().getLine() == this.lines - 1) {
            throw new Error("O agente já se encontra no extremo sul do mapa");
        }

        this.agent.moveDown();
        this.tryToPick();
        this.getBoard()[this.agent.getPosition().getLine()][this.agent.getPosition().getColumn()].setVisible(true);
    }

    public void moveAgentRight() {
        if (agent.getPosition().getColumn() == this.columns - 1) {
            throw new Error("O agente já se encontra no extremo leste do mapa");
        }

        this.agent.moveRight();
        this.tryToPick();
        this.getBoard()[this.agent.getPosition().getLine()][this.agent.getPosition().getColumn()].setVisible(true);
    }

    public void moveAgentLeft() {
        if (agent.getPosition().getColumn() == 0) {
            throw new Error("O agente já se encontra no extremo oeste do mapa");
        }

        this.agent.moveLeft();
        this.tryToPick();
        this.getBoard()[this.agent.getPosition().getLine()][this.agent.getPosition().getColumn()].setVisible(true);
    }

    private void tryToPick() {
        int agentLine = this.getAgent().getPosition().getLine();
        int agentColumn = this.getAgent().getPosition().getColumn();
        Tile tile = this.getBoard()[agentLine][agentColumn];

        if (tile.getGold() != null && this.agent.items.size() < 3) {
            this.agent.items.add(new GoldNugget());
            tile.removeGold();
        }

        if (tile.getWood() != null && this.agent.items.size() < 3) {
            this.agent.items.add(new WoodPlank());
            tile.removeWood();
        }
    }

    public void revealTilesUp() {
        Optional<Item> result = this.agent.items.stream().filter(item -> item instanceof Flashlight).findAny();

        if (result.isPresent()) {
            for (int i = this.agent.getPosition().getLine(); i >= 0; i--) {
                this.getBoard()[i][this.agent.getPosition().getColumn()].setVisible(true);
            }

            this.agent.items.remove(result.get());
        }
    }

    public void revealTilesDown() {
        Optional<Item> result = this.agent.items.stream().filter(item -> item instanceof Flashlight).findAny();

        if (result.isPresent()) {
            for (int i = this.agent.getPosition().getLine(); i < this.lines; i++) {
                this.getBoard()[i][this.agent.getPosition().getColumn()].setVisible(true);
            }

            this.agent.items.remove(result.get());
        }
    }

    public void revealTilesLeft() {
        Optional<Item> result = this.agent.items.stream().filter(item -> item instanceof Flashlight).findAny();

        if (result.isPresent()) {
            for (int i = this.agent.getPosition().getColumn(); i >= 0; i--) {
                this.getBoard()[this.agent.getPosition().getLine()][i].setVisible(true);
            }

            this.agent.items.remove(result.get());
        }
    }

    public void revealTilesRight() {
        Optional<Item> result = this.agent.items.stream().filter(item -> item instanceof Flashlight).findAny();

        if (result.isPresent()) {
            for (int i = this.agent.getPosition().getColumn(); i < this.columns; i++) {
                this.getBoard()[this.agent.getPosition().getLine()][i].setVisible(true);
            }

            this.agent.items.remove(result.get());
        }
    }
}
