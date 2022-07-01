package com.survivingcodingbootcamp.blog.controller;

import com.survivingcodingbootcamp.blog.model.Hashtag;
import com.survivingcodingbootcamp.blog.model.Post;
import com.survivingcodingbootcamp.blog.repository.HashtagRepository;
import com.survivingcodingbootcamp.blog.repository.PostRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequestMapping("/posts")
public class PostController {
    private PostRepository postRepo;

    private HashtagRepository hashtagRepo;

    public PostController(PostRepository postRepo, HashtagRepository hashtagRepo) {
        this.postRepo = postRepo;
        this.hashtagRepo = hashtagRepo;
    }

    @GetMapping("/{id}")
    public String displaySinglePost(@PathVariable long id, Model model) {
        model.addAttribute("post", postRepo.findById(id).get());
        return "single-post-template";
    }
    @RequestMapping("/{id}/addHashtag")
    public String addHashtag(@PathVariable Long id, @RequestParam String hashtag) {
        Post post = postRepo.findById(id).get();
        if (hashtag.charAt(0) != '#') {
            hashtag = "#" + hashtag;
        }
        Optional<Hashtag> hashtagOptional = hashtagRepo.findByNameIgnoreCase(hashtag);
        if (hashtagOptional.isPresent()) {
            if (!post.getHashtags().contains(hashtagOptional.get())) {
                hashtagOptional.get().addPost(post);
                hashtagRepo.save(hashtagOptional.get());
            }
        } else {
            Hashtag hashtag8 = new Hashtag(hashtag, post);
            hashtagRepo.save(hashtag8);
        }
        return "redirect:/posts/" + id;
    }


}
