package objetos;

import pokemon.Pokemon;

public class Pocion extends Objeto {
    private static final long serialVersionUID = 1L;

    public Pocion(int cantidad) {
        super("Poción", 300, "Medicina en spray que restaura 20 puntos de salud de un pokémon", cantidad);
    }

    @Override
    protected boolean aplicarEfecto(Pokemon objetivo) {
        System.out.println("Las Pokébolas re tu equipo fuera de combate salvaje.");
        return false;
    }
}