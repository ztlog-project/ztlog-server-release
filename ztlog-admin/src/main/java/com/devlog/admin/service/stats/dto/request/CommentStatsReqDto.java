package com.devlog.admin.service.stats.dto.request;

import com.devlog.admin.service.stats.dto.response.GiscusDataResDto;
import com.devlog.core.common.constants.CommonConstants;
import lombok.*;

import java.util.Optional;
import java.util.regex.Matcher;

@Getter
@NoArgsConstructor // 추가
@AllArgsConstructor
@Builder(access = AccessLevel.PRIVATE)
public class CommentStatsReqDto {
    private Long ctntNo;
    private Integer commentCnt;

    private static CommentStatsReqDto of(Long ctntNo, Integer commentCnt) {
        return CommentStatsReqDto.builder().ctntNo(ctntNo).commentCnt(commentCnt).build();
    }

    public static Optional<CommentStatsReqDto> convertToDto(GiscusDataResDto.Node node) {
        // 추출 로직은 서비스 내부 private 메서드로 관리
        Matcher matcher = CommonConstants.POST_ID_PATTERN.matcher(node.getTitle());
        if (matcher.find()) {
            return Optional.of(CommentStatsReqDto.of(Long.parseLong(matcher.group(1)), node.getComments().getTotalCount()));
        }
        return Optional.empty();
    }
}
