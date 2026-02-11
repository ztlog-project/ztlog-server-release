package com.devlog.admin.service.main;

import com.devlog.admin.mapper.main.MainDashBoardMapper;
import com.devlog.admin.service.main.dto.MainDashBoardDto;
import com.devlog.admin.service.main.dto.MainDashBoardResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MainDashBoardService {

    private final MainDashBoardMapper mainDashboardMapper;

    public MainDashBoardResDto getMainStatusInfo() {
        MainDashBoardDto dto = new MainDashBoardDto();
        dto = mainDashboardMapper.getMainStatusInfo();

        MainDashBoardResDto resDto = new MainDashBoardResDto();

        return resDto;
    }

}
