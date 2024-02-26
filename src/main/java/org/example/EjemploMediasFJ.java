package org.example;

import java.util.concurrent.RecursiveTask;
import java.util.concurrent.ForkJoinPool;

public class EjemploMediasFJ extends RecursiveTask<Float> {

    // private static final int LONGITUD_ARRAY = 12;
    private static final int LONGITUD_ARRAY = 1_000;
    private short[] a_Array = null;
    private int a_Inicio, a_Fin = 0;


    public EjemploMediasFJ() {
    }


    public EjemploMediasFJ(short[] p_Array, int p_Inicio, int p_Fin) {
        this.a_Array = p_Array;
        this.a_Inicio = p_Inicio;
        this.a_Fin = p_Fin;
    }   // MediasForkJoin()  */


    private short[] crearArray() {
        short[] l_Array = new short[LONGITUD_ARRAY];
        int l_Contador = 0;

        for (l_Contador = 0; l_Contador < LONGITUD_ARRAY; l_Contador++)
            l_Array[l_Contador] = (short) l_Contador;

        return (l_Array);
    }   // crearArray()


    @Override
    protected Float compute()     // Llamado por invoke() e invokeAll().
    {
        float l_Media = 0f;
        int l_Contador = 0;
        int l_Acumulador = 0;

        for (l_Contador = a_Inicio + 1; l_Contador < a_Fin; l_Contador++)
            l_Acumulador += a_Array[l_Contador];
        // System.out.printf(l_Contador + " " + l_Acumulador + " %.2f \n", (float)l_Acumulador/(a_Fin-a_Inicio));
        l_Media = (float) l_Acumulador / (a_Fin - a_Inicio);

        return (l_Media);
    }   // compute()


    public static void main(String[] args) {
        EjemploMediasFJ l_Aplicacion = new EjemploMediasFJ();
        EjemploMediasFJ l_Tarea1 = null;
        EjemploMediasFJ l_Tarea2 = null;
        short[] l_Array = null;
        l_Aplicacion.crearArray();
        float l_ResultadoInvoke1, l_ResultadoInvoke2 = 0;
        float l_ResultadoJoin1, l_ResultadoJoin2 = 0;
        long l_TiempoInicial = System.currentTimeMillis();

        System.out.println("Inicio del cálculo.");

        // Crear el array y darle valores.
        l_Array = l_Aplicacion.crearArray();

        // Crear el pool ForkJoin.
        ForkJoinPool l_Pool = new ForkJoinPool();

        // Crear la tarea correspondiente a la primera mitad, lanzarla, y obtener el resultado "invoke".
        l_Tarea1 = new EjemploMediasFJ(l_Array, 0, LONGITUD_ARRAY / 2);
        l_ResultadoInvoke1 = l_Pool.invoke(l_Tarea1);

        // Crear la tarea correspondiente a la segunda mitad, lanzarla, y obtener el resultado "invoke".
        l_Tarea2 = new EjemploMediasFJ(l_Array, LONGITUD_ARRAY / 2, LONGITUD_ARRAY);
        l_ResultadoInvoke2 = l_Pool.invoke(l_Tarea2);

        // Obtener el resultado "join".
        l_ResultadoJoin1 = l_Tarea1.join();
        l_ResultadoJoin2 = l_Tarea2.join();

        // Imprimir resultados.
        System.out.println("Milisegundos empleados: " + (System.currentTimeMillis() - l_TiempoInicial));
        System.out.println("La media de la primera mitad según ‘invoke1’ es: " + l_ResultadoInvoke1);
        System.out.println("Coincide con la media según ‘join1’ es: " + l_ResultadoJoin1);
        System.out.println("La media de la primera mitad según ‘invoke2’ es: " + l_ResultadoInvoke2);
        System.out.println("Coincide con la media según ‘join2’ es: " + l_ResultadoJoin2);
    }   // main()

}   // MediasForkJoin
