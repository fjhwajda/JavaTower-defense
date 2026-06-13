# Tower Defense Java Project

Welcome to my Tower Defense learning project! This repository documents my journey in building a game from scratch using Java, focusing on game logic, object-oriented design, and GUI development with Swing.

## Project Structure

The project follows a modular structure to ensure clean code and separation of concerns:

- `src/`: Main source code directory.
  - `main/java/`: Contains the actual Java classes.
    - `engine/`: Handles the game loop and physics/calculations.
    - `entities/`: Base classes for Enemies and Towers.
    - `gui/`: View classes (Swing/JFrame).
    - `model/`: Data structures like the game map and player stats.
- `lib/`: External dependencies.
- `resources/`: Assets such as images or sound files.

## Getting Started

### Prerequisites
- Java Development Kit (JDK) 11 or higher.
- An IDE (VS Code with the "Extension Pack for Java" is recommended).

### How to Run
1. Clone this repository to your local machine.
2. Open the project folder in VS Code.
3. Locate `App.java` inside `src/main/java/com/game/`.
4. Run the file using the "Run" button provided by the Java extension.

## Learning Goals

This project serves as a practical implementation of several core concepts:
- **Game Loop:** Implementing a constant update cycle.
- **MVC Architecture:** Separating Model (logic), View (graphics), and Controller (input).
- **State Machines:** Managing game states like Menu, Playing, and GameOver.
- **Git Workflow:** Version control best practices.

## Roadmap

- [X] Implement game grid and path visualization.
- [X] Create basic enemy pathfinding.
- [X] Add interactive tower placement via `MouseListener`.
- [X] Implement distance-based shooting logic.
