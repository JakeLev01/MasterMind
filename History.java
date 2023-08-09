/* EE422C Assignment #2 submission by
 * Jake Leverett
 * jsl3356
 */

package assignment2;

class History extends Game{
    public static int index = 0;
    public static String[] guesses;
    public static String[] results;

    History(){
        guesses = new String[GameConfiguration.guessNumber];
        results = new String[GameConfiguration.guessNumber];
        index = 0;
    }

    public static void reset() {
        for(int i = 0;i<GameConfiguration.guessNumber-1;i++){
            guesses[i] = null;
            results[i] = null;
        }
        index = 0;
    }

    public void add(String guess, String result) {
        guesses[index] = guess;
        results[index] = result;
        index++;
    }

    public static void printGuesses(){
        for(int i = 0;i<index;i++){
            if(guesses[i].equals("")){
                return;
            }
            System.out.print("\n" + guesses[i] + "\t\t" + results[i]);
        }
        System.out.println();
    }
}
