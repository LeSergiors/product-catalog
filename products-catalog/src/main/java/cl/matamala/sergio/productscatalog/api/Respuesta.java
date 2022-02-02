package cl.matamala.sergio.productscatalog.api;

/**
 * Clase que representa la estructura para respuesta de los servicios.
 *
 * @author sergiomatamala
 * @param <T> tipo objeto
 */
public class Respuesta<T> {

    private boolean success;
    private Long total;
    private T data;
    private String error;

    /**
     * Constructor de la clase.
     */
    public Respuesta() {
        super();
    }

    /**
     * Constructor de la clase. Permite setear el valor a la variable success.
     *
     * @param success valor a setear
     */
    public Respuesta(final boolean success) {
        super();
        this.success = success;
    }

    /**
     * Permite obtener el valor almacenado en la variable data.
     *
     * @return valor almacenado
     */
    public T getData() {
        return this.data;
    }

    /**
     * Permite setear el valor a la variable data.
     *
     * @param data objeto a almacenar en la variable data
     */
    public void setData(final T data) {
        this.data = data;
    }

    /**
     * Permite setear el valor a la variable total.
     *
     * @param total valor a setear
     */
    public void setTotal(final Long total) {
        this.total = total;
    }

    /**
     * Permite obtener el valor almacenado en la variable total.
     *
     * @return valor variable total
     */
    public Long getTotal() {
        return this.total;
    }

    /**
     * Permite obtener el valor de la variable success.
     *
     * @return valor variable success
     */
    public boolean isSuccess() {
        return this.success;
    }

    /**
     * Permite setear el valor a la variable success.
     *
     * @param success valor a setear
     */
    public void setSuccess(final boolean success) {
        this.success = success;
    }

    /**
     * Permite obtener el valor de la variable error.
     *
     * @return valor variable error
     */
    public String getError() {
        return this.error;
    }

    /**
     * Permite setear el valor a la variable error.
     *
     * @param error valor a setear
     */
    public void setError(final String error) {
        this.error = error;
    }

}
