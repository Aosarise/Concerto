package com.example.concerto.dao;

import com.example.concerto.pojo.TaskComment;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TaskCommentDao {

    /**
     * 添加任务评论/留言
     * @param tc
     * @return
     */
    int addComment(TaskComment tc);

    /**
     * 删除留言
     * @param taskCommentId
     * @return
     */
    int deletecomment(Long taskCommentId);

}
