# 🎬 Java Guess The Movie

A console-based movie guessing game developed in **Java**.

The player must discover a randomly selected movie title by guessing individual letters or attempting to guess the complete title. The game includes a scoring system, limited attempts, persistent movie data, and a Top 5 player ranking.

This project was developed as part of my Web Application Development studies to practice Java fundamentals, object-oriented programming, file handling, collections, and serialization.

---

## 🎮 How the Game Works

At the beginning of the game, a movie is randomly selected from the available movie list.

The letters of the movie title are hidden:

```text
Movie: ***** ******
```

During each turn, the player can:

```text
[1] Guess a letter
[2] Guess the movie title
[3] Exit
```

Correct letters are revealed in their corresponding positions.

The player has a maximum of **10 attempts** and must discover the movie before running out of attempts.

---

## ✨ Features

- Random movie selection
- Hidden movie titles
- Guess individual letters
- Guess the complete movie title
- Maximum number of attempts
- Detection of previously guessed letters
- Tracking of incorrect letters
- Input validation
- Scoring system
- Player nickname system
- Persistent Top 5 ranking
- Movie data stored using Java serialization
- Ranking data stored between executions

---

## 🏆 Scoring System

Players earn points by correctly identifying letters and completing the game.

For example, a correct letter adds points to the player's score:

```text
Good! The letter 'a' is in the title!
```

At the end of the game, players with a high enough score can enter the ranking.

---

## 🥇 Ranking

The game maintains a **Top 5 player ranking**.

When a player's score qualifies for the ranking, the game asks for a unique nickname.

The ranking is automatically sorted from the highest to the lowest score and stored locally so that it can be loaded again the next time the program runs.

---

## 🎥 Movie List

The game includes movies such as:

```text
Mamma Mia
Moulin Rouge
Avatar
El diario de Noa
Harry Potter
Toy Story
La La Land
Gladiator
Aladdin
Joker
```

One movie is randomly selected for each game.

---

## 🛠️ Technologies

- Java
- Object-Oriented Programming
- Java Collections
- Java Serialization
- File I/O
- Regular Expressions
- Exception Handling
- Random
- Scanner

---

## 📁 Project Structure

```text
Java-Guess-The-Movie/
├── src/
│   ├── Main.java
│   ├── Game.java
│   ├── Player.java
│   ├── Pelicula.java
│   └── JugadorRanking.java
│
├── Peliculas
├── objects.data
└── ranking.dat
```

### Main Classes

**`Main.java`**

Contains the main execution flow of the application, including the game loop, user interaction, movie selection, scoring, and ranking integration.

**`Player.java`**

Represents the player and manages the player's score.

**`Pelicula.java`**

Represents a movie used by the game.

**`JugadorRanking.java`**

Handles ranking entries, reading and writing ranking data, sorting players by score, and maintaining the Top 5.

**`Game.java`**

Contains game-related functionality such as guessing letters and movie titles.

---

## 🚀 Running the Project

### Requirements

Make sure Java is installed:

```bash
java -version
```

and:

```bash
javac -version
```

### Compile

From the project directory:

```bash
javac -d out src/*.java
```

### Run

Execute the application with:

```bash
java -cp out Main
```

---

## 💾 Data Persistence

The project uses Java object serialization to save and load information.

Movie objects can be stored in:

```text
objects.data
```

The player ranking is stored in:

```text
ranking.dat
```

This allows ranking information to persist between different executions of the program.

---

## 🧠 Concepts Practiced

This project demonstrates practical experience with:

- Java syntax and fundamentals
- Object-oriented programming
- Classes and objects
- Methods and constructors
- Lists and Sets
- Loops and conditional statements
- User input with `Scanner`
- Random value generation
- String manipulation
- Regular expressions
- File reading and writing
- Object serialization
- Exception handling
- Sorting collections
- Persistent application data

---

## 🎯 Project Purpose

Java Guess The Movie was created as an academic Java project to practice the development of an interactive console application.

The project combines game logic with object-oriented programming, data persistence, collections, file management, and user input validation.

---

## 🔮 Possible Future Improvements

Some possible improvements include:

- Add more movies
- Add movie categories
- Add difficulty levels
- Improve input validation
- Refactor duplicated game logic
- Add automated tests
- Store game configuration separately
- Improve ranking persistence
- Create a graphical user interface

---

## 👩‍💻 Author

**Ariadna Pérez Sánchez**

GitHub: `ariadnaperezsanchez`
