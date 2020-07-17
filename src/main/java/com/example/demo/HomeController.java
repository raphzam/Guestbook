package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
public class HomeController {
    ArrayList<Post> listOfPosts = new ArrayList<Post>();


    @RequestMapping("/")
    public String loadHomePage(Model model){
        model.addAttribute("listOfPosts", listOfPosts);
        return "home";

    }

    @RequestMapping("/add")
    public String newPost(Model model){
        model.addAttribute("post", new Post());
        return "newpost";
    }

    @PostMapping("/confirmpost")
    public String confirmPost(@ModelAttribute Post post, Model model){

        listOfPosts.add(post);
        model.addAttribute("listOfPosts", listOfPosts);
        return "redirect:/";
    }

    @RequestMapping("/view/{name}")
    public String viewPost(@PathVariable("name") String name, Model model){
        for (Post post : listOfPosts){
            if(post.getName().equals(name)){
                model.addAttribute("post", post);
                break;
            }
        }
        return "viewpost";
    }

    @RequestMapping("/delete/{name}")
    public String deletePost(@PathVariable("name") String name, Model model){
        for (Post post : listOfPosts){
            if(post.getName().equals(name)){
                listOfPosts.remove(post);
                break;
            }
        }
        return "redirect:/";
    }

    @RequestMapping("/update/{name}")
    public String updatePost(@PathVariable("name") String name, Model model){
    Post editedPost = new Post();
        for (Post post : listOfPosts){
            if(post.getName().equals(name)){
                editedPost = post;
                listOfPosts.remove(post);
                break;
            }
        }
        model.addAttribute("post", editedPost);
        return "newpost";
    }


    @GetMapping("/welcome")
    public String welcomePage(){
        return "welcome";
    }

    @RequestMapping("/like/{name}")
    public String likePost(@PathVariable("name") String name, Model model){
        for (Post post : listOfPosts){
            if(post.getName().equals(name)){
                post.setLiked(true);
            }
        }
        return "redirect:/";
    }


}
