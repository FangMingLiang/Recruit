package com.mingliang.recruit.service.impl;

import com.mingliang.recruit.mapper.PositionMapper;
import com.mingliang.recruit.model.Position;
import com.mingliang.recruit.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PositionServiceImpl implements PositionService {
    @Autowired
    private PositionMapper positionMapper;
    @Override
    public void AddPosition(Position position) {
        positionMapper.insert(position);
    }

    @Override
    public Position PositionInformation(Integer positionid) {
        return positionMapper.selectByPrimaryKey(positionid);
    }

    @Override
    public List<Position> SelectAllPositions() {
        return positionMapper.selectAllPositions();
    }

    @Override
    public List<Position> SearchPositionName(String PositionName) {
        return positionMapper.SearchPositionName(PositionName);
    }


}
