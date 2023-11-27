package pizzaria.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String obs;


    /*
     Quando há uma relação N para N simples, utiliza-se o @ManytoMany. Porém, quando
     se faz necessário trazer outros atributos dentro dessa tabela da relação no banco de dados,
     precisamos escrever a tabela da relação como classe no Java.

     A relação entre PEDIDO e PRODUTO é N para N. Porém, quando trazemos uma classe
      que representa a tabela da relação, que é nosso caso, as cardinalidades mudam e
      ficam dessa forma:
     */
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("pedido")
    private List<PedidoProduto> pedidoProdutoList;


    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<Balcao> balcoes;
}