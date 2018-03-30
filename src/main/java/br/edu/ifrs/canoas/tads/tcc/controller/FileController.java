package br.edu.ifrs.canoas.tads.tcc.controller;

import br.edu.ifrs.canoas.tads.tcc.service.FileService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@AllArgsConstructor
@RequestMapping("/file")
public class FileController {

    private final FileService fileService;

    @GetMapping("/{id}")
    @ResponseBody
    public HttpEntity<byte[]> download(@PathVariable Long id) {
        return fileService.download(id);
    }

    @GetMapping("/delete/{id}")
    @ResponseBody
    public void delete(@PathVariable Long id) {
         fileService.deleteById(id);
    }
}
