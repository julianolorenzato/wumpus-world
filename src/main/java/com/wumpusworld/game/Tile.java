package com.wumpusworld.game;

public class Tile {
    private boolean visible = false;
    private boolean breeze = false;
    private boolean badSmell = false;
    private Pit pit;
    private Wood wood;
    private Gold gold;

    Tile() {
    }

    Tile(Pit pit) {
        this.pit = pit;
    }

    Tile(Gold gold) {
        this.gold = gold;
    }

    Tile(Wood wood) {
        this.wood = wood;
    }

    public Pit getPit() {
        return pit;
    }

    public Wood getWood() {
        return wood;
    }

    public Gold getGold() {
        return gold;
    }

    public boolean isVisible() {
        return visible;
    }

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

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public void removeGold() {
        this.gold = null;
    }

    public void removeWood() {
        this.wood = null;
    }

    void closePit() {
        if (this.pit == null) {
            throw new Error("Não há poço para ser fechado");
        } else {
            this.pit.close();
        }
    }
}
