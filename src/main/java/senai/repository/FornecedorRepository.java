package senai.repository;

import senai.database.Conexao;
import senai.model.Fornecedor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            stmt.executeQuery();
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Fornecedor(rs.getInt("id"), rs.getString("nome"), rs.getString("cnpj"));
            }
        }

        return null;

    }

    public List<Fornecedor> listarTodos() throws SQLException{
        String query = """
                SELECT id, nome, cnpj FROM Fornecedor
                """;

        List<Fornecedor> listFornecedor = new ArrayList<>();

        try(Connection conn = Conexao.conectar();
        PreparedStatement stmt = conn.prepareStatement(query)){

            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                 listFornecedor.add(
                        new Fornecedor(rs.getInt("id"), rs.getString("nome"), rs.getString("cnpj")));
            }
             return listFornecedor;
        }
    }

    public void updateFornecedor(Fornecedor fornecedor) throws SQLException{
        String query = """
                UPDATE Fornecedor SET nome = ?, cnpj = ? where id = ?
                """;

        try(Connection conn = Conexao.conectar();
        PreparedStatement stmt = conn.prepareStatement(query)){

            stmt.setString(1, fornecedor.getNome());
            stmt.setString(2, fornecedor.getCnpj());
            stmt.setInt(3, fornecedor.getId());
            stmt.executeUpdate();

        }
    }

    public void dltFornecedor (int id) throws SQLException{
        String query = """
                DELETE FROM Fornecedor WHERE id = ?;
                """;
        try(Connection conn = Conexao.conectar();
        PreparedStatement stmt = conn.prepareStatement(query)){

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
