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

    private static final String UP = Direction.UP.toString();
    private static final String DOWN = Direction.DOWN.toString();
    private static final String LEFT = Direction.LEFT.toString();
    private static final String RIGHT = Direction.RIGHT.toString();
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
        Point apple = board.getApples().get(0);
        Point stone = board.getStones().get(0);
        Point head = board.getHead();
        List<Point> snake = board.getSnake();
        Direction direction = board.getSnakeDirection();//apple[12,10] head[9,5]
        if (head != null) {
            if (apple.getY() < head.getY() && apple.getX() == head.getX()
                    || (apple.getY() < head.getY() && apple.getX() > head.getX())
                    || (apple.getY() < head.getY() && apple.getX() < head.getX())) {
                //return DOWN;
                return stoneCheck(DOWN, stone, head, snake);
            }
            if ((apple.getY() > head.getY() && apple.getX() == head.getX())
                    || (apple.getY() > head.getY() && apple.getX() > head.getX())
                    || (apple.getY() > head.getY() && apple.getX() < head.getX())) {
                return stoneCheck(UP, stone, head, snake);
            }
            if (apple.getY() == head.getY() && apple.getX() > head.getX()) {
                //return RIGHT;
                return stoneCheck(RIGHT, stone, head, snake);
            }
            if (apple.getY() == head.getY() && apple.getX() < head.getX()) {
                //return LEFT;
                return stoneCheck(LEFT, stone, head, snake);
            }
//            } else if (apple.getY() > head.getY() && apple.getX() < head.getX()) {
//                //return UP;
//                return stoneCheck(UP, stone, head, snake);
//            } else if (apple.getY() > head.getY() && apple.getX() > head.getX()) {
//                //return UP;
//                return stoneCheck(UP, stone, head, snake);
//            } else if (apple.getY() < head.getY() && apple.getX() > head.getX()) {
//                //return DOWN;
//                return stoneCheck(DOWN, stone, head, snake);
//            } else if (apple.getY() < head.getY() && apple.getX() < head.getX()) {
//                //return DOWN;
//                return stoneCheck(DOWN, stone, head, snake);
        }
        return UP;
    }

    private String stoneCheck(String direction, Point stone, Point head, List<Point> snake) {
        if (UP.equals(direction)) {
            return (stone.getY() == head.getY() + 1) && stone.getX() == head.getX() ? LEFT : direction;
        } else if (DOWN.equals(direction)) {
            return (stone.getY() == head.getY() - 1) && stone.getX() == head.getX() ? LEFT : direction;
        } else if (LEFT.equals(direction)) {
            return (stone.getX() == head.getX() - 1) && stone.getY() == head.getY() ? DOWN : direction;
        } else if (RIGHT.equals(direction)) {
            return (stone.getX() == head.getX() + 1) && stone.getY() == head.getY() ? DOWN : direction;
        }
        return direction;
    }

    public static void main(String[] args) {
        WebSocketRunner.runClient(
                // paste here board page url from browser after registration
                "http://159.89.27.106/codenjoy-contest/board/player/z1gx77hfnqv649gj7zjm?code=3486469516435015618",
                new YourSolver(new RandomDice()),
                new Board());
    }
}

