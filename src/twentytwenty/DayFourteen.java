package twentytwenty;

import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

import static shared.Input.readRaw;
import static shared.Utils.print;

public class DayFourteen {

    private static List<Character> charList(String s) {
        List<Character> newList = new ArrayList<>(s.length());
        s.chars().forEach(c -> newList.add((char) c));
        return newList;
    }

    private static BigInteger partOne(String[] input) {
        HashMap<Integer, BigInteger> mem = new HashMap<>();
        for (String s : input) {
            String mask = s.split("\n")[0].split("= ")[1];
            for (String v : Arrays.asList(s.split("\n")).subList(1, s.split("\n").length)) {
                int address = Integer.parseInt(v.split(" = ")[0].substring(4).split("]")[0]);
                mem.put(address, applyValueMask(mask, v.split(" = ")[1]));
            }
        }
        return mem.values().stream().reduce(BigInteger::add).get();
    }

    private static BigInteger applyValueMask(String mask, String value) {
        List<Character> bits = charList(Integer.toBinaryString(Integer.parseInt(value)));
        List<Character> bitmask = charList(mask);
        Collections.reverse(bits);
        Collections.reverse(bitmask);
        List<Character> newValues = new ArrayList<>(Math.max(bits.size(), bitmask.size()));
        for (int i = 0; i < Math.max(bits.size(), bitmask.size()); i++) {
            if (bitmask.size()>i && bitmask.get(i) != 'X')
                newValues.add(bitmask.get(i));
            else if (bits.size()>i)
                newValues.add(bits.get(i));
            else
                newValues.add('0');
        }
        Collections.reverse(newValues);
        return new BigInteger(newValues.stream().map(String::valueOf).collect(Collectors.joining("", "", "")), 2);
    }


    private static BigInteger partTwo(String[] input) {
        HashMap<BigInteger, BigInteger> mem = new HashMap<>();
        for (String s : input) {
            String mask = s.split("\n")[0].split("= ")[1];
            for (String v : Arrays.asList(s.split("\n")).subList(1, s.split("\n").length)) {
                String address = v.split(" = ")[0].substring(4).split("]")[0];
                //applyMemoryMask(mask, address).stream().forEach(System.out::println);
                applyMemoryMask(mask, address).stream().forEach(maskedMem -> mem.put(maskedMem, new BigInteger(v.split(" = ")[1])));
            }
        }
        return mem.values().stream().reduce(BigInteger::add).get();
    }

    private static List<BigInteger> applyMemoryMask(String mask, String address) {
        List<Character> bits = charList(Integer.toBinaryString(Integer.parseInt(address)));
        List<Character> bitmask = charList(mask);
        Collections.reverse(bits);
        Collections.reverse(bitmask);
        List<Character> newValues = new ArrayList<>(Math.max(bits.size(), bitmask.size()));
        for (int i = 0; i < Math.max(bits.size(), bitmask.size()); i++) {
            if (bitmask.size()>i && bitmask.get(i) != '0')
                newValues.add(bitmask.get(i));
            else if (bits.size()>i)
                newValues.add(bits.get(i));
            else
                newValues.add('0');
        }

        Collections.reverse(newValues);

        int floats = (int) newValues.stream().filter(character -> character.equals('X')).count();
        List<BigInteger> bigInts = new ArrayList<>();

        for (int i = 0; i < Math.pow(2, floats); i++) {
            List<Character> newnewValues = new ArrayList<>();
            String binaryString = Integer.toBinaryString(i);
            String s = "";
            for (int x = 0; x < floats-binaryString.length(); x++)
                s = s.concat("0");
            binaryString = s.concat(binaryString);
            for (char c: newValues) {
                if (c == 'X') {
                    newnewValues.add(binaryString.charAt(0));
                    binaryString = binaryString.substring(1);
                } else
                    newnewValues.add(c);
            }
            bigInts.add(new BigInteger(newnewValues.stream().map(Object::toString).reduce((acc, e) -> acc  + e).get()));
        }

        return bigInts;
    }

    public static void main(String[] args) {
        String[] input = readRaw("src\\twentytwenty\\input\\DayFourteen.txt").split("(?=mask)");
        print(partOne(input).toString());
        print(partTwo(input).toString());
    }
}