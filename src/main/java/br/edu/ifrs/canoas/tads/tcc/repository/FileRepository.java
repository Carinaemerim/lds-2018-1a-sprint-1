package br.edu.ifrs.canoas.tads.tcc.repository;

import br.edu.ifrs.canoas.tads.tcc.domain.File;
import br.edu.ifrs.canoas.tads.tcc.domain.FileType;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File,Long>{

	List<File> findByFileType(FileType fileType);

}
