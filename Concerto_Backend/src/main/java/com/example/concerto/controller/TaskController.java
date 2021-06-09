package com.example.concerto.controller;

import com.example.concerto.annotation.PassToken;
import com.example.concerto.fo.*;
import com.example.concerto.pojo.TaskComment;
import com.example.concerto.response.CommonResponse;
import com.example.concerto.service.TaskService;
import com.example.concerto.vo.TaskVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;

/**
 * @author sarise
 * @version 1.0
 * @date 2021/4/29 上午9:34
 */
@Slf4j
@RestController
public class TaskController {
    @Autowired
    TaskService taskService;

    @PassToken
    @PutMapping(value = "/task")
    public CommonResponse createTask(@Valid @RequestBody AddTaskForm addTaskForm){
        Long taskId = taskService.createTask(addTaskForm);
        return new CommonResponse(200,"创建任务成功",taskId);
    }

    @PassToken
    //Long createMileStone(MileStoneForm mileStoneForm);
    @PutMapping(value = "/mileStone")
    public CommonResponse createMileStone(@Valid @RequestBody MileStoneForm mileStoneForm){
        Long taskId = taskService.createMileStone(mileStoneForm);
        return new CommonResponse(200,"创建里程碑成功",taskId);
    }

    @PassToken
    //Integer modifyTask(ModifyTaskForm modifyTaskForm);
    @PostMapping(value = "/task")
    public CommonResponse modifyTask(@Valid @RequestBody ModifyTaskForm modifyTaskForm){
        Integer taskVersion = taskService.modifyTask(modifyTaskForm);
        return new CommonResponse(200,"修改任务成功",taskVersion);
    }

    //TaskVo queryTask(Long taskId);
    @PassToken
    @GetMapping(value = "/task/{taskId}")
    public CommonResponse queryTask(@PathVariable(value="taskId") Long taskId){
        TaskVo taskVo = taskService.queryTask(taskId);
        return new CommonResponse(200,"查询成功",taskVo);
    }

    //Long addSubtask(SubtaskForm subtaskForm);
    @PassToken
    @PutMapping(value = "/subtask")
    public CommonResponse addSubtask(@Valid @RequestBody SubtaskForm subtaskForm){
        Long taskId = taskService.addSubtask(subtaskForm);
        return new CommonResponse(200,"添加子任务成功",taskId);
    }

    //void modifySubtask(ModifySubtaskForm modifySubtaskForm);
    @PassToken
    @PostMapping(value = "/subtask")
    public CommonResponse modifySubtask(@Valid @RequestBody ModifySubtaskForm modifySubtaskForm){
        taskService.modifySubtask(modifySubtaskForm);
        return new CommonResponse(200,"修改子任务成功","");
    }

    //void changeTaskStatus(Long taskId);
    @PassToken
    @PostMapping(value = "/task/status/{taskId}")
    public CommonResponse changeTaskStatus(@PathVariable(value="taskId") Long taskId){
        taskService.changeTaskStatus(taskId);
        return new CommonResponse(200,"修改任务状态成功","");
    }

    @PostMapping("/task/comment")
    public CommonResponse releaseComment(Long taskId, Long userId, String content){
        log.info("[taskID]:" + taskId);
        Date now = new Date();
        TaskComment tc = TaskComment.builder()
                .taskId(taskId)
                .taskCommentUserId(userId)
                .commentContent(content)
                .commentTime(now)
                .build();
        int result = taskService.addComment(tc);
        return new CommonResponse(200,"任务留言发布成功！","");
    }

    @DeleteMapping("/task/comment")
    public CommonResponse deleteComment(Long taskCommentId){
        int result = taskService.deleteComment(taskCommentId);
        return new CommonResponse(200,"删除留言成功！","");
    }
}
