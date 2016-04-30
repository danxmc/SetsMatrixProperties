/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalproject;

import java.util.Scanner;

/**
 *
 * @author LuisaFernandaPineda, DanielAlexandroMartínez
 */
public class FinalProject {

    static Scanner scan = new Scanner(System.in);
    static int[][] matrix;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int select = 0;
        System.out.println("Bienevenido al sistema de verificación de propiedades "
                + "de sets");
        do {
            System.out.println("Presione '1' si desea ingresar un set para checar "
                    + "sus propiedades");
            System.out.println("Presione '2' si desea salir");
            System.out.print("Selección: ");
            select = scan.nextInt();
            switch (select) {
                case 1:
                    System.out.println("");
                    int n = 0;
                    while (n <= 1) {
                    System.out.print("¿Cuantos nodos existen? (Tiene que ser mayor"
                            + " o igual a 2) ");
                    n = scan.nextInt();  
                    }
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

    /**
     * Metodo que ordena las coordenadas que el usuario ingrese, en una matriz y
     * despues la imprime para verificar si es correcta, en caso que no lo sea 
     * se puede volver a ingresar las coordenadas en la matriz
     */
    private static void setCoordenadas() {
        int select = 1;
        
        do {
            System.out.print("¿Cuantas parejas ordenadas habra en total? ");
            int temp = scan.nextInt();
            System.out.println("Escriba las coordenadas en orden ascendente "
                    + "(empieza en 0, 0): ");
            boolean flag = false;
            int a = 0, b = 0, count = 1;

            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[0].length; j++) {
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
                    if ((a != i) || (b != j)) {
                        matrix[i][j] = 0;
                    }
                    if (((a == i) && (b == j)) && flag) {
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
            System.out.println("¿Es esta la matriz a evaluar?\n"
                    + "Presione 1 si lo es, 0 si no: ");
            select = scan.nextInt();
            System.out.println("");
        } while (select != 1);

    }

    /**
     * Metodo que checa la reflexividad de la matriz, recorre cada espacio de
     * la matriz y evalua cuando las columnas tienen el mismo valor que los
     * renglones.
     */
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

    /**
     * Metodo que checa la simetria de la matriz, recorre cada espacio de la 
     * matriz y evalua la coordenada actual y su inversa.
     */
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

    /**
     * Metodo que checa la antisimetria de la matriz, recorre cada espacio de la
     * matriz, SOLO evalua cuando el renglon y la columna no tienen el mismo
     * valor, para evitar confundirse con "los datos reflexivos" (ya que su
     * posicion inversa sigue siendo la misma), cuando esta condicion se cumple,
     * evalua los valores en las posicion actual e inversa.
     */
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

    /**
     * Metodo que checa la transitividad de la matriz, primero hace una copia de
     * la matriz ingresada y la coloca en otra variable, para despues hacer
     * una multiplicación entre ellas, el resultado se evalua con los valores
     * en las posiciones de la original, de igual manera se imprime la matriz 
     * resultante como comprobación para el usuario.
     */
    private static void checkTransitivity() {
        boolean flag = true;
        int[][] m1 = new int[matrix.length][matrix[0].length];
        int[][] r = new int[matrix.length][matrix[0].length];
        m1 = matrix;
        
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
        System.out.println("Comprobación de la matriz resultante "
                + "(matriz inicial multiplicada por si misma): ");
        for (int i = 0; i < r.length; i++) {
            for (int j = 0; j < r[0].length; j++) {
                System.out.print(r[i][j] + " ");
            }
            System.out.println("");
        }
    }

}
