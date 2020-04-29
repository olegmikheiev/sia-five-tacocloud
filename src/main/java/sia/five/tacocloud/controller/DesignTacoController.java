package sia.five.tacocloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import sia.five.tacocloud.data.IngredientRepository;
import sia.five.tacocloud.domain.Ingredient;
import sia.five.tacocloud.domain.Ingredient.Type;
import sia.five.tacocloud.domain.Taco;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignTacoController {

    private static final String DESIGN_VIEW = "design/design";
    private static final String REDIRECT_ORDERS_VIEW = "redirect:orders/current";

    private final IngredientRepository ingredientRepository;

    @Autowired
    public DesignTacoController(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @GetMapping
    public String showDesignForm(Model model) {

        List<Ingredient> ingredients = new ArrayList<>();
        ingredientRepository.findAll().forEach(ingredients::add);

        log.info("GET design form");
        Type[] types = Ingredient.Type.values();
        for (Type type : types) {
            model.addAttribute(
                    type.toString().toLowerCase(),
                    filterByType(ingredients, type));
        }
        model.addAttribute("taco", new Taco());
        return DESIGN_VIEW;
    }

    @PostMapping
    public String processDesign(@Valid Taco taco, Errors errors) {
        if (errors.hasErrors()) {
            log.warn("Found errors on submitting Taco Design form:");
            log.warn("{}", errors);
            return DESIGN_VIEW;
        }
        // TODO: Persist design in the storage
        log.info("Processing design: {}", taco);
        return REDIRECT_ORDERS_VIEW;
    }

    private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
        return ingredients.stream()
                .filter(ingredient -> ingredient.getType().equals(type))
                .collect(Collectors.toList());
    }


}
