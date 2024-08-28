import javax.swing.*;
import java.awt.*;

public class TicTacToe extends JFrame {
    private static final int BOARD_SIZE = 3;
    private JPanel boardPanel;
    private JButton[][] buttons;
    private String currentPlayer = "X";

    public TicTacToe() {
        initializeUI();
    }

    private void initializeUI() {
        boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(BOARD_SIZE, BOARD_SIZE));

        buttons = new JButton[BOARD_SIZE][BOARD_SIZE];
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                JButton button = new JButton();
                button.setFont(new Font("Arial", Font.PLAIN, 60));
                button.addActionListener(e -> handleButtonClick((JButton) e.getSource()));
                buttons[row][col] = button;
                boardPanel.add(button);
            }
        }

        getContentPane().add(boardPanel);
        setTitle("Tic-Tac-Toe");
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void handleButtonClick(JButton clickedButton) {
        if (clickedButton.getText().isEmpty()) {
            clickedButton.setText(currentPlayer);
            clickedButton.setEnabled(false);

            if (checkForWin(currentPlayer)) {
                handleGameEnd("Player " + currentPlayer + " wins!");
            } else if (checkForDraw()) {
                handleGameEnd("The game is a draw!");
            } else {
                currentPlayer = currentPlayer.equals("X") ? "O" : "X";
            }
        }
    }

    private boolean checkForWin(String player) {
        for (int row = 0; row < BOARD_SIZE; row++) {
            if (buttons[row][0].getText().equals(player) &&
                buttons[row][1].getText().equals(player) &&
                buttons[row][2].getText().equals(player)) {
                return true;
            }
        }

        for (int col = 0; col < BOARD_SIZE; col++) {
            if (buttons[0][col].getText().equals(player) &&
                buttons[1][col].getText().equals(player) &&
                buttons[2][col].getText().equals(player)) {
                return true;
            }
        }

        if (buttons[0][0].getText().equals(player) &&
            buttons[1][1].getText().equals(player) &&
            buttons[2][2].getText().equals(player)) {
            return true;
        }

        if (buttons[0][2].getText().equals(player) &&
            buttons[1][1].getText().equals(player) &&
            buttons[2][0].getText().equals(player)) {
            return true;
        }

        return false;
    }

    private boolean checkForDraw() {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                if (buttons[row][col].getText().isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }

    private void handleGameEnd(String message) {
        int response = JOptionPane.showConfirmDialog(TicTacToe.this,
                message + " Do you want to play again?",
                "Game Over", JOptionPane.YES_NO_OPTION);
        if (response == JOptionPane.YES_OPTION) {
            resetGame();
        } else {
            System.exit(0);
        }
    }

    private void resetGame() {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                buttons[row][col].setText("");
                buttons[row][col].setEnabled(true);
            }
        }
        currentPlayer = "X";
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TicTacToe frame = new TicTacToe();
            frame.setVisible(true);
        });
    }
}
