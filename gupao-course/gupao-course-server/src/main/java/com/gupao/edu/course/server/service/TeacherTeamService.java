package com.gupao.edu.course.server.service;

import com.gupao.edu.course.client.resp.TeacherTeam;

import java.util.Collection;
import java.util.List;

/**
 * todo 待删除 已经换为 lecture
 * @Author zhangpan@centaline.com.cn
 * @Date 2020/4/15 15:33
 */
public interface TeacherTeamService {
    Collection<TeacherTeam> listByIds(List<String> asList);
}
