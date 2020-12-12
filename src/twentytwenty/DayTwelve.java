package twentytwenty;

import java.util.HashMap;
import java.util.List;

import static shared.Input.readAsIntList;
import static shared.Input.readAsList;
import static shared.Utils.print;

public class DayTwelve {
    enum Direction {
        N(0,1) {
            @Override
            public int degrees() { return 0; }
        },
        E(1,0){
            @Override
            public int degrees() { return 90; }
        },
        S(0,-1){
            @Override
            public int degrees() { return 180; }
        },
        W(-1,0){
            @Override
            public int degrees() { return 270; }
        };
    int x, y;
        Direction(int x, int y) {
            this.x = x;
            this.y = y;
        }
        public abstract int degrees();
    }

    private static int partOne(List<String> input) {
        Ship ship = new Ship(0,0, Direction.E);
        input.forEach(Ship::parse);
        return ship.manhattanDistance();
    }


    private static int partTwo(List<String> input) {
        ShipTwo ship = new ShipTwo(0,0, 10, 1);
        input.forEach(ShipTwo::parse);
        return ship.manhattanDistance();
    }

    public static void main(String[] args) {
        List<String> input = readAsList("src\\twentytwenty\\input\\DayTwelve.txt");
        print(partOne(input));
        print(partTwo(input));
    }

    private static class Ship {
        static int x, y, startX, startY;
        static Direction direction;

        public Ship(int x, int y, Direction direction) {
            Ship.x = startX = x;
            Ship.y = this.startY = y;
            Ship.direction = direction;
        }

        public static void forward(int distance) {
            x += direction.x * distance;
            y += direction.y * distance;
        }

        public static void moveDirection(Direction direction, int distance) {
            x += direction.x * distance;
            y += direction.y * distance;
        }

        public static void rotate(int degrees) {
            switch ((direction.degrees()+degrees + 360)%360) {
                case 0: direction = Direction.N; break;
                case 90: direction = Direction.E; break;
                case 180: direction = Direction.S; break;
                case 270: direction = Direction.W; break;
                default: throw new NumberFormatException("Wrong number of degrees: " + (direction.degrees()+degrees)%360);
            }
        }

        public int manhattanDistance() {
            return Math.abs(x-startX) + Math.abs(y-startY);
        }

        private static void parse(String i) throws EnumConstantNotPresentException {
            int value = Integer.parseInt(i.substring(1));
            switch (i.charAt(0)) {
                case 'F': forward(value); break;
                case 'N': moveDirection(Direction.N, value); break;
                case 'E': moveDirection(Direction.E, value); break;
                case 'S': moveDirection(Direction.S, value); break;
                case 'W': moveDirection(Direction.W, value); break;
                case 'R': rotate(value); break;
                case 'L': rotate(-value); break;
            }
        }
    }

    private static class ShipTwo {
        static int x, y, startX, startY, wX, wY;

        public ShipTwo(int x, int y, int wX, int wY) {
            ShipTwo.x = startX = x;
            ShipTwo.y = startY = y;
            ShipTwo.wX = wX;
            ShipTwo.wY = wY;
        }

        public static void forward(int distance) {
            x += wX * distance;
            y += wY * distance;
        }

        public static void rotate(int degrees) {
            int cX = wX;
            int cY = wY;

            switch (degrees/90) {
                case 1:
                case -3:
                    wX = cY;
                    wY = -cX;
                    break;
                case 2:
                case -2:
                    wX = -cX;
                    wY = -cY;
                    break;
                case 3:
                case -1:
                    wX = -cY;
                    wY = cX;
                    break;
            }
        }

        public int manhattanDistance() {
            return Math.abs(x-startX) + Math.abs(y-startY);
        }

        private static void parse(String i) throws EnumConstantNotPresentException {
            int value = Integer.parseInt(i.substring(1));
            switch (i.charAt(0)) {
                case 'F': forward(value); break;
                case 'N': wY += value; break;
                case 'E': wX += value; break;
                case 'S': wY -= value; break;
                case 'W': wX -= value; break;
                case 'R': rotate(value); break;
                case 'L': rotate(-value); break;
            }
        }
    }
}