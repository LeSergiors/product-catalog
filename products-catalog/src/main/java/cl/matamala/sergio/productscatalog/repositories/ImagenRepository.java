package cl.matamala.sergio.productscatalog.repositories;

import cl.matamala.sergio.productscatalog.entities.Imagen;
import cl.matamala.sergio.productscatalog.entities.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interfaz que presenta las operaciones sobre Images
 *
 * @author sergiomatamalasilva
 *
 */
@Repository
public interface ImagenRepository extends JpaRepository<Imagen, Long> {

    /**
     * Método que permite eliminar todas las imagenes para un producto.
     * @param p producto.
     */
    void deleteAllByProduct(final Producto p);

}
