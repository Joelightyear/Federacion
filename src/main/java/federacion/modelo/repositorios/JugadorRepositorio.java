package federacion.modelo.repositorios;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import federacion.modelo.entidades.Jugador;

@Repository
public interface JugadorRepositorio extends CrudRepository<Jugador, Long>{

}
