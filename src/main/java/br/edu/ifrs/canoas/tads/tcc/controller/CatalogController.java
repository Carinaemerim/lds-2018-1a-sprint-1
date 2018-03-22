package br.edu.ifrs.canoas.tads.tcc.controller;

import br.edu.ifrs.canoas.tads.tcc.domain.TermPaper;
import br.edu.ifrs.canoas.tads.tcc.service.CatalogService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@AllArgsConstructor
@RequestMapping("/catalog")
public class CatalogController {

    private final CatalogService catalogService;

    @GetMapping("/")
    public ModelAndView home(){
        return new ModelAndView("/document/catalog");
    }


    @PostMapping("/search")
    public ModelAndView search(@RequestParam("criteria") String criteria){
        ModelAndView mav = new ModelAndView("/document/catalog :: search-results");
        mav.addObject("termPapers", catalogService.search(criteria));
        return mav;
    }

}
