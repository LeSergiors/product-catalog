package cl.matamala.sergio.productscatalog.entities;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Clase que presenta las propiedades de tabla images
 *
 * @author sergiomatamalasilva
 *
 */
@Entity
@Table(name = "images")
public class Imagen implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Producto product;

    @Column(name = "url", nullable = false)
    private String url;

    @Column(name = "principal", nullable = false, columnDefinition = "boolean default false")
    private Boolean principal;

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
     * Metodo que permite obtener valor de product
     *
     * @return product
     **/
    public Producto getProduct() {
        return product;
    }

    /**
     * Metodo que permite setear el valor de product
     *
     * @param product a setear
     **/
    public void setProduct(Producto product) {
        this.product = product;
    }

    /**
     * Metodo que permite obtener valor de url
     *
     * @return url
     **/
    public String getUrl() {
        return url;
    }

    /**
     * Metodo que permite setear el valor de url
     *
     * @param url a setear
     **/
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Metodo que permite obtener valor de principal
     *
     * @return principal
     **/
    public Boolean getPrincipal() {
        return principal;
    }

    /**
     * Metodo que permite setear el valor de principal
     *
     * @param principal a setear
     **/
    public void setPrincipal(Boolean principal) {
        this.principal = principal;
    }
}
