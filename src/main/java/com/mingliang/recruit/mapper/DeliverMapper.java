package com.mingliang.recruit.mapper;

import com.mingliang.recruit.model.Deliver;
import com.mingliang.recruit.model.Position;

import java.util.List;

public interface DeliverMapper {
    int deleteByPrimaryKey(Integer deliverid);

    int insert(Deliver record);

    int insertSelective(Deliver record);

    Deliver selectByPrimaryKey(Integer deliverid);

    int updateByPrimaryKeySelective(Deliver record);

    int updateByPrimaryKey(Deliver record);
    List<Position> FindDeliverList(String CandidateId);
    int FindDeliverStatus(String candidateId,String positionid);
    List<Deliver> FindCandidateByPositionId(int positionid,String resultsign);
    void ChangeSign(String candidateid,String positionid,String sign);
}