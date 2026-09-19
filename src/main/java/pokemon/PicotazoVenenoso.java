package pokemon;

import java.util.Random;

public class PicotazoVenenoso extends MovimientoFisico {
    private static final long serialVersionUID = 1L;

    protected PicotazoVenenoso() {
        super("Picotazo venenoso", 20, "Lanza un aguijón tóxico que causa daño y tiene 15% de probabilidad de envenenar.");
    }

    @Override
    public void ejecutar(Pokemon atacante, Pokemon defensor) {
        super.ejecutar(atacante, defensor); // Realiza el daño físico primero

        // 15% de probabilidad de envenenar
        if (new Random().nextInt(100) < 15) {
            if (defensor.getEstado() == EstadoPokemon.NORMAL) {
                defensor.setEstado(EstadoPokemon.ENVENENADO);
                System.out.println("¡" + defensor.getApodo() + " ha sido envenenado!");
            }
        }
    }
}