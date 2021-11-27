package es.organlist.repository;

import es.organlist.model.entity.ProductEntity;
import es.organlist.model.entity.ProductShopEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {
}
