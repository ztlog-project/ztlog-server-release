package com.devlog.core.domain.repository.tags;

import com.devlog.core.domain.entity.file.FileEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagsRepository extends CrudRepository<FileEntity, Long> {
}
