package sia.five.tacocloud.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import sia.five.tacocloud.domain.Ingredient;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class JdbcIngredientRepository implements IngredientRepository {

    private static final String INGREDIENT_QUERY = "select ID, NAME, TYPE from INGREDIENT";

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcIngredientRepository(JdbcTemplate template) {
        this.jdbcTemplate = template;
    }

    @Override
    public Iterable<Ingredient> findAll() {
        return jdbcTemplate.query(INGREDIENT_QUERY, this::mapRowToIngredient);
    }

    @Override
    public Ingredient findOne(String id) {
        return jdbcTemplate.queryForObject(INGREDIENT_QUERY + " where ID = ?", this::mapRowToIngredient, id);
    }

    @Override
    public Ingredient save(Ingredient ingredient) {
        jdbcTemplate.update(
                "insert into INGREDIENT (ID, NAME, TYPE) values (?, ?, ?)",
                ingredient.getId(),
                ingredient.getName(),
                ingredient.getType().toString()
        );
        return ingredient;
    }

    private Ingredient mapRowToIngredient(ResultSet rs, int rowNum) throws SQLException {
        return new Ingredient(
                rs.getString("ID"),
                rs.getString("NAME"),
                Ingredient.Type.valueOf(rs.getString("TYPE"))
        );
    }

}
