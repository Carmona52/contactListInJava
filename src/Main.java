import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Main {
    private static Object[][] consContacts;

    public static void main(String[] args) {
            createAndShowGUI();
    }


    private static void createAndShowGUI() {
        JFrame mainFrame = new JFrame("Lista de Contactos");
        mainFrame.setSize(500, 500);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout());
        mainFrame.add(panel);

        LabelFactory labelFactory = new CreateLabelFactory();
        JLabel label = labelFactory.createLabel("Bienvenido a tu lista de contactos");
        panel.add(label, BorderLayout.NORTH);

        String[] columnNames = {"Nombre", "Apellido", "Telefono", "Actualizar"};
        DefaultTableModel tableModel = new DefaultTableModel(consContacts, columnNames);
        JTable table = new JTable(tableModel);
        table.setDefaultEditor(Object.class, null);
        table.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = getButtonPanel(tableModel);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        mainFrame.setVisible(true);
        updateContacts(tableModel);
    }

    public static void updateContacts(DefaultTableModel tableModel) {
        ButtonFactory buttonFactory = new CreateButtonFactory();
        List<Persona> personaList = read.executeRead();
        consContacts = new Object[personaList.size()][4];
        for (int i = 0; i < personaList.size(); i++) {
            Persona persona = personaList.get(i);
            consContacts[i][0] = persona.getNombre();
            consContacts[i][1] = persona.getApellido();
            consContacts[i][2] = persona.getTelefono();
            consContacts[i][3] = buttonFactory.createButton("Actualizar");
        }
        tableModel.setDataVector(consContacts, new Object[]{"Nombre", "Apellido", "Telefono", "Actualizar"});
    }

    private static JPanel getButtonPanel(DefaultTableModel tableModel) {
        ButtonFactory buttonFactory = new CreateButtonFactory();

        JPanel buttonPanel = new JPanel(new FlowLayout());

        JButton addButton = buttonFactory.createButton("Añadir Contacto");
        JButton updateButton = buttonFactory.createButton("Actualizar Contacto");
        JButton deleteButton = buttonFactory.createButton("Eliminar Contacto");

        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);

        addButton.addActionListener(e -> {
            JFrame mainFrame = (JFrame) SwingUtilities.getWindowAncestor(buttonPanel);
            mainFrame.setVisible(false);
            load.save(mainFrame, tableModel);
        });

        updateButton.addActionListener(e -> {
            JFrame mainFrame = (JFrame) SwingUtilities.getWindowAncestor(buttonPanel);
            mainFrame.setVisible(false);
            load.update(mainFrame, tableModel);
        });

        deleteButton.addActionListener(e -> {
            JFrame mainFrame = (JFrame) SwingUtilities.getWindowAncestor(buttonPanel);
            mainFrame.setVisible(false);
            delete.errase(mainFrame, tableModel);
        });

        return buttonPanel;
    }
}
