package com.mingliang.recruit.service.impl;

import com.mingliang.recruit.mapper.DeliverMapper;
import com.mingliang.recruit.model.Deliver;
import com.mingliang.recruit.model.Position;
import com.mingliang.recruit.service.DeliverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliverServiceImpl implements DeliverService {
    @Autowired
    private DeliverMapper deliverMapper;
    @Override
    public void AddDeliver(Deliver deliver) {
        deliverMapper.insertSelective(deliver);
    }

    @Override
    public List<Position> DeliverList(String CandidateId) {
        return deliverMapper.FindDeliverList(CandidateId);
    }

    @Override
    public int DeliverStatus(String candidateId, String positionId) {
        return deliverMapper.FindDeliverStatus(candidateId,positionId);
    }

    @Override
    public List<Deliver> FindCandidateByPositionId(int positionid, String resultsign) {
        return deliverMapper.FindCandidateByPositionId(positionid,resultsign);
    }
}
