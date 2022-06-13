/**
 * Pawn class
 * Can capture other pawns en-passant https://www.youtube.com/watch?v=bJtfOjqy07Y
 */
public class Pawn extends Piece{
    private int[] passingPawn;

    public Pawn(boolean white){
        super(white);
    }

    public Pawn(Pawn other) {
        this(other.isWhite());
    }

    public String toString(){
        return isWhite() ? new String(new int[] { 0x2659 }, 0, 1) : new String(new int[] { 0x265F }, 0, 1);
    }

    public int[][] reachableCoordinates(Piece[][] board, int x, int y){
        int[][] result = new int[0][0];

        if(isWhite()){
            if(board[x - 1][y] == null){
                result = appendToResult(result, new int[]{x-1,y});
                if(x == 6 && board[x - 2][y] == null){
                    result = appendToResult(result, new int[]{x-2,y});
                }
            }

            if(y > 0 && board[x - 1][y - 1] != null && !board[x - 1][y - 1].isWhite()){
                result = appendToResult(result, new int[]{x - 1,y-1});
            }

            if(y < BOARD_FILES - 1 && board[x - 1][y + 1] != null && !board[x - 1][y + 1].isWhite()){
                result = appendToResult(result, new int[]{x - 1,y+1});
            }
        } else{
            if(board[x + 1][y] == null){
                result = appendToResult(result, new int[]{x+1,y});
                if(x == 1 && board[x + 2][y] == null){
                    result = appendToResult(result, new int[]{x+2,y});
                }
            }

            if(y > 0 && board[x + 1][y - 1] != null && board[x + 1][y - 1].isWhite()){
                result = appendToResult(result, new int[]{x + 1,y-1});
            }

            if(y < BOARD_FILES - 1 && board[x + 1][y + 1] != null && board[x + 1][y + 1].isWhite()){
                result = appendToResult(result, new int[]{x + 1,y+1});
            }
        }

        return result;
    }

    public void setPassingPawn(int x, int y){
        this.passingPawn = new int[]{x, y};
    }

    public void setPassingPawn(){
        this.passingPawn = null;
    }
}

