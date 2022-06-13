/**
 * Bishops move diagonally.
 */
public class Bishop extends Piece{
    public Bishop(boolean white){
        super(white);
    }

    public Bishop(Bishop other) {
        this(other.isWhite());
    }

    public String toString(){
        if(this.isWhite()){
            return new String(new int[] { 0x2657 }, 0, 1);
        }else{
            return new String(new int[] { 0x265D }, 0, 1);
        }
    }

    /**
     * Set of coordinates of squares that a Bishop can travel to, from where it is.
     * @param board The game board
     * @param x Starting position row coordinate
     * @param y Starting position column coordinate
     * @return Coordinates of board squares
     */
    public int[][] reachableCoordinates(Piece[][] board, int x, int y){
        return Bishop.reachableCoordinatesStatic(board, x, y, this.isWhite());
    }


    public static int[][] reachableCoordinatesStatic(Piece[][] board, int x, int y, boolean isWhite) {

        int[][] result = new int[0][0];

        for(int i = 1; x + i < BOARD_RANKS && y + i < BOARD_FILES; i++){
            if(board[x+i][y+i] == null){
                result = appendToResult(result,new int[]{x+i,y+i});
            }else{
                if(
                        (board[x+i][y+i].isWhite() && isWhite) ||
                                ((!board[x+i][y+i].isWhite() && !isWhite))
                ){
                    break;

                }else{
                    result = appendToResult(result,new int[]{x+i,y+i});
                    break;
                }

            }
        }

        for(int i = 1; x - i >= 0 && y + i < BOARD_FILES; i++){
            if(board[x-i][y+i] == null){
                result = appendToResult(result,new int[]{x-i,y+i});
            }else{
                if(
                        (board[x-i][y+i].isWhite() && isWhite) ||
                                ((!board[x-i][y+i].isWhite() && !isWhite))
                ){
                    break;

                }else{
                    result = appendToResult(result,new int[]{x-i,y+i});
                    break;
                }

            }
        }

        for(int i = 1; x + i < BOARD_RANKS && y - i >= 0; i++){
            if(board[x+i][y-i] == null){
                result = appendToResult(result,new int[]{x+i,y-i});
            }else{
                if(
                        (board[x+i][y-i].isWhite() && isWhite) ||
                                ((!board[x+i][y-i].isWhite() && !isWhite))
                ){
                    break;

                }else{
                    result = appendToResult(result,new int[]{x+i,y-i});
                    break;
                }

            }
        }

        for(int i = 1; x - i >= 0 && y - i >= 0; i++){
            if(board[x-i][y-i] == null){
                result = appendToResult(result,new int[]{x-i,y-i});
            }else{
                if(
                        (board[x-i][y-i].isWhite() && isWhite) ||
                                ((!board[x-i][y-i].isWhite() && !isWhite))
                ){
                    break;

                }else{
                    result = appendToResult(result,new int[]{x-i,y-i});
                    break;
                }

            }
        }


        return result;

    }
}

