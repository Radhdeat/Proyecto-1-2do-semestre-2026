package pokemon;

import java.util.Random;

public class AtaqueFuria extends MovimientoFisico {
    protected AtaqueFuria() { super("Ataque furia", 15, "Picotea al oponente de dos a cinco veces seguidas."); }

    @Override
    public void ejecutar(Pokemon atacante, Pokemon defensor) {
        int golpes = 2 + new Random().nextInt(4);
        int totalDano = 0;
        for (int i = 0; i < golpes; i++) {
            int dano = calcularDaño(atacante, defensor);
            defensor.recibirDano(dano);
            totalDano += dano;
        }
        System.out.println(atacante.getApodo() + " usó Ataque furia " + golpes + " veces causando " + totalDano + " de daño total!");
    }
}