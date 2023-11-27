package pizzaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pizzaria.entity.Balcao;

public interface BalcaoRepository extends JpaRepository< Balcao, Long> {
}
