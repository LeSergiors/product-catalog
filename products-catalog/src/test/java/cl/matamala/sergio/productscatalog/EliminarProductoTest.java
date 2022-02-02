package cl.matamala.sergio.productscatalog;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class EliminarProductoTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void eliminarProductoTest() throws Exception {
        mockMvc.perform(get("/productos/eliminar-producto")
                        .param("product_id", "1"))
                .andExpect(status().isOk());
    }

    @Test
    public void eliminarProductoErroneo() throws Exception {
        mockMvc.perform(get("/productos/eliminar-producto")
                        .param("product_id", "A"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void eliminarProductoSinParametro() throws Exception {
        mockMvc.perform(get("/productos/eliminar-producto"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void obtenerProductoNoExiste() throws Exception {
        mockMvc.perform(get("/productos/eliminar-producto")
                        .param("product_id", "522"))
                .andExpect(status().is5xxServerError());
    }

}
