package org.example.v2Tarea2;

/**
 * ¡¡¡¡¡¡¡¡¡¡¡¡¡ EJEMPLO CON INTERFACE (IMPLEMENTS) !!!!!!!!!!!
 */

public class v2Tarea2Thread implements Runnable {

    // private static final int LONGITUD_ARRAY = 12;
    private static final int LONGITUD_ARRAY = 1_000; // Definir la longitud del array como 1000
    private short[] a_Array = null; // Declaración del array
    private int a_Inicio, a_Fin = 0; // Declaración de índices de inicio y fin

    // Constructor con parámetros para inicializar el array e índices
    public v2Tarea2Thread(short[] a_Array, int a_Inicio, int a_Fin) {
        this.a_Array = a_Array;
        this.a_Inicio = a_Inicio;
        this.a_Fin = a_Fin;
    }

    public v2Tarea2Thread() {
    }

    // Método para crear un array con valores secuenciales
    private short[] crearArray() {
        short[] l_Array = new short[LONGITUD_ARRAY]; // Crear un nuevo array de shorts con la longitud especificada
        int l_Contador = 0;

        for (l_Contador = 0; l_Contador < LONGITUD_ARRAY; l_Contador++)
            l_Array[l_Contador] = (short) l_Contador; // Asignar valores secuenciales al array

        return l_Array; // Retornar el array creado
    }   // Fin del método crearArray()

    // Método run para la ejecución del hilo
    @Override
    public void run() {

        float l_Media = 0f;
        int l_Contador = 0;
        int l_Acumulador = 0;

        // Calcular la suma de los elementos del array en el rango especificado
        for (l_Contador = a_Inicio + 1; l_Contador < a_Fin; l_Contador++)
            l_Acumulador += a_Array[l_Contador];

        // Calcular la media de los elementos del array en el rango especificado
        l_Media = (float) l_Acumulador / (a_Fin - a_Inicio);

        // Imprimir la media
        System.out.println(l_Media);

    }

    // Método principal para la ejecución del programa
    public static void main(String[] args) {

        System.out.println("Inicio del cálculo.");

        // Crear una instancia de la clase
        v2Tarea2Thread l_Aplicacion = new v2Tarea2Thread();

        // Crear un array de shorts
        short[] l_Array = l_Aplicacion.crearArray();

        // Crear dos objetos de tipo v2Tarea2Thread con distintos rangos de índices
        v2Tarea2Thread l_ObjRunnable = new v2Tarea2Thread(l_Array, 0, LONGITUD_ARRAY / 2);
        v2Tarea2Thread l_ObjRunnable2 = new v2Tarea2Thread(l_Array, LONGITUD_ARRAY / 2, LONGITUD_ARRAY);

        // Crear dos hilos con los objetos anteriores
        Thread l_Hilo1 = new Thread(l_ObjRunnable);
        Thread l_Hilo2 = new Thread(l_ObjRunnable2);

        // Iniciar la ejecución de los hilos
        l_Hilo1.start();
        l_Hilo2.start();
    }
}
