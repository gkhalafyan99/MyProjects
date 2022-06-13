/**
 * Class for Rooks, contains partial logic for castling
 */
public class Rook extends Piece{

    /**
     * A rook that has moved, cannot be used for castling.
     */
    private boolean hasMoved;

    /**
     * Constructor
     * @param white
     */
    public Rook(boolean white){
        this(white,false);
    }

    /**
     * Overloaded constructor, useful for initializing puzzles.
     * @param white
     * @param hasMoved
     */
    public Rook(boolean white, boolean hasMoved){
        super(white);
        this.hasMoved = hasMoved;
    }

    public Rook(Rook other) {
        this(other.isWhite());
    }

    public void setMoved(){
        this.hasMoved = true;
    }

    public boolean getMovedInfo() {
        return this.hasMoved;
    }

    public String toString(){
        if(this.isWhite()){
            return new String(new int[] { 0x2656 }, 0, 1);
        }else{
            return new String(new int[] { 0x265C }, 0, 1);
        }
    }

    /**
     * Set of coordinates of squares that a Rook can travel to, from where it is.
     * @param board The game board
     * @param x Starting position row coordinate
     * @param y Starting position column coordinate
     * @return Coordinates of board squares
     */
    public int[][] reachableCoordinates(Piece[][] board, int x, int y){
        return reachableCoordinatesStatic(board, x, y, this.isWhite());
    }



    public static int[][] reachableCoordinatesStatic(Piece[][] board, int x, int y, boolean isWhite) {

        int[][] result = new int[0][0];

        for(int i = x + 1; i < BOARD_RANKS; i++){
            if(board[i][y] == null) {
                result = appendToResult(result, new int[]{i, y});
            }else{
                if(!((board[i][y].isWhite() && isWhite) ||
                        (!board[i][y].isWhite() && !isWhite)))
                {
                    result = appendToResult(result,new int[]{i,y});
                }
                break;
            }
        }

        for(int i = x - 1; i >= 0; i--){
            if(board[i][y] == null) {
                result = appendToResult(result, new int[]{i, y});
            }else{
                if(!((board[i][y].isWhite() && isWhite) ||
                        (!board[i][y].isWhite() && !isWhite)))
                {
                    result = appendToResult(result,new int[]{i,y});
                }
                break;
            }
        }

        for(int i = y + 1; i < BOARD_FILES; i++){
            if(board[x][i] == null) {
                result = appendToResult(result, new int[]{x, i});
            }else{
                if(!((board[x][i].isWhite() && isWhite) ||
                        (!board[x][i].isWhite() && !isWhite)))
                {
                    result = appendToResult(result,new int[]{x,i});
                }
                break;
            }
        }

        for(int i = y - 1; i >= 0; i--){
            if(board[x][i] == null) {
                result = appendToResult(result, new int[]{x, i});
            }else{
                if(!((board[x][i].isWhite() && isWhite) ||
                        (!board[x][i].isWhite() && !isWhite)))
                {
                    result = appendToResult(result,new int[]{x,i});
                }
                break;
            }
        }

        return result;

    }
}
