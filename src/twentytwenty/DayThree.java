package twentytwenty;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

import static shared.Input.readAsCharMatrix;
import static shared.Utils.*;

public class DayThree {

    private static int partOne(char[][] map, Position direction) {
    Position position = new Position(0, 0);
        int trees = 0;
        while (position.getY() < map[0].length)
            if (travel(map, position, direction.getX(), direction.getY()))
                trees++;
        return trees;
    }

    private static String partTwo(char[][] map) {
        List<Position> directions = Arrays.asList(new Position(1, 1), new Position(3, 1), new Position(5, 1), new Position(7, 1), new Position(1, 2));
        return directions.stream()
                .map(dir -> new BigInteger(String.valueOf(partOne(map, dir))))
                .reduce(BigInteger::multiply)
                .orElseThrow(NullPointerException::new)
                .toString();
    }

    private static boolean travel(char[][] map, Position position, int x, int y) {
        position.setX((position.getX() + x) % map.length);
        position.setY(position.getY() + y);
        return position.getY() < map[0].length && map[position.getX()][position.getY()] == '#';
    }

    public static void main(String[] args) {
        char[][] map = readAsCharMatrix("src\\twentytwenty\\input\\DayThree.txt");

        print(partOne(map, new Position(3, 1)));
        print(partTwo(map));
    }

    static class Position {
        private int x;
        private int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }

        @Override
        public String toString() {
            return "Position{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }
}