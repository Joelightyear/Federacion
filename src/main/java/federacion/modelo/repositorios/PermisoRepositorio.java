package federacion.modelo.repositorios;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import federacion.modelo.entidades.Federacion;
import federacion.modelo.entidades.Permiso;

@Repository
public interface PermisoRepositorio extends CrudRepository<Permiso, Long>{

}
