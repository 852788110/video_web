package com.liu.nosql.mongodb.repository;

import com.liu.nosql.mongodb.document.Category;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CategoryRepository extends MongoRepository<Category,String> {

}
