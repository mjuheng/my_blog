package com.heng.service;

import com.heng.entity.History;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service
public interface IHistoryService {
    void insertHistory(History history);

    HashMap<String,String> getAllHistory();
}
