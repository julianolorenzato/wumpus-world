package com.wumpusworld.game;

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

    private Tile[][] fillBoard() {
        Tile[][] tiles = new Tile[this.lines][this.lines];

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

    void moveAgentUp() {
        if (agent.getPosition().getLine() == this.lines - 1) {
            throw new Error("O agente já se encontra no extremo norte do mapa");
        }

        this.agent.moveUp();
    }

    void moveAgentDown() {
        if (agent.getPosition().getLine() == 0) {
            throw new Error("O agente já se encontra no extremo sul do mapa");
        }

        this.agent.moveDown();
    }

    void moveAgentRight() {
        if (agent.getPosition().getLine() == this.columns - 1) {
            throw new Error("O agente já se encontra no extremo leste do sul");
        }

        this.agent.moveRight();
    }

    void moveAgentLeft() {
        if (agent.getPosition().getLine() == 0) {
            throw new Error("O agente já se encontra no extremo oeste do sul");
        }

        this.agent.moveLeft();
    }
}
