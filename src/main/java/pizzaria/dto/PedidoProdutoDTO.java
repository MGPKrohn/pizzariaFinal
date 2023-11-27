package pizzaria.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PedidoProdutoDTO {

    private Long id;
    private PedidoDTO pedido;
    private ProdutoDTO produto;
    private List<SaborDTO> sabores;

}