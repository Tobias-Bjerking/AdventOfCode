package twentytwenty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static shared.Input.readAsList;
import static shared.Utils.print;

public class DayFive {

    private static int calculateID(Seat seat) { return seat.getRow() * 8 + seat.getColumn(); }

    private static int partOne(List<String> input){
        int highestID = -1;
        for (String s : input) {
            Seat seat = parse(s);
            int id = calculateID(seat);
            if (id > highestID)
                highestID = id;
        }
        return highestID;
    }

    private static int partTwo(List<String> input){
        HashMap<Integer, ArrayList<Seat>> rows = new HashMap<>();

        for (String s : input) {
            Seat seat = parse(s);
            List<Seat> row = rows.computeIfAbsent(seat.getRow(), k -> new ArrayList<>());
            row.add(seat);
        }

        List<Seat> incompleteRow = null;
        for (ArrayList<Seat> row : rows.values())
            if (row.size() == 7)
                incompleteRow = row;

        assert incompleteRow != null;
        int total = (incompleteRow.size() + 1) * (incompleteRow.size() + 2) / 2;
        for (Seat seat : incompleteRow)
            total -= seat.getColumn() + 1;

        return calculateID(new Seat(incompleteRow.get(0).getRow(), total - 1));
    }

    private static Seat parse(String boardingCode) {
        int row = 0;
        int column = 0;
        for(int i = 0; i < boardingCode.length(); i++) {
            char c = boardingCode.charAt(boardingCode.length()-i-1);
            if (c == 'B' || c == 'F') {
                row += c == 'B' ? Math.pow(2, i-3)  : 0;
            }
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