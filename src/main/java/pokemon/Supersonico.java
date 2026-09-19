package pokemon;

public class Supersonico extends MovimientoEstado {
    private static final long serialVersionUID = 1L;

    protected Supersonico() {
        super("Supersónico", "Emite ondas sónicas que dejan al oponente confuso.");
    }

    @Override
    public void ejecutar(Pokemon atacante, Pokemon defensor) {
        if (defensor.getEstado() == EstadoPokemon.NORMAL) {
            defensor.setEstado(EstadoPokemon.CONFUSO);
            System.out.println(atacante.getApodo() + " usó Supersónico. ¡" + defensor.getApodo() + " ahora está confuso!");
        } else {
            System.out.println(atacante.getApodo() + " usó Supersónico, pero no tuvo efecto.");
        }
    }
}