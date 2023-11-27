package pizzaria.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteDTO {

    private long id;
    private String nome;
    private String endereco;
    private String telefone;
}
