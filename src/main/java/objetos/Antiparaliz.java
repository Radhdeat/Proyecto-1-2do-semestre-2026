package objetos;

import pokemon.Pokemon;

public class Antiparaliz extends Objeto {
    private static final long serialVersionUID = 1L;

    public Antiparaliz(int cantidad) {
        super("Superpoción", 600, "Medicina en spray que restaura 50 puntos de salud de un pokémon", cantidad);
    }

    @Override
    protected boolean aplicarEfecto(Pokemon objetivo) {
        System.out.println("Las Pokébolas no se usan directamente sobre tu equipo fuera de combate salvaje.");
        return false;
    }
}