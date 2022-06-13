public class Chess {

    /**
     * The board.
     */
    private Piece[][] board;

    /**
        Ranks are rows, Files are columns
     */
    private static final int BOARD_RANKS = 8;
    private static final int BOARD_FILES = 8;


    public Chess(String s) {
        // If an exception is thrown, a default game will be started
        try {
            if (validatePuzzle(s, 0, 0) == -1) {
                throw new InvalidPuzzleException(true);
            } else if (validatePuzzle(s, 0, 0) == -2) {
                throw new InvalidPuzzleException(1);
            }
        } catch (Exception e) {
            System.out.println();
            System.out.println(e.getMessage());
            System.out.println();
            System.out.println("A game with default puzzle will start !!!");
            s = "24232225262223242121212121212121000000000000000000000000000000000000000000000000000000000000000011111111111111111413121516121314";
        }
        

        this.board = new Piece[BOARD_RANKS][BOARD_FILES];
        int count = 0;
        for(int i = 0; i < this.board.length; i++) {
            for (int j = 0; j < this.board[i].length; j++) {
                String piece = s.substring(count, count+2);
                switch (piece) {
                    case "00":
                        this.board[i][j] = null;
                        break;
                    case "11":
                        this.board[i][j] = new Pawn(true);
                        break;
                    case "12":
                        this.board[i][j] = new Bishop(true);
                        break;
                    case "13":
                        this.board[i][j] = new Knight(true);
                        break;
                    case "14":
                        this.board[i][j] = new Rook(true);
                        break;
                    case "15":
                        this.board[i][j] = new Queen(true);
                        break;
                    case "16":
                        this.board[i][j] = new King(true);
                        break;
                    case "17":
                        this.board[i][j] = new Rook(true, true);
                        break;
                    case "18":
                        this.board[i][j] = new King(true, true);
                        break;
                    case "21":
                        this.board[i][j] = new Pawn(false);
                        break;
                    case "22":
                        this.board[i][j] = new Bishop(false);
                        break;
                    case "23":
                        this.board[i][j] = new Knight(false);
                        break;
                    case "24":
                        this.board[i][j] = new Rook(false);
                        break;
                    case "25":
                        this.board[i][j] = new Queen(false);
                        break;
                    case "26":
                        this.board[i][j] = new King(false);
                        break;
                    case "27":
                        this.board[i][j] = new Rook(false, true);
                        break;
                    case "28":
                        this.board[i][j] = new King(false, true);
                        break;
                }
                count += 2;
            }
        }

    }

    public Chess() {
        this("24232225262223242121212121212121000000000000000000000000000000000000000000000000000000000000000011111111111111111413121516121314");
    }


    public Piece[][] getChessBoard() {
        Piece[][] newBoard = new Piece[BOARD_RANKS][BOARD_FILES];
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[r].length; c++) {
                if (board[r][c] instanceof Queen) {
                    newBoard[r][c] = new Queen((Queen) board[r][c]);
                } else if (board[r][c] instanceof King) {
                    newBoard[r][c] = new King((King) board[r][c]);
                } else if (board[r][c] instanceof Rook) {
                    newBoard[r][c] = new Rook((Rook) board[r][c]);
                } else if (board[r][c] instanceof Bishop) {
                    newBoard[r][c] = new Bishop((Bishop) board[r][c]);
                } else if (board[r][c] instanceof Knight) {
                    newBoard[r][c] = new Knight((Knight) board[r][c]);
                } else if (board[r][c] instanceof Pawn){
                    newBoard[r][c] = new Pawn((Pawn) board[r][c]);
                } else {
                    newBoard[r][c] = null;
                }
                
            }
        }

        return newBoard;
    }


 

    public static void move(Piece[][] board, int sourceX, int sourceY, int destX, int destY) {
        // Castling right (mooving the rook)
        if ((sourceX == 7 || sourceX == 0) && sourceY == 4 && destY == 6 &&
        board[sourceX][sourceY] instanceof King) {
            Rook r = (Rook) board[destX][7];
            board[destX][destY-1] = new Rook(r.isWhite(), true);
            board[destX][7] = null;
        }

        // Castling left (mooving the rook)
        if ((sourceX == 7 || sourceX == 0) && sourceY == 4 && destY == 2 &&
        board[sourceX][sourceY] instanceof King) {
            Rook r = (Rook) board[destX][0];
            board[destX][destY+1] = new Rook(r.isWhite(), true);
            board[destX][0] = null;
        }

        board[destX][destY] = board[sourceX][sourceY];
        board[sourceX][sourceY] = null;

        if (board[destX][destY] instanceof King) {
            board[destX][destY] = new King(board[destX][destY].isWhite(), true);
        } else if (board[destX][destY] instanceof Rook) {
            board[destX][destY] = new Rook(board[destX][destY].isWhite(), true);
        }
    }

    public static boolean finished(Piece[][] board, boolean whiteTurn) {
        int count = 0;
        if (whiteTurn) {
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    if (board[i][j] != null && board[i][j].isWhite()) {
                        int[][] coords = board[i][j].reachableCoordinates(board, i, j);
                        for (int[] coord : coords) {
                            if (isSquareReachable(board, i, j, coord[0], coord[1])) {
                                count++;
                            }
                        }
                        
                    }
                }
            } 
        } else {
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    if (board[i][j] != null && !board[i][j].isWhite()) {
                        int[][] coords = board[i][j].reachableCoordinates(board, i, j);
                        for (int[] coord : coords) {
                            if (isSquareReachable(board, i, j, coord[0], coord[1])) {
                                count++;
                            }
                        }
                        
                    }
                }
            } 
        }


        return count == 0;
    }

    /**
     * Converts a given square from chess notation to matrix coordinates.
     * @param s A chessboard square, such as b7
     * @return An array with row and col of the matrix. {1,1} for b7
     */
    public static int[] squareToCoords(String s){
        return new int[] {56  - s.charAt(1), s.charAt(0) - 97};
    }

    /**
     * Prints the board and the pieces on it.
     */
    public static void print(Piece[][] board){
        for(int i = 0; i < BOARD_RANKS;i++ ){
            System.out.print((8 - i) + " ");
            for(int j = 0; j < BOARD_FILES; j++){
                if(board[i][j] != null){
                    System.out.print("[" + board[i][j] + "]");
                }else{
                    System.out.print("[ ]");
                }
            }
            System.out.println();
        }
        System.out.println("   A  B  C  D  E  F  G  H ");
        System.out.println();
    }

    /**
     * The highlight version of the print method. Prints the board with the pieces on it. Highlights
     * the squares that are reachable from a given square, according to the piece that resides in it.
     * @param sourceX row
     * @param sourceY column
     */
    public static void print(Piece[][] board, int sourceX, int sourceY){
        if(board[sourceX][sourceY] == null) return;

        for(int i = 0; i < BOARD_RANKS; i++ ){
            System.out.print((8 - i) + " ");
            for(int j = 0; j < BOARD_FILES; j++){
                if(isSquareReachable(board, sourceX,sourceY,i,j)){
                    if(board[i][j] != null){
                        System.out.print("\u001b[31m[" + board[i][j] + "]\u001b[0m");

                    }else{
                        System.out.print("\u001b[31m[ ]\u001b[0m");
                    }
                }else{
                    if(board[i][j] != null){
                        System.out.print("[" + board[i][j] + "]");

                    }else{
                        System.out.print("[ ]");
                    }
                }
            }
            System.out.println();
        }
        System.out.println("   A  B  C  D  E  F  G  H ");
        System.out.println();
    }

    public void printBoard() {
        for(int i = 0; i < BOARD_RANKS;i++ ){
            System.out.print((8 - i) + " ");
            for(int j = 0; j < BOARD_FILES; j++){
                if(board[i][j] != null){
                    System.out.print("[" + board[i][j] + "]");
                }else{
                    System.out.print("[ ]");
                }
            }
            System.out.println();
        }
        System.out.println("   A  B  C  D  E  F  G  H ");
        System.out.println();
    }

    /**
     * Determines whether the piece in the source square can go to the destination square
     * @param sourceX row
     * @param sourceY column
     * @param destinationX row
     * @param destinationY column
     * @return true, if the movement is possible and false, otherwise.
     */
    public static boolean isSquareReachable(Piece[][] board, int sourceX, int sourceY,
                                      int destinationX, int destinationY){

        if(board[sourceX][sourceY] == null )return false;

        if (!notUnderThreat(board, sourceX, sourceY, destinationX, destinationY)) {
            return false;
        }

        int[][] reachableFrom = board[sourceX][sourceY].reachableCoordinates(board, sourceX, sourceY);

        for(int i = 0; i < reachableFrom.length; i++){
            if(reachableFrom[i][0] == destinationX && reachableFrom[i][1] == destinationY){

                // Castling right
                if((sourceX == 7 || sourceX == 0) && sourceY == 4 && destinationX == sourceX && destinationY == sourceY+2 && 
                board[sourceX][sourceY] instanceof King) {
                    if (notUnderThreat(board, sourceX, sourceY) && notUnderThreat(board, sourceX, sourceY, destinationX, sourceY+1)) {
                        return true;
                    } 
                    
                    return false;
                }

                // Castling left
                if((sourceX == 7 || sourceX == 0) && sourceY == 4 && destinationX == sourceX && destinationY == sourceY-2 && 
                board[sourceX][sourceY] instanceof King) {
                    if (notUnderThreat(board, sourceX, sourceY) && notUnderThreat(board, sourceX, sourceY, destinationX, sourceY-1)) {
                        return true;
                    } 
                    
                    return false;
                }

                return true;
            }

        }
        return false;
    }


    private static boolean notUnderThreat(Piece[][] board, int sourceX, int sourceY, int destinationX, int destinationY) {

        Piece toMove = board[sourceX][sourceY];
        Piece[][] newBoard = new Piece[board.length][board[0].length];
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[r].length; c++) {
                if (board[r][c] instanceof Queen) {
                    newBoard[r][c] = new Queen((Queen) board[r][c]);
                } else if (board[r][c] instanceof King) {
                    newBoard[r][c] = new King((King) board[r][c]);
                } else if (board[r][c] instanceof Rook) {
                    newBoard[r][c] = new Rook((Rook) board[r][c]);
                } else if (board[r][c] instanceof Bishop) {
                    newBoard[r][c] = new Bishop((Bishop) board[r][c]);
                } else if (board[r][c] instanceof Knight) {
                    newBoard[r][c] = new Knight((Knight) board[r][c]);
                } else if (board[r][c] instanceof Pawn){
                    newBoard[r][c] = new Pawn((Pawn) board[r][c]);
                } else {
                    newBoard[r][c] = null;
                }
                
            }
        }

        newBoard[destinationX][destinationY] = newBoard[sourceX][sourceY];
        newBoard[sourceX][sourceY] = null;

        int kingX = -1;
        int kingY = -1;

        for (int l = 0; l < newBoard.length; l++) {
            for (int k = 0; k < newBoard[l].length; k++) {
                if (newBoard[l][k] instanceof King) {
                    if (newBoard[l][k].isWhite() == toMove.isWhite()) {
                        kingX = l;
                        kingY = k;
                    }
                }
            }
        }

        for (int i = 0; i < newBoard.length; i++) {
            for (int j = 0; j < newBoard[i].length; j++) {
                if (newBoard[i][j] != null && newBoard[i][j].isWhite() != toMove.isWhite()) {
                    int[][] movesUnderThreat;
                    
                    movesUnderThreat = newBoard[i][j].reachableCoordinates(newBoard, i, j);
                     
                    for (int[] coord : movesUnderThreat) {
                        if(coord[0] == kingX && coord[1] == kingY) {
                            return false;
                        }
                    }
                }
                
            }
        }

        return true;
    }


    private static boolean notUnderThreat(Piece[][] board, int sourceX, int sourceY) {

        Piece toMove = board[sourceX][sourceY];

        int kingX = -1;
        int kingY = -1;

        for (int l = 0; l < board.length; l++) {
            for (int k = 0; k < board[l].length; k++) {
                if (board[l][k] instanceof King) {
                    if (board[l][k].isWhite() == toMove.isWhite()) {
                        kingX = l;
                        kingY = k;
                    }
                }
            }
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] != null && board[i][j].isWhite() != toMove.isWhite()) {
                    int[][] movesUnderThreat;
                    
                    movesUnderThreat = board[i][j].reachableCoordinates(board, i, j);
                     
                    for (int[] coord : movesUnderThreat) {
                        if(coord[0] == kingX && coord[1] == kingY) {
                            return false;
                        }
                    }
                }
                
            }
        }

        return true;
    }



    /**
     * Method for printing integer matrices.
     * @param m The matrix that needs to be printed
     */
    public static void printMatrix(int[][] m){
        for(int i = 0; i < m.length; i++){
            for(int j = 0; j < m[i].length; j++){
                System.out.print(m[i][j] + " ");
            }
            System.out.println();
        }
    }


    private int validatePuzzle(String puzzle, int whiteKingCount, int blackKingCount) {

            if (puzzle.length() != 128) {
                return -1;
            }

            for (int j = 0; j < 128; j += 2) {
                String piece = puzzle.substring(j, j+2);
                if (piece.equals("16") || piece.equals("18")) {
                    whiteKingCount++;
                }
                else if (piece.equals("26") || piece.equals("28")) {
                    blackKingCount++;
                }
            }

            if (whiteKingCount != 1 || blackKingCount != 1) {
                return -2;
            }

            return 0;
        
    }


}
