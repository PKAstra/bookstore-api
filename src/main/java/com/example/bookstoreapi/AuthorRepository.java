package com.example.bookstoreapi;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    @Override
    @Transactional
    <S extends Author> S save(S entity);
}
