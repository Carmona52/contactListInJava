import java.sql.*;

public class conecction {
  private static final String URL = "jdbc:mysql://localhost:3309/contactos";
  private static final String USER = "carmona";
  private static final String PASSWORD = "nuevaContra";

  private static Connection instance;


  private conecction() {}


  public static Connection getConnection() throws SQLException {

    if (instance == null || instance.isClosed()) {
      try {
        instance = DriverManager.getConnection(URL, USER, PASSWORD);
      } catch (SQLException e) {
        System.err.println("Error al conectar con la base de datos: " + e.getMessage());
        throw e;
      }
    }
    return instance;
  }


  public static void closeConnection() {
    try {
      if (instance != null && !instance.isClosed()) {
        instance.close();
      }
    } catch (SQLException e) {
      System.err.println("Error al cerrar la conexión: " + e.getMessage());
    }
  }
}
