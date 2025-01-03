import java.io.Serializable;
import java.io.*;
import java.util.*;

public class JugadorRanking implements Serializable {
    //Atributos de la clase
    private String nickname;//nombre usuario
    private int puntos;//puntuación del jugador
    // Creamos constructor
    public JugadorRanking(String nickname, int puntos) {
        this.nickname = nickname;
        this.puntos = puntos;
    }

    //método para obtener el nombre del jugador
    public String getNickname() {
        return nickname;
    }
    //método para obtener la puntuación del jugador

    public int getPuntos() {
        return puntos;
    }

    @Override
    public String toString() {
        return nickname + " - " + puntos + " puntos";
    }

    //Archivo donde se almacenará el ranking
    private static final String RANKING_FILE = "ranking.dat";

    // Método para Leer el ranking del archivo
    public static List<JugadorRanking> leerRanking() {
        List<JugadorRanking> ranking = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(RANKING_FILE))) {
            //intentamos leer el ranking desde el archivo
            ranking = (List<JugadorRanking>) ois.readObject();
        } catch (FileNotFoundException e) {
            // Si el archivo no existe, simplemente se devuelve una lista vacía
            System.out.println("El archivo de ranking no existe, se creará uno nuevo.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al leer el archivo de ranking: " + e.getMessage());
        }
        return ranking;
    }

    // Método para escribir el ranking en el archivo
    public static void escribirRanking(List<JugadorRanking> ranking) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(RANKING_FILE))) {
            //para escribir la lista de jugadores en el archivo
            oos.writeObject(ranking);
        } catch (IOException e) {
            //Manejo de errores al escribir en el archivo
            System.out.println("Error al escribir el archivo de ranking: " + e.getMessage());
        }
    }

    // Método para actualizar el ranking con un nuevo jugador.
    public static void actualizarRanking(JugadorRanking nuevoJugador) {
        //leemos el ranking existente desde el archivo
        List<JugadorRanking> ranking = leerRanking();

        // Comprobamos si el nickname ya existe
        boolean nicknameExistente = ranking.stream()
                .anyMatch(jugador -> jugador.getNickname().equalsIgnoreCase(nuevoJugador.getNickname()));
        //si existe el nickname mostramos un mensaje y no lo añadimos
        if (nicknameExistente) {
            System.out.println("El nickname '" + nuevoJugador.getNickname() + "' ya está en el ranking. Por favor, elige otro.");
            return;
        }

        // Añadimos el nuevo jugador y ordena el ranking
        ranking.add(nuevoJugador);
        ranking.sort((a, b) -> Integer.compare(b.getPuntos(), a.getPuntos()));

        // Limitamos el ranking a los 5 mejores
        List<JugadorRanking> rankingTop5 = new ArrayList<>(ranking.subList(0, Math.min(ranking.size(), 5)));

        // Escribimos el ranking actualizado en el archivo
        escribirRanking(rankingTop5);

        // Muestramos el ranking actualizado
        System.out.println("\nRanking actualizado:");
        rankingTop5.forEach(System.out::println);
    }

}