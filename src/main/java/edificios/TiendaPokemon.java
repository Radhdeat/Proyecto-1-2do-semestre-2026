package edificios;

import java.util.Scanner;
import jugador.Jugador;
import objetos.*;

public class TiendaPokemon extends Edificios {
    private static final long serialVersionUID = 1L;

    // constuctor
    public TiendaPokemon() {
        super("Tienda Pokémon");
    }

    // interactuar
    public void interactuar(Jugador jugador, Scanner scanner) {
        int opcion;

        do {
            mostrarCatalogo(jugador.getPokemonedas());
            System.out.print("Selecciona una opción: ");
            opcion = scanner.nextInt();

            // si el jugador escogue algo dentro de los parametros hace la compra
            if (opcion >= 1 && opcion <= 6) {
                procesarCompra(opcion, jugador, scanner);
                // si no no hace nada
            } else if (opcion != 0) {
                System.out.println("Opción no válida. Intenta de nuevo.");
            }
            //y con 0 cierra
        } while (opcion != 0);

        System.out.println("Gracias por tu visita a la Tienda Pokémon");
    }

    public void mostrarCatalogo(int pokemonedasJugador) {
        System.out.println("-------------------------------------------------");
        System.out.println("           TIENDA POKÉMON - CATÁLOGO             ");
        System.out.println("-------------------------------------------------");
        System.out.println("Tus Pokémonedas: P" + pokemonedasJugador);
        System.out.println("-------------------------------------------------");
        System.out.println("1. Pokébola      - P200 (Sirve para atrapar pokémon salvajes)");
        System.out.println("2. Poción        - P300 (Restaura 20 HP)");
        System.out.println("3. Superpoción   - P600 (Restaura 50 HP)");
        System.out.println("4. Antídoto      - P100 (Cura envenenamiento)");
        System.out.println("5. Antiparaliz   - P200 (Cura parálisis)");
        System.out.println("6. Restaura todo - P700 (Sana HP completo y estados)");
        System.out.println("0. Salir de la tienda");
        System.out.println("-------------------------------------------------");

    }

    private void procesarCompra(int opcion, Jugador jugador, Scanner scanner) {
        int precio = 0;
        Objeto objetoAComprar = null;

        switch (opcion) {
            case 1:
                precio = 200;
                objetoAComprar = new Pokebola(1);
                break;
            case 2:
                precio = 300;
                objetoAComprar = new Pocion(1);
                break;
            case 3:
                precio = 600;
                objetoAComprar = new Superpocion(1);
                break;
            case 4:
                precio = 100;
                objetoAComprar = new Antidoto(1);
                break;
            case 5:
                precio = 200;
                objetoAComprar = new Antiparaliz(1);
                break;
            case 6:
                precio = 700;
                objetoAComprar = new RestauraTodo(1);
                break;
        }

        System.out.print("¿Cuántas unidades deseas comprar?: ");
        int cantidad = scanner.nextInt();

        if (cantidad <= 0) {
            System.out.println("La cantidad debe ser mayor a 0.");
            return;
        }

        // por si el jugador compra mas
        int costoTotal = precio * cantidad;

        // si el jugador
        if (jugador.getPokemonedas() >= costoTotal) {
            jugador.setPokemonedas(jugador.getPokemonedas() - costoTotal);
            jugador.getMochila().agregarObjeto(objetoAComprar, cantidad);
            System.out.println("¡Compra exitosa! Obtuviste " + cantidad + "x " +
                    objetoAComprar.getNombre() + " por P" + costoTotal + ".");
        } else {
            System.out.println("No tienes suficientes Pokémonedas. Te faltan P" +
                    (costoTotal - jugador.getPokemonedas()) + ".");
        }
    }
}