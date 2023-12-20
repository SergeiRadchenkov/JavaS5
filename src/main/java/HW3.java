/*
Необходимо разработать программу для сортировки массива целых чисел с использованием сортировки кучей (Heap Sort).
Программа должна работать следующим образом:
Принимать на вход массив целых чисел для сортировки.
Если массив не предоставлен, программа использует массив по умолчанию.
Сначала выводить исходный массив на экран.
Затем применять сортировку кучей (Heap Sort) для сортировки элементов массива в порядке возрастания.
Выводить отсортированный массив на экран.
Ваше решение должно содержать два основных метода: buildTree,
который строит сортирующее дерево на основе массива, и heapSort,
который выполняет собственно сортировку кучей.
Программа должна быть способной сортировать массив,
даже если он состоит из отрицательных чисел и имеет дубликаты.

На входе:
'5 8 12 3 6 9'
На выходе:
Initial array:
[5, 8, 12, 3, 6, 9]
Sorted array:
[3, 5, 6, 8, 9, 12]
 */
import java.util.Arrays;
public class HW3 {
    static class HeapSort {
        public static void buildTree(int[] tree, int i, int sortLength) {
            int a = 2 * i;
            int b = 2 * i + 1;
            int large;
            if (a <= sortLength && tree[a] > tree[i]) {
                large = a;
            } else {
                large = i;
            }
            if (b <= sortLength && tree[b] > tree[large]) {
                large = b;
            }
            if (large != i) {
                int temp = tree[i];
                tree[i] = tree[large];
                tree[large] = temp;
                buildTree(tree, large, sortLength);
            }
        }

        public static void heapSort(int[] sortArray, int sortLength) {
            int temp;
            int size = sortLength - 1;
            for (int i = (sortLength / 2); i >= 0; i--) {
                buildTree(sortArray, i, size);
            }
            for (int i = size; i >= 0; i--) {
                temp = sortArray[0];
                sortArray[0] = sortArray[size];
                sortArray[size] = temp;
                size--;
                buildTree(sortArray, 0, size);
            }
        }
    }
    public static class Printer {
        public static void main(String[] args) {
            int[] initArray;

            if (args.length == 0) {
                initArray = new int[]{17, 32, 1, 4, 25, 17, 0, 3, 10, 7, 64, 1};
            } else {
                initArray = Arrays.stream(args[0].split(" ")).mapToInt(Integer::parseInt).toArray();
            }

            System.out.println("Initial array:");
            System.out.println(Arrays.toString(initArray));
            HeapSort.heapSort(initArray, initArray.length);
            System.out.println("Sorted array:");
            System.out.println(Arrays.toString(initArray));
        }
    }
}
