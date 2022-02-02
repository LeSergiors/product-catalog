package cl.matamala.sergio.productscatalog.repositories;

import cl.matamala.sergio.productscatalog.entities.Producto;
import cl.matamala.sergio.productscatalog.models.ProductoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Interfaz que presenta las operaciones sobre Product
 *
 * @author sergiomatamalasilva
 *
 */
@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

    /**
     * Método que permite obtener listado de productos.
     * @return productos.
     */
    @Query("SELECT new cl.matamala.sergio.productscatalog.models.ProductoModel(p) FROM Producto p ORDER BY p.id ASC")
    List<ProductoModel> obtenerProductModel();

    /**
     * Método que permite obtener producto.
     * @param id id
     * @return producto
     */
    @Query("SELECT new cl.matamala.sergio.productscatalog.models.ProductoModel(p) FROM Producto p WHERE p.id = :id ")
    ProductoModel obtenerProductoModel(@Param("id") final Long id);

    /**
     * Método que permite obtener producto por SKU
     * @param sku SKU
     * @return producto
     */
    @Query("SELECT new cl.matamala.sergio.productscatalog.models.ProductoModel(p) FROM Producto p WHERE p.sku = :sku ")
    ProductoModel obtenerProductoModelPorSku(@Param("sku") final Long sku);

    /**
     * Método que permite obtener producto por SKU
     * @param sku sku
     * @return producto
     */
    Optional<Producto> findProductoBySku(final Long sku);

}
