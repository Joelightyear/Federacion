package federacion.controladores;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import federacion.modelo.entidades.Equipo;
import federacion.modelo.entidades.Federacion;
import federacion.modelo.entidades.Jugador;
import federacion.modelo.enumeraciones.EquipoDivision;
import federacion.modelo.enumeraciones.JugadorPosicion;
import federacion.modelo.repositorios.EquipoRepositorio;
import federacion.modelo.repositorios.FederacionRepositorio;
import federacion.propertyeditors.FederacionPropertyEditor;




@Controller
@RequestMapping("/equipos")
public class EquipoController {

	@Autowired
	private FederacionPropertyEditor federacionPropertyEditor;
	@Autowired
	private FederacionRepositorio federacionRepo;
	@Autowired
	private EquipoRepositorio equipoRepo;

	@RequestMapping(method = RequestMethod.GET)
	public String listarEquipos(Model model) {
		
		Iterable<Equipo> lista = equipoRepo.findAll();
		model.addAttribute("titulo", "Listado de Equipos");
		model.addAttribute("equipos", lista);
		model.addAttribute("federacion", federacionRepo.findAll());
		model.addAttribute("divisiones", EquipoDivision.values());
		return "equipo/listado";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String guardar(Model model, @Valid @ModelAttribute Equipo equipo, BindingResult bindingResult) {
		
		equipoRepo.save(equipo);
		Iterable <Equipo> lista = equipoRepo.findAll();
		model.addAttribute("titulo", "Listado de Equipos");
		model.addAttribute("equipos", lista);
		model.addAttribute("federacion", federacionRepo.findAll());
		model.addAttribute("divisiones", EquipoDivision.values());
		return "equipo/listado";

	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/{id}")
	public ResponseEntity<String> borrarEquipo(@PathVariable Long id){

		try {
			equipoRepo.delete(id);
			return new ResponseEntity<String>(HttpStatus.OK);
			
		}catch(Exception e){
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
			}
		
				
		}
	
	@RequestMapping(method=RequestMethod.GET, value="/detalle/{id}")
	public String detalleEquipo(Model model, @PathVariable Long id){
		model.addAttribute("equipo", equipoRepo.findOne(id));
		return "equipo/detalle";
		
	}
	
	
	@RequestMapping(method=RequestMethod.GET, value="/{id}")
	@ResponseBody
	public Equipo buscarEquipo(@PathVariable Long id){
		return equipoRepo.findOne(id);
		
	}
	
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.registerCustomEditor(Federacion.class, federacionPropertyEditor);
	}

}



