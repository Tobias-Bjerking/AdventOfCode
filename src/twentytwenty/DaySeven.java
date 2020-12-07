package twentytwenty;

import java.util.*;

import static shared.Input.readAsList;
import static shared.Utils.print;

public class DaySeven {

    static Map<String, Bag> rules = new HashMap<>();

    private static boolean getContainedBags(String bag, String target) {
        if (bag.equals(target))
            return true;
        if (!rules.containsKey(bag) || rules.get(bag).isEmpty())
            return false;
        Map<String, Integer> contains = rules.get(bag).getContains();
        for (String b: contains.keySet())
            if (getContainedBags(b, target))
                return true;
        return false;
    }

    private static int getNumberOfBags(String bag) {
        if (!rules.containsKey(bag) || rules.get(bag).isEmpty())
            return 0;

        int total = 0;
        Map<String, Integer> contains = rules.get(bag).getContains();
        for (String b: contains.keySet())
            for (int i = 0; i < contains.get(b); i++)
                total += getNumberOfBags(b) + 1;
        return total;
    }

    private static int partOne() {
        return (int) rules.keySet().stream()
                .filter(bag -> getContainedBags(bag, "shiny gold bag"))
                .count() - 1;
    }

    private static int partTwo() {
        return getNumberOfBags("shiny gold bag");
    }

    private static void parse(String rule) {
        String[] split = rule.split(" contain ");
        String bagName = split[0].replace("bags", "bag");
        rules.put(bagName, new Bag(bagName));
        if (!split[1].equals("no other bags.")) {
            String[] bags = split[1].split(", ");
            for (String bag : bags)
                rules.get(bagName).addBag(bag.replace("bags", "bag"));
        }
    }

    public static void main(String[] args) {
        List<String> input = readAsList("src\\twentytwenty\\input\\DaySeven.txt");
        for (String line : input)
            parse(line);
        print(partOne());
        print(partTwo());
    }

    static class Bag {
        String name;
        Map<String, Integer> contains;

        public Bag(String name) {
            this.name = name;
            contains = new HashMap<>();
        }

        public void addBag(String s) {
            String bag = s.substring(2).replace(".", "");
            int n = Integer.parseInt(s.split(" ")[0]);
            contains.put(bag, n);
        }

        public Map<String, Integer> getContains() { return contains; }

        public boolean isEmpty() { return contains.isEmpty(); }
    }
}