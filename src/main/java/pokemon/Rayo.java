package pokemon;

import java.util.Random;
public class Rayo extends MovimientoFisico {
    public Rayo() { super("Rayo", 100, "Poderoso rayo con 20% de probabilidad de parálisis."); }

    @Override
    public void ejecutar(Pokemon atacante, Pokemon defensor) {
        super.ejecutar(atacante, defensor);
        if (new Random().nextInt(100) < 20 && defensor.getEstado() == EstadoPokemon.NORMAL) {
            defensor.setEstado(EstadoPokemon.PARALIZADO);
            System.out.println("¡" + defensor.getApodo() + " ha sido paralizado!");
        }
    }
}