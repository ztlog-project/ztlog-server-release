package com.devlog.admin.service.main;

import com.devlog.admin.mapper.main.MainDashBoardMapper;
import com.devlog.admin.dto.main.request.MainStatisticsDto;
import com.devlog.admin.dto.main.response.MainDashBoardResDto;
import com.devlog.core.common.enumulation.ResponseCode;
import com.devlog.core.config.exception.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MainDashBoardService {

    private final MainDashBoardMapper mainDashboardMapper;

    /**
     * 메인화면 대쉬보드 통계 조회
     *
     * @return 메인화면(대쉬보드) 정보
     */
    public MainDashBoardResDto getMainStatisticsInfo() {
        MainStatisticsDto mainStatisticsDto = mainDashboardMapper.selectMainStatistics()
                .orElseThrow(() -> new DataNotFoundException(ResponseCode.NOT_FOUND_DATA.getMessage()));
        return MainDashBoardResDto.of(mainStatisticsDto);
    }

}
