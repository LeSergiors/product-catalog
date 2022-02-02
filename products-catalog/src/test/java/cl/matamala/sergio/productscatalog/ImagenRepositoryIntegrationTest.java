package cl.matamala.sergio.productscatalog;

import cl.matamala.sergio.productscatalog.entities.Producto;
import cl.matamala.sergio.productscatalog.repositories.ImagenRepository;
import cl.matamala.sergio.productscatalog.repositories.ProductoRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.*;

@SpringBootTest
public class ImagenRepositoryIntegrationTest {

    ImagenRepository imagenRepository = Mockito.mock(ImagenRepository.class);

    @Autowired
    private ProductoRepository productoRepository;

    @Test
    void deleteByProductTest() {
        final Producto producto = this.productoRepository.findById(1L).get();
        imagenRepository.deleteAllByProduct(producto);
        verify(imagenRepository, times(1)).deleteAllByProduct(producto);
    }
}
