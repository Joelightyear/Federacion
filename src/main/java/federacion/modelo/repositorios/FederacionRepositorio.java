package federacion.modelo.repositorios;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import federacion.modelo.entidades.Equipo;
import federacion.modelo.entidades.Federacion;

@Repository
public interface FederacionRepositorio extends CrudRepository<Federacion, Long>{

}

