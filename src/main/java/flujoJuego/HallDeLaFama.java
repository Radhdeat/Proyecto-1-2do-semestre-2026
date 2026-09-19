package flujoJuego;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import jugador.Jugador;
import pokemon.Pokemon;

// hall de la fama
public class HallDeLaFama {

    // guarda el nombre del archivo plano donde se registran y leen los datos del Hall de la Fama.
    private static final String ARCHIVO = "hallDeLaFama.txt";

    // Muestra todo el historial desde el archivo .txt
    public static void mostrar() {
        // se usa como cadena de texto para una convension de java para las constantes
        File file = new File(ARCHIVO);
        if (!file.exists()) {
            System.out.println("Aún no hay entrenadores registrados en el Hall de la Fama.");
            return;
        }

        // el try es para abir un flujo de lectura y garantiza que este se cierre evitando fugas

        // el new file Reader conecta el programa con el archivo  para leer caracteres
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {

            String linea;

            // bucle que que lee el documento fila por fila y se detiene al llegar al final del archivo
            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
            }
            // Captura cualquier problema de lectura
        } catch (IOException e) {
            System.out.println("Error al leer el Hall de la Fama: " + e.getMessage());
        }
    }

    // Registra la victoria en el archivo .txt sin sobrescribir los anteriores
    public static void registrarVictoria(
            Jugador jugador,
            String[] medallasConIcono,
            String[] ciudadesMedallas,
            int batallasSalvajes,
            int batallasEntrenador,
            int pokebolasLanzadas,
            int pokemonsCapturados,
            Pokemon pokemonMVP
    ) {

        // utilizaremos pw.println para escribir y guardar la informacion fisicamente en el archivo
        try (PrintWriter pw = new PrintWriter(new FileWriter(ARCHIVO, true))) {

            // se escribe y se guarda en el archivo
            pw.println("--------------------------------------------------");
            pw.println("             HALL DE LA FAMA - POKÉMON            ");
            pw.println("--------------------------------------------------");

            // este es el perfil del entrenador
            pw.println("--------------------------------");
            pw.println("        FICHA DEL ENTRENADOR    ");
            pw.println("--------------------------------");
            pw.println("Entrenador: " + jugador.getNombre());
            pw.println("Pokemonedas Finales: P" + jugador.getPokemonedas());
            pw.println("Medallas Obtendas:");
            // se escriben las medallas que el jugador tenga
            for (int i = 0; i < medallasConIcono.length; i++) {
                pw.println("  - " + medallasConIcono[i] + " (" + ciudadesMedallas[i] + ")");
            }

            pw.println();

            // equipo pokemon
            pw.println("--------------------------------");
            pw.println("    EQUIPO POKÉMON VICTORIOSO   ");
            pw.println("--------------------------------");
            Pokemon[] equipo = jugador.getEquipo();
            for (int i = 0; i < jugador.getCantidadPokemon(); i++) {
                if (equipo[i] != null) {
                    pw.printf(equipo[i].getClass().getSimpleName() + equipo[i].getApodo() + "Nivel: " + equipo[i].getNivel() + "PS Máx: " + equipo[i].getSaludMaxima());
                }
            }
            pw.println();

            // Estadisticas
            pw.println("--------------------------------");
            pw.println("   ESTADÍSTICAS DE LA PARTIDA   ");
            pw.println("--------------------------------");
            pw.println("Batallas Totales: " + (batallasSalvajes + batallasEntrenador));
            pw.println("  - Salvajes: " + batallasSalvajes);
            pw.println("  - Entrenadores: " + batallasEntrenador);
            pw.println("Pokébolas Lanzadas: " + pokebolasLanzadas);
            pw.println("Pokémon Capturados: " + pokemonsCapturados);
            // si hay un mejor mugador entonces imprime su nombre
            if (pokemonMVP != null) {
                pw.println("Pokémon MVP: " + pokemonMVP.getApodo() + " (" + pokemonMVP.getClass().getSimpleName() + ")");
            } else {
                pw.println("Pokémon MVP: N/A");
            }

            pw.println("------------------------------");
            System.out.println("¡Tu hazaña ha sido registrada en el Hall de la Fama!");

        } catch (IOException e) {
            System.out.println("Error al guardar en el Hall de la Fama: " + e.getMessage());
        }
    }
}