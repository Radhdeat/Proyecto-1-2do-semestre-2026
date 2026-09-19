package pokemon;

public class AnilloIgneo extends MovimientoFisico {
    protected AnilloIgneo() { super("Anillo Ígneo", 120, "Calcina al oponente; requiere descansar el siguiente turno."); }

    @Override
    public void ejecutar(Pokemon atacante, Pokemon defensor) {
        super.ejecutar(atacante, defensor);
        atacante.setEstado(EstadoPokemon.CANSADO);
        System.out.println("¡" + atacante.getApodo() + " se encuentra exhausto y deberá descansar!");
    }
}