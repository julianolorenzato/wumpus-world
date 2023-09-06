package com.wumpusworld.game;

import java.util.HashSet;
import java.util.Set;

public class Agent extends Moveable {
    int life = 100;
    Set<Item> items = new HashSet<Item>();

    Agent() {
        super(Position.originPosition());
    }

    void addItem(Item item) {
        if (this.items.size() == 3) {
            throw new Error("O agente pode carregar no máximo 3 items");
        } else {
            this.items.add(item);
        }
    }

    void removeItem(Item item) {
        this.items.remove(item);
    }
}
