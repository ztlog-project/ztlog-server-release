package com.devlog.core.domain.entity.content;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContentTagsPKEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 5120626339046399508L;

    private Long tags;
    private Long contents;
}
