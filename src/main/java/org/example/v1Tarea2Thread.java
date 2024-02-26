package org.example;

/**
 * ¡¡¡¡¡¡¡¡¡¡¡¡¡ EJEMPLO CON HERECIA (EXTENDS) !!!!!!!!!!!
 */

public class v1Tarea2Thread extends Thread {

    private static final int LONGITUD_ARRAY = 1_000;
    private short[] a_Array = null;
    private int a_Inicio, a_Fin = 0;

    public v1Tarea2Thread(short[] a_Array, int a_Inicio, int a_Fin) {
        this.a_Array = a_Array;
        this.a_Inicio = a_Inicio;
        this.a_Fin = a_Fin;
    }

    private short[] crearArray() {
        short[] l_Array = new short[LONGITUD_ARRAY];
        int l_Contador = 0;

        for (l_Contador = 0; l_Contador < LONGITUD_ARRAY; l_Contador++)
            l_Array[l_Contador] = (short) l_Contador;

        return (l_Array);
    }   // crearArray()

    @Override
    public void run() {

        float l_Media = 0f;
        int l_Contador = 0;
        int l_Acumulador = 0;

        for (l_Contador = a_Inicio + 1; l_Contador < a_Fin; l_Contador++)
            l_Acumulador += a_Array[l_Contador];
        // System.out.printf(l_Contador + " " + l_Acumulador + " %.2f \n", (float)l_Acumulador/(a_Fin-a_Inicio));
        l_Media = (float) l_Acumulador / (a_Fin - a_Inicio);

        System.out.println(getName() + ": " + l_Media + " - Prioridad: " + getPriority());
    }

    public v1Tarea2Thread() {
    }

    public static void main(String[] args) {

        System.out.println("Inicio del cálculo.");
        v1Tarea2Thread l_Aplicacion = new v1Tarea2Thread();

        short[] l_Array = l_Aplicacion.crearArray();

        v1Tarea2Thread l_Tarea1 = new v1Tarea2Thread(l_Array, 0, LONGITUD_ARRAY / 2);
        v1Tarea2Thread l_Tarea2 = new v1Tarea2Thread(l_Array, LONGITUD_ARRAY / 2, LONGITUD_ARRAY);

        l_Tarea1.setName("Media1");
        l_Tarea1.setPriority(10);

        l_Tarea2.setName("Media2");
        l_Tarea2.setPriority(1);

//        System.out.println(l_Tarea1.getName());
//        System.out.println(l_Tarea2.getName());

        l_Tarea1.start();
        l_Tarea2.start();

    }
}