

public class Player {
    //atributo que almacena el nombre del jugador
    private String nombre;

    //atributo que almacena los punto acumulados por el jugador
    private int puntos;
    //contructor
    public Player(String nombre, int puntos) {
        this.nombre = nombre;
        this.puntos = puntos;
    }
    //Método para incrementar los puntos del jugador.
    public void incrementarPuntos(int cantidad) {
        this.puntos += cantidad;
    }
    //Método para obtener los puntos actuales del jugador.
    public int getPuntos() {
        return puntos;
    }

    @Override
    //devuelve el nombre del jugador junto con su puntuación actual
    public String toString() {
        return nombre + " - Puntos: " + puntos;
    }
}
