package pizzaria.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoDTO {

    private Long id;
    private String nome;
    private boolean temSabores;
    private int maximoSabores;
    private double valor;

}