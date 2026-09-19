package flujoJuego;

// El main lo dejo solo para llamar al Flujo del juego
public class Main {
    public static void main(String[] args) {

        // Se crea el objeto Flujo de juego y se guarda en la variable juego
        FlujoDelJuego juego = new FlujoDelJuego();

        // llamamos al sub metodo menu principal
        juego.menuPrincipal();
    }
}