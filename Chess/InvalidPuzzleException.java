public class InvalidPuzzleException extends Exception {

    public InvalidPuzzleException(boolean e) {
        super("Invalid puzzle: Malformed arrangement.");
    }

    public InvalidPuzzleException(int e) {
        super("Invalid puzzle: Invalid number of kings in a single puzzle.");
    }

}
