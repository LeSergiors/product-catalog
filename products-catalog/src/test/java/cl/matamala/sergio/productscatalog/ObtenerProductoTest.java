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
public class ObtenerProductoTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void obtenerProductoTest() throws Exception {
        mockMvc.perform(get("/productos/obtener-productos"))
                .andExpect(status().isOk());
    }
}
