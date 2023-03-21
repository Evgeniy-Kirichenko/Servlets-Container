package ru.netology.repository;

import ru.netology.exception.NotFoundException;
import ru.netology.model.Post;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

// Stub
public class PostRepository {
    private final ConcurrentHashMap<Long, Post> posts;
    private final AtomicLong isCount = new AtomicLong(0L);

    public PostRepository() {
        posts = new ConcurrentHashMap<>();
    }

    public List<Post> all() {
        return new ArrayList<Post>(posts.values());
    }

    public Optional<Post> getById(long id) {
        return Optional.ofNullable(posts.get(id));
    }

    public Post save(Post post) {

        if (posts.containsKey(post.getId())) {
            posts.put(post.getId(), post);
        } else {
            var newId = isCount.incrementAndGet();
            post.setId(newId);
            posts.put(post.getId(), post);
        }
        return post;
    }

    public void removeById(long id) {
        if (posts.contains(id)) posts.remove(id);
        else throw new NotFoundException("ВВеден неверный id");
    }
}
