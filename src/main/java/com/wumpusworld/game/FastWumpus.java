package com.wumpusworld.game;

public class FastWumpus extends Moveable{
    private boolean dead = false;

    FastWumpus(Position position) {
        super(position);
    }

    public boolean isDead() {
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }

    @Override
    protected void moveUp() {
        super.moveUp();
        super.moveUp();
        super.moveRight();
    }

    @Override
    protected void moveDown() {
        super.moveDown();
        super.moveDown();
        super.moveLeft();
    }

    @Override
    protected void moveRight() {
        super.moveRight();
        super.moveRight();
        super.moveDown();
    }

    @Override
    protected void moveLeft() {
        super.moveLeft();
        super.moveLeft();
        super.moveUp();
    }
}
