package com.devlog.core.common.service.repository.file;

import com.devlog.core.common.service.entity.file.FileEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends CrudRepository<FileEntity, Long> {
}
