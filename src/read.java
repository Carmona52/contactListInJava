import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class read {
    public static String executeRead() {
        String query = "SELECT * FROM users";
        StringBuilder result = new StringBuilder();

        try (Connection connection = conecction.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

           result.append(String.format("%-5s %-20s %-20s %-15s%n", "ID", "Nombre", "Apellido", "Teléfono"));
            result.append("-----------------------------------------------------------\n");

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nombre = resultSet.getString("name");
                String apellido = resultSet.getString("lastName");
                String telefono = resultSet.getString("phone");

                result.append(String.format("%-5d %-20s %-20s %-15s%n", id, nombre, apellido,telefono));
            }

        } catch (SQLException e) {
            System.err.println("Error al leer los datos de la base de datos: " + e.getMessage());
        }
        return result.toString();
    }
}
