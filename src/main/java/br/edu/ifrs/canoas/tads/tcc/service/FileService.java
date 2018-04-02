package br.edu.ifrs.canoas.tads.tcc.service;

import br.edu.ifrs.canoas.tads.tcc.domain.File;
import br.edu.ifrs.canoas.tads.tcc.repository.FileRepository;
import lombok.AllArgsConstructor;

import java.io.IOException;
import java.util.Date;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@AllArgsConstructor
public class FileService {

    private final FileRepository fileRepository;

    public HttpEntity<byte[]>  download(Long id) {

        if (id == null)
            return null;

        File file = fileRepository.getOne(id);
        HttpEntity<byte[]> httpEntity = null;

        if (file.getContent() != null
                && file.getFilename() != null
                && file.getContentType() != null){

            byte[] documentBody = file.getContent();
            HttpHeaders header = new HttpHeaders();
            header.setContentType(MediaType.parseMediaType(file.getContentType()));
            header.set(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=" + file.getFilename());
            header.setContentLength(documentBody.length);
            httpEntity = new HttpEntity<byte[]>(documentBody, header);
        }

        return httpEntity;
    }

    public void save(File file) {

    	fileRepository.save(file);
    }

    public void saveMultipartFile(MultipartFile mFile) throws IOException {

    	File file = new File();
		file.setFilename(mFile.getOriginalFilename());
		file.setContent(mFile.getBytes());
		file.setContentType(mFile.getContentType());
		file.setCreatedOn(new Date());

		this.save(file);
    }

    public void deleteById(Long file) {

        fileRepository.deleteById(file);
    }

}
