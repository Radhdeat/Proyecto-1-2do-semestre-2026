package flujoJuego;

// ya que en partida esta la logica del juego se importan las otras carpetas para poder usarlas

// se importa para poder guardar los datos de la partida
import java.io.FileInputStream;
// se importa para poder leer los datos de la partida
import java.io.FileOutputStream;
// manejar errores de archivos
import java.io.IOException;
// cargar archivos
import java.io.ObjectInputStream;
// Permite guardar objetos
import java.io.ObjectOutputStream;
// permite que la clase pueda guardarse como objeto
import java.io.Serializable;
import java.util.Random;
import java.util.Scanner;
import jugador.Jugador;
import objetos.Pocion;
import objetos.Pokebola;
// aqui se importan todas las clases de la carpeta pokemon
import pokemon.*;

// el implemest serializable se usa para poder convertir partida en datos
public class Partida implements Serializable {

    // Basicamente es un indicador de la version del serializador y se usa L porque asi se guarda como 64 bits
    private static final long serialVersionUID = 1L;

    // se instancian todos los atributos del objeto
    private String nombrePartida;
    private Jugador jugador;

    private transient Scanner scanner;

    private Ciudad[] ciudades;
    private Ciudad ciudadActual;

    private int batallasSalvajes;
    private int batallasEntrenadores;
    private int pokebolasLanzadas;
    private int pokemonCapturados;
    private int entrenadoresDerrotados;

    // Escaner que se va a usar en esta clase
    protected Partida() {
        this.scanner = new Scanner(System.in);
    }

    // se usa un getScanner ya que asi obtenemos un valor
    private Scanner getScanner() {
        if (scanner == null) {
            scanner = new Scanner(System.in);
        }
        return scanner;
    }

    // Este metodo es para cuando el jugador crea una nueva partida
    protected void nuevaPartida() {

        // se instancian todas las variables que utilizaremos
        this.batallasSalvajes = 0;
        this.batallasEntrenadores = 0;
        this.pokebolasLanzadas = 0;
        this.pokemonCapturados = 0;
        this.entrenadoresDerrotados = 0;

        // llamamos al metodo para crear ciudades
        crearCiudades();

        // ya que nos piden que las ciudades sean aleatorias aqui metemos las ciudades
        int numeroAleatorio = (int) (Math.random() * ciudades.length);
        this.ciudadActual = ciudades[numeroAleatorio];

        System.out.println("Bienvenido al juego de pokemon");
        System.out.println("En este juego ganaras al derrotar a los 3 jefes de gimnasio");
        // llamamos a los submetodos que hacen que el juego funcione
        bienvenidaOAK();
        jugarMapa();
        datosPartida();
    }

    // este metodo crea las ciudades
    private void crearCiudades() {

        // Aqui se guardan los nombres de las ciudades
        String[] nombres = {
                "Pueblo Paleta", "Ciudad Verde", "Ciudad Plateada",
                "Ciudad Celeste", "Ciudad Carmín"
        };

        // Ya teniendo las ciudades asignamos cuantas ciudades tienen que ser
        ciudades = new Ciudad[3];

        // aqui se define cual es la primera ciudad
        int ciudad1 = (int) (Math.random() * nombres.length);
        ciudades[0] = new Ciudad(nombres[ciudad1]);

        // La ciudad 2
        int ciudad2;

        // siempre se hara hasta encontrar un numero distinto a ciudad1 asi no se repiten
        do {
            ciudad2 = (int) (Math.random() * nombres.length);
        } while (nombres[ciudad2].equals(ciudades[0].getNombre()));
        ciudades[1] = new Ciudad(nombres[ciudad2]);

        // ciudad 3
        int ciudad3;
        // siempre se hara hasta encontrar un numero distinto a ciudad1 y ciudad 2
        do {
            ciudad3 = (int) (Math.random() * nombres.length);
        } while (nombres[ciudad3].equals(ciudades[0].getNombre()) ||
                nombres[ciudad3].equals(ciudades[1].getNombre()));
        ciudades[2] = new Ciudad(nombres[ciudad3]);
    }

    // bienvenida oak
    protected void bienvenidaOAK() {

        System.out.println("Has creado una nueva partida");
        System.out.print("Ingresa el nombre de la partida: ");
        this.nombrePartida = getScanner().nextLine();

        System.out.println("--------------------------------------");
        System.out.println("      Bienvenido joven entrenador     ");
        System.out.println("      Yo soy el Profesor OAK          ");
        System.out.println("--------------------------------------");
        System.out.println("Estas apunto de envarcarte a una aventura sin igual");
        System.out.println("En este mundo existen pokemones");

        System.out.print("Antes de iniciar, ingresa tu nombre: ");
        String nombreJugador = getScanner().nextLine();
        this.jugador = new Jugador(nombreJugador);

        // primero llamamos al Objeto mochila junto a su metodo de agregar objetos el la cual creamos 5 pokebolas
        this.jugador.getMochila().agregarObjeto(new Pokebola(5), 5);
        // y aqui una posion
        this.jugador.getMochila().agregarObjeto(new Pocion(1), 1);

        // llamamos al metodo de para elegir un pokemon
        System.out.println("Escoge a tu Pokémon inicial para comenzar:");
        pokemonSeleccionado();
    }

    // Aqui permiten seleccionar un pokemon
    protected void pokemonSeleccionado() {

        int opcion;
        do {
            System.out.println("----------------------------------");
            System.out.println("       Escoge tu inicial"          );
            System.out.println("----------------------------------");
            System.out.println("          1. Bulbasaur"            );
            System.out.println("          2. Charmander"           );
            System.out.println("          3. Squirtle"             );
            System.out.println("----------------------------------");

            System.out.print("Selecciona tu opción: ");

            // Se guarda la opcion
            opcion = getScanner().nextInt();
            getScanner().nextLine();

            // y entra en un switch
            switch (opcion) {
                case 1 -> {
                    System.out.println("Has elegido a Bulbasaur.");
                    // Una vez instanciado se llama al submetodo agregar jugador y se crea un objeto bulbasaur
                    this.jugador.agregarPokemon(new Bulbasaur());
                }
                case 2 -> {
                    System.out.println("Has elegido a Charmander.");
                    this.jugador.agregarPokemon(new Charmander());
                }
                case 3 -> {
                    System.out.println("Has elegido a Squirtle.");
                    this.jugador.agregarPokemon(new Squirtle());
                }
            }
            // el ciclo se repite si la opcion es mayor que 3 o menor de 1
        } while (opcion < 1 || opcion > 3);
    }

    // Este metodo es el que llama al mapa
    protected void jugarMapa() {

        // variable que representa al movimiento
        String movimiento;
        // Simpre se llamara al mapa y sus condicionales
        do {
            System.out.println();
            // Ya una vez definida la ciudad en la que estamos se llama al mapa
            ciudadActual.mostrarMapa();

            // Las instrucciones para hacer mas intuitivo el mapa
            System.out.println("[W/A/S/D] Mover | [1] Equipo | [2] Mochila | [3] Perfil | [4] Cambiar Ciudad | [5] Guardar | [6] Pokedex | [7] Salir");
            System.out.print("Opción: ");
            movimiento = getScanner().nextLine().toUpperCase();

            // dependiendo de lo que escoga el jugador entra al switch
            switch (movimiento) {
                // en caso de que el jugador escoga etas opciones se llama al sub metodo para mover al jugador
                case "W", "S", "A", "D" -> {
                    char casilla = ciudadActual.moverJugador(movimiento);
                    procesarCasilla(casilla);
                }
                // los otros casos xd
                case "1" -> equipoPokemon();
                case "2" -> mochila();
                case "3" -> perfil();
                case "4" -> cambiarCiudad();
                case "5" -> guardarPartida();
                case "6" -> pokedex();
                case "7" -> System.out.println("Saliendo del mapa...");
                default -> System.out.println("Opción inválida.");
            }
        } while (!movimiento.equals("7"));
    }

    // metodo para las casillas especiales
    private void procesarCasilla(char casilla) {
        // si el jugador cae en estas casillas llaman a los objetos corresponientes
        switch (casilla) {

            case 'C' -> {
                System.out.println("Entraste al Centro Pokémon.");
                ciudadActual.getCentroPokemon().interactuar(jugador,scanner);
            }
            case 'T' -> {
                System.out.println("Entraste a la Tienda Pokémon.");
                ciudadActual.getTiendaPokemon().interactuar(this.jugador, this.scanner);
            }
            case 'G' -> {
                System.out.println("Entraste al Gimnasio de " + ciudadActual.getNombre());
                ciudadActual.getGimnasio().interactuar(jugador, scanner);
                System.out.println("Regresaste a la ciudad");
            }

            case '"' -> {
                // Aui esta la probabilidad de la hierba alta
                probabilidadHierbaAlta();
            }
            default -> {}
        }
    }

    private void probabilidadHierbaAlta() {
        // Se crea un Random que utilizaremos
        Random aleatorio = new Random();

        // Aqui eta la probabilidad del 15% de aparicion
        if (aleatorio.nextInt(100) <15) {
            System.out.println("Ha aparecido un pokemon salvaje");
            batallaSalvaje();
        }else  {
            System.out.println();
        }
    }

    protected void pokedex(){


    }

    // Este metodo es para cambiar ciudad
    private void cambiarCiudad() {

        System.out.println("---------------------------------------");
        System.out.println("          CAMBIAR DE CIUDAD");
        System.out.println("---------------------------------------");

        // solo es para imprimir el mapa de las ciudades
        for (int i = 0; i < ciudades.length; i++) {
            System.out.println("         " + (i + 1) + ". " + ciudades[i].getNombre());
        }
        System.out.println("         0. Cancelar");
        System.out.print("Seleccione una ciudad: ");

        // se guarda la opcion elegida
        int opcion = getScanner().nextInt();
        getScanner().nextLine();

        // Basicamente se mira que el jugador escoga una opcion posible
        if (opcion > 0 && opcion <= ciudades.length) {
            // aqui lo hacemos mas natural ponendo -1
            this.ciudadActual = ciudades[opcion - 1];
            System.out.println("Has viajado a: " + ciudadActual.getNombre());
        }
    }

    protected void guardarPartida() {
        /*
        // Basicamente El nombre de Partida se convertira el nombre del archivo donde se guardara
        // El FileOutput Sirve para abrir o crear el archivo donde se van a escribir los datos
        // ObjetOutout es para guardar los objetos en archivos
        // writeObject basicamente guarda el objeto completo en el archivo de partida
        */
        // Gracias al try el objetOuput se cierra cuando termina de usarse
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nombrePartida + ".dat"))) {
            oos.writeObject(this);
            System.out.println("Partida '" + nombrePartida + "' guardada con éxito");
            // el catch es para que evitar errores por en caso de que no haya o no pueda acceder al archivo
        } catch (IOException e) {
            System.out.println("Error al guardar la partida: " + e.getMessage());
        }
    }

    // este metodo es para cargar la partida
    protected void cargarPartida() {
        // esto es importante ya que pedimos el nombre de la partida ya que asi podemos llamar a los datos
        System.out.print("Escriba el nombre de la partida que deseas cargar: ");
        String nombre = getScanner().nextLine();

        /*
        // El file input es para poder llamar el archivo previamente creado
        // El objetInput es para poder leer los objetos previamente guardados
        // el readObject es para poder leer lo que estaba guardado en los archivos
         */
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nombre + ".dat"))) {
            Partida cargada = (Partida) ois.readObject();
            // aqui se reescribe el nombre de la partida
            this.nombrePartida = cargada.nombrePartida;
            // se reescribe el nombre del jugador
            this.jugador = cargada.jugador;
            // se reescribe las ciudades que se habian seleccionado
            this.ciudades = cargada.ciudades;
            // se reescribe la ciudad en la que el jugador se encuentra
            this.ciudadActual = cargada.ciudadActual;
            // se reescribe los entrenadores derrotaso
            this.entrenadoresDerrotados = cargada.entrenadoresDerrotados;
            System.out.println("Partida cargada exitosamente.");
            jugarMapa();

            // el catch es por si hay errores
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("No se pudo cargar la partida: " + e.getMessage());
        }
    }

    // metodo de mochila
    protected void mochila() {
        // verifica si el jugador y la mochila del jugador existen
        if (jugador != null && jugador.getMochila() != null) {
            //si existen los dos entonces llama el metodo para mostrar mochila
            jugador.getMochila().mostrarMochila();
        }
    }

    // este es el equipo pokemon
    protected void equipoPokemon() {
        System.out.println("--- EQUIPO POKÉMON ---");
        // si existe un jugador y el jugador tiene mas de un pokemon
        if (jugador != null && jugador.getCantidadPokemon() > 0) {
            //solo imprime el equipo del jugador
            for (int i = 0; i < jugador.getCantidadPokemon(); i++) {
                System.out.println((i + 1) + ". " + jugador.getEquipo()[i].getApodo() +
                        " - Nvl: " + jugador.getEquipo()[i].getNivel());
            }
        } else {
            System.out.println("No tienes Pokémon en tu equipo.");
        }
    }

    // perfil
    protected void perfil() {
        System.out.println("----------- PERFIL -----------");
        // si jugador existe entonces imprime
        if (jugador != null) {
            // las estadisticas
            System.out.println("Jugador: " + jugador.getNombre());
            System.out.println("Pokémonedas: P" + jugador.getPokemonedas());
            System.out.println("Medallas obtenidas: " + jugador.getCantidadMedallas() + "/3");
        }
        System.out.println("------------------------------");
    }

    // batallas salbajes
    public void batallaSalvaje() {
        //aumenta un contador
        this.batallasSalvajes++;

        int pokemonedasVictoria;

        // segun la formula M = 0.4 para Pokémon salvajes
        Pokemon salvaje = generarPokemonAleatorio(0.4);

        System.out.println("Apareció un " + salvaje.getApodo() + " salvaje de nivel " + salvaje.getNivel());

        // se llama a la batalla pokemon
        Batalla combate = new Batalla(this.jugador, salvaje);
        boolean victoria = combate.iniciar();

        // si el jugador gana entonces recibe 150 a 500 monedas
        if (victoria) {
            // se crea un numero aleatorio entre 150 a 500
            Random rand = new Random();
            pokemonedasVictoria = rand.nextInt(351) + 150;

            // Se guardan las mondas a las pokemonedas
            this.jugador.setPokemonedas(this.jugador.getPokemonedas() + pokemonedasVictoria);
            System.out.println("Ganaste P"+ pokemonedasVictoria +" por la victoria.");
        }
    }

    // esto es para usar la formulada dada
    protected int calcularNivelOponente(double M) {
        // ya teniendo a tus pokemones
        Pokemon[] equipo = jugador.getEquipo();
        int sumaNiveles = 0;
        int cantidadPokemon = 0;

        // Recorre el equipo del jugador para obtener la suma y la cantidad real
        for (Pokemon p : equipo) {
            if (p != null) {
                sumaNiveles += p.getNivel();
                cantidadPokemon++;
            }
        }

        if (cantidadPokemon == 0) return 1;

        // Fórmula: (Σ nivel / cantidad) * M
        int nivelCalculado = (int) Math.round(((double) sumaNiveles / cantidadPokemon) * M);

        // Garantiza que el nivel sea al menos 1
        return Math.max(1, nivelCalculado);
    }

    // Aqui se selecciona al pokemon que va a luchar
    public  Pokemon generarPokemonAleatorio(double M) {
        // El nivel final del pokemon se iguala a los calculos
        int nivelFinal = calcularNivelOponente(M);

        // Selección aleatoria de la especie desde el catálogo
        Random random = new Random();
        int opcion = random.nextInt(25);

        switch (opcion) {
            case 0: return new Bulbasaur(nivelFinal);
            case 1: return new Inysaur(nivelFinal);
            case 2: return new Venasaur(nivelFinal);
            case 3: return new Charmander(nivelFinal);
            case 4: return new Charmeleon(nivelFinal);
            case 5: return new Charizard(nivelFinal);
            case 6: return new Squirtle(nivelFinal);
            case 7: return new Wartortle(nivelFinal);
            case 8: return new Blastoise(nivelFinal);
            case 9: return new Caterpie(nivelFinal);
            case 10: return new Metapod(nivelFinal);
            case 11: return new Weedle(nivelFinal);
            case 12: return new Beedrill(nivelFinal);
            case 13: return new Kakuna(nivelFinal);
            case 14: return new Beedrill(nivelFinal);
            case 15: return new Pidgey(nivelFinal);
            case 16: return new Pidgey(nivelFinal);
            case 17: return new Pidgeotto(nivelFinal);
            case 18: return new Rattata(nivelFinal);
            case 19: return new Raticate(nivelFinal);
            case 20: return new Spearow(nivelFinal);
            case 21: return new Fearow(nivelFinal);
            case 22: return new Ekans(nivelFinal);
            case 23: return new Arbok(nivelFinal);
            case 24:return new Pikachu(nivelFinal);
            default: return new Bulbasaur(nivelFinal);
        }
    }

    // aqui se muestan los datos de las partias
    private void datosPartida() {
        System.out.println("---------------------------------------");
        System.out.println("          Datos de partida             ");
        System.out.println("---------------------------------------");
        System.out.println("Nombre de la partida: " + nombrePartida);
        if (jugador != null) {
            System.out.println("Nombre del jugador: " + jugador.getNombre());
            System.out.println("Pokémonedas: P" + jugador.getPokemonedas());
        }
        if (ciudadActual != null) {
            System.out.println("Ciudad actual: " + ciudadActual.getNombre());
        }
    }
}