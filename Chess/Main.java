import java.util.*; 
import java.io.FileInputStream; 
import java.io.FileNotFoundException;

public class Main {

    /**
     * java Main - will run default game in console
     * java Main puzzles.txt - will ask to select puzzles in console and will start the game with selected puzzle in console
     * java Main -ui - will run default game in GUI
     * java Main -ui puzzles.txt - will ask to select puzzles in console and will start the game with selected puzzle in GUI
     * @param args
     */
    public static void main(String[] args) {
        // 
        // 
        // 
        // 
        if (args.length != 0) {
            if (args.length == 1 && !args[0].equals("-ui") || args.length == 2) {
                System.out.println("Available puzzles");
                System.out.println();

                Scanner inputStream = null;
                try {
                    if (args.length != 2) {
                        inputStream = new Scanner(new FileInputStream(args[0]));
                    } else {
                        inputStream = new Scanner(new FileInputStream(args[1]));
                    }
                    
                } catch (FileNotFoundException e) {
                    System.out.println("The file couldn't be loaded.");
                    System.exit(0);
                }

                
                ArrayList<String> headings = new ArrayList<>();
                ArrayList<String> puzzles = new ArrayList<>();

                int count = 1;
                while (inputStream.hasNextLine()) {
                    headings.add("Puzzle N" + count + ". " + inputStream.nextLine());
                    count++;
                    puzzles.add(inputStream.nextLine());
                }

                for (int i = 0; i < headings.size(); i++) {
                    System.out.println(headings.get(i));
                    Chess c = new Chess(puzzles.get(i));
                    c.printBoard();
                }


                Scanner keyboard = new Scanner(System.in);
                System.out.print("Please pick the one of the puzzles listed above (type in the puzzle number): ");

                int n;
                while (true) {
                    n = keyboard.nextInt();
                    if (n > 0 && n < headings.size()+1) {
                        break;
                    }
                    System.out.print("You must type in a positive integer less than " + (headings.size()+1) + ". Try again: ");
                }
                System.out.println();

                if (args.length != 2) {
                    ChessConsole game = new ChessConsole(puzzles.get(n-1));
                    game.play();
                } else {
                    ChessUI game = new ChessUI(puzzles.get(n-1));
                    game.play();
                }

            } else if (args.length == 1 && args[0].equals("-ui")) {
                ChessUI game = new ChessUI();
                game.play();
            } 
            
            
        } else {
            ChessConsole game = new ChessConsole();
            game.play();
        }


    }
}