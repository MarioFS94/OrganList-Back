package es.organlist.repository;

import es.organlist.model.entity.ListEntity;
import es.organlist.model.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ListRepository extends JpaRepository<ListEntity, Integer> {
}
