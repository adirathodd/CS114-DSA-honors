public class pali {
    public static boolean recur(String x){
        if (x.length() == 1 || x.length() == 0){
            return true;
        }
        else if (x.charAt(0) == x.charAt(x.length()-1)) {
            return recur(x.substring(1, x.length()-1));
        }
        return false;
    }
    public static void main(String[] args){

        String s = "desded";
        System.out.println(recur(s));

    }
}
