package cl.matamala.sergio.productscatalog;

import cl.matamala.sergio.productscatalog.models.ImagenModel;
import cl.matamala.sergio.productscatalog.models.ProductoModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Collections;

@SpringBootTest
@AutoConfigureMockMvc
public class GuardarProductoTest {

    @Autowired
    private MockMvc mockMvc;

    public void cargarAnterior() {

    }

    @Test
    public void guardarProductoTest() throws Exception {
        final ProductoModel productoModel = new ProductoModel();
        productoModel.setSku(1000000L);
        productoModel.setName("TEST");
        productoModel.setBrand("TEST");
        productoModel.setSize("L");
        productoModel.setPrice(1D);

        final ImagenModel imagenModel = new ImagenModel();
        imagenModel.setPrincipal(Boolean.TRUE);
        imagenModel.setUrl("www.test.cl");

        productoModel.setImagenes(Collections.singletonList(imagenModel));

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(productoModel);

        mockMvc.perform(post("/productos/guardar-producto")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isOk());
    }

    @Test
    public void guardarProductoSinValores() throws Exception {
        final ProductoModel productoModel = new ProductoModel();
        productoModel.setSku(1000000L);

        productoModel.setSize("L");
        productoModel.setPrice(1D);

        final ImagenModel imagenModel = new ImagenModel();
        imagenModel.setPrincipal(Boolean.TRUE);
        imagenModel.setUrl("www.test.cl");

        productoModel.setImagenes(Collections.singletonList(imagenModel));

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(productoModel);

        mockMvc.perform(post("/productos/guardar-producto")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().is5xxServerError());
    }

}
