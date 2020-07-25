package com.dionysos.api.timehistory.service;

import com.dionysos.api.timehistory.dto.RequestSaveTimeHistoryDto;
import com.dionysos.api.timehistory.dto.RequestUpdateTimeHistoryDto;
import com.dionysos.api.timehistory.dto.ResponseTimeHistoryDto;
import com.dionysos.api.timehistory.entity.TimeHistory;
import com.dionysos.api.timehistory.repository.TimeHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class TimeHistoryService {

    private final TimeHistoryRepository timeHistoryRepository;

    @Transactional
    public void create(RequestSaveTimeHistoryDto requestSaveTimeHistoryDto) {
        timeHistoryRepository.save(requestSaveTimeHistoryDto.toEntity());
    }

    @Transactional
    public void update(Long id, RequestUpdateTimeHistoryDto requestUpdateTimeHistoryDto) {
        TimeHistory timeHistory = timeHistoryRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("해당 timeHistory가 없습니다. id=" + id));
        timeHistory.update(requestUpdateTimeHistoryDto.getDuration());
    }

    public ResponseTimeHistoryDto findById(Long id) {
        TimeHistory entity = timeHistoryRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 timeHistory가 없습니다. id=" + id));

        return new ResponseTimeHistoryDto(entity);
    }
}
