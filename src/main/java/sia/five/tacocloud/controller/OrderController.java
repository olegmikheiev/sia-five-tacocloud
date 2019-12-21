package sia.five.tacocloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import sia.five.tacocloud.domain.Order;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/orders")
public class OrderController {

    private static final String ORDER_FORM_VIEW = "order/orderForm";
    private static final String REDIRECT_TO_THE_ROOT = "redirect:/";

    @GetMapping("/current")
    public String orderForm(Model model) {
        model.addAttribute("order", new Order());
        return ORDER_FORM_VIEW;
    }

    @PostMapping
    public String processOrder(@Valid Order order, Errors errors) {
        if (errors.hasErrors()) {
            log.warn("Found errors on submitting Order form:");
            log.warn("{}", errors);
            return ORDER_FORM_VIEW;
        }
        log.info("Processing order: {}", order);
        return REDIRECT_TO_THE_ROOT;
    }

}
