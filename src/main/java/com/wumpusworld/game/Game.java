package com.wumpusworld.game;

public class Game {
    private enum Direction { UP, DOWN, LEFT, RIGHT }

    int rounds = 0;
    Board board = new Board();
    Agent agent = new Agent();

    final String greetings = "Olá, seja bem vindo ao Mundo de Wumpus, selecione uma opção para começar a jogar!";

    void moveAgent(Direction d) {
        switch (d) {
            case UP:
                if (agent.position.line == Board.LINES - 1)
                    throw new Error("O agente já se encontra no extremo norte do mapa");
                else agent.position.line++;
                break;
            case DOWN:
                if (agent.position.line == 0)
                    throw new Error("O agente já se encontra no extremo sul do mapa");
                else agent.position.line++;
                break;
            case LEFT:
                if (agent.position.column == 0)
                    throw new Error("O agente já se encontra no extremo oeste do mapa");
                break;
            case RIGHT:
                if (agent.position.column == Board.COLUMNS - 1)
                    throw new Error("O agente já se encontra no extremo leste do mapa");
                break;
        }
    }
}
