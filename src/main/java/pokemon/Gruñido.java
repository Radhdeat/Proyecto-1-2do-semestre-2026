package pokemon;

public class Gruñido extends MovimientoEstado {
    private static final long serialVersionUID = 1L;

    protected Gruñido() {
        super("Gruñido", "Distrae al oponente reduciendo su ataque un 20%.");
    }

    @Override
    public void ejecutar(Pokemon atacante, Pokemon defensor) {
        defensor.modificarAtaque(0.80);
        System.out.println(atacante.getApodo() + " usó Gruñido. ¡El ataque de " + defensor.getApodo() + " bajó un 20%!");
    }
}