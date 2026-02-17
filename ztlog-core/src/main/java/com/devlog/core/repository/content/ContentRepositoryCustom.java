package com.devlog.core.repository.content;

import com.devlog.core.common.enumulation.SearchType;
import com.devlog.core.entity.content.Content;
import org.springframework.data.domain.Page;

public interface ContentRepositoryCustom {

    Page<Content> findContentsByCondition(SearchType type, String param, int page);
}
