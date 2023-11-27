package pizzaria.service;

import pizzaria.entity.Cliente;
import pizzaria.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {
    private final ClienteRepository clienteRepository;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente buscarporid(Long id){return clienteRepository.findById(id).orElse(null);}

    public Cliente criarcliente(Cliente cliente){return clienteRepository.save(cliente);}

    public List<Cliente> listarTodosClientes(){ return clienteRepository.findAll(); }

    public Cliente atualizarcliente(Long clienteId, String novoNome, String novoTelefone, String novoEndereco) {

        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));


        if (novoNome != null) {
            cliente.setNome(novoNome);
        }
        if (novoTelefone != null) {
            cliente.setTelefone(novoTelefone);
        }
        if (novoEndereco != null) {
            cliente.setEndereco(novoEndereco);
        }


        return clienteRepository.save(cliente);
    }

    public void deletarcliente(Long clienteId) {
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        clienteRepository.delete(cliente);
    }
}