import java.awt.*;
import javax.swing.*;

public class Main {
    private static JFrame mainFrame;
    private static JTextArea consContact;

    public static void main(String[] args) {
        mainFrame = new JFrame("Lista de Contactos");
        JPanel panel = new JPanel();
        mainFrame.setSize(500, 500);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel.setLayout(new BorderLayout());

        TextAreaFactory textAreaFactory = new CreateTextAreaFactory();
        LabelFactory labelFactory = new CreateLabelFactory();

        JLabel label = labelFactory.createLabel("Bienvenido a tu lista de contactos");
        panel.add(label, BorderLayout.NORTH);

        consContact = new JTextArea();
        consContact.setEditable(false);
        JScrollPane scroll = new JScrollPane(consContact);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        panel.add(scroll, BorderLayout.CENTER);

        JPanel buttonPanel = getButtonPanel();

        panel.add(buttonPanel, BorderLayout.SOUTH);

        mainFrame.add(panel);
        updateContacts();
        mainFrame.setVisible(true);
    }

    public static void updateContacts() {
        String contactos = read.executeRead();
        consContact.setText(contactos);
    }

    private static JPanel getButtonPanel() {
        ButtonFactory buttonFactory = new CreateButtonFactory();

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        JButton addButton = buttonFactory.createButton("Añadir Contacto");
        JButton updateButton = buttonFactory.createButton("Actualizar Contacto");
        JButton deleteButton = buttonFactory.createButton("Eliminar Contacto");

        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);

        addButton.addActionListener(e -> {
            mainFrame.setVisible(false);
            load.save(mainFrame);
        });

        updateButton.addActionListener(e -> {
            mainFrame.setVisible(false);
            load.update(mainFrame);
        });

        deleteButton.addActionListener(e -> {
            mainFrame.setVisible(false);
            delete.errase(mainFrame);
        });

        return buttonPanel;
    }
}
