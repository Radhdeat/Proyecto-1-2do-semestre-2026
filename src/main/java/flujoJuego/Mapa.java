package flujoJuego;

import java.io.Serializable;

// el mapa
public class Mapa implements Serializable {
    // la etiquera para la serializacion
    private static final long serialVersionUID = 1L;

    // Mapa
    private char[][] mapa;

    // Terreno debajo del jugador
    private char elementoDebajoJugador;

    // Dimensiones
    private int filas;
    private int columnas;

    // Posición del jugador
    private int jugadorFila;
    private int jugadorColumna;

    // Nombre de la ciudad
    private String nombreCiudad;

    // Constructor
    protected Mapa(String nombre) {
        this.nombreCiudad = nombre;
        this.filas = 10;
        this.columnas = 20;
        this.mapa = new char[filas][columnas];
        this.generar();
    }

    // Generar mapa
    protected void generar() {

        // Se imprime el mapa con espacios basillos
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                mapa[i][j] = ' ';
            }
        }

        // Muros verticales
        for (int columna = 0; columna < columnas; columna++) {
            mapa[0][columna] = 'Y';
            mapa[filas - 1][columna] = 'Y';
        }

        // Muros Horizontales
        for (int fila = 0; fila < filas; fila++) {
            mapa[fila][0] = 'Y';
            mapa[fila][columnas - 1] = 'Y';
        }

        // Posición inicial
        jugadorFila = 5;
        jugadorColumna = 3;
        elementoDebajoJugador = ' ';
        mapa[jugadorFila][jugadorColumna] = 'J';

        // Elementos interactivos
        colocarElemento('C');
        colocarElemento('T');
        colocarElemento('G');
        colocarHierba();
        colocarRocas();
    }

    // aqui se colocan lso elementos
    private void colocarElemento(char elemento) {
        int fila, columna;

        // Siempre intenta colocar una casillas
        do {
            // se generan los espacios pero se le restan los vordes
            fila = 1 + (int) (Math.random() * (filas - 2));
            // se genera cada espacio pero se le restan los vordes
            columna = 1 + (int) (Math.random() * (columnas - 2));

            // pero revisa si esta vacia
        } while (mapa[fila][columna] != ' ');

        // asigna el espacio en el mapa
        mapa[fila][columna] = elemento;
    }

    // se coloca la hierba alta
    private void colocarHierba() {
        // la cantidad de hierba alta
        int cantidadHierba = 10;
        for (int i = 0; i < cantidadHierba; i++) {
            int fila, columna;
            // se coloca la hierba en un espacio pero se le restan los bordes
            do {
                fila = 1 + (int) (Math.random() * (filas - 2));
                columna = 1 + (int) (Math.random() * (columnas - 2));

                // pero siempre y cuando este vacio
            } while (mapa[fila][columna] != ' ');

            // se asigna la hierba en el mapa
            mapa[fila][columna] = '"';
        }
    }

    private void colocarRocas() {
        int cantidadRocas = 5;
        for (int i = 0; i < cantidadRocas; i++) {
            int fila, columna;
            do {
                fila = 1 + (int) (Math.random() * (filas - 2));
                columna = 1 + (int) (Math.random() * (columnas - 2));
            } while (mapa[fila][columna] != ' ');

            mapa[fila][columna] = 'o';
        }
    }

    // Aqui se muestrea el mapa
    protected void mostrar() {
        // se hace mas intuitivo
        System.out.println("+------------------------------------------+");
        System.out.println("|        MAPA: " + nombreCiudad + "              |");
        System.out.println("+------------------------------------------+");

        // se imprimen los vordes
        for (int renglones = 0; renglones < filas; renglones++) {
            System.out.print("| ");
            for (int columna = 0; columna < columnas; columna++) {
                System.out.print(mapa[renglones][columna] + " ");
            }
            System.out.println("|");
        }

        // se imprimen las indicacions
        System.out.println("+------------------------------------------+");
        System.out.println("| Controles: [W] Arriba | [S] Abajo       |");
        System.out.println("|            [A] Izq    | [D] Derecha     |");
        System.out.println("+------------------------------------------+");
        System.out.println("{ J=Jugador, C=Centro Pokemon, T=Tienda, G=Gimnasio, Hierba, o=Roca, Y=Muro }");
    }

    // Aqui esta la logica para mover al jugador
    protected boolean moverJugador(String movimiento) {

        // copia la posicion actual del jugador
        int nuevaFila = jugadorFila;
        int nuevaColumna = jugadorColumna;

        // eaqui se ve la direccion hacia donde va
        switch (movimiento) {
            // basicamente suma o resta la posicion en filas y columnas
            case "W": nuevaFila--; break;
            case "S": nuevaFila++; break;
            case "A": nuevaColumna--; break;
            case "D": nuevaColumna++; break;
            default:
                // en caso de que el jugador escoga otra cosa
                System.out.println("Movimiento inválido.");
                return false;
        }

        // se verifica que el jugador no se salga del mapa
        if (nuevaFila < 0 || nuevaFila >= filas || nuevaColumna < 0 || nuevaColumna >= columnas) {
            System.out.println("No puedes salir del mapa.");
            return false;
        }

        if (mapa[nuevaFila][nuevaColumna] == 'Y' || mapa[nuevaFila][nuevaColumna] == 'o') {
            System.out.println("Obstáculo en el camino.");
            return false;
        }

        // aqui se borra la posicion anterior
        char nuevoElemento = mapa[nuevaFila][nuevaColumna];
        mapa[jugadorFila][jugadorColumna] = elementoDebajoJugador;

        // aqui se actualiza la posicion
        jugadorFila = nuevaFila;
        jugadorColumna = nuevaColumna;

        elementoDebajoJugador = nuevoElemento;
        mapa[jugadorFila][jugadorColumna] = 'J';

        // aqui se comprueva por en caso de que el jugador llegue a una casilla especial
        verificarCasilla();
        return true;
    }

    private void verificarCasilla() {
        switch (elementoDebajoJugador) {
            case 'C':
                System.out.println("Has llegado al Centro Pokémon.");
                break;
            case 'T':
                System.out.println("Has llegado a la Tienda Pokémon.");
                break;
            case 'G':
                System.out.println("Has llegado al Gimnasio Pokémon.");
                break;
            case '"':
                System.out.println("Estás caminando sobre hierba alta.");
                break;
            default:
                break;
        }
    }

    protected char obtenerCasillaActual() {
        return elementoDebajoJugador;
    }
}