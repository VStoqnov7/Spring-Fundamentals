package com.example.spotifyPlaylist.controller;

import com.example.spotifyPlaylist.model.dtos.SongAddDTO;
import com.example.spotifyPlaylist.model.entity.Song;
import com.example.spotifyPlaylist.model.enums.StyleName;
import com.example.spotifyPlaylist.service.SongService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/home")
public class SongController {

    private final SongService songService;

    public SongController(SongService songService) {
        this.songService = songService;
    }

    @GetMapping()
    public ModelAndView home(ModelAndView model){
        List<Song> popSongs = this.songService.findAllPopSongs();
        List<Song> rockSongs = this.songService.findAllRockSongs();
        List<Song> jazzSongs = this.songService.findAllJazzSongs();
        List<Song> mySongs = this.songService.findAllMySongs();
        model.addObject("popSongs",popSongs);
        model.addObject("rockSongs",rockSongs);
        model.addObject("jazzSongs",jazzSongs);
        model.addObject("mySongs",mySongs);
        model.setViewName("home");
        return model;
    }

    @GetMapping("/addSong")
    public ModelAndView addSong(ModelAndView model){
        model.addObject("styleNameValues", StyleName.values());
        model.setViewName("song-add");
        return model;
    }

    @ModelAttribute(name = "songAddDTO")
    public SongAddDTO songAddForm(){
        return new SongAddDTO();
    }


    @PostMapping("/addSong")
    public ModelAndView addTask(ModelAndView model,
                                @Valid SongAddDTO songAddDTO,
                                BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            model.addObject("styleNameValues", StyleName.values());
            model.setViewName("song-add");
            return model;
        }

        this.songService.saveSong(songAddDTO);
        model.setViewName("redirect:/home");
        return model;
    }

    @GetMapping("/addToMyPlaylist/{songId}")
    public ModelAndView addToMyPlaylist(@PathVariable Long songId, ModelAndView model){
        this.songService.addToMyPlaylist(songId);
        model.setViewName("redirect:/home");
        return model;
    }
}
