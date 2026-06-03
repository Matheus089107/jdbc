package senai.service.fornecedor;

import senai.model.Fornecedor;
import senai.repository.FornecedorRepository;

import java.sql.SQLException;
import java.util.List;

public class FornecedorServiceImpl implements FornecedorService{

    FornecedorRepository repository = new FornecedorRepository();

    @Override
    public Fornecedor criarFornecedor(Fornecedor fornecedor) throws SQLException {
        return repository.cadastrar(fornecedor);
    }

    @Override
    public Fornecedor buscarPorId(int id) throws SQLException {
        return null;
    }

    @Override
    public List<Fornecedor> buscarTodos() throws SQLException {
        return List.of();
    }

    @Override
    public void atualizarFornecedor(Fornecedor fornecedor) throws SQLException {

    }

    @Override
    public void deletarFornecedor(int id) throws SQLException {

    }
}
