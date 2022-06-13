public class King extends Piece {

    private boolean hasMoved;


    public King(boolean white){
        this(white,false);
    }


    public King(boolean white, boolean hasMoved){
        super(white);
        this.hasMoved = hasMoved;
    }

    public King(King other) {
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
            return new String(new int[] { 0x2654 }, 0, 1);
        }else{
            return new String(new int[] { 0x265A }, 0, 1);
        }
    }


    public int[][] reachableCoordinates(Piece[][] board, int x, int y){

        int[][] result = new int[0][0];

        int[][] pattern = {
                {x + 1, y + 1},
                {x + 1, y},
                {x + 1, y - 1},

                {x, y + 1},
                {x, y - 1},

                {x - 1, y + 1},
                {x - 1, y},
                {x - 1, y - 1}
        };


        for(int i = 0; i < pattern.length; i++){

            if(pattern[i][0] >= 0 && pattern[i][0] < BOARD_RANKS && pattern[i][1] >= 0
                    && pattern[i][1] < BOARD_FILES) {


                if(board[pattern[i][0]][pattern[i][1]] == null) {

                        result = appendToResult(result, new int[]{pattern[i][0], pattern[i][1]});
                } else {
                    if ((board[pattern[i][0]][pattern[i][1]].isWhite() && !this.isWhite()) ||
                            (!board[pattern[i][0]][pattern[i][1]].isWhite() && this.isWhite())){
                        result = appendToResult(result, new int[]{pattern[i][0], pattern[i][1]});
                    }

                }

            }
        }

        // Castling right
        if ((x == 7 || x == 0) && y == 4 && board[x][5] == null && board[x][6] == null && board[x][7] instanceof Rook) {
            Rook r = (Rook) board[x][y+3];
            
            if (!this.hasMoved && !r.getMovedInfo()) {
                for (int[] square : result) {
                    if (square[0] == x && square[1] == y+1) {
                        result = appendToResult(result, new int[]{x, y+2});
                        break;
                    }
                }
            }
        }


        // Castling left
        if ((x == 7 || x == 0) && y == 4 && board[x][3] == null && board[x][2] == null && board[x][0] instanceof Rook) {
            Rook r = (Rook) board[x][0];
            
            if (!this.hasMoved && !r.getMovedInfo()) {
                for (int[] square : result) {
                    if (square[0] == x && square[1] == y-1) {
                        result = appendToResult(result, new int[]{x, y-2});
                        break;
                    }
                }
            }
        }


        return result;
    }

    
}
