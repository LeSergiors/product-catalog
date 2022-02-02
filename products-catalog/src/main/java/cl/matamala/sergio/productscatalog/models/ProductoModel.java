package cl.matamala.sergio.productscatalog.models;

import cl.matamala.sergio.productscatalog.entities.Imagen;
import cl.matamala.sergio.productscatalog.entities.Producto;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que presenta las propiedades de ProductModel
 *
 * @author sergiomatamalasilva
 *
 */
public class ProductoModel {

    private static final String FAL = "FAL";

    private Long id;
    private String tipo;
    private Long sku;
    private String name;
    private String brand;
    private String size;
    private Double price;
    private ImagenModel principal;
    private List<ImagenModel> imagenes;

    /**
     * Constructor de la clase
     */
    public ProductoModel() {
        super();
    }

    /**
     * Constructor de la clase
     * @param p producto.
     */
    public ProductoModel(final Producto p) {
        super();
        this.id = p.getId();
        this.tipo = FAL;
        this.name = p.getName();
        this.sku = p.getSku();
        this.brand = p.getBrand();
        this.size = p.getSize();
        this.price = p.getPrice();

        if (null != p.getImages() && !p.getImages().isEmpty()) {
         this.imagenes = new ArrayList<>();
         for (Imagen i : p.getImages()) {
             this.imagenes.add(new ImagenModel(i));
             if (null != i.getPrincipal() && i.getPrincipal()) {
                 this.principal = new ImagenModel(i);
             }
         }
        }
    }

    /**
     * Metodo que permite obtener valor de id
     *
     * @return id
     **/
    public Long getId() {
        return id;
    }

    /**
     * Metodo que permite setear el valor de id
     *
     * @param id a setear
     **/
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Metodo que permite obtener valor de brand
     *
     * @return brand
     **/
    public String getBrand() {
        return brand;
    }

    /**
     * Metodo que permite setear el valor de brand
     *
     * @param brand a setear
     **/
    public void setBrand(String brand) {
        this.brand = brand;
    }

    /**
     * Metodo que permite obtener valor de size
     *
     * @return size
     **/
    public String getSize() {
        return size;
    }

    /**
     * Metodo que permite setear el valor de size
     *
     * @param size a setear
     **/
    public void setSize(String size) {
        this.size = size;
    }

    /**
     * Metodo que permite obtener valor de price
     *
     * @return price
     **/
    public Double getPrice() {
        return price;
    }

    /**
     * Metodo que permite setear el valor de price
     *
     * @param price a setear
     **/
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * Metodo que permite obtener valor de imagenes
     *
     * @return imagenes
     **/
    public List<ImagenModel> getImagenes() {
        return imagenes;
    }

    /**
     * Metodo que permite setear el valor de imagenes
     *
     * @param imagenes a setear
     **/
    public void setImagenes(List<ImagenModel> imagenes) {
        this.imagenes = imagenes;
    }

    /**
     * Metodo que permite obtener valor de tipo
     *
     * @return tipo
     **/
    public String getTipo() {
        return tipo;
    }

    /**
     * Metodo que permite setear el valor de tipo
     *
     * @param tipo a setear
     **/
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * Metodo que permite obtener valor de sku
     *
     * @return sku
     **/
    public Long getSku() {
        return sku;
    }

    /**
     * Metodo que permite setear el valor de sku
     *
     * @param sku a setear
     **/
    public void setSku(Long sku) {
        this.sku = sku;
    }

    /**
     * Metodo que permite obtener valor de name
     *
     * @return name
     **/
    public String getName() {
        return name;
    }

    /**
     * Metodo que permite setear el valor de name
     *
     * @param name a setear
     **/
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Metodo que permite obtener valor de principal
     *
     * @return principal
     **/
    public ImagenModel getPrincipal() {
        return principal;
    }

    /**
     * Metodo que permite setear el valor de principal
     *
     * @param principal a setear
     **/
    public void setPrincipal(ImagenModel principal) {
        this.principal = principal;
    }
}
