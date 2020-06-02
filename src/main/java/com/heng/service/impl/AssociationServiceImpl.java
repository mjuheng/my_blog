package com.heng.service.impl;

import com.heng.mapper.AssociationMapper;
import com.heng.service.IAssociationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AssociationServiceImpl implements IAssociationService {

    @Autowired
    private AssociationMapper associationMapper;

    @Override
    public void saveAssociation(Integer aid, List<Integer> cidList) {
        associationMapper.saveAssociation(aid,cidList);
    }

    @Override
    public List<Integer> listCidByAid(Integer aid) {

        return associationMapper.listCidByAid(aid);
    }

    @Override
    public List<Integer> listAidByCid(Integer cid) {
        return associationMapper.listAidByCid(cid);
    }

    @Override
    public void deleteCidByAid(Integer aid) {
        associationMapper.deleteCidByAid(aid);
    }
}
