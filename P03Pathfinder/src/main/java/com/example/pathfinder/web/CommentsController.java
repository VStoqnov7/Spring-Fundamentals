package com.example.pathfinder.web;

import com.example.pathfinder.models.dto.RouteDetailDTO;
import com.example.pathfinder.service.RouteService;
import com.example.pathfinder.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class CommentsController {

    private RouteService routeService;
    private final UserService userService;

    public CommentsController(RouteService routeService, UserService userService) {
        this.routeService = routeService;
        this.userService = userService;
    }

    @PostMapping("/routeComments/{routeId}")
    public ModelAndView commentRoute(@PathVariable String routeId, @RequestParam("message") String message, ModelAndView model){
        RouteDetailDTO route = this.routeService.findRouteById(routeId);
        boolean userIsLogin = this.userService.isLogin();
        model.addObject("route",route);
        model.addObject("isLogin", userIsLogin);
        if (message.isEmpty()){
            model.setViewName("route-details");
            return model;
        }
        this.routeService.addComment(routeId,message);
        model.setViewName("route-details");
        return model;

    }
}
