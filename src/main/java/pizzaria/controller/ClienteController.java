package pizzaria.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pizzaria.dto.ClienteDTO;
import pizzaria.entity.Cliente;
import pizzaria.service.ClienteService;
import pizzaria.repository.ClienteRepository;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/cliente")
@CrossOrigin(origins = "http://localhost:4200")
public class ClienteController {
    @Autowired
    ClienteService clienteService;

    @Autowired
    ClienteRepository clienteRepository;

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> buscarporid(@PathVariable Long id) {
        Cliente cliente = clienteService.buscarporid(id);
        ClienteDTO clienteDTO = mapToDTO(cliente);
        return ResponseEntity.ok().body(clienteDTO);
    }

    @GetMapping
    public List<ClienteDTO> getAllClientes() {
        List<Cliente> clientes = clienteRepository.findAll();
        List<ClienteDTO> clienteDTOs = mapToDTOList(clientes);
        return clienteDTOs;
    }

    @PostMapping
    public ResponseEntity<ClienteDTO> cadastrar(@RequestBody ClienteDTO clienteDTO) {
        try {
            Cliente cliente = mapToEntity(clienteDTO);
            Cliente novoCliente = clienteService.criarcliente(cliente);
            ClienteDTO novoClienteDTO = mapToDTO(novoCliente);
            return ResponseEntity.ok(novoClienteDTO);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> atualizar(@PathVariable Long id, @RequestBody ClienteDTO clienteDTO) {
        try {
            Cliente cliente = mapToEntity(clienteDTO);
            Cliente clienteAtualizado = clienteService.atualizarcliente(id, cliente.getNome(), cliente.getTelefone(), cliente.getEndereco());
            ClienteDTO clienteAtualizadoDTO = mapToDTO(clienteAtualizado);
            return ResponseEntity.ok(clienteAtualizadoDTO);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletar(@PathVariable Long id) {
        try {
            clienteService.deletarcliente(id);
            return ResponseEntity.ok("Cliente deletado com sucesso");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao deletar cliente: " + e.getMessage());
        }
    }

    // Helper methods to map between Entity and DTO
    private ClienteDTO mapToDTO(Cliente cliente) {
        ClienteDTO clienteDTO = new ClienteDTO();
        BeanUtils.copyProperties(cliente, clienteDTO);
        return clienteDTO;
    }

    private List<ClienteDTO> mapToDTOList(List<Cliente> clientes) {
        return clientes.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private Cliente mapToEntity(ClienteDTO clienteDTO) {
        Cliente cliente = new Cliente();
        BeanUtils.copyProperties(clienteDTO, cliente);
        return cliente;
    }
}