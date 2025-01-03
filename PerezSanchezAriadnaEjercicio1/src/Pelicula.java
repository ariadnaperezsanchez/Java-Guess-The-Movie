import java.io.Serializable;

// El objeto Pelicula - en un fichero de Bytes por eso ponemos serializable
public class Pelicula implements Serializable {
    //atributo
    private String nombre;

    // Constructor
    public Pelicula(String nombre) {
        this.nombre = nombre;
    }

    // Metodo que devuelve el nombre de la película
    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
