package com.leyou.item.web;

import com.leyou.item.pojo.SpecGroup;
import com.leyou.item.pojo.Specification;
import com.leyou.item.service.SpecifcationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("spec")
public class SpecifcationCrontroller {
    @Autowired
    private SpecifcationService specifcationService;

    @GetMapping("groups/{cid}")
    public ResponseEntity<List<SpecGroup>> queryGroupsByCid(@PathVariable("cid")Long cid){

        return ResponseEntity.ok(specifcationService.queryGroupsByCid(cid));
    }

    /**
     *
     * @param cid
     * @param gid
     * @return
     */
    @GetMapping("{cid}")
    public ResponseEntity<List<Specification>> queryGroupsByList(@PathVariable(value = "cid",required = false)Long cid,
                                                                 @PathVariable(value = "gid",required = false)Long gid,
                                                                 @RequestParam(value = "searching",required = false)Boolean searching){

        return ResponseEntity.ok(specifcationService.queryGroupsByList(cid,gid,searching));
    }
}
