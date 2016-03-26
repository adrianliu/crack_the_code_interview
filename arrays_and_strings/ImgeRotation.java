package cc150.arrays_and_strings;

/**
 * Created by xiaopengliu on 26/03/16.
 */
public class ImgeRotation {

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
        };
        System.out.println("--- Before Rotation ---");
        printMatrix(matrix);
        rotateImage(matrix);
        System.out.println();
        System.out.println("--- After Rotation ---");
        printMatrix(matrix);
    }

    private static void printMatrix(int[][] matrix) {
        int n = matrix.length;
        if(n == 0) {
            return;
        }
        for(int i = 0;i < n;i++) {
            for(int j = 0;j < n;j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void rotateImage(int[][] matrix) {
        int n = matrix.length;
        if(n == 0) {
            return;
        }
        //swap matrix[i][j] and matrix[j][i] elements
        for(int i = 0;i < n;i++) {
            for(int j = 0;j < i;j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

        //reverse each row.
        for(int i = 0;i < n;i++) {
            for(int j = 0;j < n/2;j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][n-1-j];
                matrix[i][n-1-j] = temp;
            }
        }
    }



}
