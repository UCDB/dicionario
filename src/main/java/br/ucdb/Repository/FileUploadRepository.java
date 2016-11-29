package br.ucdb.Repository;

import br.ucdb.model.FileUpload;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileUploadRepository extends JpaRepository<FileUpload, Integer>{

	FileUpload findByFileName(String filename);

}
