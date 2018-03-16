package br.edu.ifrs.canoas.tads.tcc.service;

import br.edu.ifrs.canoas.tads.tcc.domain.File;
import br.edu.ifrs.canoas.tads.tcc.repository.FileRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FileService {

    private final FileRepository fileRepository;

    public HttpEntity<byte[]> download(Long id) {
        File file = fileRepository.getOne(id);

        byte[] documentBody = file.getContent();

        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.parseMediaType(file.getContentType()));
        header.set(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=" + file.getFilename());
        header.setContentLength(documentBody.length);

        return new HttpEntity<byte[]>(documentBody, header);
    }

}
