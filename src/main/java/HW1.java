/*
Напишите программу, представляющую телефонную книгу. Программа должна иметь следующие функции:

add(String name, Integer phoneNum): Добавляет запись в телефонную книгу.
Если запись с именем name уже существует, добавляет новый номер телефона в существующую запись.
Если запись с именем name не существует, создает новую запись с этим именем и номером телефона phoneNum.

find(String name): Поиск номеров телефона по имени в телефонной книге.
Если запись с именем name существует, возвращает список номеров телефона для этой записи.
Если запись с именем name не существует, возвращает пустой список.

getPhoneBook(): Возвращает всю телефонную книгу в виде HashMap, где ключи - это имена, а значения - списки номеров телефона.

Пример.
На входе:
add("Alice", 123456);
add("Alice", 789012);
add("Bob", 789012);

На выходе:
Команды:
find("Alice")
getPhoneBook()
find("Me")

Результат:
[123456, 789012].
{Bob=[789012], Alice=[123456, 789012]}
[]
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class HW1 {
    static class PhoneBook {
        private static HashMap<String, ArrayList<Integer>> phoneBook = new HashMap<>();

        public void add(String name, Integer phoneNum) {
            if (phoneBook.containsKey(name)) {
                phoneBook.get(name).add(phoneNum);
            } else {
                ArrayList<Integer> list = new ArrayList<>();
                list.add(phoneNum);
                phoneBook.put(name, list);
            }
        }

        public ArrayList<Integer> find(String name) {
            if (phoneBook.containsKey(name)) {
                return phoneBook.get(name);
            } else {
                ArrayList<Integer> a = new ArrayList<>();
                return a;
            }
        }

        public static HashMap<String, ArrayList<Integer>> getPhoneBook() {
            HashMap<String, ArrayList<Integer>> gPB = new HashMap<>();
            for (Map.Entry<String, ArrayList<Integer>> element : phoneBook.entrySet())
                gPB.put(element.getKey(), (ArrayList<Integer>) element.getValue());
            return gPB;
        }
    }

    public static class Printer {
        public static void main(String[] args) {
            String name1;
            String name2;
            int phone1;
            int phone2;

            if (args.length == 0) {
                name1 = "Carol";
                name2 = "Eve";
                phone1 = 555555;
                phone2 = 666666;
            } else {
                name1 = args[0];
                name2 = args[1];
                phone1 = Integer.parseInt(args[2]);
                phone2 = Integer.parseInt(args[3]);
            }

            PhoneBook myPhoneBook = new PhoneBook();
            myPhoneBook.add(name1, phone1);
            myPhoneBook.add(name1, phone2);
            myPhoneBook.add(name2, phone2);

            System.out.println(myPhoneBook.find(name1));
            System.out.println(PhoneBook.getPhoneBook());
            System.out.println(myPhoneBook.find("Me"));
        }
    }
}
