package array;


/**
 *
 * You are given an n x n 2D matrix representing an image.
 * Rotate the image by 90 degrees (clockwise).
 *
 * @author softtwilight 2020/01/22
 *
 */
public class _48_Rotata_Image {

    public void rotate(int[][] matrix) {
        int t = matrix.length - 1;
        int temp;
        for (int i = 0; i <= t; i++) {
            for (int j = i; j < t - i; j++) {
                temp = matrix[j][t - i];
                matrix[j][t - i] = matrix[i][j];
                matrix[i][j] = matrix[t - j][i];
                matrix[t - j][i] = matrix[t - i][t - j];
                matrix[t - i][t - j] = temp;
            }
        }

    }

}
