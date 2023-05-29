package it.prova.dottori_be.dto;

import javax.validation.constraints.NotBlank;

import it.prova.dottori_be.model.Dottore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DottorePazienteDTO {
	
	@NotBlank(message = "{codiceDottore.notblank}")
	private String codiceDottore;
	@NotBlank(message = "{codiceCfPaziente.notblank}")
	private String codFiscalePazienteAttualmenteInVisita;
	
	
	public static DottorePazienteDTO buildDottoreDTOFromModel(Dottore dottoreModel) {
		DottorePazienteDTO result = new DottorePazienteDTO(dottoreModel.getCodiceDottore(),
				dottoreModel.getCodFiscalePazienteAttualmenteInVisita());
		return result;
	}

}
