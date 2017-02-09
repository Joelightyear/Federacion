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
import federacion.modelo.entidades.Jugador;
import federacion.modelo.enumeraciones.JugadorPosicion;
import federacion.modelo.repositorios.EquipoRepositorio;
import federacion.modelo.repositorios.JugadorRepositorio;
import federacion.propertyeditors.EquipoPropertyEditor;

@Controller
@RequestMapping("/jugadores")
public class JugadorController {

	@Autowired
	private JugadorRepositorio jugadorRepo;
	@Autowired
	private EquipoRepositorio equipoRepo;
	
	@Autowired 
	private EquipoPropertyEditor equipoPropertyEditor;
	

	@RequestMapping(method = RequestMethod.GET)
	public String listarJugadores(Model model) {
		
		Iterable<Jugador> lista = jugadorRepo.findAll();
		model.addAttribute("titulo", "Listado de Jugadores");
		model.addAttribute("jugadores", lista);
		model.addAttribute("equipo", equipoRepo.findAll());
		model.addAttribute("posiciones", JugadorPosicion.values());
		return "jugador/listado";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String guardar(Model model, @Valid @ModelAttribute Jugador jugador, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			bindingResult.getFieldErrors();
		}else {
			jugadorRepo.save(jugador);	
		}
		
		Iterable <Jugador> lista = jugadorRepo.findAll();
		model.addAttribute("titulo", "Listado de Jugadores");
		model.addAttribute("jugadores", lista);
		model.addAttribute("equipo", equipoRepo.findAll());
		model.addAttribute("posiciones", JugadorPosicion.values());
		return "jugador/listado";

	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/{id}")
	public ResponseEntity<String> borrarJugador(@PathVariable Long id){

		try {
			jugadorRepo.delete(id);
			return new ResponseEntity<String>(HttpStatus.OK);
			
		}catch(Exception e){
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
			}			
		}
	
	
	@RequestMapping(method=RequestMethod.GET, value="/detalle/{id}")
	public String detalleJugador(Model model, @PathVariable Long id){
		model.addAttribute("jugador", jugadorRepo.findOne(id));
		return "jugador/detalle";
		
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/{id}")
	@ResponseBody
	public Jugador buscarJugador(@PathVariable Long id){
		return jugadorRepo.findOne(id);
		
	}
	
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.registerCustomEditor(Equipo.class, equipoPropertyEditor);
	}

}


