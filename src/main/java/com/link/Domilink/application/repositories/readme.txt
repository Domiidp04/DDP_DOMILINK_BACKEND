Aquí van las interfaces que solo ponemos lo que queremos :
EJ :
public interface CategoryRepository {
    List<String> getAllCategories();
    List<CategoryWithIdDto> getAllCategoriesWithId();
    Category save(Category category);

    Optional<CategoryDto> updateState(CategoryToUpdateDto categoryToUpdateDto);

    List<CategoryWithNumberOfProductsDto> getTheCategoriesWithMoreProducts(Integer numberOfCategories);
}