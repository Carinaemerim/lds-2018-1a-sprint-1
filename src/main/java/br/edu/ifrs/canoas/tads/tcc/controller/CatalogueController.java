package br.edu.ifrs.canoas.tads.tcc.controller;

import br.edu.ifrs.canoas.tads.tcc.service.CatalogueService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@AllArgsConstructor
@RequestMapping("/catalogue")
public class CatalogueController {

    private final CatalogueService catalogueService;

    @GetMapping("/")
    public ModelAndView home(){
        return new ModelAndView("/document/catalogue");
    }


    @PostMapping("/search")
    public ModelAndView search(@RequestParam("criteria") String criteria){
        ModelAndView mav = new ModelAndView("/document/catalogue :: search-results");
        mav.addObject("termPapers", catalogueService.search(criteria));
        return mav;
    }


}
