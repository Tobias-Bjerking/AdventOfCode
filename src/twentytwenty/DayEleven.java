package twentytwenty;

import java.util.Arrays;
import java.util.List;

import static shared.Input.*;
import static shared.Utils.print;

public class DayEleven {

    static List<Direction> dirs = Arrays.asList(new Direction(-1,-1), new Direction(0, -1), new Direction(1, -1),
            new Direction(-1, 0), new Direction(1, 0),
            new Direction(-1,1), new Direction(0, 1), new Direction(1, 1));

    private static boolean goodSeat(int x, int y, char[][] map) {
        if (map[x][y] == '.')
            return false;
        int total = 0;
        for (Direction dir : dirs) {
            try {
            if (map[x+dir.x][y+dir.y] == '#')
                total++;
            } catch (IndexOutOfBoundsException e) {}
        }
        return total < 4;
    }

    private static boolean freeSeat(int x, int y, char[][] map) {
        if (map[x][y] == '.')
            return false;
        for (Direction dir : dirs) {
            try {
                if (map[x+dir.x][y+dir.y] == '#')
                    return false;
            } catch (IndexOutOfBoundsException e) {}
        }
        return true;
    }

    private static Long partOne(char[][] map) {
        char[][] newMap = clone(map);
        do {
            map = clone(newMap);
            for (int x = 0; x < map.length; x++) {
                for (int y = 0; y < map[x].length; y++) {
                    if (map[x][y] == 'L' && freeSeat(x, y, map))
                        newMap[x][y] = '#';
                    else if (map[x][y] != '.' && !goodSeat(x,y,map))
                        newMap[x][y] = 'L';
                }
            }
        } while (!match(map, newMap));

        return Arrays.stream(map)
                .map(cs -> new String(cs).chars().filter(c -> c == '#').count())
                .reduce(Long::sum)
                .orElse(-1L);
    }

    private static boolean goodSeatPartTwo(int x, int y, char[][] map) {
        if (map[x][y] == '.')
            return false;
        int total = 0;
        for (Direction dir: dirs) {
            boolean running = true;
            int dx = x;
            int dy = y;
            while (running) {
                try {
                    dx += dir.x;
                    dy += dir.y;
                    if (map[dx][dy] == '#') {
                        total++;
                        running = false;
                    }
                    else if (map[dx][dy] == 'L')
                        running = false;
                } catch (IndexOutOfBoundsException e) { running = false; }
            }
        }
        return total < 5;
    }

    private static boolean freeSeatPartTwo(int x, int y, char[][] map) {
        if (map[x][y] == '.')
            return false;

        for (Direction dir: dirs) {
            boolean running = true;
            int dx = x;
            int dy = y;
            while (running) {
                try {
                    dx += dir.x;
                    dy += dir.y;
                    if (map[dx][dy] == '#')
                        return false;
                    else if (map[dx][dy] == 'L')
                        running = false;
                } catch (IndexOutOfBoundsException e) { running = false; }
            }
        }
        return true;
    }


    private static Long partTwo(char[][] map) {
        char[][] newMap = clone(map);
        do {
            map = clone(newMap);
            for (int x = 0; x < map.length; x++) {
                for (int y = 0; y < map[x].length; y++) {
                    if (map[x][y] == 'L' && freeSeatPartTwo(x, y, map))
                        newMap[x][y] = '#';
                    else if (map[x][y] != '.' && !goodSeatPartTwo(x,y,map))
                        newMap[x][y] = 'L';
                }
            }
        } while (!match(map, newMap));

        return Arrays.stream(map)
                .map(cs -> new String(cs).chars().filter(c -> c == '#').count())
                .reduce(Long::sum)
                .orElse(-1L);
    }

    public static void main(String[] args) {
        char[][] input = readAsCharMatrix("src\\twentytwenty\\input\\DayEleven.txt");
        print(partOne(input));
        print(partTwo(input));
    }

    private static char[][] clone(char[][] map) {
        char [][] newMap = new char[map.length][map[0].length];
        for (int x = 0; x < map.length; x++)
            for (int y = 0; y < map[x].length; y++)
                newMap[x][y] = map[x][y];
        return newMap;
    }

    private static boolean match(char[][] map, char[][] newMap) {
        for (int x = 0; x < map.length; x++)
            for (int y = 0; y < map[x].length; y++)
                if (newMap[x][y] != map[x][y])
                    return false;
        return true;
    }

    static class Direction {
        int x, y;
        public Direction(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
