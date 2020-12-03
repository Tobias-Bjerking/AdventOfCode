package twentytwenty;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

import static shared.Input.readAsList;
import static shared.Utils.*;

public class DayThree {

    private static int partOne(String[][] map, Position direction) {
    Position position = new Position(0, 0);
        int trees = 0;
        while (position.getY() < map[0].length)
            if (travel(map, position, direction.getX(), direction.getY()))
                trees++;
        return trees;
    }

    private static String partTwo(String[][] map) {
        List<Position> directions = Arrays.asList(new Position(1, 1), new Position(3, 1), new Position(5, 1), new Position(7, 1), new Position(1, 2));
        return directions.stream()
                .map(dir -> new BigInteger(String.valueOf(partOne(map, dir))))
                .reduce(BigInteger::multiply)
                .orElseThrow(NullPointerException::new)
                .toString();
    }

    private static boolean travel(String[][] map, Position position, int x, int y) {
        position.setX((position.getX() + x) % map.length);
        position.setY(position.getY() + y);
        return position.getY() < map[0].length && map[position.getX()][position.getY()].equals("#");
    }

    private static String[][] createMap(List<String> strings) {
        int width = strings.get(0).length();
        int height = strings.size();
        String[][] map = new String[width][height];
        for (int w = 0; w < width; w++)
            for (int h = 0; h < height; h++)
                map[w][h] = String.valueOf(strings.get(h).charAt(w));
        return map;
    }

    public static void main(String[] args) {
        List<String> strings = readAsList("src\\twentytwenty\\DayThree.txt");
        String[][] map = createMap(strings);

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