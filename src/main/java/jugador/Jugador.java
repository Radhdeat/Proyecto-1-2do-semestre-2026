package jugador;

import java.io.Serializable;
import pokemon.Pokemon;

public class Jugador implements Serializable {
    private static final long serialVersionUID = 1L;

    // atributos
    private String nombre;
    private int pokemonedas;
    // Maximo 6 pokemon
    private Pokemon[] equipo;
    // Contador de pisones
    private int cantidadPokemon;
    // mochila
    private Mochila mochila;
    // maximo 3 medallas
    private String[] medallas;
    private int cantidadMedallas;

    // constuctor
    public Jugador(String nombre) {
        this.nombre = nombre;
        // los mil pesos de inico
        this.pokemonedas = 1000;
        this.equipo = new Pokemon[6];
        this.cantidadPokemon = 0;
        this.mochila = new Mochila();
        this.medallas = new String[3];
        this.cantidadMedallas = 0;
    }

    // 5 pokebolas regaladas
    private int pokebolas = 5;

    // Aqui se guardan los pokemon
    public boolean agregarPokemon(Pokemon nuevo) {

        for (int i = 0; i < equipo.length; i++) {
            // si el equipo no tiene pokemon entonces asigna otro y aumenta la cantidad de pokemon
            if (equipo[i] == null) {
                equipo[i] = nuevo;
                cantidadPokemon++;

                return true;
            }
        }
        // si el equipo esta lleno ya no se agraga
        return false;
    }

    // metodo para agregar medalla
    private boolean agregarMedalla(String medalla) {
        if (cantidadMedallas < medallas.length) {
            medallas[cantidadMedallas] = medalla;
            cantidadMedallas++;
            return true;
        }
        return false;
    }
    // Getters y Setters

    public int getPokebolas() {
        return mochila.getCantidadObjeto("Pokébola");
    }

    // Descuenta la Pokébola directamente de la mochila
    public boolean usarPokebola() {
        return mochila.consumirObjeto("Pokébola");
    }

    public void agregarPokebolas(
            int cantidad) { this.pokebolas += cantidad; }

    public String getNombre() {
        return nombre; }

    public int getPokemonedas() {
        return pokemonedas; }

    public void setPokemonedas(int pokemonedas) {
        this.pokemonedas = pokemonedas; }

    public Pokemon[] getEquipo() {
        return equipo; }

    public int getCantidadPokemon() {
        return cantidadPokemon; }

    public Mochila getMochila() {
        return mochila; }

    protected String[] getMedallas() {
        return medallas; }

    public int getCantidadMedallas() {
        return cantidadMedallas; }
}