package pokemon;

import java.util.Random;

public class LanzaLlamas extends MovimientoFisico {
    protected LanzaLlamas() { super("Lanzallamas", 90, "Ataca con una gran ráfaga de fuego."); }

    @Override
    public void ejecutar(Pokemon atacante, Pokemon defensor) {
        super.ejecutar(atacante, defensor);
        if (new Random().nextInt(100) < 10) {
            System.out.println("¡" + defensor.getApodo() + " ha sufrido una quemadura!");
        }
    }
}