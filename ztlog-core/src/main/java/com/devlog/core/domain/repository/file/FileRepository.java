package com.devlog.core.domain.repository.file;

import com.devlog.core.domain.entity.file.FileEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends CrudRepository<FileEntity, Long> {
}
