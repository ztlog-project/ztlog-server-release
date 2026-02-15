package com.devlog.admin.service.main;

import com.devlog.admin.mapper.main.MainDashBoardMapper;
import com.devlog.admin.dto.main.request.MainStatisticsDto;
import com.devlog.admin.dto.main.response.MainDashBoardResDto;
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
     *
     * @return 메인화면(대쉬보드) 정보
     */
    public MainDashBoardResDto getMainStatisticsInfo() {
        MainStatisticsDto dto = mainDashboardMapper.selectMainStatistics();
        return MainDashBoardResDto.of(dto);
    }

}
