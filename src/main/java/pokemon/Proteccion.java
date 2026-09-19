package pokemon;

import java.util.Random;

public class Proteccion extends MovimientoEstado {
    private static final long serialVersionUID = 1L;

    protected Proteccion() {
        super("Protección", "Tiene 70% de probabilidad de bloquear el próximo ataque.");
    }

    @Override
    public void ejecutar(Pokemon atacante, Pokemon defensor) {
        if (new Random().nextInt(100) < 70) {
            atacante.setProtegido(true);
            System.out.println(atacante.getApodo() + " usó Protección. ¡Se protegerá del próximo ataque!");
        } else {
            System.out.println(atacante.getApodo() + " usó Protección... ¡pero falló!");
        }
    }
}