package com.devlog.admin.service.tags;

import com.devlog.admin.mapper.TagsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class TagsService {

    private final TagsMapper tagsMapper;
}
