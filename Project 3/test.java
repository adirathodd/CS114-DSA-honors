import java.util.Random;

public class test {
   public static void main(String[] args) {
        int num = args.length == 1 ? Integer.parseInt(args[0]) : 7;
        Integer[] array = new Integer[num];
        Random rand;
        rand = new Random(1);
        for (int i = 0; i < array.length; ++i) {
            array[i] = rand.nextInt(num);
            System.out.print(array[i]);
        }

        maxHeapify(array, array.length, 2);

        System.out.println();
        for (int i = 0; i < array.length - 1; ++i) {
            System.out.print(array[i]);
        }
   }

public static <T extends Comparable<? super T>> void maxHeapify(T[] arr, int length, int root){
    int max = root;
    int l = (root * 2) + 1;
    int r = (root * 2) + 2;

    if (l < length && arr[l].compareTo(arr[max]) > 0){
        max = l;
    }
    if (r < length && arr[r].compareTo(arr[max]) > 0){
        max = r;
    }
    if (max != root){
        T temp = arr[root];
        arr[root] = arr[max];
        arr[max] = temp;

        maxHeapify(arr, length, max);
    }
}
}