package com.wumpusworld.game;

public class Board {
    static final int LINES = 15;
    static final int COLUMNS = 15;

    Tile[][] tiles = this.fillBoard();
    Coordinate wumpusPosition = new Coordinate();

    private Tile[][] fillBoard() {
        Tile[][] tiles = new Tile[Board.LINES][Board.COLUMNS];

        for (int i = 0; i <  Board.LINES; i++) {
            for (int j = 0; j < Board.COLUMNS; j++) {
                tiles[i][j] = new Tile();
            }
        }

        return tiles;
    }
}
