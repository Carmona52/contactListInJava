import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Main {
    private static JFrame mainFrame;
    private static Object[][] consContacts;

    public static void main(String[] args) {
        LabelFactory labelFactory = new CreateLabelFactory();

        mainFrame = new JFrame("Lista de Contactos");
        JPanel panel = new JPanel();
        mainFrame.setSize(500, 500);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel.setLayout(new BorderLayout());

        JLabel label = labelFactory.createLabel("Bienvenido a tu lista de contactos");
        panel.add(label, BorderLayout.NORTH);

        String[] columnNames = {"Nombre", "Apellido", "Telefono"};
        DefaultTableModel tableModel = new DefaultTableModel(consContacts,columnNames);
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        JPanel buttonPanel = getButtonPanel(tableModel);

        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        mainFrame.add(panel);
        updateContacts(tableModel);
        mainFrame.setVisible(true);
    }

    public static void updateContacts(DefaultTableModel tableModel) {
        List<Persona> personaList =read.executeRead();
       consContacts = new Object[personaList.size()][4];
       for (int i = 0; i < personaList.size(); i++) {
           Persona persona = personaList.get(i);
           consContacts[i][0] = persona.getId();
           consContacts[i][1] = persona.getNombre();
           consContacts[i][2] = persona.getApellido();
           consContacts[i][3] = persona.getTelefono();

           tableModel.setDataVector(consContacts, new Object[]{"NOMBRE", "APELLIDO","Telefono"});
       }
    }

    private static JPanel getButtonPanel(DefaultTableModel tableModel) {
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
            load.save(mainFrame, tableModel);
        });

        updateButton.addActionListener(e -> {
            mainFrame.setVisible(false);
            load.update(mainFrame, tableModel);
        });

        deleteButton.addActionListener(e -> {
            mainFrame.setVisible(false);
            delete.errase(mainFrame, tableModel);
        });

        return buttonPanel;
    }
}
