import java.util.Scanner;
import java.io.*;
public class maze{
    public static void main(String[] args) throws FileNotFoundException{
        File mz = new File("samp1.txt");
        Scanner scan = new Scanner(mz);
        int rows = scan.nextInt();
        int cols = scan.nextInt();
        scan.nextLine();
        char[][] maze = new char[rows][cols];
        for (int i = 0; i < rows; i++){
            String line = scan.nextLine();
            for (int j = 0; j < cols; j++){
                maze[i][j] = line.charAt(j);
            }
        }
        scan.close();
        if (findPath(maze, 1, 0)){
            System.out.println("Maze solved");
            for (int i = 0; i < rows; i++){
                for (int j = 0; j < cols; j++){
                    System.out.print(maze[i][j]);
                }
                System.out.println("");
            }
        }
        else {
            System.out.println("No path found");
        }
    }
    public static boolean valid(char[][] mz, int row, int col) {
        if (row >= 0 && row < mz.length && col >= 0 && col < mz[0].length) {
            if (mz[row][col] == ' ' || mz[row][col] == '+' || mz[row][col] == '-'){
                return true;
            }
    }
        return false;
    }
    public static boolean findPath(char[][] mz, int row, int col){
        boolean check = false;
        if (valid(mz, row, col)){
            if (mz[row][col] == '-'){
                check = true;
            }
            mz[row][col] = '.';
            if (!check) {
                check = findPath(mz, row - 1, col);
            }
            if (!check) {
                check = findPath(mz, row + 1, col);
            }
            if (!check) {
                check = findPath(mz, row, col + 1);
            }
            if (!check) {
                check = findPath(mz, row, col - 1);
            }
        }
        if (check) {
            mz[row][col] = '+';
        }
        return check;
    }
}