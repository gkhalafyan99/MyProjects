import javax.swing.*;
import java.awt.Color;
import java.awt.event.*;
import java.awt.*;


public class ChessUI {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 800;
    private Piece[][] board;
    private static boolean whiteTurn = true;
    JLabel footer = new JLabel("White's turn");

    public ChessUI(String s) {
        this.board = new Chess(s).getChessBoard();
    }

    public ChessUI() {
        this.board =  new Chess().getChessBoard();
    }


    public void play() {

        JFrame gameWindow = new JFrame();


        gameWindow.setSize(WIDTH, HEIGHT);
        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameWindow.setLayout(new BorderLayout());
        gameWindow.getContentPane().setBackground(Color.LIGHT_GRAY);

        // Heading
        JPanel headingPanel = new JPanel();
        headingPanel.setLayout(new FlowLayout());
        JLabel heading = new JLabel("♞ CHESS GAME ♞");
        heading.setForeground(Color.ORANGE);
        headingPanel.add(heading);
        gameWindow.add(headingPanel, BorderLayout.NORTH);

        // Board
        JPanel boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(10, 10));
        String[] letters = {"a", "b", "c", "d", "e", "f", "g", "h"};
        JButton[][] squareList = new JButton[8][8];
        Color[][] colorList = new Color[8][8];
        Color lightColor = new Color(252, 211, 159);
        Color darkColor = new Color(201, 111, 0);
        Color frameColor = new Color(193, 224, 114);
        Color yellow = new Color(246, 255, 0);
        Color red = new Color(250, 42, 42);

        // First row
        for (int i = 0; i < 10; i++) {
            if (i == 0 || i == 9) {
                JButton square = new JButton("");
                square.setEnabled(false);
                square.setBackground(frameColor);
                square.setOpaque(true);
                square.setBorderPainted(false);
                boardPanel.add(square);

            } else {
                JButton square = new JButton(letters[i-1]);
                square.setEnabled(false);
                square.setFont(new Font("Arial", Font.PLAIN, 20));
                square.setBackground(frameColor);
                square.setOpaque(true);
                square.setBorderPainted(false);
                boardPanel.add(square);
            }
        }   

        boolean isWhite = true;
        // Main board
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 10; j++) {
                if (j == 0 || j == 9) {
                    Integer num = i + 1;
                    JButton square = new JButton(num.toString());
                    square.setEnabled(false);
                    square.setFont(new Font("Arial", Font.PLAIN, 20));
                    square.setBackground(frameColor);
                    square.setOpaque(true);
                    square.setBorderPainted(false);
                    boardPanel.add(square);
                } else {
                    JButton square;
                    int x = i;
                    int y = j-1;
                    if (board[x][y] == null) {
                        square = new JButton("");
                    } else {
                        square = new JButton(board[i][j-1].toString());
                    }

                    square.setFont(new Font("Arial", Font.PLAIN, 50));
                    if (isWhite) {
                        square.setBackground(lightColor);
                    } else {
                        square.setBackground(darkColor);
                    }

                    square.setOpaque(true);
                    square.setBorderPainted(false);

                    square.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            if (square.getBackground() == red) {
                                square.setBackground(Color.GRAY);
                                int[] coords = new int[2];
                                for (int n = 0; n < 8; n++) {
                                    for (int m = 0; m < 8; m++) {
                                        if (squareList[n][m].getBackground() == Color.GRAY) {
                                            coords[0] = n;
                                            coords[1] = m;
                                        }
                                    }
                                }

                             
                                for (int n = 0; n < 8; n++) {
                                    for (int m = 0; m < 8; m++) {
                                        if (squareList[n][m].getBackground() == yellow) {

                                            Chess.move(board, n, m, coords[0], coords[1]);

                                            for (int l = 0; l < 8; l++) {
                                                for (int p = 0; p < 8; p++) {
                                                    if (board[l][p] == null) {
                                                        squareList[l][p].setText("");
                                                    } else {
                                                        squareList[l][p].setText(board[l][p].toString());
                                                    }
                                                }
                                            }

                                            whiteTurn = !whiteTurn;

                                            if (whiteTurn) {
                                                if (Chess.finished(board, true)) {
                                                    for (int q = 0; q < 8; q++) {
                                                        for (int w = 0; w < 8; w++) {
                                                            squareList[q][w].setBackground(colorList[q][w]);
                                                        }
                                                    }
                                                    JOptionPane.showMessageDialog(gameWindow,"The game is over.\n" + "Blacks won !!!","",JOptionPane.PLAIN_MESSAGE);
                                                    footer.setText("Blacks won !!!");
                                                    return;
                                                }
                                                footer.setText("White's turn");
                                            } else {
                                                if (Chess.finished(board, false)) {
                                                    for (int q = 0; q < 8; q++) {
                                                        for (int w = 0; w < 8; w++) {
                                                            squareList[q][w].setBackground(colorList[q][w]);
                                                        }
                                                    }
                                                    JOptionPane.showMessageDialog(gameWindow,"The game is over.\n" + "Whites won !!!","",JOptionPane.PLAIN_MESSAGE);
                                                    footer.setText("Whites won !!!");
                                                    return;
                                                }
                                                footer.setText("Black's turn");
                                            }

                                        }
                                        squareList[n][m].setBackground(colorList[n][m]);
                                    }
                                }

                            } else if (square.getBackground() == yellow) {
                                for (int n = 0; n < 8; n++) {
                                    for (int m = 0; m < 8; m++) {
                                        squareList[n][m].setBackground(colorList[n][m]);
                                    }
                                }
                            } else {
                                for (int n = 0; n < 8; n++) {
                                    for (int m = 0; m < 8; m++) {
                                        squareList[n][m].setBackground(colorList[n][m]);
                                    }
                                }
    
                                if (square.getText() != "") {
                                    if ((whiteTurn && board[x][y].isWhite()) || (!whiteTurn && !board[x][y].isWhite())) {
                                        square.setBackground(yellow);
    
                                        int[][] coords = board[x][y].reachableCoordinates(board, x, y);
                                        for (int[] coord : coords) {
                                            if (Chess.isSquareReachable(board, x, y, coord[0], coord[1])) {
                                                squareList[coord[0]][coord[1]].setBackground(red);
                                            }
                                            
                                        }
                                    }

                                }
                            }
                            
                        }
                    });

                    squareList[x][y] = square;
                    if (isWhite) {
                        colorList[x][y] = lightColor;
                    } else {
                        colorList[x][y] = darkColor;
                    }

                    boardPanel.add(square);

                    if (j != 8) {
                        isWhite = !isWhite;
                    }
                }

            }
        }

        // Last row
        for (int i = 0; i < 10; i++) {
            if (i == 0 || i == 9) {
                JButton square = new JButton("");
                square.setEnabled(false);
                square.setBackground(frameColor);
                square.setOpaque(true);
                square.setBorderPainted(false);
                boardPanel.add(square);

            } else {
                JButton square = new JButton(letters[i-1]);
                square.setEnabled(false);
                square.setFont(new Font("Arial", Font.PLAIN, 20));
                square.setBackground(frameColor);
                square.setOpaque(true);
                square.setBorderPainted(false);
                boardPanel.add(square);
            }
        }
        for (JButton[] squares : squareList) {
            for (JButton square : squares) {
                square.setPreferredSize(new Dimension(30, 30));
                square.setMaximumSize(new Dimension(30, 30));
                square.setMinimumSize(new Dimension(30, 30));
            }
        }

        gameWindow.add(boardPanel, BorderLayout.CENTER);


        // Footer
        JPanel footerPanel = new JPanel();
        footerPanel.setLayout(new FlowLayout());
        footer.setForeground(Color.GRAY);
        footerPanel.add(footer);
        gameWindow.add(footerPanel, BorderLayout.SOUTH);

        gameWindow.setVisible(true);
        JOptionPane.showMessageDialog(gameWindow,"The game begins.\n" + "Whites start.","",JOptionPane.PLAIN_MESSAGE);
    }
}
