package com.devlog.core.common.service.repository.tags;

import com.devlog.core.common.service.entity.file.FileEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagsRepository extends CrudRepository<FileEntity, Long> {
}
