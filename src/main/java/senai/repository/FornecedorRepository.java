package senai.repository;

import senai.database.Conexao;
import senai.model.Fornecedor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FornecedorRepository {

    public Fornecedor cadastrar(Fornecedor fornecedor) throws SQLException {
        String query = """
                INSERT INTO Fornecedor (
                    nome,
                    cnpj
                )
                VALUES(?,?);
                """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, fornecedor.getNome());
            stmt.setString(2, fornecedor.getCnpj());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();

            if (rs.next()) {
                fornecedor.setId(rs.getInt(1));
            }

            return fornecedor;
        }

    }

    public Fornecedor buscarPorId(int id) throws SQLException {

        String query = """
                SELECT id, nome, cnpj FROM Fornecedor WHERE id = ?;
                """;

            try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){

                stmt.setInt(1, id);
                stmt.executeQuery();
                ResultSet rs = stmt.executeQuery();


            }

        return null;

    }

}
