import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Program {
    public static void main(String[] args) {
        String[] data = {
                "123456 Иванов",
                "321456 Васильев",
                "234561 Петрова",
                "234432 Иванов",
                "654321 Петрова",
                "345678 Иванов"
        };
        task1(data);

        System.out.println(task2("paper", "title"));
        System.out.println(task2("egg", "add"));
        System.out.println(task2("kick", "side"));

        String[] students = {
                "Григорьев Анатолий 4 5 2",
                "Фокин Глеб 1 5 2",
                "Шестаков Клим 4 3 2",
                "Хохлов Мартин 2 5 2",
                "Шубин Лазарь 4 5 2",
                "Гущина Арьяна 4 3 3",
                "Брагина Виоланта 2 5 2",
                "Афанасьева Екатерина 1 1 2",
                "Рыбакова Снежана 4 2 2",
                "Лазарева Алла 4 3 2",
                "Бирюков Владлен 4 5 2",
                "Копылов Панкратий 4 4 4",
                "Горбунов Рубен 4 5 3",
                "Лыткин Герман 1 5 2",
                "Соколов Орест 1 1 5"
        };
        System.out.println("~~~~~~~~~~~~~~~~~~~~~");
        task3(students);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~");
        task4(students);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~");
    }

    /*
    Задача:
    Создать структуру для хранения номеров паспортов и фамилий сотрудников организаций.
    123456 Иванов
    321456 Васильев
    234561 Петрова
    234432 Иванов
    654321 Петрова
    345678 Иванов
    Вывести данные по сотрудникам с фамилией Иванов.
     */
    static void task1(String[] data) {
        // Способ 1
        HashMap<Integer, String> hashMap1 = new HashMap<>();

        for (String person: data) {
            String[] personParts = person.split(" ");
            hashMap1.put(Integer.parseInt(personParts[0]), personParts[1]);
        }

        for (Map.Entry<Integer, String> element : hashMap1.entrySet()) {
            if (element.getValue().equals("Иванов")) {
                System.out.println(element.getKey() + " : " + element.getValue());
            }
        }
        System.out.println("~~~~~~~~~~~~~~~~~~~~~");

        // Способ 2
        HashMap<String, ArrayList<Integer>> hashMap2 = new HashMap<>();

        for (String person: data) {
            String[] personParts = person.split(" ");
            if (hashMap2.containsKey(personParts[1])) {
                hashMap2.get(personParts[1]).add(Integer.parseInt(personParts[0]));
            } else {
                ArrayList<Integer> list = new ArrayList<>();
                list.add(Integer.parseInt(personParts[0]));
                hashMap2.put(personParts[1], list);
            }
        }

        System.out.println(hashMap2.get("Иванов"));
        System.out.println("~~~~~~~~~~~~~~~~~~~~~");
    }

    /*
    Задача:
    Даны 2 строки, написать метод, который вернёт true,
    если эти строки являются изоморфными и false, если нет.
    Строки изоморфны, ели одну букву в первом слове можно
    заменить на некоторую букву во втором слове, при этом:
    1. повторяющиеся буквы одного слова меняются на одну и ту же букву с
    сохранением порядка следования. (Например, add - egg изоморфны)
    2. буква может не меняться, а остаться такой же. (Например, note - code)
    paper title
    egg add
    kick side
     */
    static boolean task2(String firstString, String secondString) {
        if (firstString.length() != secondString.length())
            return false;

        HashMap<Character, Character> hashMap = new HashMap<>();
        for (int i = 0; i < firstString.length(); i++) {
            char letterFirst = firstString.charAt(i);
            char letterSecond = secondString.charAt(i);
            if (!hashMap.containsKey(letterFirst)) {
                hashMap.put(letterFirst, letterSecond);
            } else {
                if (hashMap.get(letterFirst) != letterSecond)
                    return false;
            }
        }
        return true;
    }

    public static void task3(String[] students) {
        HashMap<Double, ArrayList<Student>> statistics = new HashMap<>();
        for (String student : students) {
            String[] studentParts = student.split(" ");
            int a = Integer.parseInt(studentParts[2]);
            int b = Integer.parseInt(studentParts[3]);
            int c = Integer.parseInt(studentParts[4]);
            double r = (double) (a + b + c) / 3;
            Student st = new Student(studentParts[1], studentParts[0], r);
            if (statistics.containsKey(r)) {
                statistics.get(r).add(st);
            } else {
                ArrayList<Student> list = new ArrayList<>();
                list.add(st);
                statistics.put(r, list);
            }
        }
        System.out.println("ТОП3 худших средних баллов среди студентов: ");
        TreeMap<Double, ArrayList<Student>> sortedStatistic = new TreeMap<>();
        sortedStatistic.putAll(statistics);

        int counter = 1;
        for (Map.Entry<Double, ArrayList<Student>> element : sortedStatistic.entrySet()) {
            System.out.printf("%d место - средний балл %.2f\n", counter, element.getKey());
            for (Student student : element.getValue()) {
                System.out.println("\t" + student);
            }
            counter++;
            if (counter > 3)
                break;
        }
        TreeMap<Double, ArrayList<Student>> sortedStatistic2 = new TreeMap<>(new RatingComparator());
        sortedStatistic.putAll(statistics);

        int counter2 = 1;
        for (Map.Entry<Double, ArrayList<Student>> element : sortedStatistic2.entrySet()) {
            System.out.printf("%d место - средний балл %.2f\n", counter2, element.getKey());
            for (Student student : element.getValue()) {
                System.out.println("\t" + student);
            }
            counter2++;
            if (counter2 > 3)
                break;
        }
    }

    public static void task4(String[] students) {
        HashMap<Double, ArrayList<Student>> statistics = new HashMap<>();
        for (String student : students) {
            String[] studentParts = student.split(" ");
            int a = Integer.parseInt(studentParts[2]);
            int b = Integer.parseInt(studentParts[3]);
            int c = Integer.parseInt(studentParts[4]);
            double r = (double) (a + b + c) / 3;
            Student st = new Student(studentParts[1], studentParts[0], r);
            if (statistics.containsKey(r)) {
                statistics.get(r).add(st);
            } else {
                ArrayList<Student> list = new ArrayList<>();
                list.add(st);
                statistics.put(r, list);
            }
        }
        System.out.println("ТОП3 лудших средних баллов среди студентов: ");
        TreeMap<Double, ArrayList<Student>> sortedStatistic = new TreeMap<>(new RatingComparator());
        sortedStatistic.putAll(statistics);

        int counter = 1;
        for (Map.Entry<Double, ArrayList<Student>> element : sortedStatistic.entrySet()) {
            System.out.printf("%d место - средний балл %.2f\n", counter, element.getKey());
            for (Student student : element.getValue()) {
                System.out.println("\t" + student);
            }
            counter++;
            if (counter > 3)
                break;
        }
    }
}
