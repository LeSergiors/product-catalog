package cl.matamala.sergio.productscatalog.services;

import cl.matamala.sergio.productscatalog.entities.Imagen;
import cl.matamala.sergio.productscatalog.entities.Producto;
import cl.matamala.sergio.productscatalog.exceptions.NegocioException;
import cl.matamala.sergio.productscatalog.models.ImagenModel;
import cl.matamala.sergio.productscatalog.models.ProductoModel;
import cl.matamala.sergio.productscatalog.repositories.ImagenRepository;
import cl.matamala.sergio.productscatalog.repositories.ProductoRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Clase que implementa las operaciones sobre Products
 *
 * @author sergiomatamalasilva
 *
 */
@Service
public class ProductoServiceImpl implements ProductoService {

    private static final Long MIN_SKU = 1000000L;
    private static final Long MAX_SKU = 99999999L;
    private static final Integer MIN_LENGTH = 3;
    private static final Integer MAX_LENGTH = 50;
    private static final Double MIN_PRICE = 1D;
    private static final Double MAX_PRICE = 99999999D;

    private final ProductoRepository productoRepository;
    private final ImagenRepository imagenRepository;

    @Autowired
    public ProductoServiceImpl(
            ProductoRepository productoRepository,
            ImagenRepository imagenRepository
    ) {
        this.productoRepository = productoRepository;
        this.imagenRepository = imagenRepository;
    }

    @Override
    public List<ProductoModel> obtenerProductosModel() {
        return this.productoRepository.obtenerProductModel();
    }

    @Override
    public ProductoModel obtenerProductoModel(Long id) {
        if (null == id) {
            throw new NegocioException("Información de Producto se encuentra nula.");
        }
        if (this.productoRepository.findById(id).isEmpty()) {
            throw new NegocioException("Producto no encontrado.");
        }
        return this.productoRepository.obtenerProductoModel(id);
    }

    @Override
    @Transactional
    public void guardarProducto(ProductoModel model) {

        if (null == model) {
            throw new NegocioException("Datos de Producto se encuentran nulos.");
        }

        Producto product = null;
        if (null == model.getId()) {
            product = new Producto();
            if (this.productoRepository.findProductoBySku(model.getSku()).isPresent()) {
                throw new NegocioException("Ya existe producto asociado a ese SKU");
            }
        } else {
            final Optional<Producto> optional = this.productoRepository.findById(model.getId());
            if (optional.isEmpty()) {
                throw new NegocioException("Producto no encontrado.");
            }
            product = optional.get();
        }

        this.validarCantidadPrincipal(model.getImagenes());

        final boolean existsImagenPrincipalModel = this.validarImagenPrincipalModel(model.getImagenes());
        final boolean existsImagenPrincipalEntidad = this.validarImagenPricinpalEntidad(product.getImages());

        if (!existsImagenPrincipalEntidad && !existsImagenPrincipalModel) {
            throw new NegocioException("Producto no posee una imagen principal");
        }

        this.prepararProducto(product, model);
        this.productoRepository.save(product);
        this.prepararImagenesProducto(product, model.getImagenes());
    }


    @Transactional
    @Override
    public void eliminarProducto(Long id) {
        if (this.productoRepository.findById(id).isEmpty()) {
            throw new NegocioException("Producto no encontrado");
        }

        final Producto producto = this.productoRepository.findById(id).get();
        this.imagenRepository.deleteAllByProduct(producto);
        this.productoRepository.delete(producto);
    }

    @Override
    public ProductoModel obtenerProductoModelPorSku(Long sku) {
        if (null == sku) {
            throw new NegocioException("Información de Producto se encuentra nula.");
        }
        if (this.productoRepository.findProductoBySku(sku).isEmpty()) {
            throw new NegocioException("Producto no encontrado.");
        }
        return this.productoRepository.obtenerProductoModelPorSku(sku);
    }

    /**
     * Método que permite validar la existencia de una imagen marcada como "principal" en el body enviado por el usuario
     * @param imagenes imagenes
     * @return existe
     */
    private boolean validarImagenPrincipalModel(final List<ImagenModel> imagenes) {
        boolean exists = false;
        for (ImagenModel i : imagenes) {
                exists = i.getPrincipal();
                break;
        }

        return exists;
    }

    /**
     * Método que permite evaluar si existe una imagen marcada como principal asociada al producto.
     * @param imagenes imagenes
     * @return existe
     */
    private boolean validarImagenPricinpalEntidad(final List<Imagen> imagenes) {
        boolean exists = false;
        if (null != imagenes && !imagenes.isEmpty()) {
            for (Imagen i : imagenes) {
                exists = i.getPrincipal();
            }
        }
        return exists;
    }

    /**
     * Método que realiza validación y permite preparar los datos para agregar un producto o actualizar un prodcto
     * @param product producto
     * @param model model
     */
    private void prepararProducto(final Producto product, final ProductoModel model) {

        if (null == model.getSku()) {
            throw new NegocioException("SKU se encuentra nulo.");
        }

        if (MIN_SKU > model.getSku() || MAX_SKU < model.getSku()) {
            throw new NegocioException("SKU fuera dentro del rango permitido.");
        }

        if (StringUtils.isBlank(model.getBrand())) {
            throw new NegocioException("Marca se encuentran nula.");
        }

        if (MIN_LENGTH > model.getBrand().length() || MAX_LENGTH < model.getBrand().length()) {
            throw new NegocioException("Largo de nombre de marca no permitido.");
        }

        if (StringUtils.isBlank(model.getName())) {
            throw new NegocioException("Nombre se encuentran nulos.");
        }

        if (MIN_LENGTH > model.getName().length() || MAX_LENGTH < model.getName().length()) {
            throw new NegocioException("Largo de nombre de producto no permitido.");
        }

        if(StringUtils.isNotEmpty(model.getSize()) && StringUtils.isBlank(model.getSize())) {
            throw new NegocioException("Tamaño se producto se encuentra en blanco.");
        }

        if (null == model.getPrice()) {
            throw new NegocioException("Precio se encuentra vacio.");
        }

        if (MIN_PRICE > model.getPrice() || MAX_PRICE < model.getPrice()){
            throw new NegocioException("Precio se encuentra fuera del rago permitido.");
        }

        product.setSku(model.getSku());
        product.setName(model.getName());
        product.setBrand(model.getBrand());
        product.setSize(model.getSize());
        product.setPrice(model.getPrice());
    }

    /**
     * Método que permite procesar las imagenes asociadas a un producto.
     * @param product prodcto
     * @param imagenes imagenes
     */
    private void prepararImagenesProducto(final Producto product, final List<ImagenModel> imagenes) {
        this.imagenRepository.deleteAllByProduct(product);
        final List<Imagen> imagenList = new ArrayList<>();
        for (ImagenModel i : imagenes) {
            final Imagen imagen = new Imagen();
            imagen.setProduct(product);
            imagen.setPrincipal(i.getPrincipal());
            imagen.setUrl(i.getUrl());

            imagenList.add(imagen);
        }
        this.imagenRepository.saveAll(imagenList);
    }

    /**
     * Método que permite validar cantidad de imagenes marcadas como "principal" enviadas para un producto.
     * @param imagenes imagenes.
     */
    private void validarCantidadPrincipal(final List<ImagenModel> imagenes){
        int total = 0;
        for (ImagenModel imagen : imagenes) {
            if (null != imagen.getPrincipal() && imagen.getPrincipal()) {
                total++;
            }
        }

        if (total > 1) {
            throw new NegocioException("Se ha marcado más de una imagen como principal.");
        }
    }

}
