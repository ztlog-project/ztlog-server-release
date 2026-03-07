package com.devlog.admin.service.stats;

import com.devlog.admin.dto.stats.request.ViewRawLogReqDto;
import com.devlog.admin.dto.stats.response.ViewRawDataDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ViewLogService {

    public void collectViewRawLogs(ViewRawLogReqDto reqDto) {

    }
}
