package pizzaria.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class PedidoProduto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;

    @ManyToMany
    @JoinTable(name = "pedido_produto_sabor",
            joinColumns = @JoinColumn(name = "pedido_produto_id"),
            inverseJoinColumns = @JoinColumn(name = "sabor_id"))
    private List<Sabor> sabores;


	/*
	 Quando há uma relação N para N simples, utiliza-se o @ManytoMany. Porém, quando
	 se faz necessário trazer outros atributos dentro dessa tabela da relação no banco de dados,
	 precisamos escrever a tabela da relação como classe no Java.

	 A relação entre PEDIDO e PRODUTO é N para N. Porém, quando trazemos uma classe
	  que representa a tabela da relação, que é nosso caso, as cardinalidades mudam e
	  ficam dessa forma:
	 */

}