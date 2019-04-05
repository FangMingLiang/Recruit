package com.mingliang.recruit.mapper;

import com.mingliang.recruit.model.Position;

import java.util.List;

public interface PositionMapper {
    int deleteByPrimaryKey(Integer positionid);

    int insert(Position record);

    int insertSelective(Position record);

    Position selectByPrimaryKey(Integer positionid);

    int updateByPrimaryKeySelective(Position record);

    int updateByPrimaryKeyWithBLOBs(Position record);

    int updateByPrimaryKey(Position record);
    List<Position> selectAllPositions();
    List<Position> SearchPositionName(String PositionName);
}