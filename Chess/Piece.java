/**
    Abstract class that acts as a prototype for all pieces but is not a specific chess piece.
 */
public abstract class Piece {

    private boolean pieceIsWhite;
    protected static final int BOARD_RANKS = 8;
    protected static final int BOARD_FILES = 8;


    public Piece(boolean white){
        this.pieceIsWhite = white;
    }

    public abstract int[][] reachableCoordinates(Piece[][] board, int x, int y);

    /**
     * Overriden toString for unspecified pieces.
     * @return
     */
    public String toString(){
        return null;
    }


    /**
        @return true is the piece is white.
     */
    public boolean isWhite(){
        return pieceIsWhite;
    }

    /**
     * Utility method for adding the coordinates of a board square to an existing set of board square coordinates.
     * Subclasses use this method to add multiple squares to the set of squares they can reach
     * with more ease.
     *
     * @param squares Existing elements
     * @param square Element to be added
     * @return Exisiting elements with the new element added.
     */
    protected static int[][] appendToResult(int[][] squares, int[] square){

        int[][] result = new int[squares.length + 1][];

        for(int i = 0; i < squares.length; i++){
            result[i] = new int[]{squares[i][0], squares[i][1]};
        }

        result[result.length - 1] = new int[]{square[0],square[1]};


        return result;
    }



}
