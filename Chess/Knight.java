public class Knight extends Piece{
    
    public Knight(boolean white){
        super(white);
    }

    public Knight(Knight other) {
        this(other.isWhite());
    }

    public String toString(){
        if(this.isWhite()){
            return new String(new int[] { 0x2658 }, 0, 1);
        }else{
            return new String(new int[] { 0x265E }, 0, 1);
        }
    }


    /**
     * Set of coordinates of squares that a Knight can travel to, from where it is.
     * @param board The game board
     * @param x Starting position row coordinate
     * @param y Starting position column coordinate
     * @return Coordinates of board squares
     */
    public int[][] reachableCoordinates(Piece[][] board, int x, int y){

        int[][] result = new int[0][0];

        int[][] pattern = {
                {x + 2, y + 1},
                {x + 2, y - 1},
                {x - 2, y + 1},
                {x - 2, y - 1},

                {x + 1, y + 2},
                {x + 1, y - 2},
                {x - 1, y + 2},
                {x - 1, y - 2}
        };


        for(int i = 0; i < pattern.length; i++){

            if(pattern[i][0] >= 0 && pattern[i][0] < BOARD_RANKS && pattern[i][1] >= 0
                    && pattern[i][1] < BOARD_FILES) {


                if(board[pattern[i][0]][pattern[i][1]] == null){
                    result = appendToResult(result, new int[]{pattern[i][0], pattern[i][1]});
                }else {
                    if ((board[pattern[i][0]][pattern[i][1]].isWhite() && !this.isWhite()) ||
                            (!board[pattern[i][0]][pattern[i][1]].isWhite() && this.isWhite())
                    ) {
                        result = appendToResult(result, new int[]{pattern[i][0], pattern[i][1]});
                    }

                }

            }
        }


        return result;
    }
}

