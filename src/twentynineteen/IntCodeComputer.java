package twentynineteen;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static shared.Utils.print;
import static twentynineteen.IntCodeComputer.Operation.*;


public class IntCodeComputer {

    enum Operation {ADD, MUL, EXIT}
    static HashMap<Integer, Operation> operations = new HashMap<>();

    private Memory memory;
    private int pointer;

    public IntCodeComputer() {
        operations.put(1, ADD);
        operations.put(2, MUL);
        operations.put(99, EXIT);
    }

    public void loadProgram(List<Integer> input) {
        this.memory = new Memory(input);
        this.pointer = 0;
    }

    public Memory memoryDump() {
        return memory;
    }

    public Integer memoryDump(int index) {
        return memory.get(index);
    }

    public void run() {
        while (execute());
    }

    public void debug() {
        execute();
        print(memory.toString());
        print(pointer);
    }

    private boolean execute() {
        switch (operations.get(memory.get(pointer))) {
            case ADD: add();
                break;
            case MUL: multiply();
                break;
            case EXIT:
                return false;
        }
        return true;
    }


    private void add() {
        int x = memory.get(memory.get(pointer + 1));
        int y = memory.get(memory.get(pointer + 2));
        memory.set(memory.get(pointer + 3), x + y);
        pointer += 4;
    }

    private void multiply() {
        int x = memory.get(memory.get(pointer + 1));
        int y = memory.get(memory.get(pointer + 2));
        memory.set(memory.get(pointer + 3), x * y);
        pointer += 4;
    }

    private static class Memory {
        private Integer[] memory;


        public Memory(List<Integer> input) {
            memory = new Integer[input.size()];
            memory = input.toArray(memory);
        }

        public int get(int index) {
                return memory[index];
        }

        public void set(int index, int value) {
            try {
                memory[index] = value;
            } catch (IndexOutOfBoundsException e) {
                Integer[] temp = this.memory;
                memory = new Integer[index];
                for (int i = 0; i <= index; i++) {
                    try {
                        memory[i] = temp[i];
                    } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
                        memory[i] = 0;
                    }
                }
            }
            memory[index] = value;
        }

        @Override
        public String toString() {
            return Arrays.toString(memory);
        }
    }
}
