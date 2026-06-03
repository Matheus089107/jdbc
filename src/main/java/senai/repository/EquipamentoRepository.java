package senai.repository;

import senai.database.Conexao;
import senai.model.Equipamento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EquipamentoRepository {

    public Equipamento cadastrar(Equipamento equipamento) throws SQLException {
        String query = """
                INSERT INTO Equipamento (
                              nome,
                              numero_serie,
                              fornecedor_id
                              ) VALUES
                              (?,?,?);
                """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, equipamento.getNome());
            stmt.setString(2, equipamento.getNumeroSerie());
            stmt.setInt(3, equipamento.getFornecedorId());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();

            if (rs.next()) {
                equipamento.setId(rs.getInt(1));
            }

        }
        return equipamento;
    }

    public Equipamento buscarPorId(int id) throws SQLException {
        String query = """
                SELECT id, nome, numero_serie, fornecedor_id FROM Equipamento where id = ?;
                """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Equipamento(rs.getInt("id"), rs.getString("nome"), rs.getString("numero_serie"), rs.getInt("fornecedor_id"));
            }
        }
        return null;
    }

    public List<Equipamento> buscaPorFornecedorId(int fornecedorId) throws SQLException {
        String query = """
                SELECT e.id, e.nome, e.numero_serie, e.fornecedor_id FROM Equipamento e
                JOIN Fornecedor f
                ON f.id = e.fornecedor_id
                WHERE e.fornecedor_id = ?;
                """;

       List<Equipamento> listEquipamento = new ArrayList<>();

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, fornecedorId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                listEquipamento.add(
                new Equipamento(rs.getInt("id"), rs.getString("nome"), rs.getString("numero_serie"), rs.getInt("fornecedor_id")));
            }
        }
        return listEquipamento;
    }

    public Equipamento updEquipamento(Equipamento equipamento) throws SQLException{
        String query = """
                UPDATE Equipamento SET nome = ?, numero_serie = ? WHERE id = ?
                """;

        try(Connection conn = Conexao.conectar();
        PreparedStatement stmt = conn.prepareStatement(query)){

            stmt.setString(1, equipamento.getNome());
            stmt.setString(2, equipamento.getNumeroSerie());
            stmt.setInt(3, equipamento.getId());
            stmt.executeUpdate();
        }
        return equipamento;
    }

    public void dltEquipamento(int id) throws SQLException{
        String query = """
                DELETE FROM Equipamento WHERE id = ?;
                """;
        try(Connection conn = Conexao.conectar();
        PreparedStatement stmt = conn.prepareStatement(query)){

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

}
