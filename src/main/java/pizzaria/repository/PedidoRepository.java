package pizzaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pizzaria.entity.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

}