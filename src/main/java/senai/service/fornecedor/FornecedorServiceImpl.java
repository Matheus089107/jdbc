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
        Fornecedor fornecedor = repository.buscarPorId(id);
        if(fornecedor == null){
            throw new RuntimeException("Id do Fornecedor não encontrado!");
        }
        return fornecedor;
    }

    @Override
    public List<Fornecedor> buscarTodos() throws SQLException {
        return repository.listarTodos();
    }

    @Override
    public void atualizarFornecedor(Fornecedor fornecedor) throws SQLException {
       Fornecedor fornecedor2 = repository.buscarPorId(fornecedor.getId());

        if(fornecedor2 == null){
            throw new RuntimeException("Id do fornecedor não encontrado!");
        }
        repository.updateFornecedor(fornecedor);
    }

    @Override
    public void deletarFornecedor(int id) throws SQLException {
        Fornecedor fornecedor = repository.buscarPorId(id);
        if(fornecedor == null){
            throw new RuntimeException("Id do Fornecedor não encontrado!");
        }
        repository.dltFornecedor(id);
    }
}
