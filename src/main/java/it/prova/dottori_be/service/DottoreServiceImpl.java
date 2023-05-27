package it.prova.dottori_be.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Predicate;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.dottori_be.model.Dottore;
import it.prova.dottori_be.repository.DottoreRepository;


@Service
@Transactional(readOnly = true)
public class DottoreServiceImpl implements DottoreService {

	@Autowired
	private DottoreRepository repository;
	
	@Override
	public List<Dottore> listAllElements() {
		return (List<Dottore>) repository.findAll();
	}

	@Override
	public Dottore caricaSingoloElemento(Long id) {
		return repository.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Dottore aggiorna(Dottore dottoreInstance) {
		return repository.save(dottoreInstance);
	}

	@Override
	@Transactional
	public Dottore inserisciNuovo(Dottore dottoreInstance) {
		return repository.save(dottoreInstance);
	}

	@Override
	@Transactional
	public void elimina(Long id) {
		repository.deleteById(id);
	}

	public Page<Dottore> findByExampleWithPagination(Dottore example, Integer pageNo, Integer pageSize, String sortBy) {
		Specification<Dottore> specificationCriteria = (root, query, cb) -> {

			List<Predicate> predicates = new ArrayList<Predicate>();

			if (StringUtils.isNotEmpty(example.getNome()))
				predicates.add(cb.like(cb.upper(root.get("nome")), "%" + example.getNome().toUpperCase() + "%"));
			if (StringUtils.isNotEmpty(example.getCognome()))
				predicates.add(cb.like(cb.upper(root.get("cognome")), "%" + example.getCognome().toUpperCase() + "%"));
			if (StringUtils.isNotEmpty(example.getCodiceDottore()))
				predicates.add(cb.like(cb.upper(root.get("codiceDottore")),
						"%" + example.getCodiceDottore().toUpperCase() + "%"));
			if (StringUtils.isNotEmpty(example.getCodFiscalePazienteAttualmenteInVisita()))
				predicates.add(cb.like(cb.upper(root.get("codFiscalePazienteAttualmenteInVisita")),
						"%" + example.getCodFiscalePazienteAttualmenteInVisita().toUpperCase() + "%"));

			if (example.getInServizio() != null)
				predicates.add(cb.equal(root.get("inServizio"), example.getInServizio()));
			if (example.getInVisita() != null)
				predicates.add(cb.equal(root.get("inVisita"), example.getInVisita()));

			return cb.and(predicates.toArray(new Predicate[predicates.size()]));
		};

		Pageable paging = null;
		// se non passo parametri di paginazione non ne tengo conto
		if (pageSize == null || pageSize < 10)
			paging = Pageable.unpaged();
		else
			paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

		return repository.findAll(specificationCriteria, paging);
	}

}
