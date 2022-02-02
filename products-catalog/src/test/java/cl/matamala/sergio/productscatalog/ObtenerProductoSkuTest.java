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
public class ObtenerProductoSkuTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void obtenerProductosTest() throws Exception {
        mockMvc.perform(get("/productos/obtener-por-sku")
                        .param("sku", "8406270"))
                .andExpect(status().isOk());
    }

    @Test
    public void obtenerProductoErroneo() throws Exception {
        mockMvc.perform(get("/productos/obtener-por-sku")
                        .param("sku", "A"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void obtenerProductoSinParametro() throws Exception {
        mockMvc.perform(get("/productos/obtener-por-sku"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void obtenerProductoNoExiste() throws Exception {
        mockMvc.perform(get("/productos/obtener-por-sku")
                        .param("sku", "522"))
                .andExpect(status().is5xxServerError());
    }

}
