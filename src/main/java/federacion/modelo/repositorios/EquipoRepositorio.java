package federacion.modelo.repositorios;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import federacion.modelo.entidades.Equipo;


@Repository
public interface EquipoRepositorio extends CrudRepository<Equipo, Long>{

}
