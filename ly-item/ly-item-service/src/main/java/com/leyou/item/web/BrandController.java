package com.leyou.item.web;

import com.leyou.common.vo.PageResult;
import com.leyou.item.pojo.Brand;
import com.leyou.item.pojo.Category;
import com.leyou.item.service.BrandService;
import com.leyou.item.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("brand")
@Slf4j
public class BrandController {
    @Autowired
    private BrandService brandService;

    /**
     * 根据父节点id查询商品分类
     * @param
     * @return
     */
    @GetMapping("page")
    public ResponseEntity<PageResult<Brand>> queryBrandByPage(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                                              @RequestParam(value = "rows", defaultValue = "5") Integer rows,
                                                              @RequestParam(value = "sortBy", required = false) String sortBy,
                                                              @RequestParam(value = "desc", defaultValue = "false") Boolean desc,
                                                              @RequestParam(value = "key", required = false) String key){//required = false表示可以不传值,不传值时为null
        PageResult<Brand> result=brandService.queryBrandByPage(page,rows,sortBy,desc,key);
        return ResponseEntity.ok(result);
    }

    /**
     * 新增品牌
     * //@param brand
     * //@param cids
     * @return
     */
    @PostMapping("saveBrand")

    //Brand brand,@RequestParam("cids")List<Long> cids
    public ResponseEntity<Void> saveBrand(Brand brand,@RequestParam("cids")List<Long> cids){
        log.info("走saveBrand,切克闹");
        log.info("brand的值为: "+brand.toString());
        log.info("cids的值为: "+cids.toString());
        //log.debug("cids=",cids.toString());
        brandService.saveBrand(brand,cids);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @PostMapping("upload")
    public ResponseEntity<Void> upload(@RequestParam("file")MultipartFile file){
        log.info("走upload,切克闹");
        log.info("file的名字为: "+file.getOriginalFilename());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @GetMapping("cid/{cid}")
    public ResponseEntity<List<Brand>> queryBrandByCid(@PathVariable("cid") Long cid){
        return ResponseEntity.ok(brandService.queryBrandByCid(cid));
    }
}
