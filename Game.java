/* EE422C Assignment #2 submission by
 * Jake Leverett
 * jsl3356
 */

package assignment2;


class Game extends Driver{
    public static History history = new History();
    private static String code;
    static int numOfGuessesLeft;
    static int numOfPegs;
    static String[] validColors;
    public static int numGuessed;
    static String guess;
    static String result;
    static boolean isGameActive;
    static String youLose = "\nSorry, you are out of guesses. You lose, boo-hoo";
    static String youWin = " - You win !!";

    Game(){
        numOfGuessesLeft = GameConfiguration.guessNumber;
        numOfPegs = GameConfiguration.pegNumber;
        validColors = GameConfiguration.colors;
        numGuessed = 0;
        setCode();
        isGameActive = true;
    }

    private static void setCode(){
        code = SecretCodeGenerator.getInstance().getNewSecretCode();
    }

    public static String getCode(){
        return code;
    }

    public static void runGame() {
        //System.out.println("\n" + getCode());

        while(isGameActive){
            if(numOfGuessesLeft == 0){
                youLost();
            }

            System.out.print("\nYou have " + numOfGuessesLeft + " guesses left. \n" + "What is your next guess? \n" + "Type in the characters for your guess and press enter. \n" + "Enter guess: ");

            guess = scnr.next();

            if(guess.equals("HISTORY")){
                history.printGuesses();
            }else if(isGuessValid(guess)){
                numGuessed++;
                numOfGuessesLeft--;
                getClues();
                history.add(guess, result);
                System.out.println("\n" + guess + " -> Result: " + result);
            }else{
                System.out.println("\n" + guess + " -> INVALID GUESS");
            }
        }
    }

    //DOES NOT WORK!!!! PLEASE FIX!!!!
    private static void getClues() {
        int numBlackPegs = 0;
        int numWhitePegs = 0;
        StringBuilder parseGuess = new StringBuilder(guess);
        StringBuilder parseCode = new StringBuilder(getCode());
        if(guess.equals(getCode())){
            result = GameConfiguration.pegNumber + "B_0W";
            youWin();
        }
        for(int i = 0;i<numOfPegs;i++){
            if(parseGuess.charAt(i) == parseCode.charAt(i) && parseGuess.charAt(i) != '-' && parseCode.charAt(i) != '-'){
                numBlackPegs++;
                parseGuess.setCharAt(i, '-');
                parseCode.setCharAt(i, '-');
            }
        }
        for(int i = 0;i<numOfPegs;i++){
            for(int j = 0;j<numOfPegs;j++){
                if(parseGuess.charAt(i) == parseCode.charAt(j) && parseGuess.charAt(i) != '-' && parseCode.charAt(j) != '-'){
                    numWhitePegs++;
                    parseGuess.setCharAt(i, '-');
                    parseCode.setCharAt(j, '-');
                    break;
                }
            }
        }
        result = numBlackPegs + "B_" + numWhitePegs + "W";
    }

    private static void youWin() {
        System.out.print("\n" + guess + " -> Result: " + result);
        System.out.println(youWin);
        System.out.print("\nAre you ready for another game (Y/N): ");
        String next = scnr.next();
        if(next.equals("Y")){
            playAgain();
        }else {
            isGameActive = false;
            System.exit(0);
        }
    }

    private static void youLost() {
        System.out.println(youLose);
        System.out.print("\nAre you ready for another game (Y/N): ");
        String next = scnr.next();
        if(next.equals("Y")){
            playAgain();
        }else{
            isGameActive = false;
            System.exit(0);
        }
    }

    private static void playAgain() {
        numOfGuessesLeft = GameConfiguration.guessNumber;
        numGuessed = 0;
        setCode();
        if(testMode){
            System.out.println("\nGenerating secret code ... (for this example the secret code is " + getCode() + ")");
        }else{
            System.out.println("\nGenerating secret code ...");
        }
        history.reset();
        runGame();
    }

    private static boolean isGuessValid(String guess){
        boolean found = false;
        if(guess.length() != numOfPegs){
            return false;
        }
        for(int i = 0;i<guess.length();i++){
            String comp = guess.substring(i,i+1);
            for(int j = 0;j< validColors.length;j++){
                String color = validColors[j];
                if(comp.equals(color)){
                    found = true;
                    break;
                }else{
                    found = false;
                }
            }
            if(!found) return false;
        }
        return true;
    }

}
