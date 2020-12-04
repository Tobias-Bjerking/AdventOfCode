package shared;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Input {

    public static String readRaw(String path) {
        String raw = "";
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(path));
            String line = reader.readLine();
            while (line != null) {
                raw = raw.concat(line).concat("\n");
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) { e.printStackTrace(); }
        return raw;
    }

    public static List<String> readAsList(String path) {
        List<String> list = new ArrayList<>();
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(path));
            String line = reader.readLine();
            while (line != null) {
                list.add(line);
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) { e.printStackTrace(); }
        return list;
    }

    public static List<String> readAsLineSeperatedList(String path) {
        List<String> strings = readAsList(path);
        List<String> newList = new ArrayList<>();
        String temp = "";
        for (String s : strings) {
            if (s.equals("")) {
                newList.add(temp);
                temp = "";
            } else
                temp = temp.concat(" ").concat(s);
        }
        newList.add(temp);
        return newList;
    }

    public static List<Integer> readAsIntList(String path) {
        List<Integer> list = new ArrayList<>();
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(path));
            String line = reader.readLine();
            while (line != null) {
                list.add(Integer.parseInt(line));
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) { e.printStackTrace(); }
        return list;
    }

    public static char[][] readAsCharMatrix(String path) {
        List<String> list = readAsList(path);
        char[][] matrix = new char[list.get(0).length()][list.size()];

        for (int y = 0; y < list.size(); y++)
            for (int x = 0; x < list.get(0).length(); x++)
                matrix[x][y] = list.get(y).charAt(x);
        return matrix;
    }
}
