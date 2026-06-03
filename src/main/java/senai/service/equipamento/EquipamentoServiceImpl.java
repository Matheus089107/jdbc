package senai.service.equipamento;

import senai.model.Equipamento;
import senai.model.Fornecedor;
import senai.repository.EquipamentoRepository;
import senai.repository.FornecedorRepository;

import java.sql.SQLException;
import java.util.List;

public class EquipamentoServiceImpl implements EquipamentoService{

    EquipamentoRepository repository = new EquipamentoRepository();
    FornecedorRepository fornecedorRepository=new FornecedorRepository();


    @Override
    public Equipamento criarEquipamento(Equipamento equipamento) throws SQLException {
        Fornecedor fornecedor = fornecedorRepository.buscarPorId(equipamento.getFornecedorId());
        if(fornecedor == null){
            throw new RuntimeException("Fornecedor inválido ou inexistente!");
        }
        return repository.cadastrar(equipamento);
    }

    @Override
    public Equipamento buscarPorId(int id) throws SQLException {
        Equipamento equipamento = repository.buscarPorId(id);

        if(equipamento == null){
            throw new RuntimeException("Id do Equipamento não encontrado!");
        }
        return equipamento;
    }

    @Override
    public List<Equipamento> buscarPorFornecedorId(int fornecedorId) throws SQLException {

        return repository.buscaPorFornecedorId(fornecedorId);
    }

    @Override
    public void atualizarEquipamento(Equipamento equipamento) throws SQLException {

        Fornecedor fornecedor = fornecedorRepository.buscarPorId(equipamento.getFornecedorId());
        if(fornecedor == null){
            throw new RuntimeException("Equipamento não encontrado para atualização!");
        }
        repository.updEquipamento(equipamento);
    }

    @Override
    public void deletarEquipamento(int id) throws SQLException {
            Equipamento equipamento = repository.buscarPorId(id);
            if(equipamento == null){
                throw new RuntimeException("Equipamento não encontrado para exclusão!");
            }
            repository.dltEquipamento(id);
    }
}
