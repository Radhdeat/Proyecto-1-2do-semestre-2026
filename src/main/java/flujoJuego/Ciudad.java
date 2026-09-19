package flujoJuego;

import java.io.Serializable;
import edificios.CentroPokemon;
import edificios.Gimnasio;
import edificios.TiendaPokemon;

public class Ciudad implements Serializable {
    private static final long serialVersionUID = 1L;

    // atributos importantes
    private String nombre;
    private Mapa mapa;

    // los otros objetos
    private CentroPokemon centroPokemon;
    private TiendaPokemon tiendaPokemon;
    private Gimnasio gimnasio;

    // constructor
    protected Ciudad(String nombre) {
        this.nombre = nombre;
        this.generarCiudad();
        this.inicializarEdificios();
    }

    // se guarda el nombre de la nueva ciudad
    private void generarCiudad() {
        this.mapa = new Mapa(this.nombre);
    }

    // se llaman a los edificios
    private void inicializarEdificios() {
        this.centroPokemon = new CentroPokemon();
        this.tiendaPokemon = new TiendaPokemon();
        this.gimnasio = new Gimnasio(this.nombre);
    }

    // se llama al mover jugador
    protected char moverJugador(String movimiento) {
        boolean seMovio = mapa.moverJugador(movimiento);
        if (seMovio) {
            return mapa.obtenerCasillaActual();
        }
        return ' ';
    }

    // los geters
    public CentroPokemon getCentroPokemon() { return centroPokemon; }
    public TiendaPokemon getTiendaPokemon() { return tiendaPokemon; }
    public Gimnasio getGimnasio() { return gimnasio; }
    public String getNombre() { return nombre; }

    // para mostrar el mapa
    protected void mostrarMapa() {
        mapa.mostrar();
    }
}