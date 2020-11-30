package templates;

import java.io.IOException;
import java.util.List;

import static shared.Input.*;
import static shared.Utils.*;

public class Template {

    private static void partOne(){
        return;
    }

    private static void partTwo(){
        return;
    }

    public static void main(String[] args) throws IOException {
        String input = readRaw("src\\twentynineteen\\DayOne.txt");
        List<Integer> input = readAsIntList("src\\twentynineteen\\DayOne.txt");
        print(partOne(input));
        print(partTwo(input));

    }
}