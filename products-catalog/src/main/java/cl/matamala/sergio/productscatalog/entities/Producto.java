package cl.matamala.sergio.productscatalog.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Clase que presenta las propiedades de tabla Product.
 *
 * @author sergiomatamalasilva
 *
 */
@Entity
@Table(name = "product")
public class Producto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    @Column(name = "sku", nullable = false, unique = true)
    private Long sku;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "brand", nullable = false)
    private String brand;

    @Column(name = "size")
    private String size;

    @Column(name = "price")
    private Double price;

    @Column(name = "created_by")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdBy;

    @Column(name = "updated_by")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedBy;

    @OneToMany(mappedBy = "product", fetch = FetchType.EAGER)
    private List<Imagen> images;

    @PrePersist
    public void prePersist(){
        this.createdBy = new Date();
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedBy = new Date();
    }

    /**
     * Metodo que permite obtener valor de createdBy
     *
     * @return createdBy
     **/
    public Date getCreatedBy() {
        return createdBy;
    }

    /**
     * Metodo que permite setear el valor de createdBy
     *
     * @param createdBy a setear
     **/
    public void setCreatedBy(Date createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * Metodo que permite obtener valor de updatedBy
     *
     * @return updatedBy
     **/
    public Date getUpdatedBy() {
        return updatedBy;
    }

    /**
     * Metodo que permite setear el valor de updatedBy
     *
     * @param updatedBy a setear
     **/
    public void setUpdatedBy(Date updatedBy) {
        this.updatedBy = updatedBy;
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
     * Metodo que permite obtener valor de images
     *
     * @return images
     **/
    public List<Imagen> getImages() {
        return images;
    }

    /**
     * Metodo que permite setear el valor de images
     *
     * @param images a setear
     **/
    public void setImages(List<Imagen> images) {
        this.images = images;
    }
}
