package com.djh.example;

import com.djh.example.comparison.ComparisonClient;
import com.djh.example.postcode.Postcode;
import com.djh.example.postcode.PostcodeClient;
import com.djh.example.render.TemplateRenderer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author David Hancock
 */
@Controller
@RequiredArgsConstructor
public class AppController {

    private final TemplateRenderer templateRenderer;
    private final PostcodeClient postcodeClient;
    private final ComparisonClient comparisonClient;

    @ModelAttribute("navTemplate")
    public String navTemplate() {
        List<String> navItems = Arrays.asList("Home", "Postcodes", "Inline");
        return templateRenderer.renderTemplate("nav", navItems);
    }

    @GetMapping("/")
    public String base() {
        return "redirect:/home";
    }

    @GetMapping("/home")
    public String home(Model model) {

        Map<String, Object> comparisonData = comparisonClient.retrieveComparisonsFor("foo");

        String renderedComparisonWidget = templateRenderer.renderTemplate("comparison-widget", comparisonData);
        String renderedComparisonTable = templateRenderer.renderTemplate("comparison-table", comparisonData);

        model.addAttribute("comparisonWidgetTemplate", renderedComparisonWidget);
        model.addAttribute("comparisonTableTemplate", renderedComparisonTable);

        return "content/home";
    }

    @GetMapping("/postcodes")
    public String postcodes(Model model) {

        List<Postcode> postcodes = postcodeClient.retrievePostcodesFor("foo");
        String renderedPostcodeTable = templateRenderer.renderTemplate("postcode", postcodes);
        model.addAttribute("postcodeTemplate", renderedPostcodeTable);

        return "content/postcodes";
    }

    @GetMapping("/inline")
    public String magic() {
        return "<div><h1 style=\"text-align: center\">Some Awesome Inline Content</h1></div>";
    }

}
