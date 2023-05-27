package it.prova.dottori_be.dto;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import it.prova.dottori_be.model.Dottore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DottoreDTO {

	private Long id;

	@NotBlank(message = "Il campo Nome deve essere valorizzato")
	private String nome;
	@NotBlank(message = "Il campo Cognome deve essere valorizzato")
	private String cognome;
	@NotBlank(message = "Il campo Codice Dottore  deve essere valorizzato")
	private String codiceDottore;

	@JsonIgnore(value = true)
	private String codFiscalePazienteAttualmenteInVisita;

	private Boolean inVisita;

	private Boolean inServizio;

	public Dottore buildModel() {
		Dottore result = Dottore.builder().id(this.id).nome(this.nome).cognome(this.cognome)
				.codiceDottore(this.codiceDottore)
				.codFiscalePazienteAttualmenteInVisita(this.codFiscalePazienteAttualmenteInVisita)
				.inVisita(this.inVisita).inServizio(this.inServizio).build();
		return result;
	}

	public static DottoreDTO buildDottoreDTOFromModel(Dottore dottoreModel) {
		DottoreDTO result = DottoreDTO.builder().id(dottoreModel.getId()).nome(dottoreModel.getNome())
				.cognome(dottoreModel.getCognome()).codiceDottore(dottoreModel.getCodiceDottore())
				.codFiscalePazienteAttualmenteInVisita(dottoreModel.getCodFiscalePazienteAttualmenteInVisita())
				.inVisita(dottoreModel.getInVisita()).inServizio(dottoreModel.getInServizio()).build();
		return result;
	}

	public static List<DottoreDTO> createDottoreDTOListFromModelList(List<Dottore> modelList) {
		return modelList.stream().map(entity -> DottoreDTO.buildDottoreDTOFromModel(entity))
				.collect(Collectors.toList());
	}

	public static Page<DottoreDTO> fromModelPageToDTOPage(Page<Dottore> input) {
		return new PageImpl<>(createDottoreDTOListFromModelList(input.getContent()),
				PageRequest.of(input.getPageable().getPageNumber(), input.getPageable().getPageSize(),
						input.getPageable().getSort()),
				input.getTotalElements());
	}
}
