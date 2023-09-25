package com.wumpusworld.game;

public class Pit {
    private boolean open = true;

    public boolean isOpen() {
        return open;
    }

    void close() {
        this.open = false;
    }
}
