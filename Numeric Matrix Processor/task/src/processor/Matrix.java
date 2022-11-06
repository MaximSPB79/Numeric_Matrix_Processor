package processor;

import java.util.Scanner;

public class Matrix {
    private int rows;
    private int columns;
    private Scanner scanner = new Scanner(System.in);
    private double[][] matrix;

    public Matrix(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.matrix = new double[rows][columns];
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public void initialization() {
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                this.matrix[i][j] = scanner.nextDouble();
            }
        }
    }

    public double[][] getMatrix() {
        return matrix;
    }

    public static double[][] getSumMatrix(Matrix matrix1, Matrix matrix2) {
        if (matrix1.rows != matrix2.rows || matrix1.columns != matrix2.columns) {
            return null;
        }
        double[][] sumMatrix = new double[matrix1.rows][matrix1.columns];
        for (int i = 0; i < matrix1.rows; i++) {
            for (int j = 0; j < matrix1.columns; j++) {
                sumMatrix[i][j] = matrix1.getMatrix()[i][j] + matrix2.getMatrix()[i][j];
            }
        }
        return sumMatrix;
    }

    public static double[][] multiplicationByNumber(Matrix matrix, double number) {
        double[][] multiplicationMatrix = new double[matrix.rows][matrix.columns];
        for (int i = 0; i < matrix.rows; i++) {
            for (int j = 0; j < matrix.columns; j++) {
                multiplicationMatrix[i][j] = matrix.getMatrix()[i][j] * number;
            }
        }
        return multiplicationMatrix;
    }

    public static double[][] getMultMatrix(Matrix matrix1, Matrix matrix2) {
        int n = matrix1.getRows();
        int m = matrix1.getColumns();
        int k = matrix2.getColumns();
        double[][] multiplicationMatrix = new double[n][k];
        for (int i = 0; i < n; i++) {
            for (int u = 0; u < k; u++) {
                for (int j = 0; j < m; j++) {
                    multiplicationMatrix[i][u] += matrix1.getMatrix()[i][j] * matrix2.getMatrix()[j][u];
                }
            }
        }
        return multiplicationMatrix;
    }

    public static double[][] getTransposeMainDiagonal(Matrix matrix) {
        double[][] arrayMainDiagonal = new double[matrix.rows][matrix.columns];
        for (int i = 0; i < arrayMainDiagonal.length; i++) {
            for (int j = 0; j < arrayMainDiagonal[i].length; j++) {
                arrayMainDiagonal[i][j] = matrix.getMatrix()[j][i];
            }
        }
        return arrayMainDiagonal;
    }

    public static double[][] getTransposeSideDiagonal(Matrix matrix) {
        double[][] arraySideDiagonal = new double[matrix.rows][matrix.columns];
        for (int i = 0; i < arraySideDiagonal.length; i++) {
            for (int j = 0; j < arraySideDiagonal[i].length; j++) {
                arraySideDiagonal[i][j] = matrix.getMatrix()[matrix.getMatrix().length - 1 - j][matrix.getMatrix().length - 1 - i];
            }
        }
        return arraySideDiagonal;
    }

    public static double[][] getTransposeVerticalLine(Matrix matrix) {
        double[][] arrayVerticalLine = new double[matrix.rows][matrix.columns];
        for (int i = 0; i < arrayVerticalLine.length; i++) {
            for (int j = 0; j < arrayVerticalLine[i].length; j++) {
                arrayVerticalLine[i][j] = matrix.getMatrix()[i][matrix.getMatrix().length - 1 - j];
            }
        }
        return arrayVerticalLine;
    }

    public static double[][] getTransposeHorizontalLine(Matrix matrix) {
        double[][] arrayHorizontalLine = new double[matrix.rows][matrix.columns];
        for (int i = 0; i < arrayHorizontalLine.length; i++) {
            for (int j = 0; j < arrayHorizontalLine[i].length; j++) {
                arrayHorizontalLine[i][j] = matrix.getMatrix()[matrix.getMatrix().length - 1 - i][j];
            }
        }
        return arrayHorizontalLine;
    }

    public static void printMatrix(double[][] arr) {
        if (arr == null) {
            System.out.println("The operation cannot be performed.\n");
        } else {
            System.out.print("The result is: ");
            for (int i = 0; i < arr.length; i++) {
                System.out.println();
                for (int j = 0; j < arr[i].length; j++) {
                    System.out.print(arr[i][j] + " ");
                }
            }
        }
        System.out.println();
    }

    public static double getDeterminants(Matrix matrix) {
        double[][] arr1 = matrix.matrix;
        int n = arr1.length;
        if (n == 1) {
            return arr1[0][0];
        }
        double determinant = 0;
        Matrix matrix1 = new Matrix(n - 1, n - 1);
        double[][] arr2 = matrix1.matrix;
        int l = 1;
        for (int i = 0; i < n; ++i) {
            int x = 0, y = 0;
            for (int j = 1; j < n; ++j) {
                for (int k = 0; k < n; ++k) {
                    if (i == k) continue;
                    arr2[x][y] = arr1[j][k];
                    ++y;
                    if (y == n - 1) {
                        y = 0;
                        ++x;
                    }
                }
            }
            determinant += l * arr1[0][i] * getDeterminants(matrix1);
            l *= (-1);
        }
        return determinant;
    }

    public static double[][] getInverseMatrix(Matrix matrix) {
        double[][] backMatr = new double[matrix.rows][matrix.columns];
        double[][] matr = matrix.matrix;
        for (int i = 0; i < matrix.rows; i++)
            for (int j = 0; j < matrix.columns; j++) {
                if (i == j)
                    backMatr[i][j] = 1;
                else
                    backMatr[i][j] = 0;
            }

        double p = 0, q = 0, s = 0;
        for (int i = 0; i < matrix.rows; i++) {
            p = matr[i][i];
            for (int j = i + 1; j < matrix.rows; j++) {
                q = matr[j][i];
                for (int k = 0; k < matrix.rows; k++) {
                    matr[j][k] = (float) (matr[i][k] * q - matr[j][k] * p);
                    backMatr[j][k] = (float) (backMatr[i][k] * q - backMatr[j][k] * p);
                }
            }
        }
        for (int i = 0; i < matrix.rows; i++) {
            for (int j = matrix.rows - 1; j >= 0; j--) {
                s = 0;
                for (int k = matrix.rows - 1; k > j; k--)
                    s += matr[j][k] * backMatr[k][i];
                backMatr[j][i] = (float) ((backMatr[j][i] - s) / matr[j][j]);
            }
        }
        return backMatr;
    }
}
