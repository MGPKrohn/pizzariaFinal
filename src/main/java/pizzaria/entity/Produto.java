package pizzaria.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private boolean temSabores;
    private int maximoSabores;
    private double valor;

    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL)
    private List<Balcao> balcoes;
}