package pokemon;

public class Descanso extends MovimientoEstado {
    private static final long serialVersionUID = 1L;

    protected Descanso() {
        super("Descanso", "Restaura la salud completa y cura estados, pero se queda dormido.");
    }

    @Override
    public void ejecutar(Pokemon atacante, Pokemon defensor) {
        atacante.curarCompleto();
        atacante.setEstado(EstadoPokemon.DORMIDO);
        System.out.println(atacante.getApodo() + " usó Descanso. ¡Recuperó toda su salud pero quedó dormido!");
    }
}