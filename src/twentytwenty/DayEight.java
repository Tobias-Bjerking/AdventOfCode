package twentytwenty;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static shared.Input.readAsList;
import static shared.Utils.print;

public class DayEight {

    private static int partOne(List<Instruction> instructions) {
        Set<Integer> visitedIndex = new HashSet<>();
        int total = 0;
        int i = 0;
        while (i < instructions.size()) {
            if (visitedIndex.contains(i))
                return total;
            visitedIndex.add(i);
            switch (instructions.get(i).instruction) {
                case "acc":
                    total += instructions.get(i).value;
                    i++;
                    break;
                case "jmp":
                    i += instructions.get(i).value;
                    break;
                case "nop":
                    i++;
                    break;
            }
        }
        return total;
    }

    private static int partTwo(List<Instruction> instructions) {
        for (int i = 0; i < instructions.size(); i++) {
            List<Instruction> newInstructions = new ArrayList<>(instructions);
            newInstructions.set(i, swap(newInstructions.get(i)));
            if (testInstructions(newInstructions))
                return partOne(newInstructions);
        }
        return -1;
    }

    private static Instruction swap(Instruction instruction) {
            if (instruction.instruction.equals("jmp"))
                return new Instruction("nop", instruction.value);
            else if (instruction.instruction.equals("nop"))
                return new Instruction("jmp", instruction.value);
            return instruction;
    }

    private static boolean testInstructions(List<Instruction> instructions) {
        Set<Integer> visitedIndex = new HashSet<>();
        int i = 0;
        while (i < instructions.size()) {
            if (visitedIndex.contains(i))
                return false;
            visitedIndex.add(i);
            switch (instructions.get(i).instruction) {
                case "jmp":
                    i += instructions.get(i).value;
                    break;
                case "acc":
                case "nop":
                    i++;
                    break;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        List<Instruction> instructions = readAsList("src\\twentytwenty\\input\\DayEight.txt").stream()
                .map(s -> new Instruction(s.split(" ")[0], Integer.parseInt(s.split(" ")[1])))
                .collect(Collectors.toList());
        print(partOne(instructions));
        print(partTwo(instructions));
    }

    static class Instruction {
        String instruction;
        int value;

        public Instruction(String instruction, int value) {
            this.instruction = instruction;
            this.value = value;
        }
    }
}