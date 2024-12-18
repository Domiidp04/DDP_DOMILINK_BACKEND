Para poder desacoplarnos de los repositorios que requiere Spring Data, y no usar anotaciones de Spring en la capa de aplicación,
debemos crear un fichero de configuración en el que registraremos nuestros servicios como beans de Spring,
para que se produzca la inyección de dependencias.

@Configuration
@ComponentScan(basePackages = "com.sstark.generalmarket")
public class BeanConfiguration {
    @Bean
    ProductService productService(final ProductRepository productRepository) {
        return new ProductService(productRepository);
    }

    @Bean
    PurchaseService purchaseService(final PurchaseRepository purchaseRepository) {
        return new PurchaseService(purchaseRepository);
    }

    @Bean
    CategoryService categoryService(final CategoryRepository categoryRepository) {
        return new CategoryService(categoryRepository);
    }
}