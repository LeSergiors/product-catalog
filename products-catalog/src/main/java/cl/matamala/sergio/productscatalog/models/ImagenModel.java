package cl.matamala.sergio.productscatalog.models;

import cl.matamala.sergio.productscatalog.entities.Imagen;

/**
 * Clase que presenta las propiedades de ImagesModel
 *
 * @author sergiomatamalasilva
 *
 */
public class ImagenModel {

    private Long id;
    private String url;
    private Boolean principal;

    /**
     * Constructor de la Clase
     */
    public ImagenModel() {
        super();
    }

    /**
     * Constructor de la Clase
     * @param id id
     * @param url url
     * @param principal principal
     */
    public ImagenModel(Long id, String url, Boolean principal) {
        super();
        this.id = id;
        this.url = url;
        this.principal = principal;
    }

    /**
     * Constructor de la Clase
     * @param image imagen
     */
    public ImagenModel(final Imagen image) {
        super();
        this.id = image.getId();
        this.url = image.getUrl();
        this.principal = image.getPrincipal();
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
