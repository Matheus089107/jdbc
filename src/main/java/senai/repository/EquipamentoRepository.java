package senai.repository;

import senai.database.Conexao;
import senai.model.Equipamento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EquipamentoRepository {

    public Equipamento cadastrar(Equipamento equipamento) throws SQLException{
      String query = """
              INSERT INTO Equipamento (
                            nome,
                            numero_serie,
                            fornecedor_id
                            ) VALUES
                            (?,?,?);
              """;

      try(Connection conn = Conexao.conectar();
          PreparedStatement stmt = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)){

          stmt.setString(1, equipamento.getNome());
          stmt.setString(2, equipamento.getNumeroSerie());
          stmt.setInt(3, equipamento.getFornecedorId());
          stmt.executeUpdate();

          ResultSet rs = stmt.getGeneratedKeys();

          if(rs.next()){
              equipamento.setId(rs.getInt(1));
          }

      }
      return equipamento;
    }

    public Equipamento buscarPorId(int id) throws SQLException{
        String query = """
                
                """;
        return null;
    }

}
