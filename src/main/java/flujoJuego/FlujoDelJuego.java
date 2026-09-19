package flujoJuego;

import java.util.Scanner;

// se hace publico para que el main pueda acceder
public class FlujoDelJuego {

    // se declaran los atributos
    private Partida partida;
    private Scanner scanner;

    // se crea un constructor
    protected FlujoDelJuego() {
        // this se usa para decir que el atributo es del objeto (basicamente se instancia)
        this.partida = new Partida();
        this.scanner = new Scanner(System.in);
    }

    // el sub metodo que el main va a usar
    protected void menuPrincipal() {

        // variable que recibe el switch
        int opcionSalida;

        do {
            // Bienvenida al juego
            System.out.println();
            System.out.println("---------------------------------------");
            System.out.println("      BIENVENIDO AL JUEGO POKEMON");
            System.out.println("---------------------------------------");
            System.out.println("        1. Nueva Partida");
            System.out.println("        2. Cargar Partida");
            System.out.println("        3. Cerrar Juego");
            System.out.println("---------------------------------------");
            System.out.print("Selecciona una opcion: ");

            // se eregistra la opcion que eligio el usuario
            opcionSalida = scanner.nextInt();
            scanner.nextLine();

            // Aqui llama a los metodos dependiendo la eleccion
            switch (opcionSalida) {

                case 1:
                    partida.nuevaPartida();
                    break;

                case 2:
                    partida.cargarPartida();
                    break;

                case 3:
                    System.out.println();
                    System.out.println("Saliendo del juego...");
                    break;

                default:
                    System.out.println();
                    System.out.println("Opcion invalida.");
                    break;
            }
            // el ciclo se cierre si la opcion es 3
        } while (opcionSalida != 3);
    }
}