package pokemon;

public class HidroCañon extends MovimientoFisico {
    public HidroCañon() { super("Hidrocañón", 120, "Cañonazo de agua; requiere descansar el siguiente turno."); }

    @Override
    public void ejecutar(Pokemon atacante, Pokemon defensor) {
        super.ejecutar(atacante, defensor);
        atacante.setEstado(EstadoPokemon.CANSADO);
        System.out.println("¡" + atacante.getApodo() + " se encuentra exhausto y deberá descansar!");
    }
}