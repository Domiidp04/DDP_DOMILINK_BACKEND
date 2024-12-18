Aqui van los servicios normales de siempre :

Definimos los metodos que tenemos en el repository
EJ:

public class CategoryService {
    private final CategoryRepository repository;

    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    public List<String> getAllCategories() {
        return repository.getAllCategories();
    }

    public List<CategoryWithIdDto> getAllCategoriesWithId() {
        return repository.getAllCategoriesWithId();
    }

    public Category save(Category category) {
        return repository.save(category);
    }

    public Optional<CategoryDto> updateCategoryState(CategoryToUpdateDto categoryToUpdateDto) {
        return repository.updateState(categoryToUpdateDto);
    }

    public List<CategoryWithNumberOfProductsDto> getTheCategoriesWithMoreProducts(Integer numberOfCategories) {
        return repository.getTheCategoriesWithMoreProducts(2);
    }
}