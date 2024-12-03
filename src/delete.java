import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class delete {
    private static JFrame deleteFrame;

    public static void errase(JFrame mainFrame) {
        String query = "DELETE FROM users WHERE id = ?";

        deleteFrame = new JFrame("Eliminar Contacto");
        JPanel panel = new JPanel();
        deleteFrame.setSize(500, 500);
        deleteFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel.setLayout(new GridLayout(3, 2, 1, 1));

        TextAreaFactory textAreaFactory = new CreateTextAreaFactory();
        ButtonFactory buttonFactory = new CreateButtonFactory();
        LabelFactory labelFactory = new CreateLabelFactory();

        JLabel idLabel = labelFactory.createLabel("Inserte el ID a eliminar:");
        JTextArea idTextArea = textAreaFactory.createTextArea(1, 20);

        JButton deleteButton = buttonFactory.createButton("Eliminar");

        panel.add(idLabel);
        panel.add(idTextArea);
        panel.add(new JLabel());
        panel.add(deleteButton);

        deleteFrame.add(panel);
        deleteFrame.setVisible(true);

        deleteButton.addActionListener(e -> {
            try (Connection connection = conecction.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                int deleteId = Integer.parseInt(idTextArea.getText());

                preparedStatement.setInt(1, deleteId);

                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(deleteFrame, "Contacto eliminado con éxito");
                } else {
                    JOptionPane.showMessageDialog(deleteFrame, "Error al eliminar contacto");
                }

                deleteFrame.dispose();
                mainFrame.setVisible(true);
                Main.updateContacts();

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(deleteFrame, "Error al eliminar: " + ex.getMessage());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(deleteFrame, "Por favor ingrese un ID válido.");
            }
        });
    }
}
