class BinarySearch {
    public static void main(String[] args){
        Integer[] array = {12, 23, 34, 45, 56, 67, 78, 89, 90};
        System.out.println(bsearch(array, 45));
        System.out.println(bsearch(array, 46));
        System.out.println(ssearch(array, 45));
        System.out.println(ssearch(array, 46));
    }

    public static <E extends Comparable<? super E>> int bsearch(E[] array, E key){
        return bsearch(array, key, 0, array.length - 1);
    }

    private static <E extends Comparable<? super E>> int bsearch(E[] array, E key, int left, int right){
        if (left <= right){
            int mid = (right + left)/2;
            int res = array[mid].compareTo(key);
            if (res == 0){
                return mid;
            }
            else if (res < 0){
                return bsearch(array, key, mid + 1, right);
            }
            else{
                return bsearch(array, key, left, mid - 1);
            }
        }
        return -1;
    }

    public static <E extends Comparable<? super E>> int ssearch(E[] array, E key){
        return ssearch(array, key, 0, array.length - 1);
    }

    private static <E extends Comparable<? super E>> int ssearch(E[] array, E key, int curr, int max){
        if (curr <= max){
            int res = array[curr].compareTo(key);
            if (res == 0){
                return curr;
            }
            else{
                return ssearch(array, key, curr + 1, max);
            }
        }
        return -1;
    }
}