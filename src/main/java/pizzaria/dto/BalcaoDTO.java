package pizzaria.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BalcaoDTO {

    private Long id;

    private FuncionarioDTO funcionarioDTO;

    private ClienteDTO clienteDTO;

    private ProdutoDTO produtoDTO;

    private PedidoDTO pedidoDTO;


    public BalcaoDTO() {
    }

    public BalcaoDTO(Long id, FuncionarioDTO funcionarioDTO, ClienteDTO clienteDTO, ProdutoDTO produtoDTO, PedidoDTO pedidoDTO) {
        this.id = id;
        this.funcionarioDTO = funcionarioDTO;
        this.clienteDTO = clienteDTO;
        this.produtoDTO = produtoDTO;
        this.pedidoDTO = pedidoDTO;
    }

    public static BalcaoDTO createFromComponents(FuncionarioDTO funcionarioDTO, ClienteDTO clienteDTO,
                                                 ProdutoDTO produtoDTO, PedidoDTO pedidoDTO) {
        return new BalcaoDTO(null, funcionarioDTO, clienteDTO, produtoDTO, pedidoDTO);
    }
}
