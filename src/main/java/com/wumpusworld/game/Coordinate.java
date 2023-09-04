package com.wumpusworld.game;

import java.util.Random;

public class Coordinate {
    int line;
    int column;

    Coordinate() {
        int l, c;

        do {
            l = new Random().nextInt(Board.LINES);
            c = new Random().nextInt(Board.COLUMNS);

            // Avoid coordinate(0,0)
        } while (l == 0 && c == 0);

        this.line = l;
        this.column = c;
    }

    Coordinate(int line, int column) {
        this.line = line;
        this.column = column;
    }
}
