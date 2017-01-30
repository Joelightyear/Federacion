package federacion.propertyeditors;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import federacion.modelo.entidades.Federacion;
import federacion.modelo.repositorios.FederacionRepositorio;

@Component
public class FederacionPropertyEditor extends PropertyEditorSupport {

	@Autowired 
	private FederacionRepositorio federacionRepositorio;
	
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		long idFederacion = Long.parseLong(text);
		Federacion federacion = federacionRepositorio.findOne(idFederacion);
		setValue(federacion);
	}
	
}