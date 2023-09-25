package com.wumpusworld.game;

import java.util.Random;

public class Wumpus extends Moveable {
    private boolean dead;

    Wumpus(Position position) {
        super(position);
    }

    public boolean isDead() {
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }
}
