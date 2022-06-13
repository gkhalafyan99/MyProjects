public class Queen extends Piece {

    public Queen(boolean white){
        super(white);
    }

    public Queen(Queen other) {
        this(other.isWhite());
    }


    public String toString(){
        if(this.isWhite()){
            return new String(new int[] { 0x2655 }, 0, 1);
        }else{
            return new String(new int[] { 0x265B }, 0, 1);
        }
    }


    public int[][] reachableCoordinates(Piece[][] board, int x, int y){
        int[][] result = new int[0][0];

        int[][] bishopMoves = Bishop.reachableCoordinatesStatic(board, x, y, this.isWhite());
        int[][] rookMoves = Rook.reachableCoordinatesStatic(board, x, y, this.isWhite());

        for(int[] square : bishopMoves) {
            result = appendToResult(result, square);
        }

        for(int[] square : rookMoves) {
            result = appendToResult(result, square);
        }

        return result;
    }
}
