import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CurrencyConverter extends JFrame {
    private JTextField OutputField;
    private JTextField InputField;
    private JComboBox<String> CType_comboBox;
    private JButton convertButton;
    private JButton clearButton;
    private JLabel InputL;
    private JLabel CurrencyTypeL;
    private JLabel AmountL;
    private JPanel ConverterPanel;

    // Exchange Rates
    private static final double USD_TO_JMD = 156.90;
    private static final double CAD_TO_JMD = 108.19;
    private static final double EUR_TO_JMD = 164.22;

    public CurrencyConverter() {
        setContentPane(ConverterPanel);

        setTitle("Currency Converter");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Ensure components are initialized before use
        initializeComponents();

        // Add Action Listeners
        convertButton.addActionListener(new ConvertAction());
        clearButton.addActionListener(new ClearAction());
    }

    private void initializeComponents() {
        // Ensure JComboBox has currency options
        CType_comboBox.setModel(new DefaultComboBoxModel<>(new String[]{"US Dollar (USD)", "Canadian Dollar (CAD)", "Euro (EUR)"}));

        // Ensure OutputField is read-only
        OutputField.setEditable(false);
    }

    // Convert Action
    private class ConvertAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String inputText = InputField.getText().trim();
                if (inputText.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter an amount.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                double amount = Double.parseDouble(inputText);
                double rate = switch (CType_comboBox.getSelectedIndex()) {
                    case 0 -> USD_TO_JMD;
                    case 1 -> CAD_TO_JMD;
                    case 2 -> EUR_TO_JMD;
                    default -> 0.0;
                };

                double convertedAmount = amount * rate;
                OutputField.setText(String.format("%.2f JMD", convertedAmount));

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter a numeric value.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Clear Action
    private class ClearAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            InputField.setText("");
            CType_comboBox.setSelectedIndex(0);
            OutputField.setText("");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CurrencyConverter converter = new CurrencyConverter();
            converter.setVisible(true);
        });
    }
}

