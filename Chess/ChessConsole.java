import java.util.*;

public class ChessConsole {
    private Piece[][] board;
    
    public ChessConsole(String s) {
        this.board = new Chess(s).getChessBoard();
    }

    public ChessConsole() {
        this.board =  new Chess().getChessBoard();
    }

    public void play() {
        System.out.println();
        System.out.println("♞ CHESS GAME ♞");
        System.out.println();

        Scanner sc = new Scanner(System.in);
        int turn = 0;
        boolean gameNotFinished = true;
        String[] userInput = new String[0];

        Chess.print(board);

        while(gameNotFinished){
            if(turn % 2 == 0){
        
                if (Chess.finished(board, true)) {
                    System.out.println("Black won!!!");
                    break;
                }
                System.out.print("White's move:");

            } else {
                if (Chess.finished(this.board, false)) {
                    System.out.println("White won!!!");
                    break;
                }

                System.out.print("Black's move:");
            }

            userInput = sc.nextLine().toLowerCase().split(" ");
            System.out.println();

            if(userInput.length == 1){
                
                int[] sourceCoords = Chess.squareToCoords(userInput[0]);
                Chess.print(board, sourceCoords[0],sourceCoords[1]);
                     
            } else {
                if(userInput.length == 2){
                    int[] sourceCoords = Chess.squareToCoords(userInput[0]);
                    int[] destCoords = Chess.squareToCoords(userInput[1]);

                    if(Chess.isSquareReachable(this.board, sourceCoords[0],sourceCoords[1], destCoords[0],destCoords[1])){
                        Chess.move(this.board, sourceCoords[0],sourceCoords[1], destCoords[0],destCoords[1]);

                        turn++;
                    } else {
                        System.out.println("The move is not reachable!");
                        System.out.println();
                    }
                    Chess.print(board);
                }
            }

        }
    }

    
}
