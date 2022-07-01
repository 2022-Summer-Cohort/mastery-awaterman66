package com.survivingcodingbootcamp.blog.controller;

import com.survivingcodingbootcamp.blog.model.Hashtag;
import com.survivingcodingbootcamp.blog.model.Post;
import com.survivingcodingbootcamp.blog.repository.HashtagRepository;
import com.survivingcodingbootcamp.blog.repository.PostRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequestMapping("/hashtags")
public class HashtagController {

    private HashtagRepository hashtagRepo;

    private PostRepository postRepo;

    public HashtagController(HashtagRepository hashtagRepo, PostRepository postRepo) {
        this.hashtagRepo = hashtagRepo;
        this.postRepo = postRepo;
    }

    @RequestMapping("/")
    public String displayAllHashtags(Model model){
        model.addAttribute("hashtags", hashtagRepo.findAll());
        return "all-hashtag-template";
    }

    @RequestMapping("/{id}")
    public String displaySingleHashtag(@PathVariable long id, Model model){
        model.addAttribute("hashtag", hashtagRepo.findById(id).get());
        return "single-hashtag-template";
    }

//    @RequestMapping("/{id}/addHashtag")
//    public String addHashtag(@PathVariable Long id, @RequestParam String hashtag) {
//        Post post = postRepo.findById(id).get();
//        if (hashtag.charAt(0) != '#') {
//            hashtag = "#" + hashtag;
//        }
//        Optional<Hashtag> hashtagOptional = hashtagRepo.findByNameIgnoreCase(hashtag);
//        if (hashtagOptional.isPresent()) {
//            if (!post.getHashtags().contains(hashtagOptional.get())) {
//                post.addHashtag(hashtagOptional.get());
//            }
//        } else {
//            Hashtag hashtag1 = new Hashtag();
//            hashtagRepo.save(hashtag1);
//            post.addHashtag(hashtag1);
//        }
//        postRepo.save(post);
//        return "redirect:/posts/" + id;
//    }
}
