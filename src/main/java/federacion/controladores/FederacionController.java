package federacion.controladores;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import federacion.modelo.entidades.Equipo;
import federacion.modelo.entidades.Federacion;
import federacion.modelo.repositorios.EquipoRepositorio;
import federacion.modelo.repositorios.FederacionRepositorio;

@Controller
@RequestMapping("/federaciones")
public class FederacionController {

	@Autowired
	private FederacionRepositorio federacionRepo;
	@Autowired
	private EquipoRepositorio equipoRepo;

	@RequestMapping(method = RequestMethod.GET)
	public String listarFederaciones(Model model) {
		
		Iterable<Federacion> lista = federacionRepo.findAll();
		model.addAttribute("titulo", "Listado de Federaciones");
		model.addAttribute("federaciones", lista);
		return "federacion/listado";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String guardar(Model model, @Valid @ModelAttribute Federacion federacion, BindingResult bindingResult) {
		
		federacionRepo.save(federacion);
		Iterable <Federacion> lista = federacionRepo.findAll();
		model.addAttribute("titulo", "Listado de Federacion");
		model.addAttribute("federaciones", lista);
		return "federacion/listado";

	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/{id}")
	public ResponseEntity<String> borrarFederacion(@PathVariable Long id){

		try {
			federacionRepo.delete(id);
			return new ResponseEntity<String>(HttpStatus.OK);
			
		}catch(Exception e){
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
			}			
		}
	
	@RequestMapping(method=RequestMethod.GET, value="/{id}")
	@ResponseBody
	public Federacion buscarFederacion(@PathVariable Long id){
		System.out.println("----------------"+id);
		Federacion fd = null;
		
		try {
			fd = federacionRepo.findOne(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.print(e.getStackTrace());
		}
		return fd;
	}


}


