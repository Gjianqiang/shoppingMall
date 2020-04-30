package com.leyou.item.service;

import com.leyou.common.enums.ExceptionEnums;
import com.leyou.common.exception.LyException;
import com.leyou.item.mapper.SpecGroupMapper;
import com.leyou.item.mapper.SpecificationMapper;
import com.leyou.item.pojo.SpecGroup;
import com.leyou.item.pojo.Specification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class SpecifcationService {

    @Autowired
    private SpecGroupMapper specGroupMapper;
    @Autowired
    private SpecificationMapper specificationMapper;

    public List<SpecGroup> queryGroupsByCid(Long cid) {
        SpecGroup group=new SpecGroup();
        group.setCid(cid);
        List<SpecGroup> list = specGroupMapper.select(group);
        if(CollectionUtils.isEmpty(list)){
            //没查到
            throw new LyException(ExceptionEnums.SPEC_GROUP_NOT_FOUND);
        }
        return list ;
    }

    public List<Specification> queryGroupsByList(Long cid, Long gid, Boolean searching) {
        Specification specification=new Specification();
        //specification.setGroupId(gid);
        //specification.setSearching(searching);
        specification.setCategoryId(cid);
        List<Specification> list = specificationMapper.select(specification);
        if(CollectionUtils.isEmpty(list)){
            //没查到
            throw new LyException(ExceptionEnums.SPEC_GROUP_NOT_FOUND);
        }
        return list ;
    }


}
