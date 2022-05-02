import java.util.*;

// TicTacToe class
public class TicTacToe {

    // method to print the board
    public static void printBoard(String[][] board) {
        System.out.println(board[0][0] + " | " + board[0][1] + " | " + board[0][2]);
        System.out.println("---------");
        System.out.println(board[1][0] + " | " + board[1][1] + " | " + board[1][2]);
        System.out.println("---------");
        System.out.println(board[2][0] + " | " + board[2][1] + " | " + board[2][2]);
    }

    // recursive minimax method to compute value of move
    static int minimax(String[][] board, int depth, Boolean comp) {
        String result = check(board);

        // computer wins
        if (result.equals("X")) {
            return 10 - depth;
        }

        // human wins
        if (result.equals("O")) {
            return -10 + depth;
        }

        // draw
        if (result.equals("cat")) {
            return 0;
        }

        // if this is the computer's move
        if (comp) {
            int best = -1000;

            // check for empty spots and make a move
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (!board[i][j].equals("X") && !board[i][j].equals("O")) {
                        String temp = board[i][j];
                        board[i][j] = "X";

                        // call minimax recursively and get best score
                        best = Math.max(best, minimax(board, depth + 1, !comp));

                        board[i][j] = temp;
                    }
                }
            }
            return best;
        }
        // if this is the human's move
        else {
            int best = 1000;

            // check for empty spots and make a move
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (!board[i][j].equals("X") && !board[i][j].equals("O")) {
                        String temp = board[i][j];
                        board[i][j] = "O";

                        // call minimax recursively and get best score
                        best = Math.min(best, minimax(board, depth + 1, comp));

                        board[i][j] = temp;
                    }
                }
            }
            return best;
        }
    }

    static int findBestMove(String[][] board) {
        int best = -1000;
        int r = -1;
        int c = -1;

        // check board and use minimax for all empty spots
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                // see if board is empty
                if (!board[i][j].equals("X") && !board[i][j].equals("O")) {

                    String temp = board[i][j];
                    board[i][j] = "X";
                    int value = minimax(board, 0, false);
                    board[i][j] = temp;

                    if (value > best) {
                        r = i;
                        c = j;
                        best = value;
                    }
                }
            }
        }

        // see what best move is
        if (r == 0) {
            if (c == 0) {
                return 1;
            } else if (c == 1) {
                return 2;
            } else {
                return 3;
            }
        } else if (r == 1) {
            if (c == 0) {
                return 4;
            } else if (c == 1) {
                return 5;
            } else {
                return 6;
            }
        } else {
            if (c == 0) {
                return 7;
            } else if (c == 1) {
                return 8;
            } else {
                return 9;
            }
        }
    }

    // method for the player picking a space
    public static String[][] playerPick(String[][] board, Scanner scannerObject) {
        String str;
        boolean valid = false;

        // Put player selection on board (make sure it is not invalid)
        while (!valid) {
            str = Integer.toString(scannerObject.nextInt());

            if (str.equals("1")) {
                if (!board[0][0].equals("X") && !board[0][0].equals("O")) {
                    board[0][0] = "O";
                    return board;
                } else {
                    System.out.println("Invalid Number - Try Again");
                }
            } else if (str.equals("2")) {
                if (!board[0][1].equals("X") && !board[0][1].equals("O")) {
                    board[0][1] = "O";
                    return board;
                } else {
                    System.out.println("Invalid Number - Try Again");
                }
            } else if (str.equals("3")) {
                if (!board[0][2].equals("X") && !board[0][2].equals("O")) {
                    board[0][2] = "O";
                    return board;
                } else {
                    System.out.println("Invalid Number - Try Again");
                }
            } else if (str.equals("4")) {
                if (!board[1][0].equals("X") && !board[1][0].equals("O")) {
                    board[1][0] = "O";
                    return board;
                } else {
                    System.out.println("Invalid Number - Try Again");
                }
            } else if (str.equals("5")) {
                if (!board[1][1].equals("X") && !board[1][1].equals("O")) {
                    board[1][1] = "O";
                    return board;
                } else {
                    System.out.println("Invalid Number - Try Again");
                }
            } else if (str.equals("6")) {
                if (!board[1][2].equals("X") && !board[1][2].equals("O")) {
                    board[1][2] = "O";
                    return board;
                } else {
                    System.out.println("Invalid Number - Try Again");
                }
            } else if (str.equals("7")) {
                if (!board[2][0].equals("X") && !board[2][0].equals("O")) {
                    board[2][0] = "O";
                    return board;
                } else {
                    System.out.println("Invalid Number - Try Again");
                }
            } else if (str.equals("8")) {
                if (!board[2][1].equals("X") && !board[2][1].equals("O")) {
                    board[2][1] = "O";
                    return board;
                } else {
                    System.out.println("Invalid Number - Try Again");
                }
            } else if (str.equals("9")) {
                if (!board[2][2].equals("X") && !board[2][2].equals("O")) {
                    board[2][2] = "O";
                    return board;
                } else {
                    System.out.println("Invalid Number - Try Again");
                }
            } else {
                System.out.println("Invalid Number - Try Again");
            }
        }
        return board;

    }

    // method for the computer picking a space
    public static String[][] computerPick(String[][] board, int k, boolean r) {
        System.out.println("Computer's Turn");

        int spacesLeft = 9 - k;

        String str = "";

        // check if player wants a random game or not
        // competitive games
        if (!r) {
            str = Integer.toString(findBestMove(board));
            System.out.println(str);
        }
        // random game
        else {
            String[] options = new String[spacesLeft];
            int index = 0;

            // add indexes of empty spaces into array
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (!board[i][j].equals("X") && !board[i][j].equals("O")) {
                        options[index] = board[i][j];
                        index++;
                    }
                }
            }

            // randomly select from that array
            int rando = (int) (Math.random() * spacesLeft);
            str = options[rando];
        }

        // Mark computer turn on board
        if (str.equals("1")) {
            board[0][0] = "X";
            return board;
        } else if (str.equals("2")) {
            board[0][1] = "X";
            return board;
        } else if (str.equals("3")) {
            board[0][2] = "X";
            return board;
        } else if (str.equals("4")) {
            board[1][0] = "X";
            return board;
        } else if (str.equals("5")) {
            board[1][1] = "X";
            return board;
        } else if (str.equals("6")) {
            board[1][2] = "X";
            return board;
        } else if (str.equals("7")) {
            board[2][0] = "X";
            return board;
        } else if (str.equals("8")) {
            board[2][1] = "X";
            return board;
        } else if (str.equals("9")) {
            board[2][2] = "X";
            return board;
        }
        return board;
    }

    // method to check if board is complete
    public static String check(String[][] board) {

        // check if anyone has won
        if (board[0][0].equals(board[0][1]) && board[0][1].equals(board[0][2]) && !board[0][0].equals(" ")) {
            return board[0][0];
        } else if (board[1][0].equals(board[1][1]) && board[1][1].equals(board[1][2]) && !board[1][0].equals(" ")) {
            return board[1][0];
        } else if (board[2][0].equals(board[2][1]) && board[2][1].equals(board[2][2]) && !board[2][0].equals(" ")) {
            return board[2][0];
        } else if (board[0][0].equals(board[1][0]) && board[1][0].equals(board[2][0]) && !board[0][0].equals(" ")) {
            return board[0][0];
        } else if (board[0][1].equals(board[1][1]) && board[1][1].equals(board[2][1]) && !board[0][1].equals(" ")) {
            return board[0][1];
        } else if (board[0][2].equals(board[1][2]) && board[1][2].equals(board[2][2]) && !board[0][2].equals(" ")) {
            return board[0][2];
        } else if (board[0][0].equals(board[1][1]) && board[1][1].equals(board[2][2]) && !board[0][0].equals(" ")) {
            return board[0][0];
        } else if (board[2][0].equals(board[1][1]) && board[1][1].equals(board[0][2]) && !board[2][0].equals(" ")) {
            return board[2][0];
        }

        // check if board is not full
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (!board[i][j].equals("X") && !board[i][j].equals("O")) {
                    System.out.println(board[i][j]);
                    return " ";
                }
            }
        }

        // board is full in a tie
        return "cat";
    }

    // method to print for end game
    public static boolean endGame(String s) {
        if (s.equals("X") || s.equals("O")) {
            System.out.println(s + " Wins!");
            return true;
        } else if (s.equals("cat")) {
            System.out.println("Tie! The cat wins!");
            return true;
        }
        return false;
    }

    public static void main(String[] args) {

        Scanner scannerObject = new Scanner(System.in);

        // initialize board
        String[][] board = { { "1", "2", "3" }, { "4", "5", "6" }, { "7", "8", "9" } };
        boolean r;

        // see if the player wants a random game or a competitive game
        System.out.print("\n");
        System.out.println("Would you like the computer to play randomly, or competitively?");
        System.out.println("Enter 'r' for random and anything else for competitive");
        String str = scannerObject.nextLine();
        if (str.equals("r")) {
            r = true;
            System.out.println("Random");
        } else {
            r = false;
            System.out.println("Competitive");
        }

        // print instructions
        System.out.print("\n");
        System.out.println("The computer is X - you are O");
        System.out.println("Play against the computer by entering one of 9 numbers");
        System.out.println("\n");

        int k = 0;
        // start the game
        for (int i = 0; i < 9; i++) {

            // have the computer pick a space
            board = computerPick(board, k, r);
            printBoard(board);
            System.out.println("\n");

            // check if game is over
            if (endGame(check(board))) {
                break;
            }

            // have the player pick a space
            board = playerPick(board, scannerObject);
            printBoard(board);
            System.out.println("\n");

            k += 2;

            // check if game is over
            if (endGame(check(board))) {
                break;
            }
        }
        System.out.println("Game is Over");
        scannerObject.close();
    }
}
