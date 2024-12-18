Las interfaces que extienden de JpaRepository
Es solo para utilizar jpa : findByUsername
Se utiliza en el adapter. Para definirlo.
@Repository
public interface CategoryJpaRepository extends JpaRepository<CategoryEntity, Integer> {
    @Query(value = "SELECT c.description FROM Category c", nativeQuery = true)
    List<String> getAllCategories();

    @Query(value = "UPDATE CategoryEntity SET state = :#{#categoryToUpdateDto.state()} WHERE categoryId = :#{#categoryToUpdateDto.id()}")
    @Modifying
    void updateCategoryState(@Param("categoryToUpdateDto")CategoryToUpdateDto categoryToUpdateDto);