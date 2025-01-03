import java.io.*;//importamos la clase para leer y escribir en archivos
import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;//importamos la clase Scanner para poder imprimir por consola
import java.util.Set;
import java.util.HashSet;

//se crea la clase principal Main
public class Main {

    public static void main(String[] args) {

        //cremos una instancia dela clase amin
        Main main = new Main();
        // llamamos al metodo inicio para ejecutar el programa
        main.inicio();
    }

    //creamos el metodo inicio que para la ejecución del programa
    public void inicio() {
        System.out.println("Comienza el programa");
        //el metodo write() escribe una lista de peliculas en un archivo
        write();
        //el metodo read() para leer las peliculas del archivo peliculas
        List<Pelicula> peliculas = read();
        // Creaamos un jugador inicial con 0 puntos
        Player jugador = new Player("Jugador 1", 0);
        //iniciamos el juego con las peliculas y el jugador.
        jugar(peliculas, jugador);

        System.out.println("Programa terminado");
    }

    //creamos el metodo write() para escribir un array de películas en el archivo
    public void write() {
        System.out.println("Títulos de las películas...");
        //creamos un array con las peliculas
        Pelicula[] peliculas = new Pelicula[10];
        peliculas[0] = new Pelicula("Mamma Mia");
        peliculas[1] = new Pelicula("Moulin Rouge");
        peliculas[2] = new Pelicula("Avatar");
        peliculas[3] = new Pelicula("El diario de Noa");
        peliculas[4] = new Pelicula("Harry Potter");
        peliculas[5] = new Pelicula("Toy Story");
        peliculas[6] = new Pelicula("La la land");
        peliculas[7] = new Pelicula("Gladiator");
        peliculas[8] = new Pelicula("Aladdin");
        peliculas[9] = new Pelicula("Joker");

        System.out.println("Array creado con " + peliculas.length + " elementos");
        //usamos try/catch para el manejo de errores.
        //Usamos FileOutputSream y objectOutputStream para escribir cada pelicula en el archivo.
        try (FileOutputStream fileOut = new FileOutputStream("objects.data");
             ObjectOutputStream output = new ObjectOutputStream(fileOut)) {
            //con este bucle for, agregamos cada pelicula.
            for (Pelicula pelicula : peliculas) {
                output.writeObject(pelicula);
            }
            //imprimimos por pantalla el mensaje, para indicar que ya se ha terminado la escritura en el  fichero
            System.out.println("Escritura en fichero terminada");

        } catch (Exception e) {
            System.out.println("Error al escribir el archivo: " + e.getMessage());
        }
    }

    //Metodo para leer las películas desdel archivo
    public List<Pelicula> read() {
        System.out.println("Leyendo los títulos de películas desde el archivo...");
        List<Pelicula> peliculas = new ArrayList<>();
        // lee las películas desde el archivo
        try (FileInputStream fileIn = new FileInputStream("objects.data");
             ObjectInputStream input = new ObjectInputStream(fileIn)) {
            // con el bucle while lee hasta que no haya más datos.
            while (true) {
                try {
                    //leemos el objeto y añadimos la pelicula a la lista y salimos del bucle si no hay mas datos con break
                    Pelicula pelicula = (Pelicula) input.readObject();
                    peliculas.add(pelicula);
                } catch (Exception e) {
                    break;
                }
            }
        } catch (Exception e) {//manejo de errores al leer el archivo
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
        return peliculas;// por último devuelve la lista de películas.
    }
    //creamos el metodo jugar donde se ejecutarátodo el juego.
    public void jugar(List<Pelicula> peliculas,Player jugador) {
        Scanner input = new Scanner(System.in); //lo usamos para recibir las entradas del usuario por consola.
        int menu;//aqui guardamos la opcion del menú seleccionada por el usuario.
//selecciona una película aleatoria con Random() y la ocultamos con asteriscos.
        String peliculaAdivinar = peliculas.get(new Random().nextInt(peliculas.size())).getNombre().toLowerCase();
        StringBuilder mostrada = new StringBuilder(peliculaAdivinar.replaceAll("[a-zA-Z]", "*"));

        int intentos = 10;//número de intentos disponibles
        Set<Character> letrasErroneas = new HashSet<>();//letras incorrectas
        Set<Character> letrasAdivinadas = new HashSet<>();//letras acertadas
        boolean tittleAdivinado = false; //variable para verificar si se adivinó el título.

//creamos el menú del juego
        do {
            System.out.println("\n \uD83C\uDFAC GUESS THE MOVIE!  \uD83C\uDFAC");
            System.out.println("[1] Guess a Letter");
            System.out.println("[2] Guess the movie's title");
            System.out.println("[3] Exit");
            System.out.print("Escoge una opcion del 1 al 3: ");
// con este if verificamos que la entrada sea un número entero.
            if (!input.hasNextInt()) {
                System.out.println("Opcion Incorrrecta, escoge una opcion del 1 al 3.");
                input.nextLine();
                continue;
            }

            menu = input.nextInt();//leemos la opción seleccionada
            input.nextLine();//limpiamos el buffer de entrada

            switch (menu) {
                //caso 1 para adivinar una letra
                case 1:
                    System.out.print("Introduce una letra: ");
                    String entrada = input.nextLine().toLowerCase();
                    //verificamos que sea una letra válida.
                    if (entrada.length() != 1 || !Character.isLetter(entrada.charAt(0))) {
                        System.out.println("Por favor introduce una letra de la A a la Z.");
                        break;
                    }

                    char letra = entrada.charAt(0);
                    //Aqui verificamos si la letra ya fue intentada.
                    if (letrasAdivinadas.contains(letra) || letrasErroneas.contains(letra)) {
                        System.out.println("You have already guessed this letter \uD83D\uDD01\uFE0F.");
                        break;
                    }
                    //luego verificmos que la letra este en el título
                    if (peliculaAdivinar.indexOf(letra) >= 0) {
                        System.out.println("Good! The letter '" + letra + "' is in the title \uD83D\uDC4F!");
                        letrasAdivinadas.add(letra);
                        actualizarTitulo(peliculaAdivinar, mostrada, letra);
                        jugador.incrementarPuntos(10); // Incrementa 10 puntos por cada letra acertada
                    } else {
                        //en caso que la letra no este en el título agregamos la letra a las incorrectas
                        //y restamos un intento.
                        System.out.println("The letter '" + letra + "' \uD83D\uDC4E is not in the title...");
                        letrasErroneas.add(letra);
                        intentos--;
                    }
                    break;
                //en el caso 2 adivinamos el título entero.
                case 2: // Guess the full movie title
                    System.out.print("Enter the movie title, hurry up, time is running out ⏳... ");
                    String tituloAdivinado = input.nextLine().toLowerCase();
                    if (tituloAdivinado.equals(peliculaAdivinar)) {
                        System.out.println("Great! \uD83C\uDFC6 You guessed the title.");
                        jugador.incrementarPuntos(20); // Incrementa 20 puntos por acertar el título completo
                        tittleAdivinado = true; // Marcamos el título como adivinado
                        break;
                    } else {
                        System.out.println("Oh no... You lost☹ The title was: " + peliculaAdivinar);
                        intentos = 0; //cuando intentos sea igual a 0 el juego termina.
                    }
                    break;// y salimos del juego.
                //en el caso 3 ofrcemos la opción de salir del juego.
                case 3: // Exit
                    System.out.println("Exiting the game...");
                    System.out.println("Has perdido la partida. 😞");

                    return;

                default:
                    System.out.println("Please enter a number between 1 and 3.");
            }

            // si el título es el correcto se termina el juego.
            if (!mostrada.toString().contains("*")) {
                System.out.println("Congratulations! You guessed the title \uD83C\uDF8A");
                tittleAdivinado = true;
                break;
            }

            // Muestra el estado actual del juego
            System.out.println("Title: " + mostrada);
            System.out.println("Incorrect letters: " + letrasErroneas);
            System.out.println("Remaining attempts: " + intentos);
            //con el bucle while repetimos mientras queden intentos y no se adivine el título
        } while (intentos > 0 && !tittleAdivinado);

        // Fin del juego y mostramos si ha ganado o perdido la partida
        if (tittleAdivinado) {
            System.out.println("\n🎉 ¡Felicidades! Has ganado. 🎉");
            System.out.println("El título de la película era: " + peliculaAdivinar);
        } else {
            System.out.println("\n☹ ¡Oh no! Has perdido. ☹");
            System.out.println("El título de la película era: " + peliculaAdivinar);
        }
        //mostramos la puntuación del juego
        System.out.println("Tu puntuación definitiva es: " + jugador.getPuntos());
        // Manejo del ranking
        //obtenemos los puntos del jugador actual
        int puntosJugador = jugador.getPuntos();
        //leemos el raking actual desde el archivo
        List<JugadorRanking> ranking = JugadorRanking.leerRanking();
        //verificamos si el jugador puede entrar en el ranking, comprovando si hay menos de 5 usarios y su la puntuación de ellos es menor que el último
        if (ranking.size() < 5 || puntosJugador > ranking.get(ranking.size() - 1).getPuntos()) {
            String nickname;
            boolean nicknameValido;
            do {
                System.out.print("¡Has entrado en el ranking! Introduce un nickname único: ");
                nickname = input.nextLine();
                //solicitamos al jugador un nombre de usuario para ingresar en el ranking.
                nicknameValido = true;
                //verificamos que el nickname no esta ya en el ranking
                for (JugadorRanking jugadorRanking : ranking) {
                    if (jugadorRanking.getNickname().equalsIgnoreCase(nickname)) {
                        nicknameValido = false;
                        System.out.println("Ese nickname ya está en el ranking. Intenta con otro.");
                        break;
                    }
                }

            } while (!nicknameValido); //y esto se repite hasta que el nickname sea válido.

            // Actualizamos el ranking con el nuevo jugador
            JugadorRanking.actualizarRanking(new JugadorRanking(nickname, puntosJugador));
        } else {
            System.out.println("\nNo has entrado en el ranking. ¡Mejor suerte la próxima vez!");
        }

        // Mostramos ranking final
        System.out.println("\nRanking final:");
        JugadorRanking.leerRanking().forEach(System.out::println);

        input.close(); // Cerramos el scanner
    }
    // Método para actualizar el título mostrado  con las letras acertadas.
    public void actualizarTitulo(String pelicula, StringBuilder mostrada, char letra) {
        for (int i = 0; i < pelicula.length(); i++) {
            if (pelicula.charAt(i) == letra) {
                mostrada.setCharAt(i, letra);
            }
        }
    }
}