package edificios;

import java.sql.SQLOutput;
import java.util.Random;
import java.util.Scanner;
import jugador.Jugador;
// se importa toda la carpeta pokemon
import pokemon.*;
import flujoJuego.Batalla;

// Gimnasio
public class Gimnasio extends Edificios {
    private static final long serialVersionUID = 1L;

    // Atributos de la clase
    private String nombreLider;
    private String nombreMedalla;
    private boolean medallaObtenida;

    private boolean[] entrenadoresDerrotados;
    private Pokemon[][] equiposEntrenadores;
    private Pokemon[] equipoLider;

    // arreglos importantes
    private static final String[] lideres = {"Brock", "Misty", "Lt. Surge", "Erika", "Sabrina", "Blaine"};
    private static final String[] medallas = {"Medalla Roca", "Medalla Cascada", "Medalla Trueno", "Medalla Arcoíris", "Medalla Pantano", "Medalla Volcán"};

    private char[][] mapa;
    private int filaJugador;
    private int columnaJugador;

    // Constructor
    public Gimnasio(String nombreCiudad) {
        super("Gimnasio de " + nombreCiudad);

        int indice = (int) (Math.random() * lideres.length);
        this.nombreLider = lideres[indice];
        this.nombreMedalla = medallas[indice];
        this.medallaObtenida = false;

        this.entrenadoresDerrotados = new boolean[3];
        this.equiposEntrenadores = new Pokemon[3][];
        this.equipoLider = null;

        inicializarMapa();
    }

    // Aqui esta el mapa
    private void inicializarMapa() {
        this.mapa = new char[][]{
                {'y', 'y', 'y', 'y', 'y', 'y', 'y', 'L', 'y', 'y', 'y', 'y', 'y', 'y', 'y'},
                {'y', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'y'},
                {'y', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'y'},
                {'y', ' ', ' ', 'E', ' ', ' ', ' ', 'E', ' ', ' ', ' ', 'E', ' ', ' ', 'y'},
                {'y', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'y'},
                {'y', ' ', ' ', ' ', ' ', ' ', ' ', 'J', ' ', ' ', ' ', ' ', ' ', ' ', 'y'},
                {'y', 'y', 'y', 'y', 'y', 'y', 'y', 'S', 'y', 'y', 'y', 'y', 'y', 'y', 'y'}
        };
        // Se pone al jugador en la entrada
        this.columnaJugador = 5;
        this.filaJugador = 7;
    }

    // Este metodo es el que define que hace el jugador dentro de gimnasio
    public void interactuar(Jugador jugador, Scanner scanner) {
        // Mira si el jugador entro al gimnacio a travez de un booleano
        boolean dentro = true;
        System.out.println("Bienvenido al " + getNombre());


        // si dentro es verdadero el jugador podra moverse dentro del mapa
        while (dentro) {

            imprimirMapa();
            System.out.print("Acción [W/A/S/D para mover, 0 para salir]: ");
            String input = scanner.next().toUpperCase();

            // con cero se sale en caso de que asi lo queira el jugador
            if (input.equals("0")) {
                dentro = false;
                continue;
            }

            // Aqui definimos el movimiento
            char movimiento = input.charAt(0);
            int nuevoY = columnaJugador;
            int nuevoX = filaJugador;

            // se suman y se restan en la matris
            switch (movimiento) {
                case 'W' -> nuevoY--;
                case 'S' -> nuevoY++;
                case 'A' -> nuevoX--;
                case 'D' -> nuevoX++;
                default -> {
                    System.out.println("Opcion invalida, usa W,A,S,D");
                    continue;
                }
            }


            char destino = mapa[nuevoY][nuevoX];

            // si en donde va el jugador hay un muro no avanza
            switch (destino) {
                case 'y' -> System.out.println("Hay un muro en esa direccion");
                // si hay un espacio vacio el jugador puede moverse y su nueva posision
                case ' ' -> {
                    mapa[columnaJugador][filaJugador] = ' ';
                    columnaJugador = nuevoY;
                    filaJugador = nuevoX;
                    mapa[columnaJugador][filaJugador] = 'J';
                }
                // llama a los otros metodos
                case 'E' -> entrenador(nuevoY, nuevoX, jugador, scanner);
                case 'L' -> lider(jugador, scanner);
                case 'S' -> {
                    System.out.println("has salido del Gimnasio");
                    dentro = false;
                }
            }
        }
    }

    //
    private String entrenador(int fila, int columna, Jugador jugador, Scanner scanner) {
        // Aqui se declara la posicion de los entrenadores
        int posicionEntrenadores = posicionEntrenadores(columna);
        // si derrotamos al entrenador ya no podemos luchar nuevamente con el
        if (entrenadoresDerrotados[posicionEntrenadores]) {
            return "Entrenador " + (posicionEntrenadores + 1) + ": 'Ya me has derrotado en combate.'";
        }

        // si no se ha derrotado al entrenador pokemon se Crea un nuevo entrenador y se coloca en la casilla a la que vaya el jugador
        if (equiposEntrenadores[posicionEntrenadores] == null) {
            equiposEntrenadores[posicionEntrenadores] = generarEquipoNPC(jugador, 0.6);
        }

        System.out.println("El Entrenador " + (posicionEntrenadores + 1) + " te desafía");
        // llamamos al submetodo de batalla
        Batalla batalla = new Batalla(jugador, equiposEntrenadores[posicionEntrenadores], Batalla.TipoBatalla.entrenador, "Entrenador " + (posicionEntrenadores + 1), scanner);

        // Aqui estan las condiciones ve victoria
        boolean victoria = batalla.iniciar();

        if (victoria) {
            entrenadoresDerrotados[posicionEntrenadores] = true;
            return "Derrotaste al Entrenador " + (posicionEntrenadores + 1);
        } else {
            return "Fuiste derrotado";
        }
    }

    private String lider(Jugador jugador, Scanner scanner) {
        // si el jugador ya derroto al lider
        if (medallaObtenida) {
            return "Líder " + nombreLider + ": Ya posees la " + nombreMedalla + " Demuestra tu fuerza en otros gimnasios";
        }

        // si el jugador no ha derrotado a los otros pelones
        for (boolean derrotado : entrenadoresDerrotados) {
            if (!derrotado) {
                return "Líder " + nombreLider + ": 'Primero debes derrotar a mis 3 entrenadores del gimnasio.'";
            }
        }

        // si el lider no teine un equipo aqui lo crea
        if (equipoLider == null) {
            equipoLider = generarEquipoNPC(jugador, 0.75);
        }

        // llama al metodo de batalla
        System.out.println("El Líder de Gimnasio " + nombreLider + " entra en combate");
        Batalla batalla = new Batalla(jugador, equipoLider, nombreLider, nombreMedalla, scanner);

        // aqui mira si el lider ha ganado o perdido
        boolean victoria = batalla.iniciar();
        if (victoria) {
            this.medallaObtenida = true;
            return "Felicidades, Venciste a " + nombreLider + " y ganaste la " + nombreMedalla + ".";
        } else {
            return "El Líder " + nombreLider + " te ha derrotado. Inténtalo de nuevo.";
        }
    }

    // Aqui se genera el equipo de los entrenadores
    private Pokemon[] generarEquipoNPC(Jugador jugador, double M) {
        // se hace una suma de uno y un numero aleatorio segun la cantidad de pokemotes obtenidos por el jugador
        int cantidadNPC = 1 + (int) (Math.random() * jugador.getCantidadPokemon());
        // aqui guardamos el pokemon
        Pokemon[] equipoNPC = new Pokemon[cantidadNPC];

        for (int i = 0; i < cantidadNPC; i++) {
            // aqui se guarda el pokemon en el equipo
            equipoNPC[i] = generarPokemonAleatorio(M, jugador);
        }

        return equipoNPC;
    }

    // se genera el pokemon del rival
    private Pokemon generarPokemonAleatorio(double M, Jugador jugador) {
        int sumaNiveles = 0;

        for (Pokemon p : jugador.getEquipo()) {
            if (p != null) {
                sumaNiveles += p.getNivel();
            }
        }
        // Aqui se dictamina el nivle del pokemon segun la formula dada
        int nivelFinal = (int) Math.max(1, Math.round(((double) sumaNiveles * M) / jugador.getCantidadPokemon()));

        // random para los pokemones
        Random random = new Random();
        int opcion = random.nextInt(25);

        // aqui se accede a uno de los pokemones
        return switch (opcion) {
            case 0 -> new Bulbasaur(nivelFinal);
            case 1 -> new Inysaur(nivelFinal);
            case 2 -> new Venasaur(nivelFinal);
            case 3 -> new Charmander(nivelFinal);
            case 4 -> new Charmeleon(nivelFinal);
            case 5 -> new Charizard(nivelFinal);
            case 6 -> new Squirtle(nivelFinal);
            case 7 -> new Wartortle(nivelFinal);
            case 8 -> new Blastoise(nivelFinal);
            case 9 -> new Caterpie(nivelFinal);
            case 10 -> new Metapod(nivelFinal);
            case 11 -> new Butterfree(nivelFinal);
            case 12 -> new Weedle(nivelFinal);
            case 13 -> new Kakuna(nivelFinal);
            case 14 -> new Beedrill(nivelFinal);
            case 15 -> new Pidgey(nivelFinal);
            case 16 -> new Pidgeotto(nivelFinal);
            case 17 -> new Pidgeot(nivelFinal);
            case 18 -> new Rattata(nivelFinal);
            case 19 -> new Raticate(nivelFinal);
            case 20 -> new Spearow(nivelFinal);
            case 21 -> new Fearow(nivelFinal);
            case 22 -> new Ekans(nivelFinal);
            case 23 -> new Arbok(nivelFinal);
            case 24 -> new Pikachu(nivelFinal);
            default -> new Bulbasaur(nivelFinal);
        };
    }

    // aqui ponemos los entrenadores en onde los queremos
    private int posicionEntrenadores(int columna) {
        if (columna == 3) return 0;
        if (columna == 7) return 1;
        return 2;
    }


    private void imprimirMapa() {
        System.out.println("---------------------------------------------");
        System.out.println("          " + getNombre()  + "         ");
        System.out.println("---------------------------------------------");

        //
        for (int f = 0; f < mapa.length; f++) {
            for (int c = 0; c < mapa[f].length; c++) {
                char celda = mapa[f][c];
                // switch para ver que tecla es
                switch (celda) {
                    case 'E' -> {
                        int numE = posicionEntrenadores(c) + 1;
                        System.out.print("E" + numE + " ");
                    }
                    case 'L', 'S', 'J' -> System.out.print(" " + celda + " ");
                    case 'y' -> System.out.print(" y ");
                    default  -> System.out.print("   ");
                }
            }
            System.out.println();
        }

        // Mestra como navegar
        System.out.println("Controles: [W] Arriba | [S] Abajo | [A/D] Izq/Der");
        System.out.println("Leyenda: J=Jugador, L=Líder (" + nombreLider + "), E=Entrenador, S=Salida");
    }

    public String getNombreLider() { return nombreLider; }
    public String getNombreMedalla() { return nombreMedalla; }
    public boolean isMedallaObtenida() { return medallaObtenida; }
}