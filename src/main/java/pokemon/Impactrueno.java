package pokemon;

import java.util.Random;

public class Impactrueno extends MovimientoFisico {
    protected Impactrueno() { super("Impactrueno", 50, "Ataque eléctrico con 15% de probabilidad de parálisis."); }

    @Override
    public void ejecutar(Pokemon atacante, Pokemon defensor) {
        super.ejecutar(atacante, defensor);
        if (new Random().nextInt(100) < 15 && defensor.getEstado() == EstadoPokemon.NORMAL) {
            defensor.setEstado(EstadoPokemon.PARALIZADO);
            System.out.println("¡" + defensor.getApodo() + " ha sido paralizado!");
        }
    }
}