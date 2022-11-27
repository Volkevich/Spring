package com.volkevich.InstaZoo.repository;

import com.volkevich.InstaZoo.entity.Post;
import com.volkevich.InstaZoo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {
    //SELECT * FROM POST as p WHERE User='user' SORT DESC
    List<Post> findAllByUserOrderByCreatedDateDesc(User user);

    List<Post> findAllByOrderByCreatedDateDesc();

    Optional<Post> findPostByIdAndUser(Long id, User user);
}
