package com.mingliang.recruit.service;

import com.mingliang.recruit.model.Position;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.List;

public interface PositionService {
    public void AddPosition(Position position);
    public Position PositionInformation(Integer positionid);
    public List<Position> SelectAllPositions();
    public List<Position> SearchPositionName(String PositionName);

}
