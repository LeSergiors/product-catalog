package cl.matamala.sergio.productscatalog;

import static org.assertj.core.api.Assertions.assertThat;

import cl.matamala.sergio.productscatalog.entities.Imagen;
import cl.matamala.sergio.productscatalog.entities.Producto;
import cl.matamala.sergio.productscatalog.models.ProductoModel;
import cl.matamala.sergio.productscatalog.repositories.ProductoRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class ProductoRepositoryIntegrationTest {

    @Autowired
    private ProductoRepository productoRepository;

    @Test
    public void obtenerProductosModelTest() {

        assertThat(this.productoRepository.obtenerProductModel())
                .isNotNull()
                .isNotEmpty()
                .isInstanceOf(List.class);
    }

    @Test
    public void obtenerProductoModelTest() {
        assertThat(this.productoRepository.obtenerProductoModel(1L))
                .isNotNull()
                .isInstanceOf(ProductoModel.class);
    }

    @Test
    public void obtenerProductoModelPorSkuTest(){
        assertThat(this.productoRepository.obtenerProductoModelPorSku(881952283L))
                .isNotNull()
                .isInstanceOf(ProductoModel.class);
    }

    @Test
    public void encontrarProductoPorSku(){

        final Optional<Producto> result = this.productoRepository.findProductoBySku(881952283L);
        assertThat(result)
                .isNotNull()
                .isInstanceOf(Optional.class);

        assertThat(result.isPresent())
                .isTrue();

        assertThat(result.get())
                .isNotNull()
                .isInstanceOf(Producto.class);
    }




}
