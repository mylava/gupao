package com.gupao.edu.lotus.server.service.search;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gupao.edu.common.utils.JsonUtils;
import com.gupao.edu.lotus.server.service.search.index.UserIndex;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * demo
 */
@Service
public class DemoService {

    @Autowired
    private DocumentService documentService;

    private void test() throws IOException {
        for (int i = 0; i < 100; i++) {
            UserIndex userIndex = new UserIndex();
            userIndex.setId(i+1);
            userIndex.setUsername("Mic"+i);
            userIndex.setNikename("麦克"+i);
            userIndex.setAge(30);
            userIndex.setGender(1);
            userIndex.setGoodNum(100);
            userIndex.setCreatedTime(new Date());
            userIndex.setUpdatedTime(new Date());
            userIndex.setIsdelete(0);
            userIndex.setUserStatus(1);
            userIndex.setVipNum(888);
            documentService.save(userIndex);
        }


        UserIndex updateUser = new UserIndex();
        updateUser.setId(1);
        updateUser.setUsername("Mic1");
        updateUser.setNikename("麦克1");
        documentService.update(updateUser);

        UserIndex index = documentService.getById("1", UserIndex.class);
        System.out.println(index.toString());
        Date updatedTime = index.getUpdatedTime();
        System.out.println(updatedTime.getTime());

//        documentService.delete(updateUser);
        MatchQueryBuilder queryBuilder = QueryBuilders.matchQuery("username", "Mic1");
        List<UserIndex> userList = documentService.search(queryBuilder, UserIndex.class);
        userList.forEach(System.out::println);

        Page<UserIndex> page = new Page<>(1,20);
        IPage<UserIndex> iPage = documentService.searchPage(page, null, UserIndex.class);
        System.out.println(JsonUtils.obj2String(iPage));
    }
}
