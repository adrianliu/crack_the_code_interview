package cc150.arrays_and_strings;

/**
 * Created by xiaopengliu on 26/03/16.
 */
public class MatrixSetZero {

    public static void main(String[] args) {

        int[][] matrix = {
                {1, 2, 9, 4},
                {5, 6, 7, 8},
                {9, 10, 9, 12},
                {0, 14, 15, 16}
        };

        System.out.println("--- Before Rotation ---");
        printMatrix(matrix);
        setZeroes(matrix);
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

    private static void setZeroes(int[][] matrix) {

        int m = matrix.length;
        if(m == 0) {
            return;
        }
        int n = matrix[0].length;
        //arr1 and arr2 used to get the row index and column index of 0 elements
        int[] arr1 = new int[m];
        int[] arr2 = new int[n];
        for(int i = 0;i < m;i++) {
            for(int j = 0;j < n;j++) {
                if(matrix[i][j] == 0) {
                    arr1[i] = 1;
                    arr2[j] = 1;
                }
            }
        }

        //Now set zeros
        for(int i = 0;i < m;i++) {
            for(int j = 0;j < n;j++) {
                if(arr1[i] == 1 || arr2[j] == 1) {
                    matrix[i][j] = 0;
                }
            }
        }
    }

}
