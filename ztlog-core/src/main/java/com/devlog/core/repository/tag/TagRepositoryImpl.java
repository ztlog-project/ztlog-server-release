package com.devlog.core.repository.tag;

import com.devlog.core.entity.tag.Tag;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import static com.devlog.core.entity.tag.QTag.tag;

@RequiredArgsConstructor
public class TagRepositoryImpl implements TagRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    @Override
    public Tag findTagById(Long id) {
        return queryFactory.selectFrom(tag)
                .where(tag.tagNo.eq(id))
                .fetchOne();
    }

}
