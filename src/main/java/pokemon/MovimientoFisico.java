package pokemon;

import java.io.Serializable;
import java.util.Random;

public class MovimientoFisico extends Movimiento implements Serializable {
    private static final long serialVersionUID = 1L;

    // constructor
    protected MovimientoFisico(String nombre, int potencia, String descripcion) {
        super(nombre, potencia, descripcion);
    }

    //se calcula el daño que hacen los ataques
    protected int calcularDaño(Pokemon atacante, Pokemon defensor) {

        int v = 85 + new Random().nextInt(16);
        int nivel = atacante.getNivel();
        int ataque = atacante.getAtaque();
        int potencia = getPotencia();
        int defensa = Math.max(1, defensor.getDefensa());

        double numerador = (0.2 * nivel + 1) * ataque * potencia;
        double denominador = 25.0 * defensa;
        return (int) (0.01 * v * ((numerador / denominador) + 2));
    }

    @Override
    public void ejecutar(Pokemon atacante, Pokemon defensor) {
        int daño = calcularDaño(atacante, defensor);
        defensor.recibirDano(daño);
        System.out.println(atacante.getApodo() + " usó " + getNombre() + " causando " + daño + " de daño a " + defensor.getApodo() + "!");
    }
}