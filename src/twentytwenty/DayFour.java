package twentytwenty;

import java.util.*;
import java.util.stream.Collectors;

import static shared.Input.*;
import static shared.Utils.*;

public class DayFour {

    private static int partOne(List<Passport> passports) {
        return (int) passports.stream()
                .filter(Passport::validateCountry)
                .count();
    }

    private static int partTwo(List<Passport> passports) {
        return (int) passports.stream()
                .filter(Passport::validate)
                .count();
    }

    public static void main(String[] args) {
        List<Passport> passports = readAsLineSeperatedList("src\\twentytwenty\\input\\DayFour.txt")
                .parallelStream()
                .map(Passport::parse)
                .collect(Collectors.toList());
        print(partOne(passports));
        print(partTwo(passports));
    }

    static class Passport {

        private final Map<String, String> values;
        private static final String[] keys = new String[] {"byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid", "cid"};

        private Passport() {
            values = new HashMap<>();
        }

        public static Passport parse(String input) {
            Passport passport = new Passport();
            Arrays.stream(input.split(" "))
                    .filter(s -> s.length() > 0)
                    .forEach(s -> passport.values.put(s.split(":")[0], s.split(":")[1]));
            return passport;
        }

        public boolean validateCountry() {
            return Arrays.stream(keys)
                    .filter(k -> !k.equals("cid"))
                    .allMatch(values::containsKey);
        }

        public boolean validate() {
            return Arrays.stream(keys)
                    .allMatch(this::validate);
        }

        private boolean validate(String key) {
            try {
                switch (key) {
                    case "byr":
                        if (!validRange(Integer.parseInt(values.get(key)), 1920, 2002))
                            return false;
                        break;
                    case "iyr":
                        if (!validRange(Integer.parseInt(values.get(key)), 2010, 2020))
                            return false;
                        break;
                    case "eyr":
                        if (!validRange(Integer.parseInt(values.get(key)), 2020, 2030))
                            return false;
                        break;
                    case "hgt":
                        if (!validHeight(values.get(key)))
                            return false;
                        break;
                    case "hcl":
                        if(!validHairColor(values.get(key)))
                            return false;
                        break;
                    case "ecl":
                        if(!validEyeColor(values.get(key)))
                            return false;
                        break;
                    case "pid":
                        if (!validPid(values.get(key)))
                            return false;
                        break;
                    case "cid":
                        break;
                    default:
                        return false;
                }
            } catch (NumberFormatException e) { return false; }
            return true;
        }

        private boolean validRange(int num, int min, int max) {
            return min <= num && num <= max;

        }

        private boolean validHeight(String height) {
            String unit = height.substring(height.length()-2);
            int num = Integer.parseInt(height.split(unit)[0]);
            if (unit.equals("cm"))
                return validRange(num, 150, 193);
            if (unit.equals("in"))
                return validRange(num, 59, 76);
            return false;
        }

        private boolean validHairColor(String color) {
            if (color != null && color.length() == 7)
                return color.matches("^#[a-fA-F0-9]{6}");
            return false;
        }

        private boolean validEyeColor(String color) {
            return Arrays.asList(new String[] {"amb", "blu", "brn", "gry", "grn", "hzl", "oth"}).contains(color);
        }

        private boolean validPid(String pid) {
            if (pid != null && pid.length() == 9)
                return pid.matches("[a-fA-F0-9]{9}");
            return false;
        }
    }
}
