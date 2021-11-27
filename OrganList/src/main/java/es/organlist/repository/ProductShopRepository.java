package es.organlist.repository;

import es.organlist.model.entity.ProductShopEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductShopRepository extends JpaRepository<ProductShopEntity, Integer> {
}
