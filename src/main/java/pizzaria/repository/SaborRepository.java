package pizzaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pizzaria.entity.Sabor;

public interface SaborRepository extends JpaRepository<Sabor, Long> {

}