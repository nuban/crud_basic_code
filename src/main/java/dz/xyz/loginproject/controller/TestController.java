package dz.xyz.loginproject.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import dz.xyz.loginproject.entity.Test;
import dz.xyz.loginproject.service.TestService;
import dz.xyz.loginproject.vo.ResultVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("test")
@Api(tags = "TestController接口")
public class TestController{
    /**
     * 服务对象
     */
    @Autowired
    private TestService testService;

    /**
     * 分页查询所有数据
     */
    @GetMapping
    @ApiOperation(value = "查询所有")
    public ResultVo selectAll(Page<Test> page) {
    	Page<Test> result = testService.page(page);
        return ResultVo.Success(result);
    }
    
     /**
     * 查询某条数据
     */
    @GetMapping("{id}")
    @ApiOperation(value = "查询某条数据")
    public ResultVo getOne(@PathVariable Long id) {
        Test result = testService.getById(id);
        return ResultVo.Success(result);
    }
     /**
     * 添加
     */
    @PostMapping()
    @ApiOperation(value = "添加记录")
    public ResultVo AddOne(@RequestBody Test dzTest) {
        boolean result = testService.save(dzTest);
        return result? ResultVo.Success("添加成功") : ResultVo.Fail("添加失败");
    }
    
     /**
     * 删除
     */
    @DeleteMapping("{id}")
    @ApiOperation(value = "删除一条记录")
    public ResultVo DeleteOne(@PathVariable Long id) {
        boolean result = testService.removeById(id);
        return result? ResultVo.Success("删除成功") : ResultVo.Fail("删除失败");
    }

}

