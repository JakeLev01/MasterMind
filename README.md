# MasterMind
Text Recreation of the board game MasterMind in Java.
This project uses OOP principles to create a game of MasterMind, where Game is the top class and subclasses are parts of the game such as History, GameConfigurationa and History. Once the game is generated, a secred code consisting of 4 letters (e.g. RRYP is Red Red Yellow Purple), each representing a color is created and the user is prompted to enter a guess.
The program takes in the users guess, determines if it is valid and then calculates the resulting clue. For every color guessed in the correct spot a black peg is awarded, for every color guessed that is in the code but not the right spot a white peg is awarded. If the user does not guess the code after 12 guesses, the game is over.

This program is designed to where the GameConfigurations can be altered, such as the valid colors or amount of guesses, and no errors will occur in the running of the game.

**Skills learned/used: Object-Oriented Programming, Java, Game Design, Java TextInput
