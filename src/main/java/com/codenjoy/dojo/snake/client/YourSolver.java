package com.codenjoy.dojo.snake.client;

/*-
 * #%L
 * Codenjoy - it's a dojo-like platform from developers to developers.
 * %%
 * Copyright (C) 2018 Codenjoy
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 * #L%
 */


import com.codenjoy.dojo.client.Solver;
import com.codenjoy.dojo.client.WebSocketRunner;
import com.codenjoy.dojo.services.Dice;
import com.codenjoy.dojo.services.Direction;
import com.codenjoy.dojo.services.Point;
import com.codenjoy.dojo.services.RandomDice;
import java.util.List;

/**
 * User: your name
 */
public class YourSolver implements Solver<Board> {

    private Dice dice;
    private Board board;

    public YourSolver(Dice dice) {
        this.dice = dice;
    }

    @Override
    public String get(Board board) {
        this.board = board;
        System.out.println(board.toString());
        return resolver(board);
    }

    private String resolver(Board board) {
        String UP = Direction.UP.toString();
        String DOWN = Direction.DOWN.toString();
        String LEFT = Direction.LEFT.toString();
        String RIGHT = Direction.RIGHT.toString();
        Point apple = board.getApples().get(0);
        Point head = board.getHead();
        List<Point> snake = board.getSnake();
        Direction direction = board.getSnakeDirection();//apple[9,10] head[7,5]
        if (apple.getY() > head.getY() && apple.getX() == head.getX()) {
            return UP;
        } else if (apple.getY() == head.getY() && apple.getX() > head.getX()) {
            return RIGHT;
        } else if (apple.getY() < head.getY() && apple.getX() == head.getX()) {
            return DOWN;
        } else if (apple.getY() == head.getY() && apple.getX() < head.getX()) {
            return LEFT;
        }
        else if (apple.getY() > head.getY() && apple.getX() < head.getX()) {
            return UP;
        }
        else if (apple.getY() > head.getY() && apple.getX() > head.getX()) {
            return UP;
        } else if (apple.getY() < head.getY() && apple.getX() > head.getX()) {
            return DOWN;
        } else if (apple.getY() < head.getY() && apple.getX() < head.getX()) {
            return DOWN;
        }
    }

    public static void main(String[] args) {
        WebSocketRunner.runClient(
                // paste here board page url from browser after registration
                "http://159.89.27.106/codenjoy-contest/board/player/z1gx77hfnqv649gj7zjm?code=3486469516435015618",
                new YourSolver(new RandomDice()),
                new Board());
    }

}
