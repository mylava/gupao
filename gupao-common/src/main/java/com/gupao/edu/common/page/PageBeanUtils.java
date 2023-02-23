package com.gupao.edu.common.page;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 〈〉
 *
 * @author yangshibo
 * @create 2020/3/23
 * @since 1.0.0
 */
public class PageBeanUtils<T> {

    public  static <T> PageBean<T>  PageToPageBean(Page<T> page){
        PageBean pageBean=new PageBean();
        pageBean.setCurrentPage((int)page.getCurrent());
        pageBean.setItems(page.getRecords());
        pageBean.setPageSize((int)page.getSize());
        pageBean.setTotalNum((int)page.getTotal());
        return pageBean;
    }

    /**
     * 转换分页对象   确保PageBean中的数据   t 如何 转 f  需要自行实现
     * @param pageBean 待转换分页对象
     * @param function 转换方法
     * @param <T> 待转换对象
     * @param <F> 目标对象
     * @return
     */
    public static <T,F> PageBean<F> transformPageBean(PageBean<T> pageBean, Function<T,F> function) {
        PageBean<F> resultPageBean = buildPageBean(pageBean);

        List<F> result = pageBean.getItems()
                .stream()
                .map(t -> function.apply(t))
                .collect(Collectors.toList());
        resultPageBean.setItems(result);
        return resultPageBean;
    }

    private static <T,F> PageBean<F> buildPageBean(PageBean<T> pageBean) {
        if (pageBean == null) {
            return null;
        }
        PageBean<F> result = new PageBean();
        result.setCurrentPage(pageBean.getCurrentPage());
        result.setPageSize(pageBean.getPageSize());
        result.setTotalNum(pageBean.getTotalNum());
        result.setIsMore(pageBean.getIsMore());
        result.setTotalPage(pageBean.getTotalPage());
        result.setStartIndex(pageBean.getStartIndex());
        return result;
    }

    //***************** 新增 IPage plus 自带对象转化

    /**
     * 将一个 分页对象 转化为另一个
     * @param page
     * @param function
     * @param <T>
     * @param <F>
     * @return
     */
    public static <T,F> Page<F> transPlusPageToAnotherPage(Page<T> page, Function<T,F> function) {
        Page<F> resultPageBean = buildPlusPage(page);
        List<F> result = page.getRecords()
                .stream()
                .map(t -> function.apply(t))
                .collect(Collectors.toList());
        resultPageBean.setRecords(result);
        return resultPageBean;
    }

    /**
     *
     * @param plusPage
     * @param <T> 被转化的对象
     * @param <F> 转化的目标对象
     * @return
     */
    private static <T,F> Page<F> buildPlusPage(Page<T> plusPage) {
        if (plusPage == null) {
            return null;
        }
        Page<F> fPage = new Page<>();
        //设置当前页
        fPage.setCurrent(plusPage.getCurrent());
        //设置页大小
        fPage.setSize(plusPage.getSize());
        //设置总数
        fPage.setTotal(plusPage.getTotal());
        //设置总页数
        fPage.setPages(plusPage.getPages());
        //设置开始页
        return fPage;
    }
}
