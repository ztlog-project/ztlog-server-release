package com.devlog.core.entity.content;

import lombok.*;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ContentTagPK {

    private Long tags;
    private Long contents;
}
