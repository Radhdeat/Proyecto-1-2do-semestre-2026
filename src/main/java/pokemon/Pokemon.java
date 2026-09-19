package pokemon;

import java.io.Serializable;
import java.util.Random;

public abstract class Pokemon implements Serializable {
    private static final long serialVersionUID = 1L;

    // se declaran los atributos
    private int id;
    private String apodo;
    private String especie;
    private int nivel;
    private int experiencia;

    // estadisticas maxima
    private int saludMaxima;
    private int saludActual;
    private int ataque;
    private int defensa;
    private int velocidad;

    // Estadisticas base
    private int saludBase;
    private int ataqueBase;
    private int defensaBase;
    private int velocidadBase;

    // variacion de esstadisticas
    private int variacionSalud;
    private int variacionAtaque;
    private int variacionDefensa;
    private int variacionVelocidad;

    private EstadoPokemon estado;
    private Movimiento[] movimientos;
    private int pokemonesDerrotados;

    // Banderas de efectos secundarios
    private boolean protegido;
    private boolean tieneDrenadoras;

    // constructor
    protected Pokemon(String especie, int id, int nivelInicial, int saludBase, int ataqueBase, int defensaBase, int velocidadBase, Movimiento[] movimientos) {

        this.especie = especie;
        this.apodo = especie;
        this.id = id;
        this.nivel = nivelInicial;
        this.experiencia = 0;

        this.saludBase = saludBase;
        this.ataqueBase = ataqueBase;
        this.defensaBase = defensaBase;
        this.velocidadBase = velocidadBase;

        Random random = new Random();
        this.variacionSalud = random.nextInt(32);
        this.variacionAtaque = random.nextInt(32);
        this.variacionDefensa = random.nextInt(32);
        this.variacionVelocidad = random.nextInt(32);

        this.estado = EstadoPokemon.NORMAL;
        this.movimientos = movimientos != null ? movimientos : new Movimiento[4];
        this.pokemonesDerrotados = 0;
        this.protegido = false;
        this.tieneDrenadoras = false;

        recalcularEstadisticas();
        this.saludActual = this.saludMaxima;
    }

    // metodo para atacar
    public abstract void atacar(Pokemon oponente);

    // metodos de daño y salud
    public void recibirDano(int cantidad) {
        this.saludActual = Math.max(0, this.saludActual - cantidad);
    }

    public void curarSalud(int cantidad) {
        this.saludActual = Math.min(this.saludMaxima, this.saludActual + cantidad);
    }

    // metodo para curarse
    public boolean curarCompleto() {
        // compara la vida actual y la vida maxima
        boolean hpIncompleto = (saludActual < saludMaxima);
        // compara el estado del pokemon con el normal
        boolean estadoAnormal = (estado != EstadoPokemon.NORMAL);

        // instancia los stads
        this.saludActual = this.saludMaxima;
        this.estado = EstadoPokemon.NORMAL;
        this.protegido = false;
        this.tieneDrenadoras = false;

        return hpIncompleto || estadoAnormal;
    }

    // metodo para curar
    public boolean curarEnvenenamiento() {
        // si el estado es igual al envenenando entonces los iguala al normal
        if (this.estado == EstadoPokemon.ENVENENADO) {
            this.estado = EstadoPokemon.NORMAL;
            return true;
        }
        return false;
    }

    // estadisticas
    public void recalcularEstadisticas() {
        // se suman los ataques se multipiclan por el doble y se multiplica por nivel y se divide por 105
        this.ataque = ((ataqueBase + variacionAtaque) * 2 * nivel) / 100 + 5;
        this.defensa = ((defensaBase + variacionDefensa) * 2 * nivel) / 100 + 5;
        this.velocidad = ((velocidadBase + variacionVelocidad) * 2 * nivel) / 100 + 5;
        this.saludMaxima = ((saludBase + variacionSalud) * 2 * nivel) / 100 + nivel + 10;
    }

    // Modificadores
    public void modificarAtaque(double factor) {
        this.ataque = Math.max(1, (int) (this.ataque * factor));
    }

    public void modificarDefensa(double factor) {
        this.defensa = Math.max(1, (int) (this.defensa * factor));
    }

    public void modificarVelocidad(double factor) {
        this.velocidad = Math.max(1, (int) (this.velocidad * factor));
    }

   // metodos de efectos especiales
    public void aplicarEfectoDrenadoras(Pokemon beneficiario) {

        if (this.tieneDrenadoras && this.saludActual > 0) {
            int hpDrenado = (int) (this.saludMaxima * 0.07);
            this.recibirDano(hpDrenado);
            // si hay un beneficui entonces cura al pokemon
            if (beneficiario != null) {
                beneficiario.curarSalud(hpDrenado);
            }

            System.out.println("Las Drenadoras absorbieron " + hpDrenado + " HP de " + this.apodo);
        }
    }

    //Geteres y seteres
    public String getEspecie() { return especie; }
    public int getId() { return id; }
    public String getApodo() { return apodo; }
    public void setApodo(String apodo) { this.apodo = apodo; }
    public int getNivel() { return nivel; }
    public int getSaludMaxima() { return saludMaxima; }
    public int getSaludActual() { return saludActual; }
    public int getAtaque() { return ataque; }
    public int getDefensa() { return defensa; }
    public int getVelocidad() { return velocidad; }
    public EstadoPokemon getEstado() { return estado; }
    public void setEstado(EstadoPokemon estado) { this.estado = estado; }
    public Movimiento[] getMovimientos() { return movimientos; }

    public boolean esProtegido() { return protegido; }
    public void setProtegido(boolean protegido) { this.protegido = protegido; }

    public boolean tieneDrenadoras() { return tieneDrenadoras; }
    public void setTieneDrenadoras(boolean tieneDrenadoras) { this.tieneDrenadoras = tieneDrenadoras; }

    public void setSaludActual(int saludActual) {
        this.saludActual = saludActual;
    }

}