package es.organlist.repository;

import es.organlist.model.entity.ListEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ListRepository extends JpaRepository<ListEntity, Integer> {

    //@Query(value = "select l.* from usuario u inner join lista l on u.id = l.usuario", nativeQuery = true)// campos en SQL
    @Query("select l from ListEntity l Join l.user u") // campos en JPQL
    List<ListEntity> findListData();

}
