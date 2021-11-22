package es.organlist.repository;

import es.organlist.model.entity.ProductShopEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface Product repository.
 */
@Repository
public interface ProductShopRepository extends JpaRepository<ProductShopEntity, Integer> {
}
