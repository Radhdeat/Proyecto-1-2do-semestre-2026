package flujoJuego;

import java.util.Random;
import java.util.Scanner;
import jugador.Jugador;
import pokemon.EstadoPokemon;
import pokemon.Movimiento;
import pokemon.Pokemon;

public class Batalla {

    // se usa public aunque se rompa el encapsulamiento pero asi pueden otras clases llamar este metodo
    public enum TipoBatalla {salvaje, entrenador, lider}

    // se declaran los atributos de las clases
    private Jugador jugador;
    private Pokemon[] equipoRival;
    private TipoBatalla tipo;
    private String nombreRival;
    private String medallaRecompensa;
    private Scanner scanner;
    private Random random;

    private int identificadorPokemon;
    private int identificadorRival;

    // Constructor para encuentros con Pokémon salvajes
    public Batalla(Jugador jugador, Pokemon pokemonSalvaje) {
        this.jugador = jugador;
        this.equipoRival = new Pokemon[]{ pokemonSalvaje };
        this.tipo = TipoBatalla.salvaje;
        this.nombreRival = pokemonSalvaje.getApodo() + " salvaje";
        this.scanner = new Scanner(System.in);
        this.random = new Random();
        this.medallaRecompensa = null;
    }

    // Constructor para combates contra Entrenadores comunes
    public Batalla(Jugador jugador, Pokemon[] equipoRival, TipoBatalla tipo, String nombreRival, Scanner scanner) {
        this.jugador = jugador;
        this.equipoRival = equipoRival;
        this.tipo = tipo;
        this.nombreRival = nombreRival;
        this.scanner = scanner;
        this.random = new Random();
        this.medallaRecompensa = null;
    }

    // Constructor para combate contra Líder de Gimnasio
    public Batalla(Jugador jugador, Pokemon[] equipoRival, String nombreLider, String medalla, Scanner scanner) {
        this(jugador, equipoRival, TipoBatalla.lider, nombreLider, scanner);
        this.medallaRecompensa = medalla;
    }

    // metodo que inicia el combate
    public boolean iniciar() {

        identificadorPokemon = obtenerSiguientePokemonVivo(jugador.getEquipo(), 0);
        identificadorRival = obtenerSiguientePokemonVivo(equipoRival, 0);

        // si el identificador pokemon es menor a 0 es muestra que no existe un pokemon
        if (identificadorPokemon == -1) {
            System.out.println("¡No tienes ningún Pokémon consciente para luchar!");
            return false;
        }

        // Dialogo inicio
        System.out.println("-------------------------------------------------");
        System.out.println(" Comienza la batalla contra " + nombreRival);
        System.out.println("-------------------------------------------------");

        // se va a repetir hasta que a los dos les quede mas de un pokemon
        while (identificadorPokemon != -1 && identificadorRival != -1) {
            // muestra los pokemon de los dos
            Pokemon pokemonJugador = jugador.getEquipo()[identificadorPokemon];
            Pokemon pokemonRival = equipoRival[identificadorRival];

            // Muestra el estado del combate
            mostrarEstadoCombate(pokemonJugador, pokemonRival);

            // menu
            System.out.println("--------------------------");
            System.out.println("     ¿Qué deseas hacer?");
            System.out.println("--------------------------");
            System.out.println("       1. Luchar");
            System.out.println("       2. Cambiar Pokémon");
            System.out.println("       3. Mochila");
            System.out.println("       4. Huir");
            System.out.print("       Selección: ");

            int opcion = scanner.nextInt();

            // aqui esta la logica tras batalla
            switch (opcion) {
                // se llama al metodo de ataque pokemon
                case 1 -> {
                    Movimiento movimientoJugador = seleccionarMovimiento(pokemonJugador);

                    // si jugador hace un movimiento entonces llama al metodo de ataque
                    if (movimientoJugador != null) {
                        ejecutarTurnoAtaque(pokemonJugador, pokemonRival, movimientoJugador);
                    }
                }
                case 2 -> {
                    // si el jugador cambia de pokemon llama al metodo correspondiente
                    if (cambiarPokemon(false)) {
                        ejecutarAtaqueRival(pokemonRival, jugador.getEquipo()[identificadorPokemon]);
                    }
                }
                case 3 -> {
                    // si el jugador usa mochila llama a la mochila =
                    if (usarObjetoOMochila(pokemonJugador, pokemonRival)) {
                        return true;
                    }
                    // despues de usarla el rival ataca
                    ejecutarAtaqueRival(pokemonRival, pokemonJugador);
                }
                case 4 -> {
                    // si el jugador intenta huir llama al metodo de huir
                    if (intentarHuir(pokemonJugador, pokemonRival)) {
                        return false;
                    }
                }
                // si falla sigue en la batalla
                default -> System.out.println("Opción no válida.");
            }

            verificarDebilitados(pokemonJugador, pokemonRival);
        }

        return procesarFinBatalla();
    }

    // Aqui se define que hace por ataque
    private void ejecutarTurnoAtaque(Pokemon pokemonJugador, Pokemon pokemonRival, Movimiento movJugador) {
        // iguala el movimiento del rival con los movimientos disponibles
        Movimiento[] movimientosRival = pokemonRival.getMovimientos();
        Movimiento movimientoRival = movimientosRival[random.nextInt(movimientosRival.length)];

        // Se llama al submetodo que determina cual pokemon ataca primero
        boolean jugadorPrimero = determinarPrioridad(pokemonJugador, movJugador, pokemonRival, movimientoRival);

        // si el jugador ataca primero llama al submetodo de ataque
        if (jugadorPrimero) {
            resolverAtaque(pokemonJugador, pokemonRival, movJugador);
            // si la salud del pokemon rival es mayor a 0 entonces el atacara despues
            if (pokemonRival.getSaludActual() > 0) {
                resolverAtaque(pokemonRival, pokemonJugador, movimientoRival);
            }
            // pero si es mas rapido el rival
        } else {
            // ataca primero
            resolverAtaque(pokemonRival, pokemonJugador, movimientoRival);
            // y despues el jugador
            if (pokemonJugador.getSaludActual() > 0) {
                resolverAtaque(pokemonJugador, pokemonRival, movJugador);
            }
        }

        // si la vida de los entrenadores es mayor a 0
        if (pokemonRival.getSaludActual() > 0 && pokemonJugador.getSaludActual() > 0) {
            // entonces se aplica uno de los efectos
            pokemonRival.aplicarEfectoDrenadoras(pokemonJugador);
            pokemonJugador.aplicarEfectoDrenadoras(pokemonRival);
        }
    }

    // Aqui se determina cual pokemon ataca primero
    private boolean determinarPrioridad(Pokemon pokemon1, Movimiento movimiento1, Pokemon pokemon2, Movimiento movimiento2) {
        // se determina si alguno usa ataque rapido
        boolean m1Rapido = movimiento1.getNombre().equalsIgnoreCase("Ataque Rápido") || movimiento1.getNombre().equalsIgnoreCase("Ataque Rapido");
        boolean m2Rapido = movimiento2.getNombre().equalsIgnoreCase("Ataque Rápido") || movimiento2.getNombre().equalsIgnoreCase("Ataque Rapido");

        // si alguno usa ataque rapido se le da prioridad
        if (m1Rapido && !m2Rapido) return true;
        if (!m1Rapido && m2Rapido) return false;

        // si los pokemones tienen la misma velocidad
        if (pokemon1.getVelocidad() == pokemon2.getVelocidad()) {
            return random.nextBoolean();
        }
        return pokemon1.getVelocidad() > pokemon2.getVelocidad();
    }

    // Para ver si alguno uso proteger
    private void resolverAtaque(Pokemon atacante, Pokemon defensor, Movimiento movimiento) {
        if (defensor.esProtegido()) {
            System.out.println(" " + defensor.getApodo() + " se protegió del ataque.");
            defensor.setProtegido(false);
            return;
        }
        // si se hace el movimiento entonces se llama al metodo atacar
        if (movimiento != null) {
            movimiento.ejecutar(atacante, defensor);
        }
    }

    // metodo para que el rival ataque
    private void ejecutarAtaqueRival(Pokemon pokemonRival, Pokemon pokemonJugador) {
        // si la vida de los entrenadores es mayor a 0
        if (pokemonRival.getSaludActual() > 0 && pokemonJugador.getSaludActual() > 0) {
            // entonces se usa uno de los movimienotos que tiene el pokemon rival
            Movimiento[] movimientosRival = pokemonRival.getMovimientos();
            Movimiento movRival = movimientosRival[random.nextInt(movimientosRival.length)];
            resolverAtaque(pokemonRival, pokemonJugador, movRival);
        }
    }

    // metodo para cambiar pokemon
    private boolean cambiarPokemon(boolean forzado) {

        System.out.println("---------------------");
        System.out.println("     TUS POKÉMON     ");
        System.out.println("---------------------");
        Pokemon[] equipo = jugador.getEquipo();

        // se imprimen los pokemon
        for (int i = 0; i < equipo.length; i++) {
            if (equipo[i] != null) {
                System.out.println((i + 1) + ". " + equipo[i].getApodo() + " [HP: "
                        + equipo[i].getSaludActual() + "/" + equipo[i].getSaludMaxima() + "]");
            }
        }
        // se imprimen las instrucciones
        System.out.print("Selecciona el Pokémon a enviar" + (forzado ? ": " : " (0 para cancelar): "));
        int eleccion = scanner.nextInt() - 1;

        // si el pokemon sale entonces se le resta menos unos para que agarre el qeu es
        if (!forzado && eleccion == -1) return false;

        // si la eleccion del jugador estadentro del rango y es distinta a 0
        if (eleccion >= 0 && eleccion < equipo.length && equipo[eleccion] != null) {

            // si la vida del pokemon llega a 0 o menos
            if (equipo[eleccion].getSaludActual() <= 0) {
                //entonces imprime
                System.out.println("Ese Pokémon está debilitado");
                return cambiarPokemon(forzado);
            }
            // si el pokemon es mismo que ya esta en batalla
            if (eleccion == identificadorPokemon) {
                // entonces imprime esto
                System.out.println("¡Ese Pokémon ya está en combate!");
                return cambiarPokemon(forzado);
            }
            // si todo esta bien entocnes imprime
            identificadorPokemon = eleccion;
            System.out.println("Adelante " + equipo[identificadorPokemon].getApodo());
            return true;
        }

        // en caso de qeu el jugar selecciones otra cosa
        System.out.println("Opción no válida.");
        return cambiarPokemon(forzado);
    }

    private boolean usarObjetoOMochila(Pokemon pj, Pokemon pr) {
        System.out.println("1. Usar Objeto de la Mochila");
        System.out.println("2. Lanzar Pokébola");
        System.out.print("Selección: ");
        int subOpcion = scanner.nextInt();

        // si intenta atrapar con un entrenador
        if (subOpcion == 2) {
            if (tipo != TipoBatalla.salvaje) {
                System.out.println("¡No puedes atrapar el Pokémon de otro entrenador!");
                return false;
            }
            // si el pokemon ya tiene mas de 6 pokemones y quiere seguir usando pokebolas
            if (jugador.getCantidadPokemon() >= 6) {
                System.out.println("Tu equipo está lleno (máximo 6 Pokémon).");
                return false;
            }
            return intentarCapturar(pr);
            // llama al sumbetodo de mochila
        } else if (subOpcion == 1) {
            jugador.getMochila().mostrarMochila();
            System.out.println("Usaste un objeto de la mochila.");
        }
        return false;
    }

    // intentar capturar
    private boolean intentarCapturar(Pokemon pokemonRival) {
        // si el jugador no tiene pokebolas

        if (jugador.getPokebolas() <= 0) {
            System.out.println("No tienes Pokébolas en tu mochila.");
            return false;
        }

        // llama al metodo para usar la pokebola
        jugador.usarPokebola();
        // muestra cuantas quedan
        System.out.println("Lanzaste una Pokébola (Restantes: " + jugador.getPokebolas() + ")");

        // esta la probabilidad de atrapar un pokemon
        double porcentajeHP = (double) pokemonRival.getSaludActual() / pokemonRival.getSaludMaxima();
        double probabilidad = (1.0 - porcentajeHP) * 0.7 + 0.20;

        // si el pokemon no tiene ningun efecto entonces tiene la probabilidad se le suma el 15% para atraparlo
        if (pokemonRival.getEstado() != EstadoPokemon.NORMAL) {
            probabilidad += 0.15;
        }

        // si la probabilidad es mas grande que el numero generado entonces captura al pokemon
        if (Math.random() < probabilidad) {
            System.out.println("Atrapado " + pokemonRival.getApodo() + " ha sido capturado");
            jugador.agregarPokemon(pokemonRival);
            return true;
        } else {
            // si no el pokemon se sale
            System.out.println("El Pokémon se ha salido de la Pokébola");
            return false;
        }
    }

    // Si intentas huir de un combate contra NPC
    private boolean intentarHuir(Pokemon pokemonJugador, Pokemon pokemonRival) {
        if (tipo != TipoBatalla.salvaje) {
            System.out.println("¡No puedes huir de una batalla contra un entrenador!");
            return false;
        }

        // si el rival es mas rapido ataca y despues escapas
        if (pokemonRival.getVelocidad() > pokemonJugador.getVelocidad()) {
            System.out.println("El rival es más rápido. Te ataca antes de poder huir");
            ejecutarAtaqueRival(pokemonRival, pokemonJugador);
            // si la vida del jugador es menor a  se acaba la partida sin cambiar pokemon
            if (pokemonJugador.getSaludActual() <= 0) {
                return false;
            }
        }

        System.out.println("Has escapado con éxito");
        return true;
    }

    // aqui se define si el pokemon esta debilitado
    private void verificarDebilitados(Pokemon pokemonJugador, Pokemon pokemonRival) {

        // si la vida del pokemon rival baja a 0 entonces da experiencia
        if (pokemonRival.getSaludActual() <= 0) {
            System.out.println(pokemonRival.getApodo() + " enemigo se ha debilitado!");
            otorgarExperiencia(pokemonJugador, pokemonRival);

            // por en caso de que sea un entrenador pokemon se pasa al siguiente pokemon
            identificadorRival = obtenerSiguientePokemonVivo(equipoRival, identificadorRival + 1);
            // mientras no sea menos uno el rival sacara otro pokemon
            if (identificadorRival != -1) {
                System.out.println(nombreRival + " envía a " + equipoRival[identificadorRival].getApodo() + ".");
            }
        }

        // si la salud del pokemon actual es menor a 0 entonces tu pokemon pierde
        if (pokemonJugador.getSaludActual() <= 0) {
            System.out.println("Tu " + pokemonJugador.getApodo() + " se ha debilitado");
            // Si el jugador tiene otro pokemon entonces aqui lo saca
            int siguiente = obtenerSiguientePokemonVivo(jugador.getEquipo(), 0);
            if (siguiente != -1) {
                cambiarPokemon(true);
            } else {
                // si el identificador baja a -1 entonces el jugador pierde
                identificadorPokemon = -1;
            }
        }
    }

    // aqui esta la experiencia ganada
    private void otorgarExperiencia(Pokemon ganador, Pokemon perdedor) {

        int expGanada = perdedor.getNivel() * 15;
        System.out.println(ganador.getApodo() + " ganó " + expGanada + " puntos de EXP");
    }

    private boolean procesarFinBatalla() {
        if (identificadorPokemon == -1) {
            System.out.println("-----------------------------------");
            System.out.println(" TODOS TUS POKÉMON SE DEBILITARON");
            System.out.println("------------------------------------");

            // si la batalla no es salvaje entonces el jugador le da la mitad de su monedas al rival
            if (tipo != TipoBatalla.salvaje) {
                int perdida = jugador.getPokemonedas() / 2;
                jugador.setPokemonedas(jugador.getPokemonedas() - perdida);
                System.out.println("Le diste P" + perdida + " al entrenador rival.");
            }

            // En caso de que todos tus pokemon pierdan se llevara al centro pokemon
            System.out.println("Fuiste llevado automáticamente al Centro Pokémon. Tu equipo se ha curado.");
            curarEquipoJugador();
            return false;
        }

        // pero si ganas te dan dinero
        System.out.println("Has ganado la batalla");

        if (tipo != TipoBatalla.salvaje) {
            // formula para tener un numero entre 150 a 500
            int premio = 150 + random.nextInt(351); // Recompensa entre P150 y P500
            jugador.setPokemonedas(jugador.getPokemonedas() + premio);
            System.out.println("Has recibido P" + premio + " de recompensa");

            // si la batalla es contra un lider entonces el te dara su medalla
            if (tipo == TipoBatalla.lider && medallaRecompensa != null) {
                System.out.println("Obtuviste la " + medallaRecompensa);
            }
        }
        return true;
    }

    // Se cura el pokemon en combate
    private void curarEquipoJugador() {
        for (Pokemon p : jugador.getEquipo()) {
            if (p != null) {
                p.setSaludActual(p.getSaludMaxima());
                p.setEstado(EstadoPokemon.NORMAL);
            }
        }
    }

    // metodo para obtener otro pokemon vivo
    private int obtenerSiguientePokemonVivo(Pokemon[] equipo, int inicio) {
        for (int i = inicio; i < equipo.length; i++) {
            // si el equipo no esta vacio y tienen vida entonces los llama
            if (equipo[i] != null && equipo[i].getSaludActual() > 0) return i;
        }
        // menos uno para hacerlo mas intuitivo
        return -1;
    }

    // Muestra el estado del combate
    private void mostrarEstadoCombate(Pokemon pj, Pokemon pr) {
        System.out.println(" " + pr.getApodo() + " [Nvl " + pr.getNivel() + "] HP: "
                + pr.getSaludActual() + "/" + pr.getSaludMaxima());
        System.out.println(" " + pj.getApodo() + " [Nvl " + pj.getNivel() + "] HP: "
                + pj.getSaludActual() + "/" + pj.getSaludMaxima());
    }

    // se selecciona el movimiento que va a usar el jugador
    private Movimiento seleccionarMovimiento(Pokemon pokemon) {
        Movimiento[] movimientos = pokemon.getMovimientos();
        System.out.println("Elige un movimiento:");
        //recorre el arreglo
        for (int i = 0; i < movimientos.length; i++) {
            // si el pokemon tiene movimientos entonces imprimira
            if (movimientos[i] != null) {
                System.out.println((i + 1) + ". " + movimientos[i].getNombre() + " (Pot: " + movimientos[i].getPotencia() + ")");
            }
        }
        // la seleccion intuitiva
        System.out.print("Selección: ");
        int eleccion = scanner.nextInt() - 1;

        if (eleccion >= 0 && eleccion < movimientos.length && movimientos[eleccion] != null) {
            return movimientos[eleccion];
        }
        System.out.println("Movimiento inválido.");
        return null;
    }
}
