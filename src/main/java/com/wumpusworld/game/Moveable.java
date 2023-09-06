package com.wumpusworld.game;

abstract public class Moveable {
    protected Position position;

    Moveable(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    protected void moveUp() {
        this.position.toUp();
    }

    protected void moveDown() {
        this.position.toDown();
    }

    protected void moveRight() {
        this.position.toRight();
    }

    protected void moveLeft() {
        this.position.toLeft();
    }
}
