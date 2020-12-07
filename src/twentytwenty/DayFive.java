package twentytwenty;

import java.util.*;

import static shared.Input.readAsList;
import static shared.Utils.print;

public class DayFive {

    private static int calculateID(Seat seat) { return seat.getRow() * 8 + seat.getColumn(); }

    private static int partOne(List<String> input){
        return input.stream()
                .map(DayFive::parse)
                .map(DayFive::calculateID)
                .reduce(Math::max)
                .get();
    }

    private static int partTwo(List<String> input){
        HashMap<Integer, ArrayList<Seat>> rows = new HashMap<>();

        input.stream()
                .map(DayFive::parse)
                .forEach(s -> rows.computeIfAbsent(s.getRow(), k -> new ArrayList<>()).add(s));

        ArrayList<Seat> incompleteRow = rows.values().stream()
                .filter(row -> row.size() == 7)
                .findAny()
                .orElseThrow(NullPointerException::new);

        int total = (incompleteRow.size() + 1) * (incompleteRow.size() + 2) / 2;
        total -= incompleteRow.stream()
                .map(seat -> seat.getColumn() + 1)
                .reduce(Integer::sum)
                .get();

        return calculateID(new Seat(incompleteRow.get(0).getRow(), total - 1));
    }

    private static Seat parse(String boardingCode) {
        int row = 0;
        int column = 0;
        for(int i = 0; i < boardingCode.length(); i++) {
            char c = boardingCode.charAt(boardingCode.length()-i-1);
            if (c == 'B' || c == 'F')
                row += c == 'B' ? Math.pow(2, i-3)  : 0;
            else
                column += c == 'R' ? Math.pow(2, i) : 0;
        }
        return new Seat(row, column);
    }

    public static void main(String[] args) {
        List<String> input = readAsList("src\\twentytwenty\\input\\DayFive.txt");
        print(partOne(input));
        print(partTwo(input));
    }

    static class Seat {
        int row;
        int column;

        public Seat(int row, int column) {
            this.row = row;
            this.column = column;
        }

        public int getRow() { return row; }

        public int getColumn() { return column; }
    }
}