package com.wumpusworld.game;

import java.util.Random;

public class Position {
    private int line;
    private int column;

    private Position(int line, int column) {
        this.line = line;
        this.column = column;
    }

    public int getLine() {
        return line;
    }

    public int getColumn() {
        return column;
    }

    void toUp() {
        this.line--;
    }

    void toDown() {
        this.line++;
    }

    void toRight() {
        this.column++;
    }

    void toLeft() {
        this.column--;
    }

    public static Position randomPosition(int maxLine, int maxCol) {
        int line, column;

        do {
            line = new Random().nextInt(maxLine);
            column = new Random().nextInt(maxCol);

            // Avoid coordinate(14,0)
        } while (line == 14 && column == 0);

        return new Position(line, column);
    }

    static Position originPosition() {
        return new Position(14, 0);
    }

    public boolean samePosition(Position pos) {
        return this.line == pos.line && this.column == pos.column;
    }
}
