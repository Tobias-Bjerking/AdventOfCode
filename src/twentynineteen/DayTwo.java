package twentynineteen;

import java.util.List;

import static shared.Input.readAsCSVInt;
import static shared.Input.readAsIntList;
import static shared.Utils.print;

public class DayTwo {

    private static int partOne(IntCodeComputer computer){
        computer.run(12, 2);
        return computer.memoryDump(0);
    }

    private static int partTwo(IntCodeComputer computer, List<Integer> program){
        int target = 19690720;
        for (int noun = 0; noun < 100; noun++) {
            for (int verb = 0; verb < 100; verb++) {
                computer.loadProgram(program);
                computer.run(noun, verb);
                if (computer.memoryDump(0) == target)
                    return 100 * noun + verb;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        List<Integer> input = readAsCSVInt("src\\twentynineteen\\input\\DayTwo.txt");
        IntCodeComputer computer = new IntCodeComputer();
        computer.loadProgram(input);
        print(partOne(computer));
        print(partTwo(computer, input));
    }
}