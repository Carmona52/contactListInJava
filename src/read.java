import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class read {
    public static List<Persona> executeRead() {
        String query = "SELECT * FROM users";
        List<Persona> resultList = new ArrayList<>();

        try (Connection connection = conecction.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nombre = resultSet.getString("name");
                String apellido = resultSet.getString("lastName");
                String telefono = resultSet.getString("phone");

                Persona persona  = new Persona(id, nombre, apellido, telefono);
                resultList.add(persona);
            }

        } catch (SQLException e) {
            System.err.println("Error al leer los datos de la base de datos: " + e.getMessage());
        }
        return resultList;
    }
}
