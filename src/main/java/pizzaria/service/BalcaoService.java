package pizzaria.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pizzaria.dto.*;
import pizzaria.entity.*;
import pizzaria.repository.*;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BalcaoService {

    @Autowired
    private BalcaoRepository balcaoRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    public List<BalcaoDTO> getAllBalcoes() {
        List<Balcao> balcoes = balcaoRepository.findAll();
        return mapToDTOList(balcoes);
    }

    public BalcaoDTO getBalcaoById(Long id) {
        Balcao balcao = balcaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Balcao not found"));
        return mapToDTO(balcao);
    }

    public BalcaoDTO saveBalcao(BalcaoDTO balcaoDTO) {
        Balcao balcao = mapToEntity(balcaoDTO);
        Balcao savedBalcao = balcaoRepository.save(balcao);
        return mapToDTO(savedBalcao);
    }

    public BalcaoDTO updateBalcao(Long id, BalcaoDTO balcaoDTO) {
        Balcao balcaoToUpdate = balcaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Balcao not found"));

        Balcao updatedBalcao = mapToEntity(balcaoDTO);
        BeanUtils.copyProperties(updatedBalcao, balcaoToUpdate, "id");

        Balcao savedBalcao = balcaoRepository.save(balcaoToUpdate);
        return mapToDTO(savedBalcao);
    }

    public void deleteBalcao(Long id) {
        balcaoRepository.deleteById(id);
    }

    // Helper methods to map between Entity and DTO
    private BalcaoDTO mapToDTO(Balcao balcao) {
        BalcaoDTO balcaoDTO = new BalcaoDTO();
        balcaoDTO.setId(balcao.getId());
        balcaoDTO.setFuncionarioDTO(mapToDTO(balcao.getFuncionario()));
        balcaoDTO.setClienteDTO(mapToDTO(balcao.getCliente()));
        balcaoDTO.setProdutoDTO(mapToDTO(balcao.getProduto()));
        balcaoDTO.setPedidoDTO(mapToDTO(balcao.getPedido()));
        return balcaoDTO;
    }

    private List<BalcaoDTO> mapToDTOList(List<Balcao> balcoes) {
        return balcoes.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private Balcao mapToEntity(BalcaoDTO balcaoDTO) {
        Balcao balcao = new Balcao();
        balcao.setId(balcaoDTO.getId());
        balcao.setFuncionario(mapToEntity(balcaoDTO.getFuncionarioDTO()));
        balcao.setCliente(mapToEntity(balcaoDTO.getClienteDTO()));
        balcao.setProduto(mapToEntity(balcaoDTO.getProdutoDTO()));
        balcao.setPedido(mapToEntity(balcaoDTO.getPedidoDTO()));
        return balcao;
    }

    private FuncionarioDTO mapToDTO(Funcionario funcionario) {
        FuncionarioDTO funcionarioDTO = new FuncionarioDTO();
        funcionarioDTO.setId(funcionario.getId());
        funcionarioDTO.setNome(funcionario.getNome());
        return funcionarioDTO;
    }

    private ClienteDTO mapToDTO(Cliente cliente) {
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setId(cliente.getId());
        clienteDTO.setNome(cliente.getNome());
        clienteDTO.setTelefone(cliente.getTelefone());
        clienteDTO.setEndereco(cliente.getEndereco());
        return clienteDTO;
    }

    private ProdutoDTO mapToDTO(Produto produto) {
        ProdutoDTO produtoDTO = new ProdutoDTO();
        produtoDTO.setId(produto.getId());
        produtoDTO.setNome(produto.getNome());
        produtoDTO.setValor(produto.getValor());
        produtoDTO.setMaximoSabores(produto.getMaximoSabores());
        return produtoDTO;
    }

    private PedidoDTO mapToDTO(Pedido pedido) {
        PedidoDTO pedidoDTO = new PedidoDTO();
        pedidoDTO.setId(pedido.getId());
        pedidoDTO.setObs(pedido.getObs());
        return pedidoDTO;
    }

    private Funcionario mapToEntity(FuncionarioDTO funcionarioDTO) {
        Funcionario funcionario = new Funcionario();
        funcionario.setId(funcionarioDTO.getId());
        funcionario.setNome(funcionarioDTO.getNome());
        return funcionario;
    }

    private Cliente mapToEntity(ClienteDTO clienteDTO) {
        Cliente cliente = new Cliente();
        cliente.setId(clienteDTO.getId());
        cliente.setNome(clienteDTO.getNome());
        cliente.setEndereco(clienteDTO.getEndereco());
        cliente.setTelefone((clienteDTO.getTelefone()));
        return cliente;
    }

    private Produto mapToEntity(ProdutoDTO produtoDTO) {
        Produto produto = new Produto();
        produto.setId(produtoDTO.getId());
        produto.setNome(produtoDTO.getNome());
        produto.setValor(produtoDTO.getValor());
        produto.setMaximoSabores(produtoDTO.getMaximoSabores());
        return produto;
    }

    private Pedido mapToEntity(PedidoDTO pedidoDTO) {
        Pedido pedido = new Pedido();
        pedido.setId(pedidoDTO.getId());
        pedido.setObs(pedidoDTO.getObs());
        return pedido;
    }
}
