package com.devlog.core.entity.content;

import jakarta.persistence.Id;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;


@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ContentTagPK implements Serializable {

    @Serial
    private static final long serialVersionUID = -8101983627354224421L;

    @Id
    private Long tags;

    @Id
    private Long contents;
}
