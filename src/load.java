import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class load {
    private static JFrame saveFrame;

    public static void save(JFrame mainFrame, DefaultTableModel tableModel) {
        String query = "INSERT INTO users (name, lastName, phone) VALUES (?, ?, ?)";

        saveFrame = new JFrame("Añadir Contacto");
        JPanel panel = new JPanel();
        saveFrame.setSize(500, 500);
        saveFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel.setLayout(new GridLayout(4, 2, 5, 5));

        TextAreaFactory textAreaFactory = new CreateTextAreaFactory();
        ButtonFactory buttonFactory = new CreateButtonFactory();
        LabelFactory labelFactory = new CreateLabelFactory();

        JLabel nameLabel = labelFactory.createLabel("Nombre:");
        JTextArea nameTextArea = textAreaFactory.createTextArea(1, 20);

        JLabel lastNameLabel = labelFactory.createLabel("Apellido:");
        JTextArea lastNameArea = textAreaFactory.createTextArea(1, 20);

        JLabel phoneLabel = labelFactory.createLabel("Teléfono:");
        JTextArea phoneArea = textAreaFactory.createTextArea(1, 20);

        JButton saveButton = buttonFactory.createButton("Guardar");

        panel.add(nameLabel);
        panel.add(nameTextArea);
        panel.add(lastNameLabel);
        panel.add(lastNameArea);
        panel.add(phoneLabel);
        panel.add(phoneArea);
        panel.add(new JLabel());
        panel.add(saveButton);

        saveFrame.add(panel);
        saveFrame.setVisible(true);

        saveButton.addActionListener(e -> {
            try (Connection connection = conecction.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                String name = nameTextArea.getText();
                String lastName = lastNameArea.getText();
                int phone = Integer.parseInt(phoneArea.getText());

                preparedStatement.setString(1, name);
                preparedStatement.setString(2, lastName);
                preparedStatement.setInt(3, phone);

                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(saveFrame, "Contacto guardado con éxito");
                } else {
                    JOptionPane.showMessageDialog(saveFrame, "Error al guardar contacto");
                }

                saveFrame.dispose();
                mainFrame.setVisible(true);
                Main.updateContacts(tableModel);

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(saveFrame, "Error al guardar: " + ex.getMessage());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(saveFrame, "Por favor ingrese un número de teléfono válido.");
            }
        });
    }

    public static void update(JFrame mainFrame, DefaultTableModel tableModel) {
        String query = "UPDATE users SET name=? WHERE id=?";

        saveFrame = new JFrame("Actualizar nombre Contacto");
        JPanel panel = new JPanel();
        saveFrame.setSize(500, 500);
        saveFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel.setLayout(new GridLayout(4, 2, 5, 5));

        TextAreaFactory textAreaFactory = new CreateTextAreaFactory();
        ButtonFactory buttonFactory = new CreateButtonFactory();
        LabelFactory labelFactory = new CreateLabelFactory();

        JLabel idLabel = labelFactory.createLabel("Ingrese Id del contacto:");
        JTextArea idContactArea = textAreaFactory.createTextArea(1, 20);

        JLabel newNameLabel = labelFactory.createLabel("Ingrese el nuevo nombre:");
        JTextArea newNameArea = textAreaFactory.createTextArea(1, 20);

        JButton updateButton = buttonFactory.createButton("Actualizar");

        panel.add(idLabel);
        panel.add(idContactArea);
        panel.add(newNameLabel);
        panel.add(newNameArea);
        panel.add(new JLabel());
        panel.add(updateButton);

        saveFrame.add(panel);
        saveFrame.setVisible(true);

        updateButton.addActionListener(e -> {
            try (Connection connection = conecction.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                String newName = newNameArea.getText();
                int contactId = Integer.parseInt(idContactArea.getText());

                preparedStatement.setString(1, newName);
                preparedStatement.setInt(2, contactId);

                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(saveFrame, "Contacto actualizado con éxito");
                } else {
                    JOptionPane.showMessageDialog(saveFrame, "Error al actualizar contacto");
                }

                saveFrame.dispose();
                mainFrame.setVisible(true);
                Main.updateContacts(tableModel);

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(saveFrame, "Error al actualizar: " + ex.getMessage());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(saveFrame, "Por favor ingrese un ID válido y un nombre.");
            }
        });
    }
}
