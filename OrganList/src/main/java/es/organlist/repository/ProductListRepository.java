package es.organlist.repository;

import es.organlist.model.entity.ProductListEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductListRepository extends JpaRepository<ProductListEntity, Integer> {
    void deleteByProduct(Integer productId);
    List<ProductListEntity> findByList(Integer list);
}
