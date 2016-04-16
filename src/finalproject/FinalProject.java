/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalproject;

import java.util.Scanner;

/**
 *
 * @author danx_
 */
public class FinalProject {

    static Scanner scan = new Scanner(System.in);
    static int[][] matrix;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int select = 0;
        System.out.println("Bienevenido al sistema de verificación de propiedades de sets");
        do {
            System.out.println("Presione '1' si desea ingresar un set para checar sus propiedades");
            System.out.println("Presione '2' si desea salir");
            System.out.print("Selección: ");
            select = scan.nextInt();
            switch (select) {
                case 1:
                    System.out.println("");
                    System.out.print("¿Cuantos nodos existen? ");
                    int n = scan.nextInt();
                    matrix = new int[n][n];
                    setCoordenadas();
                    checkReflexivity();
                    checkSymmetry();
                    checkAntisymmetry();
                    checkTransitivity();
                    System.out.println("");
                    break;
                case 2:
                    break;
            }
        } while (select != 2);
    }

    private static void setCoordenadas() {
        System.out.print("¿Cuantas parejas ordenadas habra en total? ");
        int temp = scan.nextInt();
        System.out.println("Escriba las coordenadas en orden ascendente (empieza en 0, 0): ");
        boolean flag = false;
        int a = 0, b = 0, count = 1;

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if ((a != i) || (b != j)) {
                    matrix[i][j] = 0;
                }
                if (count > temp) {
                    flag = true;
                }
                if (!flag) {
                    System.out.println("Par ordenado #" + count);
                    System.out.print("Ingrese la coordenada a: ");
                    a = scan.nextInt();
                    System.out.print("Ingrese la coordenada b: ");
                    b = scan.nextInt();
                    count++;
                    flag = true;
                    System.out.println("");
                }

                if ((a == i) && (b == j)) {
                    matrix[i][j] = 1;
                    flag = false;
                }
            }
        }

        System.out.println("La matriz del set es: ");
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println("");
        }

        System.out.println("");
    }

    private static void checkReflexivity() {
        boolean flag = true;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (i == j) {
                    if (matrix[i][j] == 0) {
                        flag = false;
                        i = matrix.length + 1;
                        j = matrix.length + 1;
                    }
                }
            }
        }
        System.out.println("Reflexividad: " + flag);
    }

    private static void checkSymmetry() {
        boolean flag = true;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (!(matrix[i][j] == matrix[j][i])) {
                    flag = false;
                    i = matrix.length + 1;
                    j = matrix[0].length + 1;
                }
            }
        }
        System.out.println("Simetria: " + flag);
    }

    private static void checkAntisymmetry() {
        boolean flag = true;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (i != j) {
                    if ((matrix[i][j] == 1) && (matrix[j][i] == 1)) {
                        flag = false;
                        i = matrix.length + 1;
                        j = matrix[0].length + 1;
                    }
                    /*if ((matrix[i][j] == 1) && (matrix[j][i] == 0)) {
                        flag = true;
                        i = matrix.length + 1;
                        j = matrix[0].length + 1;
                    }*/
                }
            }
        }
        System.out.println("Antisimetria: " + flag);
    }

    private static void checkTransitivity() {
        boolean flag = true;
        int[][] m1 = new int[matrix.length][matrix[0].length];
        int[][] r = new int[matrix.length][matrix[0].length];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < m1[0].length; j++) {
                for (int k = 0; k < matrix.length; k++) {
                    r[i][j] += matrix[i][k] * m1[k][j];
                }
            }
        }

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (!(matrix[i][j] > 0) && (r[i][j] > 0)) {
                    flag = false;
                    i = matrix.length + 1;
                    j = matrix[0].length + 1;
                }
            }
        }
        
        System.out.println("Transitividad: " + flag);
        System.out.println("Comprobación de la matriz resultante (matriz inicial multiplicada por si misma): ");
        for (int i = 0; i < r.length; i++) {
            for (int j = 0; j < r[0].length; j++) {
                System.out.print(r[i][j] + " ");
            }
            System.out.println("");
        }
    }

}
