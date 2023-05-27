package it.prova.dottori_be.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import it.prova.dottori_be.model.Dottore;

public interface DottoreRepository extends PagingAndSortingRepository<Dottore,Long>, JpaSpecificationExecutor<Dottore>{

	
}
