package pokemon;

public class AtaqueRapido extends MovimientoFisico {
    private static final long serialVersionUID = 1L;

    protected AtaqueRapido() {
        super("Ataque rápido", 40, "Ataca al objetivo a tal velocidad que es casi imperceptible. Tiene prioridad alta.");
    }
}