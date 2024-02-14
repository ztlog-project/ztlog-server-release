package com.devlog.core.entity.content;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ContentTagPK implements Serializable {

    @Serial
    private static final long serialVersionUID = 5120626339046399508L;

    private Long tags;
    private Long contents;
}
