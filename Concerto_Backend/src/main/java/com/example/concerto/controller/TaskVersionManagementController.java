package com.example.concerto.controller;

import com.example.concerto.annotation.PassToken;
import com.example.concerto.pojo.TaskVersionInfo;
import com.example.concerto.response.CommonResponse;
import com.example.concerto.service.TaskVersionService;
import com.example.concerto.vo.TaskVersionInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ Author     ：aaagx.
 * @ Date       ：Created in 19:32 2021/6/10
 * @Version: 1.0$
 */
@RestController()
public class TaskVersionManagementController {

    @Autowired
    TaskVersionService taskVersionService;

    @PassToken
    @GetMapping("/Task/VersionInfo/{TaskId}")
    public CommonResponse getTaskVersionInfo(@PathVariable("TaskId") long taskId)
    {
        List<TaskVersionInfoVo> taskVersionInfoList = taskVersionService.getTaskVersionInfo(taskId);
        return new CommonResponse(200,"获取版本列表成功",taskVersionInfoList);
    }
}