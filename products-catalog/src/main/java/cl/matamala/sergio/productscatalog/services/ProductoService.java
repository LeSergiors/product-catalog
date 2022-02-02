package cl.matamala.sergio.productscatalog.services;

import cl.matamala.sergio.productscatalog.models.ProductoModel;

import java.util.List;

/**
 * Interfaz que presenta las operaciones sobre Product
 *
 * @author sergiomatamalasilva
 *
 */
public interface ProductoService {

    /**
     * Método que permite obtener Productos.
     *
     * @return productos
     */
    List<ProductoModel> obtenerProductosModel();

    /**
     * Metodo que permite obtener un producto.
     * @param id id
     * @return producto
     */
    ProductoModel obtenerProductoModel(final Long id);

    /**
     * Elemento que permite guardar un producto.
     *
     * @param producto producto
     *
     */
    void guardarProducto(final ProductoModel producto);

    /**
     *
     * Método que permite eliminar un producto.
     *
     * @param id id
     */
    void eliminarProducto(final Long id);

    /**
     * Método que permite obtener Producto por SKU
     * @param sku sku
     * @return producto
     */
    ProductoModel obtenerProductoModelPorSku(final Long sku);
}
