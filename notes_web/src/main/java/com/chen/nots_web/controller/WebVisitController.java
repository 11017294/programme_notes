package com.chen.nots_web.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chen.nots_web.entity.WebVisit;
import com.chen.nots_web.service.WebVisitService;
import com.chen.nots_web.vo.BaseResponse;
import com.chen.nots_web.vo.ResultUtils;
import com.chen.nots_web.vo.WebVisitVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * Web访问记录表 前端控制器
 * </p>
 *
 * @author MaybeBin
 * @since 2022-03-19
 */
@Api(value = "用户访问记录相关接口", tags = {"用户访问记录相关接口"})
@RestController
@RequestMapping("/webVisit")
public class WebVisitController {

    @Autowired
    private WebVisitService webVisitService;

    @ApiOperation(value = "获取用户访问列表", notes = "获取用户访问列表")
    @GetMapping("/getList")
    public BaseResponse<IPage> getList(WebVisitVO webVisitVO) {
        IPage<WebVisit> userPage = webVisitService.getPageList(webVisitVO);
        return ResultUtils.success(userPage);
    }
}
