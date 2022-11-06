package processor;

import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static int rows;
    private static int columns;
    private static int choice;

    public static void main(String[] args) {
        process();
    }

    private static void process() {
        while (true) {
            menuDisplay();
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    addMatrix();
                    break;
                case 2:
                    multiplicationMatrixOfConstant();
                    break;
                case 3:
                    multiplicationMatrix();
                    break;
                case 4:
                    transposeMatrix();
                    break;
                case 5:
                    calculateDeterminants();
                    break;
                case 6:
                    inverseMatrix();
                    break;
                case 0:
                    System.exit(1);
                    break;
            }
        }
    }

    private static void menuDisplay() {
        System.out.print("\n1. Add matrices\n" +
                "2. Multiply matrix by a constant\n" +
                "3. Multiply matrices\n" +
                "4. Transpose matrix\n" +
                "5. Calculate a determinant\n" +
                "6. Inverse matrix\n" +
                "0. Exit\n" +
                "Your choice: ");
    }

    private static void addMatrix() {
        System.out.print("Enter size of first matrix: ");
        inputRowsAndColumns();
        Matrix matrix1 = new Matrix(rows, columns);
        System.out.println("Enter first matrix:");
        matrix1.initialization();
        System.out.print("Enter size of second matrix: ");
        inputRowsAndColumns();
        Matrix matrix2 = new Matrix(rows, columns);
        System.out.println("Enter second matrix:");
        matrix2.initialization();
        double[][] sum = Matrix.getSumMatrix(matrix1, matrix2);
        Matrix.printMatrix(sum);
    }

    private static void multiplicationMatrixOfConstant() {
        System.out.print("Enter size of matrix: ");
        inputRowsAndColumns();
        Matrix matrix = new Matrix(rows, columns);
        System.out.println("Enter matrix:");
        matrix.initialization();
        System.out.print("Enter constant: ");
        double number = scanner.nextDouble();
        double[][] multiplicationArray = Matrix.multiplicationByNumber(matrix, number);
        Matrix.printMatrix(multiplicationArray);
    }

    private static void multiplicationMatrix() {
        System.out.print("Enter size of first matrix: ");
        inputRowsAndColumns();
        Matrix matrix1 = new Matrix(rows, columns);
        System.out.println("Enter first matrix:");
        matrix1.initialization();
        System.out.print("Enter size of second matrix: ");
        inputRowsAndColumns();
        Matrix matrix2 = new Matrix(rows, columns);
        System.out.println("Enter second matrix:");
        matrix2.initialization();
        double[][] multMatrix = Matrix.getMultMatrix(matrix1, matrix2);
        Matrix.printMatrix(multMatrix);
    }

    private static void transposeMatrix() {
        System.out.print("\n1. Main diagonal\n" +
                "2. Side diagonal\n" +
                "3. Vertical line\n" +
                "4. Horizontal line\n" +
                "Your choice: ");
        switch (scanner.nextInt()) {
            case 1:
                transposeMainDiagonal();
                break;
            case 2:
                transposeSideDiagonal();
                break;
            case 3:
                transposeVerticalLine();
                break;
            case 4:
                transposeHorizontalLine();
                break;
        }
    }

    private static void transposeHorizontalLine() {
        Matrix matrix = createMatrix();
        double[][] arrHorizontalLine = Matrix.getTransposeHorizontalLine(matrix);
        Matrix.printMatrix(arrHorizontalLine);
    }

    private static void transposeVerticalLine() {
        Matrix matrix = createMatrix();
        double[][] arrVerticalLine = Matrix.getTransposeVerticalLine(matrix);
        Matrix.printMatrix(arrVerticalLine);
    }

    private static void transposeSideDiagonal() {
        Matrix matrix = createMatrix();
        double[][] arrSideDiagonal = Matrix.getTransposeSideDiagonal(matrix);
        Matrix.printMatrix(arrSideDiagonal);
    }

    private static void transposeMainDiagonal() {
        Matrix matrix = createMatrix();
        double[][] arrMainDiagonal = Matrix.getTransposeMainDiagonal(matrix);
        Matrix.printMatrix(arrMainDiagonal);
    }

    private static Matrix createMatrix() {
        System.out.print("Enter matrix size: ");
        inputRowsAndColumns();
        Matrix matrix = new Matrix(rows, columns);
        System.out.println("Enter matrix: ");
        matrix.initialization();
        return matrix;
    }


    private static void inputRowsAndColumns() {
        rows = scanner.nextInt();
        columns = scanner.nextInt();
    }

    private static void calculateDeterminants() {
        Matrix matrix = createMatrix();
        System.out.println("The result is:\n"
                + Matrix.getDeterminants(matrix));
    }

    private static void inverseMatrix() {
        Matrix matrix = createMatrix();
        double[][] arrInverse = Matrix.getInverseMatrix(matrix);
        Matrix.printMatrix(arrInverse);
    }

}
