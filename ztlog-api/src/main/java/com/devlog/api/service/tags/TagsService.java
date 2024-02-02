package com.devlog.api.service.tags;

import com.devlog.core.domain.repository.tags.TagsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class TagsService {

    private final TagsRepository tagsRepository;

}
