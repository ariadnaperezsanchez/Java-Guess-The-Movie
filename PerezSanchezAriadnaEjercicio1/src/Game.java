import java.util.*;

public class Game {
    //Atributos de la clase.
    private String peliculaAdivinar;
    private StringBuilder tituloMostrado;
    private Set<Character> letrasErroneas;
    private int intentos;
    private int puntuacion;
    private Player jugador;

    // Creamos el constructor
    public Game(String peliculaAdivinar) {
        //inicializamos los atributos al comenzar el juego.
        this.peliculaAdivinar = peliculaAdivinar.toLowerCase();//convertimos el título a minusculas
        this.tituloMostrado = new StringBuilder(peliculaAdivinar.replaceAll("[a-zA-Z]", "*"));//reemplazamos letras por astericos
        this.letrasErroneas = new HashSet<>();//inicializamos el conjunto de letras incorrectas
        this.intentos = 10;  // Máximo de intentos
        this.puntuacion = 0;//inicializamos puntuación en 0
        this.jugador = jugador;
    }

    // Método para iniciar el juego
    public void iniciarJuego() {
        Scanner input = new Scanner(System.in);
        //bucle principal del juego que se ejecuta minetras haya intentos y queden letras por adivinar
        while (intentos > 0 && tituloMostrado.toString().contains("*")) {
            //Mostramos el estado actual del juego.
            System.out.println("Película: " + tituloMostrado);
            System.out.println("Letras incorrectas: " + letrasErroneas);
            System.out.println("Intentos restantes: " + intentos);
            System.out.println("Puntuación actual: " + jugador.getPuntos());
            System.out.println("[1] Adivinar una letra");
            System.out.println("[2] Adivinar el título completo");
            System.out.println("[3] Salir");

            System.out.print("Elige una opción: ");
            int opcion = input.nextInt();//leemos la opción seleccionada
            input.nextLine(); // Limpiamos el buffer

            switch (opcion) {
                //caso 1 adivinamos letra
                case 1:
                    adivinarLetra(input);
                    break;
                //caso 2 Adivinamos título completo
                case 2:
                    adivinarTitulo(input);
                    break;
                //caso 3 finalizamos el juego.
                case 3:
                    finalizarJuego();
                    return;
                default:
                    System.out.println("Opción inválida. Elige entre 1 y 3.");
            }
        }

    }

    // Método para adivinar una letra
    public void adivinarLetra(Scanner input) {
        System.out.print("Introduce una letra: ");
        //convertimos la letr a minúscula
        char letra = input.nextLine().toLowerCase().charAt(0);
        //verificamos si la letra ya fue ingresada anteriormente.
        if (letrasErroneas.contains(letra)) {
            System.out.println("Ya has adivinado esta letra.");
        } else {
            //compruebamos si la letra está en el título
            if (peliculaAdivinar.indexOf(letra) >= 0) {
                System.out.println("¡Bien! La letra '" + letra + "' está en el título.");
                actualizarTitulo(letra);
                puntuacion += 10; // sumamos 10 puntos por adivinar una letra correcta.
            } else {
                System.out.println("La letra '" + letra + "' no está en el título.");
                letrasErroneas.add(letra); //Añadimos la letra incorrecta.
                intentos--; //resta un intento.
            }
        }
    }

    // Método para adivinar el título completo
    public void adivinarTitulo(Scanner input) {
        System.out.print("Introduce el título completo: ");
        String tituloAdivinado = input.nextLine().toLowerCase();//conviertimos otra vez las letras en minúsculas

        if (tituloAdivinado.equals(peliculaAdivinar)) {
            System.out.println("¡Correcto! Has adivinado el título.");
            puntuacion += 20; // Ganar 20 puntos por adivinar el título completo
            intentos = 0; // Terminar el juego
        } else {
            System.out.println("¡Incorrecto! El título era: " + peliculaAdivinar);
            intentos = 0; // Terminar el juego
        }
    }

    // Método para actualizar el título mostrado con las letras adivinadas
    private void actualizarTitulo(char letra) {
        for (int i = 0; i < peliculaAdivinar.length(); i++) {
            if (peliculaAdivinar.charAt(i) == letra) {
                tituloMostrado.setCharAt(i, letra);
            }
        }
    }

    // Método para finalizar el juego y retornar la puntuación
    private void finalizarJuego() {
        System.out.println("Tu puntuación final es: " + puntuacion);
    }
}


