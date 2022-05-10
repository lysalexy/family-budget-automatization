package com.example.demo.repository;

import com.example.demo.model.Article;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArticleRepository extends CrudRepository<Article,Integer>
{
    @Modifying
    @Query("UPDATE Article art SET art.name= :newName WHERE art.name = :oldName ")
    void renameArticle(@Param("newName") String newName, @Param("oldName") String oldName);

    @Modifying
    @Query("UPDATE Article art SET art.isValid= false " +
        " WHERE art.name = :name")
    void setIsValidToFalse(@Param("name") String name);


    @Modifying
    @Query("UPDATE Article art SET art.isValid= true " +
            " WHERE art.name = :name")
    void setIsValidToTrue(@Param("name") String name);

    Optional<Article> findByIdAndIsValidTrue(Integer id);

    Optional<Article> findByName(String name);
    Optional<Article> findByNameAndIsValidTrue(String name);
    Optional<Article> findByNameAndIsValidFalse(String name);
    List<Article> findAllByIsValidTrue();
}