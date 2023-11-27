package pizzaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pizzaria.entity.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}