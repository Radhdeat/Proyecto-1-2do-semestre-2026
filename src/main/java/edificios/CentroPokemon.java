package edificios;

import java.util.Scanner;
import jugador.Jugador;
import pokemon.Pokemon;

// enfermera joy
public class CentroPokemon extends Edificios {
    private static final long serialVersionUID = 1L;

    // se crean los atributos
    private char[][] mapa;
    private int filaJugador;
    private int columnaJugador;

    // El constructor
    public CentroPokemon() {
        super("Centro Pokémon");
        inicializarMapa();
    }

    // se crea el mapa
    private void inicializarMapa() {
        this.mapa = new char[][]{
                {'|', '-', '-', '-', '-', '-', '-', '-', '-','-', '|'},
                {'|','y', 'y', 'y', 'y', 'y', 'E', 'y', 'y', 'y', '|'},
                {'|','y', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'y', '|'},
                {'|','y', 'T', ' ', ' ', ' ', ' ', ' ', ' ', 'y', '|'},
                {'|','y', ' ', ' ', ' ', 'J', ' ', ' ', ' ', 'y', '|'},
                {'|','y', 'y', 'y', 'y', 'y', 'S', 'y', 'y', 'y', '|'},
                {'|', '-', '-', '-', '-', '-', '-', '-', '-','-', '|'}
        };
        // posicion del jugador
        this.filaJugador = 4;
        this.columnaJugador = 5;
    }

    // Este es el metodo que todos los demas usan
    public void interactuar(Jugador jugador, Scanner scanner) {

        boolean dentro = true;
        System.out.println("Bienvenido al " + getNombre());

        // si el jugador esta adentro entonces se imprime el mapa y las instrucciones
        while (dentro) {
            imprimirMapa();
            System.out.print("Acción [W/A/S/D para mover, 0 para salir]: ");
            String input = scanner.next().toUpperCase();

            // si el jugador preciona 0 entonces dentro es falso y sale
            if (input.equals("0")) {
                dentro = false;
                continue;
            }

            // las posiciones nuevas del jugador
            char movimiento = input.charAt(0);
            int nuevaFila = filaJugador;
            int nuevaColumna = columnaJugador;

            switch (movimiento) {
                case 'W' -> nuevaFila--;
                case 'S' -> nuevaFila++;
                case 'A' -> nuevaColumna--;
                case 'D' -> nuevaColumna++;
                default -> {
                    System.out.println("Opción inválida, usa W, A, S, D.");
                    continue;
                }
            }

            char destino = mapa[nuevaFila][nuevaColumna];

            // Aqui esta la logica tras el mapa
            switch (destino) {
                // si el jugador intenta ir a un lado de pareces dira esto
                case 'y', '-', '|' -> System.out.println("Hay una pared en esa dirección.");
                // si el jugador pasa a un lado vacio entonces en la posicion anterior imprime un espacio vacio
                // y la posicion nueva escribe la J
                case ' ' -> {
                    mapa[filaJugador][columnaJugador] = ' ';
                    filaJugador = nuevaFila;
                    columnaJugador = nuevaColumna;
                    mapa[filaJugador][columnaJugador] = 'J';
                }
                // llama a los otros casos
                case 'E' -> interactuarEnfermeraJoy(jugador);
                case 'T' -> interactuarTelevisor();
                case 'S' -> {
                    System.out.println("Has salido del Centro Pokémon.");
                    dentro = false;
                }
            }
        }
    }

    // aqui se imprime el mapa
    public void imprimirMapa() {

        System.out.println("----------------------------------");
        System.out.println("        " + getNombre() + "       ");
        System.out.println("----------------------------------");

        // se imprimen en filas y columnas
        for (int f = 0; f < mapa.length; f++) {
            for (int c = 0; c < mapa[f].length; c++) {
                char celda = mapa[f][c];
                switch (celda) {
                    case 'E' -> System.out.print(" E ");
                    case 'T' -> System.out.print(" T ");
                    case 'S' -> System.out.print(" S ");
                    case 'J' -> System.out.print(" J ");
                    case 'y' -> System.out.print(" y ");
                    case '|' -> System.out.print(" | ");
                    case '-' -> System.out.print(" - ");
                    default  -> System.out.print("   ");
                }
            }
            System.out.println();
        }

        System.out.println("Controles: [W] Arriba | [S] Abajo | [A/D] Izq/Der");
        System.out.println("Leyenda: J=Jugador, E=Enfermera Joy, T=Televisor, S=Salida");
    }

    // Enfermera joy hace su chamba xd
    public void interactuarEnfermeraJoy(Jugador jugador) {
        System.out.println("------------------------------------------------");
        System.out.println("Enfermera Joy: Hola, Bienvenido al Centro Pokémon.");
        System.out.println("------------------------------------------------");

        // cura a los pokemones
        if (jugador != null && jugador.getEquipo() != null) {
            Pokemon[] equipo = jugador.getEquipo();
            for (int i = 0; i < jugador.getCantidadPokemon(); i++) {
                if (equipo[i] != null) {
                    equipo[i].curarCompleto();
                }
            }
            // dialogo
            System.out.println("Enfermera Joy: He restaurado la salud y el estado");
            System.out.println("               de todo tu equipo Pokémon al 100%.");
        } else {
            System.out.println("Enfermera Joy: No llevas ningún Pokémon contigo.");
        }
        System.out.println("------------------------------------------------");
    }

    // se llama al hall de la fama
    protected void interactuarTelevisor() {
        System.out.println("------------------------------------------------");
        System.out.println("                HALL DE LA FAMA                 ");
        System.out.println("------------------------------------------------");

        // Llamada directa al método estático de lectura
        flujoJuego.HallDeLaFama.mostrar();

        System.out.println("------------------------------------------------");
    }
}