package com.mingliang.recruit.service;

import com.mingliang.recruit.model.Deliver;
import com.mingliang.recruit.model.Position;

import java.util.List;

public interface DeliverService {
    public void AddDeliver(Deliver deliver);
    public List<Position> DeliverList(String CandidateId);
    public int DeliverStatus(String candidateId,String positionId);
    public List<Deliver> FindCandidateByPositionId(int positionid,String resultsign);
    public void ChangeSign(String candidateid,String positionid,String sign);
}
