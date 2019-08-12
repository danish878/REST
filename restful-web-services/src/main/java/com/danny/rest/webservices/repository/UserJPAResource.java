package com.danny.rest.webservices.repository;

import com.danny.rest.webservices.dto.Post;
import com.danny.rest.webservices.dto.User;
import com.danny.rest.webservices.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping("/jpa/users")
public class UserJPAResource {

    @Autowired
    private UserRepository repository;

    @Autowired
    private PostRepository postRepository;

    @GetMapping
    public List<User> retrieveAllUsers() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Resource<User> retrieveUser(@PathVariable("id") int id) {
        Optional<User> user = repository.findById(id);

        if (!user.isPresent())
            throw new UserNotFoundException("id - " + id + " not found");

        Resource<User> resource = new Resource<>(user.get());

        ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());

        resource.add(linkTo.withRel("all-users"));

        return resource;
    }

    @PostMapping
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
        User savedUser = repository.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") int id) {
        Optional<User> user = repository.findById(id);

        if (!user.isPresent())
            throw new UserNotFoundException("id - " + id + " not found");

        repository.deleteById(id);
    }

    @GetMapping("/{id}/posts")
    public List<Post> retrieveAllUsers(@PathVariable("id") int id) {
        Optional<User> user = repository.findById(id);

        if (!user.isPresent())
            throw new UserNotFoundException("id - " + id + " not found");

        return user.get().getPosts();
    }

    @PostMapping("/{id}/posts")
    public ResponseEntity<Object> createPost(@PathVariable("id") int id, @RequestBody Post post) {
        Optional<User> user = repository.findById(id);

        if (!user.isPresent())
            throw new UserNotFoundException("id - " + id + " not found");

        User tempUser = user.get();

        post.setUser(tempUser);

        postRepository.save(post);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(post.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

}
