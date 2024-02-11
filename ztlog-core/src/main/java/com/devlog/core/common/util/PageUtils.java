package com.devlog.core.common.util;

import com.devlog.core.common.constants.CommonConstants;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageRequest;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PageUtils {

    public static PageRequest getPageable(int page) {
        return PageRequest.of(page-1, CommonConstants.PAGE_SIZE);
    }

}
