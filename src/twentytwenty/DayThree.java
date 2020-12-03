package twentytwenty;

import java.util.List;

import static shared.Input.readAsList;
import static shared.Utils.print;

public class DayThree {
    static Position position = new Position();

    private static int partOne(String[][] map, int x, int y) {
        position.reset();
        int trees = 0;

        while (position.getY() < map[0].length)
            if (travel(map ,x, y))
                trees++;
        return trees;
    }

    private static int partTwo() {
        return -1;
    }

    private static boolean travel(String[][] map, int x, int y) {
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

        print(partOne(map, 3, 1));
//        print(partTwo(input));
    }

    static class Position {
        private int x;
        private int y;

        public Position() {}

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public void reset() {
            this.x = 0;
            this.y = 0;
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