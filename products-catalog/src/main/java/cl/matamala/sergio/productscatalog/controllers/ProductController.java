package cl.matamala.sergio.productscatalog.controllers;

import cl.matamala.sergio.productscatalog.api.Respuesta;
import cl.matamala.sergio.productscatalog.exceptions.NegocioException;
import cl.matamala.sergio.productscatalog.models.ProductoModel;
import cl.matamala.sergio.productscatalog.services.ProductoService;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;

/**
 * Clase que presenta las operaciones sobre Products.
 *
 * @author sergiomatamalasilva
 *
 */
@RestController
@RequestMapping("/productos")
public class ProductController {

    private final ProductoService productoService;

    @Autowired
    public ProductController(
            ProductoService productoService
    ) {
        this.productoService = productoService;
    }

    /**
     * Método que permite obtener Productos.
     * @return Respuesta
     */
    @GetMapping("/obtener-productos")
    public ResponseEntity<Object> obtenerProductos() {
        final Respuesta<List<ProductoModel>> respuesta = new Respuesta<>();
        respuesta.setData(this.productoService.obtenerProductosModel());
        respuesta.setTotal((long) respuesta.getData().size());
        respuesta.setSuccess(Boolean.TRUE);

        return ResponseEntity.ok(respuesta);
    }

    /**
     * Método que permite obtener un producto.
     * @param id Identificador
     * @return producto
     */
    @GetMapping("/obtener-producto")
    public ResponseEntity<Object> obtenerProducto(@RequestParam(name = "product_id") final Long id) {
        final Respuesta<ProductoModel> respuesta = new Respuesta<>();
        respuesta.setData(this.productoService.obtenerProductoModel(id));
        respuesta.setTotal(1L);
        respuesta.setSuccess(Boolean.TRUE);

        return ResponseEntity.ok(respuesta);

    }

    /**
     * Método que permite eliminar un producto.
     * @param id Identificador
     * @return Respuesta
     */
    @GetMapping("/eliminar-producto")
    public ResponseEntity<Object> eliminarProducto(@RequestParam("product_id") final Long id) {
        final Respuesta<String> respuesta = new Respuesta<>();
        this.productoService.eliminarProducto(id);
        respuesta.setTotal(1L);
        respuesta.setSuccess(Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }

    /**
     * Método que permite guardar un producto.
     * @param model información producto.
     * @return respuesta
     */
    @PostMapping("/guardar-producto")
    public ResponseEntity<Object> guardarProducto(@RequestBody final ProductoModel model) {
        final Respuesta<String> respuesta = new Respuesta<>();
        this.productoService.guardarProducto(model);
        respuesta.setTotal(1L);
        respuesta.setSuccess(Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }

    /**
     * Método que permiet obtener Producto por SKU
     * @param sku sku
     * @return producto
     */
    @GetMapping("/obtener-por-sku")
    public ResponseEntity<Object> obtenerProductoPorSku(@RequestParam("sku") final Long sku) {
        final Respuesta<ProductoModel> respuesta = new Respuesta<>();
        respuesta.setData(this.productoService.obtenerProductoModelPorSku(sku));
        respuesta.setTotal(1L);
        respuesta.setSuccess(Boolean.TRUE);

        return ResponseEntity.ok(respuesta);
    }

    /**
     * Método que controla las excepciones de negocio emitadas por el sistema
     * @param e NegocioException
     * @return Response Entity
     */
    @ExceptionHandler({NegocioException.class})
    public ResponseEntity<Object> negocioExceptionHandler(NegocioException e) {
        final Respuesta<String> respuesta = new Respuesta<>();
        respuesta.setError(ExceptionUtils.getRootCauseMessage(e));
        respuesta.setSuccess(Boolean.FALSE);

        return ResponseEntity.internalServerError().body(respuesta);
    }

    /**
     * Método que controla las excepciones de Parametros ausentes en los endpoints
     * @param e MissingServletRequestParameterException
     * @return Response Entity
     */
    @ExceptionHandler({MissingServletRequestParameterException.class})
    public ResponseEntity<Object> handlerMissingParameterException(MissingServletRequestParameterException e) {
        final Respuesta<String> respuesta = new Respuesta<>();
        respuesta.setError(ExceptionUtils.getRootCauseMessage(e));
        respuesta.setSuccess(Boolean.FALSE);

        return ResponseEntity.badRequest().body(respuesta);
    }

    /**
     * Método que controla las ecepciones de tipo de parametro erroneo
     * @param e MethodArgumentTypeMismatchException
     * @return Response Entity
     */
    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    public ResponseEntity<Object> handlerMissingParameterException(MethodArgumentTypeMismatchException e) {
        final Respuesta<String> respuesta = new Respuesta<>();
        respuesta.setError(ExceptionUtils.getRootCauseMessage(e));
        respuesta.setSuccess(Boolean.FALSE);

        return ResponseEntity.badRequest().body(respuesta);
    }
}
