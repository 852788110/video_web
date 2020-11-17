package com.liu.service;

import com.liu.nosql.mongodb.document.Category;

import java.util.List;

public interface CategoryService {
    /*
    *   根据category得到category的名字
    * */
    Category get(String categoryId);

    /*
    *   得到全部的category
    * */
    List<Category> list();

    /*
    *   添加category
    * */
    int add(Category category);

    /*
    *   根据category的id删除category
    * */
    int delete(String categoryId);
}
