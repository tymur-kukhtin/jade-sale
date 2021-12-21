package com.jade.book;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

class BookSellerGui extends JFrame {

    private final BookSellerAgent myAgent;
    private final JTextField titleField;
    private final JTextField priceField;

    BookSellerGui(BookSellerAgent bookSellerAgent) {
        super(bookSellerAgent.getLocalName());

        myAgent = bookSellerAgent;

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 2));
        panel.add(new JLabel("Book title:"));
        titleField = new JTextField(15);
        panel.add(titleField);
        panel.add(new JLabel("Price:"));
        priceField = new JTextField(15);
        panel.add(priceField);
        getContentPane().add(panel, BorderLayout.CENTER);

        JButton addButton = new JButton("Add");
        addButton.addActionListener(ev -> {
            try {
                String title = titleField.getText().trim();
                String price = priceField.getText().trim();
                myAgent.updateCatalogue(title, Integer.parseInt(price));
                titleField.setText("");
                priceField.setText("");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Invalid values. " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        panel = new JPanel();
        panel.add(addButton);
        getContentPane().add(panel, BorderLayout.SOUTH);

        // Make the agent terminate when the user closes
        // the GUI using the button on the upper right corner
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                myAgent.doDelete();
            }
        });

        setResizable(false);
    }

    public void show() {
        pack();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int centerX = (int) screenSize.getWidth() / 2;
        int centerY = (int) screenSize.getHeight() / 2;
        setLocation(centerX - getWidth() / 2, centerY - getHeight() / 2);
        super.show();
    }
}
