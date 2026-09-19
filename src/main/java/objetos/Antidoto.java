package objetos;

import pokemon.Pokemon;

public class Antidoto extends Objeto {
    private static final long serialVersionUID = 1L;

    public Antidoto(int cantidad) {
        super("Antídoto", 100, "Medicina en spray que cura a un pokémon envenenado", cantidad);
    }

    @Override
    protected boolean aplicarEfecto(Pokemon objetivo) {
        return objetivo != null && objetivo.curarEnvenenamiento();
    }
}