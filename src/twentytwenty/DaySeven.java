package twentytwenty;

import java.util.*;

import static shared.Input.readAsList;
import static shared.Utils.print;

public class DaySeven {

    static Map<String, Bag> rules = new HashMap<>();

    private static int partOne() {
        return (int) rules.keySet().stream()
                .filter(bag -> bagContainsTarget(bag, "shiny gold bag"))
                .count() - 1;
    }

    private static int partTwo() {
        return getNumberOfBagsInside(rules.get("shiny gold bag"));
    }

    private static boolean bagContainsTarget(String bag, String target) {
        if (bag.equals(target))
            return true;
        if (rules.get(bag).isEmpty())
            return false;
        return rules.get(bag).getContains().keySet().stream()
                .anyMatch(b -> bagContainsTarget(b, target));
    }

    private static int getNumberOfBagsInside(Bag bag) {
        return bag.getContains().keySet().stream()
                .map(b -> (getNumberOfBagsInside(rules.get(b)) + 1) * bag.getContains().get(b))
                .reduce(Integer::sum)
                .orElse(0);
    }

    private static void parse(String rule) {
        String[] strings = rule.split(" contain ");
        String bagName = strings[0].replace("bags", "bag");
        rules.put(bagName, new Bag(bagName));
        if (!strings[1].equals("no other bags."))
            Arrays.stream(strings[1].split(", "))
                    .forEach(bag -> rules.get(bagName).addBag(bag.replace("bags", "bag")));
    }

    public static void main(String[] args) {
        List<String> input = readAsList("src\\twentytwenty\\input\\DaySeven.txt");
        input.forEach(DaySeven::parse);
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