package es.organlist.service;

import es.organlist.mappers.OrganListMapper;
import es.organlist.model.dto.ListDTO;
import es.organlist.model.dto.ProductDTO;
import es.organlist.model.entity.ListEntity;
import es.organlist.model.entity.UserEntity;
import es.organlist.repository.ListRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ListService {

    private static OrganListMapper mapper = Mappers.getMapper(OrganListMapper.class);

    private final ListRepository listRepository;

    public ListService(ListRepository listRepository) {
        this.listRepository = listRepository;
    }

    public List<ListDTO> getLists() {
        List<ListEntity> listEntities = listRepository.findListData();
        if (listEntities.isEmpty()) {
            throw new NotFoundException(HttpStatus.NOT_FOUND + " - No hay datos de entrada");
        }
        return listEntities.stream().map(listEntity -> mapper.toListDTO(listEntity)).collect(Collectors.toList());
    }

    public ResponseEntity insertList(ListDTO list) {
        if (list == null) {
            throw new NotFoundException(HttpStatus.NOT_FOUND + " - No hay datos de entrada");
        }
        ListEntity entityList = mapper.toListEntity(list);
        listRepository.save(entityList);
        return new ResponseEntity("Insertado!", HttpStatus.CREATED);
    }

    public ResponseEntity deleteList(Integer listId) {
        try {
            listRepository.deleteById(listId);
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException(HttpStatus.NOT_FOUND + " - No existe el producto con id " + listId + "\n" + e.getMessage());
        }
        return new ResponseEntity("Borrado!", HttpStatus.OK);
    }

    public ListEntity updateList(ListDTO listDTO) {
        ListEntity listEntity = mapper.toListEntity(listDTO);
        ListEntity updatedList;
        try {
            updatedList = listRepository.save(listEntity);
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException(HttpStatus.NOT_FOUND + " - No existe el producto con id " + listEntity.getId() + "\n" + e.getMessage());
        }
        return updatedList;
    }

    public List<ListDTO> updateFavoriteList(Integer listId) {

        Optional<ListEntity> listEntityOpt = listRepository.findById(listId);

        //Comprobamos si hay resultado
        if (listEntityOpt.isPresent()) {
            ListEntity listEntity = listEntityOpt.get();
            //vemos si es favorito y modificamos el valor
            listEntity.setFav(listEntity.isFav() ? Boolean.FALSE : Boolean.TRUE);
        } else {
            throw new NotFoundException(HttpStatus.NOT_FOUND + " - No existe el producto con id " + listId);
        }

        listRepository.save(listEntityOpt.get());

        return getLists();
    }

    public ListDTO getList(Integer listId) {
        Optional<ListEntity> listEntity = listRepository.findById(listId);
        if (!listEntity.isPresent()) {
            throw new NotFoundException(HttpStatus.NOT_FOUND + " - La lista con id " + listId + " no existe.");
        }
        return mapper.toListDTO(listEntity.get());
    }
}
