package com.wumpusworld.game;

import java.util.*;

public class Game {
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

    public Wumpus getWumpus() {
        return wumpus;
    }

    public FastWumpus getFastWumpus() {
        return fastWumpus;
    }

    public Agent getAgent() {
        return agent;
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

        // put breeze
        for (int i = 0; i < 5; i++) {
            int line = coordinates.get("x")[i];
            int col = coordinates.get("y")[i];

            try {
                tiles[line + 1][col].setBreeze(true);
            } catch (Exception e) {
            }
            try {
                tiles[line][col + 1].setBreeze(true);
            } catch (Exception e) {
            }
            try {
                tiles[line - 1][col].setBreeze(true);
            } catch (Exception e) {
            }
            try {
                tiles[line][col - 1].setBreeze(true);
            } catch (Exception e) {
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

    public boolean isGameOver() {
        return this.agent.life <= 0;
    }

    public boolean isWin() {
        boolean haveGoldNugget = this.agent.items.stream().anyMatch(item -> item instanceof GoldNugget);
        Position agentPosition = this.agent.getPosition();

        return agentPosition.getLine() == this.lines - 1 && agentPosition.getColumn() == 0 && haveGoldNugget;
    }

    public void moveAgentUp() {
        if (agent.getPosition().getLine() == 0) {
            throw new Error("O agente já se encontra no extremo norte do mapa");
        }

        int agentLine = this.agent.getPosition().getLine();
        int agentCol = this.agent.getPosition().getColumn();

        Tile nextTile = this.board[agentLine - 1][agentCol];

        if (nextTile.getPit() != null && nextTile.getPit().isOpen()) {
            throw new Error("Existe um poço no caminho, feche-o ou o contorne");
        }

        this.agent.moveUp();
        this.tryToPick();
        this.randomMoveWumpus();
        this.randomMoveFastWumpus();
        this.getBoard()[this.agent.getPosition().getLine()][this.agent.getPosition().getColumn()].setVisible(true);
    }

    public void moveAgentDown() {
        if (agent.getPosition().getLine() == this.lines - 1) {
            throw new Error("O agente já se encontra no extremo sul do mapa");
        }

        int agentLine = this.agent.getPosition().getLine();
        int agentCol = this.agent.getPosition().getColumn();

        Tile nextTile = this.board[agentLine + 1][agentCol];

        if (nextTile.getPit() != null && nextTile.getPit().isOpen()) {
            throw new Error("Existe um poço no caminho, feche-o ou o contorne");
        }

        this.agent.moveDown();
        this.tryToPick();
        this.randomMoveWumpus();
        this.randomMoveFastWumpus();
        this.getBoard()[this.agent.getPosition().getLine()][this.agent.getPosition().getColumn()].setVisible(true);
    }

    public void moveAgentRight() {
        if (agent.getPosition().getColumn() == this.columns - 1) {
            throw new Error("O agente já se encontra no extremo leste do mapa");
        }

        int agentLine = this.agent.getPosition().getLine();
        int agentCol = this.agent.getPosition().getColumn();

        Tile nextTile = this.board[agentLine][agentCol + 1];

        if (nextTile.getPit() != null && nextTile.getPit().isOpen()) {
            throw new Error("Existe um poço no caminho, feche-o ou o contorne");
        }

        this.agent.moveRight();
        this.tryToPick();
        this.randomMoveWumpus();
        this.randomMoveFastWumpus();
        this.getBoard()[this.agent.getPosition().getLine()][this.agent.getPosition().getColumn()].setVisible(true);
    }

    public void moveAgentLeft() {
        if (agent.getPosition().getColumn() == 0) {
            throw new Error("O agente já se encontra no extremo oeste do mapa");
        }

        int agentLine = this.agent.getPosition().getLine();
        int agentCol = this.agent.getPosition().getColumn();

        Tile nextTile = this.board[agentLine][agentCol - 1];

        if (nextTile.getPit() != null && nextTile.getPit().isOpen()) {
            throw new Error("Existe um poço no caminho, feche-o ou o contorne");
        }

        this.agent.moveLeft();
        this.tryToPick();
        this.randomMoveWumpus();
        this.randomMoveFastWumpus();
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

    private void resetBadSmell() {
        for (Tile[] tilesLine : this.board) {
            for (Tile tile : tilesLine) {
                tile.setBadSmell(false);
            }
        }
    }

    private void randomMoveWumpus() {
        int side = new Random().nextInt(4);

        switch (side) {
            case 0:
                if (this.wumpus.getPosition().getLine() != 0)
                    this.wumpus.moveUp();
                break;
            case 1:
                if (this.wumpus.getPosition().getLine() != this.lines - 1)
                    this.wumpus.moveDown();
                break;
            case 2:
                if (this.wumpus.getPosition().getColumn() != 0)
                    this.wumpus.moveLeft();
                break;
            case 3:
                if (this.wumpus.getPosition().getLine() != this.columns - 1)
                    this.wumpus.moveRight();
                break;
        }

        this.resetBadSmell();

        int wumpusLine = this.fastWumpus.getPosition().getLine();
        int wumpusCol = this.fastWumpus.getPosition().getColumn();

        try {
            this.board[wumpusLine + 1][wumpusCol].setBadSmell(true);
            this.board[wumpusLine][wumpusCol + 1].setBadSmell(true);
            this.board[wumpusLine - 1][wumpusCol].setBadSmell(true);
            this.board[wumpusLine][wumpusCol - 1].setBadSmell(true);
        } catch (Exception e) {
        }

        if (this.agent.getPosition().samePosition(this.wumpus.getPosition()) && !wumpus.isDead()) {
            this.agent.life -= 100;
        }
    }

    private void randomMoveFastWumpus() {
        int side = new Random().nextInt(4);
        int fastWumpusLine = this.fastWumpus.getPosition().getLine();
        int fastWumpusCol = this.fastWumpus.getPosition().getColumn();

        switch (side) {
            case 0:
                if (fastWumpusLine > 1 && fastWumpusCol != this.columns - 1)
                    this.fastWumpus.moveUp();
                break;
            case 1:
                if (fastWumpusLine < this.lines - 2 && fastWumpusCol != 0)
                    this.fastWumpus.moveDown();
                break;
            case 2:
                if (fastWumpusLine != 0 && fastWumpusCol > 1)
                    this.fastWumpus.moveLeft();
                break;
            case 3:
                if (fastWumpusLine != this.lines - 1 && fastWumpusCol < this.columns - 2)
                    this.fastWumpus.moveRight();
        }

        this.resetBadSmell();

        fastWumpusLine = this.fastWumpus.getPosition().getLine();
        fastWumpusCol = this.fastWumpus.getPosition().getColumn();

        try {
            this.board[fastWumpusLine + 1][fastWumpusCol].setBadSmell(true);
            this.board[fastWumpusLine][fastWumpusCol + 1].setBadSmell(true);
            this.board[fastWumpusLine - 1][fastWumpusCol].setBadSmell(true);
            this.board[fastWumpusLine][fastWumpusCol - 1].setBadSmell(true);
        } catch (Exception e) {
        }

        if (this.agent.getPosition().samePosition(this.fastWumpus.getPosition()) && !fastWumpus.isDead()) {
            this.agent.life -= 50;
        }
    }

    public void closePit() {
        int agentLine = this.agent.getPosition().getLine();
        int agentCol = this.agent.getPosition().getColumn();

        Optional<Item> result = this.agent.items.stream().filter(item -> item instanceof WoodPlank).findFirst();

        try {
            if (result.isPresent()) {
                if (this.board[agentLine + 1][agentCol].getPit() != null) {
                    this.board[agentLine + 1][agentCol].closePit();
                    this.agent.items.remove(result.get());
                } else if (this.board[agentLine][agentCol + 1].getPit() != null) {
                    this.board[agentLine][agentCol + 1].closePit();
                    this.agent.items.remove(result.get());
                } else if (this.board[agentLine - 1][agentCol].getPit() != null) {
                    this.board[agentLine - 1][agentCol].closePit();
                    this.agent.items.remove(result.get());
                } else if (this.board[agentLine][agentCol - 1].getPit() != null) {
                    this.agent.items.remove(result.get());
                }
            }
        } catch (Exception e) {
        }
    }

    public void makeArrow() {
        Optional<Item> result = this.agent.items.stream().filter(item -> item instanceof WoodPlank).findFirst();

        if (result.isPresent()) {
            this.agent.items.remove(result.get());
            this.agent.items.add(new Arrow());
        }
    }

    public void throwArrow(int direction) {
        int agentLine = this.agent.getPosition().getLine();
        int agentCol = this.agent.getPosition().getColumn();

        int wumpusLine = this.wumpus.getPosition().getLine();
        int wumpusCol = this.wumpus.getPosition().getColumn();

        int fastWumpusLine = this.fastWumpus.getPosition().getLine();
        int fastWumpusCol = this.fastWumpus.getPosition().getColumn();

        Optional<Item> result = this.agent.items.stream().filter(item -> item instanceof Arrow).findFirst();

        if (result.isPresent()) {
            this.agent.items.remove(result.get());

            switch (direction) {
                case 0:
                    if (agentLine - 1 == wumpusLine && agentCol == wumpusCol) {
                        wumpus.setDead(true);
                    }

                    if (agentLine - 1 == fastWumpusLine && agentCol == fastWumpusCol) {
                        fastWumpus.setDead(true);
                    }
                    break;
                case 1:
                    if (agentLine + 1 == wumpusLine && agentCol == wumpusCol) {
                        wumpus.setDead(true);
                    }

                    if (agentLine + 1 == fastWumpusLine && agentCol == fastWumpusCol) {
                        fastWumpus.setDead(true);
                    }
                    break;
                case 2:
                    if (agentLine == wumpusLine && agentCol - 1 == wumpusCol) {
                        wumpus.setDead(true);
                    }

                    if (agentLine == fastWumpusLine && agentCol - 1 == fastWumpusCol) {
                        fastWumpus.setDead(true);
                    }
                    break;
                case 3:
                    if (agentLine == wumpusLine && agentCol + 1 == wumpusCol) {
                        wumpus.setDead(true);
                    }

                    if (agentLine == fastWumpusLine && agentCol + 1 == fastWumpusCol) {
                        fastWumpus.setDead(true);
                    }
                    break;
            }
        }
    }
}
