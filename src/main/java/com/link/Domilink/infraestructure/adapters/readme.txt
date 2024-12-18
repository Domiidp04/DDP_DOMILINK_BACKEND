Aqui ponemos los adaptadores segun la base de datos que utilicemos.
Implementa el Repository en el que decimos lo que queremos

@Component
public class PostgresCategoryAdapter implements CategoryRepository {
    private final CategoryJpaRepository repository;
    private final CategoryMapper mapper;

    public PostgresCategoryAdapter(CategoryJpaRepository repository, CategoryMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<String> getAllCategories() {
        return repository.getAllCategories();
    }
}