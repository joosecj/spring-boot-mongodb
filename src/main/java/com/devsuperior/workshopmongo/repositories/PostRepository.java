package com.devsuperior.workshopmongo.repositories;

import com.devsuperior.workshopmongo.entities.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {
}
