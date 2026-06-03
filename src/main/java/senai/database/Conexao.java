package senai.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    // Adicionando o modo de compatibilidade com MySQL
    private static final String URL = "jdbc:h2:mem:provami77;DB_CLOSE_DELAY=-1;MODE=MySQL";
    private static final String USER = "sa";
    private static final String PW = "";

    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL, USER, PW);
    }
}
