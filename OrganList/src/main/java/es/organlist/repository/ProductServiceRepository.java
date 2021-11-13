package es.organlist.repository;

import es.organlist.model.dto.ProductDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface Product service repository.
 */
@Repository
public interface ProductServiceRepository extends JpaRepository<ProductDTO, Long> {
}
