import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.util.HashMap;
import java.util.Map;

public class CurrencyConverter extends javax.swing.JFrame {
    private final Map<String, Double> currencyRates = new HashMap<>();
    
    private final String[] currencyUnits = {
        "Units",
        "US Dollar",
        "Nigerian Naira",
        "Brazilian Real",
        "Canadian Dollar",
        "Kenyan Shilling",
        "Indonesian Rupiah",
        "Indian Rupee",
        "Philippine Peso",
        "Pakistani Rupee"
    };

    public CurrencyConverter() {
        initComponents();
        initializeCurrencyRates();
        setUpComboBoxRenderers();
    }

    private void initializeCurrencyRates() {
        currencyRates.put("US Dollar", 1.00);
        currencyRates.put("Nigerian Naira", 460.00);
        currencyRates.put("Brazilian Real", 5.20);
        currencyRates.put("Canadian Dollar", 1.36);
        currencyRates.put("Kenyan Shilling", 132.00);
        currencyRates.put("Indonesian Rupiah", 15350.00);
        currencyRates.put("Indian Rupee", 82.00);
        currencyRates.put("Philippine Peso", 55.00);
        currencyRates.put("Pakistani Rupee", 280.00);
    }

    private void setUpComboBoxRenderers() {
        firstCountry.setRenderer(new FlagListCellRenderer());
        secondCountry.setRenderer(new FlagListCellRenderer());
    }

    public class FlagListCellRenderer extends DefaultListCellRenderer {
        private final Map<String, Icon> flagIcons;

        public FlagListCellRenderer() {
            flagIcons = new HashMap<>();
            flagIcons.put("US Dollar", new ImageIcon(new ImageIcon(getClass().getResource("/flags/us.png")).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
            flagIcons.put("Nigerian Naira", new ImageIcon(new ImageIcon(getClass().getResource("/flags/ng.jpeg")).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
            flagIcons.put("Brazilian Real", new ImageIcon(new ImageIcon(getClass().getResource("/flags/br.png")).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
            flagIcons.put("Canadian Dollar", new ImageIcon(new ImageIcon(getClass().getResource("/flags/ca.png")).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
            flagIcons.put("Kenyan Shilling", new ImageIcon(new ImageIcon(getClass().getResource("/flags/ke.png")).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
            flagIcons.put("Indonesian Rupiah", new ImageIcon(new ImageIcon(getClass().getResource("/flags/id.png")).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
            flagIcons.put("Indian Rupee", new ImageIcon(new ImageIcon(getClass().getResource("/flags/in.png")).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
            flagIcons.put("Philippine Peso", new ImageIcon(new ImageIcon(getClass().getResource("/flags/ph.png")).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
            flagIcons.put("Pakistani Rupee", new ImageIcon(new ImageIcon(getClass().getResource("/flags/pk.png")).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
            setHorizontalAlignment(SwingConstants.LEFT);
        }

        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            Component component = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            if (value instanceof String) {
                String currency = (String) value;
                setText(currency);
                setIcon(flagIcons.getOrDefault(currency, null));
            }
            return component;
        }
    }

    private void initComponents() {
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        firstCountry = new javax.swing.JComboBox<>();
        secondCountry = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        t1 = new javax.swing.JTextField();
        t2 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        secondCurrencyUnit = new javax.swing.JLabel();
        firstCurrencyUnit = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 5));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); 
        jLabel1.setText("Currency Converter");

        firstCountry.setFont(new java.awt.Font("Tahoma", 1, 18)); 
        firstCountry.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {
            "Choose One...",
            "US Dollar",
            "Nigerian Naira",
            "Brazilian Real",
            "Canadian Dollar",
            "Kenyan Shilling",
            "Indonesian Rupiah",
            "Indian Rupee",
            "Philippine Peso",
            "Pakistani Rupee"
        }));
        firstCountry.addItemListener(this::firstCountryItemStateChanged);

        secondCountry.setFont(new java.awt.Font("Tahoma", 1, 18)); 
        secondCountry.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {
            "Choose One...",
            "US Dollar",
            "Nigerian Naira",
            "Brazilian Real",
            "Canadian Dollar",
            "Kenyan Shilling",
            "Indonesian Rupiah",
            "Indian Rupee",
            "Philippine Peso",
            "Pakistani Rupee"
        }));
        secondCountry.addItemListener(this::secondCountryItemStateChanged);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); 
        jLabel2.setText("From currency Of");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); 
        jLabel3.setText("To currency Of");

        t1.setFont(new java.awt.Font("Tahoma", 1, 18)); 

        t2.setFont(new java.awt.Font("Tahoma", 1, 18)); 

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 18)); 
        jButton1.setText("Convert");
        jButton1.addActionListener(this::jButton1ActionPerformed);

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 18)); 
        jButton2.setText("Reset");
        jButton2.addActionListener(this::jButton2ActionPerformed);

        jButton3.setFont(new java.awt.Font("Tahoma", 1, 18)); 
        jButton3.setText("Exit");
        jButton3.addActionListener(this::jButton3ActionPerformed);

        secondCurrencyUnit.setFont(new java.awt.Font("Tahoma", 1, 14)); 
        secondCurrencyUnit.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        secondCurrencyUnit.setText("units");

        firstCurrencyUnit.setFont(new java.awt.Font("Tahoma", 1, 14)); 
        firstCurrencyUnit.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        firstCurrencyUnit.setText("units");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(290, 290, 290))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(93, 93, 93)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(firstCountry, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(t1)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(firstCurrencyUnit, javax.swing.GroupLayout.DEFAULT_SIZE, 277, Short.MAX_VALUE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(81, 81, 81)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel3)
                                .addComponent(secondCountry, 0, 272, Short.MAX_VALUE)
                                .addComponent(t2))
                            .addComponent(secondCurrencyUnit, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(89, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(38, 38, 38))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(firstCountry, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(secondCountry, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(t1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(t2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(firstCurrencyUnit)
                    .addComponent(secondCurrencyUnit))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton3)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        pack();
    }

    private void firstCountryItemStateChanged(ItemEvent evt) {
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            updateCurrencyUnits();
        }
    }

    private void secondCountryItemStateChanged(ItemEvent evt) {
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            updateCurrencyUnits();
        }
    }

    private void updateCurrencyUnits() {
        String firstCurrency = (String) firstCountry.getSelectedItem();
        String secondCurrency = (String) secondCountry.getSelectedItem();

        if (firstCurrency != null && secondCurrency != null) {
            firstCurrencyUnit.setText(firstCurrency);
            secondCurrencyUnit.setText(secondCurrency);
            convertCurrency();
        }
    }

    private void convertCurrency() {
        try {
            String fromCurrency = (String) firstCountry.getSelectedItem();
            String toCurrency = (String) secondCountry.getSelectedItem();

            if (fromCurrency != null && toCurrency != null && !fromCurrency.equals("Choose One...") && !toCurrency.equals("Choose One...")) {
                double fromAmount = Double.parseDouble(t1.getText());
                double rateFrom = currencyRates.get(fromCurrency);
                double rateTo = currencyRates.get(toCurrency);

                double result = fromAmount * (rateTo / rateFrom);
                t2.setText(String.format("%.2f", result));
            } else {
                t2.setText("");
            }
        } catch (NumberFormatException e) {
            t2.setText("Invalid number");
        }
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        convertCurrency();
    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
        t1.setText("");
        t2.setText("");
        firstCountry.setSelectedIndex(0);
        secondCountry.setSelectedIndex(0);
    }

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {
        System.exit(0);
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new CurrencyConverter().setVisible(true));
    }

    // Variables declaration
    private javax.swing.JComboBox<String> firstCountry;
    private javax.swing.JComboBox<String> secondCountry;
    private javax.swing.JLabel firstCurrencyUnit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JTextField t1;
    private javax.swing.JTextField t2;
    private javax.swing.JLabel secondCurrencyUnit;
    // End of variables declaration
}
