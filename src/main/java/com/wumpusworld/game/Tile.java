package com.wumpusworld.game;

public class Tile {
    private boolean breeze = false;
    private boolean badSmell = false;

    private Pit pit;
    private Wood wood;
    private Gold gold;

    public boolean isBreeze() {
        return breeze;
    }

    public boolean isBadSmell() {
        return badSmell;
    }

    public void setBreeze(boolean breeze) {
        this.breeze = breeze;
    }
    public void setBadSmell(boolean badSmell) {
        this.badSmell = badSmell;
    }

    void closePit() {
        if (this.pit == null) {
            throw new Error("Não há poço para ser fechado");
        } else {
            this.pit.close();
        }
    }
}
