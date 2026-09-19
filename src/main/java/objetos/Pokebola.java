package objetos;

import pokemon.Pokemon;

public class Pokebola extends Objeto {
    private static final long serialVersionUID = 1L;

    public Pokebola(int cantidad) {
        super("Pokébola", 200, "Sirve para atrapar pokémon salvajes", cantidad);
    }

    @Override
    protected boolean aplicarEfecto(Pokemon objetivo) {
        System.out.println("Las Pokébolas no se usan directamente sobre tu equipo fuera de combate salvaje.");
        return false;
    }
}