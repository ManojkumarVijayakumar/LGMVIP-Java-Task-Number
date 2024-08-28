import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.function.DoubleUnaryOperator;
import java.math.*;

public class ScientificCalculator extends JFrame {
    private static final long serialVersionUID = 1L;
    private JTextField display;

    public ScientificCalculator() {
        super("Scientific Calculator");
        initializeUI();
    }

    private void initializeUI() {
        display = new JTextField();
        display.setEditable(false);
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setFont(new Font("Arial", Font.PLAIN, 36));
        display.setBackground(Color.BLACK);
        display.setForeground(Color.WHITE);
        getContentPane().add(display, BorderLayout.NORTH);

        add(new CalculatorPanel(), BorderLayout.CENTER);

        setSize(600, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private class CalculatorPanel extends JPanel implements ActionListener {
        private static final long serialVersionUID = 1L;
        private final String[] buttonLabels = {
            "7", "8", "9", "/", "C",
            "4", "5", "6", "*", "sqrt",
            "1", "2", "3", "-", "1/x",
            "0", "+/-", ".", "+", "=",
            "sin", "cos", "tan", "log", "exp",
            "x^2", "x^3", "!", "^", "√x"
        };

        public CalculatorPanel() {
            setLayout(new GridLayout(7, 5));
            setBackground(Color.BLACK);
            for (String label : buttonLabels) {
                JButton button = new JButton(label);
                button.setFont(new Font("Arial", Font.PLAIN, 24));
                button.setBackground(Color.GRAY);
                button.setForeground(Color.WHITE);
                button.addActionListener(this);
                add(button);
            }
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            switch (command) {
                case "C":
                    display.setText("");
                    break;
                case "=":
                    calculateResult();
                    break;
                case "sqrt":
                    performUnaryOperation(Math::sqrt);
                    break;
                case "1/x":
                    performUnaryOperation(value -> 1.0 / value);
                    break;
                case "+/-":
                    performUnaryOperation(value -> -value);
                    break;
                case "sin":
                    performUnaryOperation(value -> Math.sin(Math.toRadians(value)));
                    break;
                case "cos":
                    performUnaryOperation(value -> Math.cos(Math.toRadians(value)));
                    break;
                case "tan":
                    performUnaryOperation(value -> Math.tan(Math.toRadians(value)));
                    break;
                case "log":
                    performUnaryOperation(Math::log10);
                    break;
                case "exp":
                    performUnaryOperation(Math::exp);
                    break;
                case "x^2":
                    performUnaryOperation(value -> value * value);
                    break;
                case "x^3":
                    performUnaryOperation(value -> value * value * value);
                    break;
                case "!":
                    performUnaryOperation(value -> factorial((int) value));
                    break;
                case "^":
                    handlePowerOperation();
                    break;
                case "√x":
                    performUnaryOperation(Math::sqrt);
                    break;
                default:
                    display.setText(display.getText() + command);
                    break;
            }
        }

        private void calculateResult() {
            try {
                String expression = display.getText();
                display.setText(String.valueOf(new ExpressionEvaluator().evaluate(expression)));
            } catch (IllegalArgumentException ex) {
                display.setText("Error");
            }
        }

        private void performUnaryOperation(DoubleUnaryOperator operation) {
            try {
                double value = Double.parseDouble(display.getText());
                display.setText(String.valueOf(operation.applyAsDouble(value)));
            } catch (NumberFormatException ex) {
                display.setText("Error");
            }
        }

        private void handlePowerOperation() {
            try {
                String[] parts = display.getText().split("\\^");
                if (parts.length == 2) {
                    double base = Double.parseDouble(parts[0]);
                    double exponent = Double.parseDouble(parts[1]);
                    display.setText(String.valueOf(Math.pow(base, exponent)));
                } else {
                    display.setText("Error");
                }
            } catch (NumberFormatException ex) {
                display.setText("Error");
            }
        }

        private double factorial(int n) {
            if (n < 0) return 0;
            double result = 1;
            for (int i = 1; i <= n; i++) {
                result *= i;
            }
            return result;
        }
    }

    private class ExpressionEvaluator {
        private String expression;
        private int index;

        public double evaluate(String expression) {
            this.expression = expression;
            index = 0;
            double result = parseExpression();
            if (index != expression.length()) {
                throw new IllegalArgumentException("Invalid expression");
            }
            return result;
        }

        private double parseExpression() {
            double result = parseTerm();
            while (index < expression.length()) {
                char operator = expression.charAt(index);
                if (operator != '+' && operator != '-') {
                    break;
                }
                index++;
                double operand = parseTerm();
                result = (operator == '+') ? result + operand : result - operand;
            }
            return result;
        }

        private double parseTerm() {
            double result = parseFactor();
            while (index < expression.length()) {
                char operator = expression.charAt(index);
                if (operator != '*' && operator != '/') {
                    break;
                }
                index++;
                double operand = parseFactor();
                result = (operator == '*') ? result * operand : result / operand;
            }
            return result;
        }

        private double parseFactor() {
            char ch = expression.charAt(index);
            if (Character.isDigit(ch)) {
                return parseNumber();
            } else if (ch == '(') {
                index++;
                double result = parseExpression();
                if (expression.charAt(index) != ')') {
                    throw new IllegalArgumentException("Mismatched parentheses");
                }
                index++;
                return result;
            } else if (ch == '-') {
                index++;
                return -parseFactor();
            } else if (ch == '+') {
                index++;
                return parseFactor();
            } else if (ch == '^') {
                index++;
                double base = parseFactor();
                double exponent = parseFactor();
                return Math.pow(base, exponent);
            } else {
                throw new IllegalArgumentException("Invalid character: " + ch);
            }
        }

        private double parseNumber() {
            int startIndex = index;
            while (index < expression.length() && Character.isDigit(expression.charAt(index))) {
                index++;
            }
            if (index < expression.length() && expression.charAt(index) == '.') {
                index++;
                while (index < expression.length() && Character.isDigit(expression.charAt(index))) {
                    index++;
                }
            }
            return Double.parseDouble(expression.substring(startIndex, index));
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ScientificCalculator().setVisible(true));
    }
}
