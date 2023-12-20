import java.util.Arrays;
public class HW3_1 {
    static class HeapSort {
        public static void buildTree(int[] tree, int sortLength) {
            // Для всех вершин дерева начиная с середины сортируемой части массива
            // (движемся влево до нулевого индекса)
            for (int i = sortLength / 2 - 1; i >= 0; i--) {
                int maxIndex;
                // Если у вершины два листа, выбираем больший
                if (i * 2 + 2 <= sortLength - 1) {
                    if (tree[i * 2 + 2] > tree[i * 2 + 1]) maxIndex = i * 2 + 2;
                    else maxIndex = i * 2 + 1;
                    // Иначе один лист
                } else maxIndex = i * 2 + 1;
                // Сравниваем лист с максимальным значением с вершиной, при большем значении
                // дочернего элемента меняем его местами с вершиной
                if (tree[i] < tree[maxIndex]) {
                    int temp = tree[i];
                    tree[i] = tree[maxIndex];
                    tree[maxIndex] = temp;
                }
            }
        }

        public static void heapSort(int[] sortArray, int sortLength) {
            // Выход из рекурсии: длина сортируемой части равна 0
            if (sortLength == 0) return;
            // Выстраивание элементов части массива в виде сортирующего дерева
            buildTree(sortArray, sortLength);
            // Замена местами первого и последнего элемента в сортируемой части
            int temp = sortArray[0];
            sortArray[0] = sortArray[sortLength - 1];
            sortArray[sortLength - 1] = temp;
            // Рекурсивно построение дерева и замена элементов для n - 1 элементов массива
            heapSort(sortArray, sortLength - 1);
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
