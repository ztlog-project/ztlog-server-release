package com.devlog.admin.dto.stats.request;

import com.google.api.services.searchconsole.v1.model.ApiDataRow;
import lombok.*;

import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
public class ViewRawDataReqDto {

    private LocalDate viewDt;   // VIEW_DT
    private Long ctntNo;        // CTNT_NO
    private Long viewCnt;       // VIEW_CNT

    public static ViewRawDataReqDto of(ApiDataRow row, LocalDate viewDt, String pageUrl, Long ctntNo) {
        return ViewRawDataReqDto.builder()
                .viewDt(viewDt)
                .ctntNo(ctntNo)
                .viewCnt(row.getClicks().longValue())
                .build();
    }
}
