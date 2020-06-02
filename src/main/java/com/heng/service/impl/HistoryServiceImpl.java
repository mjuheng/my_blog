package com.heng.service.impl;

import com.heng.entity.History;
import com.heng.mapper.HistoryMapper;
import com.heng.service.IHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;


@Service
public class HistoryServiceImpl implements IHistoryService {

    @Autowired
    private HistoryMapper historyMapper;

    @Override
    public void insertHistory(History history) {
        historyMapper.insertHistory(history);
    }

    @Override
    public HashMap<String, String> getAllHistory() {
        List<History> histories = historyMapper.getAllHistory();
        HashMap<String, String> historyHashMap = new HashMap<>();
        histories.forEach(history -> {
            historyHashMap.put(history.getIp(), history.getLocation());
        });
        return historyHashMap;
    }
}
