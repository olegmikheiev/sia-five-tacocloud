package sia.five.tacocloud.data;

import sia.five.tacocloud.domain.Ingredient;

public interface IngredientRepository {

    Iterable<Ingredient> findAll();

    Ingredient findOne(String id);

    Ingredient save(Ingredient ingredient);

}
