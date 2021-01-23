package de.structuremade.ms.homeworkservice.api.routes;

import com.google.gson.Gson;
import de.structuremade.ms.homeworkservice.api.json.CreateHomework;
import de.structuremade.ms.homeworkservice.api.json.EditHomework;
import de.structuremade.ms.homeworkservice.api.json.answer.GetMyHomework;
import de.structuremade.ms.homeworkservice.api.service.HomeworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/service/homework")
public class HomeworkRoute {

    @Autowired
    HomeworkService homeworkService;

    Gson gson = new Gson();

    @CrossOrigin
    @PostMapping("/create")
    public void create(@RequestBody CreateHomework ch, HttpServletResponse response, HttpServletRequest request){
        switch (homeworkService.create(ch, request.getHeader("Authorization").substring(7))){
            case 0 -> response.setStatus(HttpStatus.CREATED.value());
            case 1 -> response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            case 2 -> response.setStatus(HttpStatus.UNAUTHORIZED.value());
        }
    }

    @CrossOrigin
    @GetMapping("getmy")
    public Object getMy(HttpServletResponse response, HttpServletRequest request){
        GetMyHomework gmh = (GetMyHomework) homeworkService.getMy(request.getHeader("Authorization").substring(7));
        if (gmh != null){
            if (gmh.getHomework() != null){
                response.setStatus(HttpStatus.OK.value());
                return gson.toJson(gmh);
            }else response.setStatus(HttpStatus.UNAUTHORIZED.value());
        }else response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return null;
    }

    @CrossOrigin
    @DeleteMapping("/delete/{homeworkid}")
    public void delete(@PathVariable String homework, HttpServletResponse response, HttpServletRequest request){
        switch (homeworkService.delete(homework, request.getHeader("Authorization").substring(7))){
            case 0 -> response.setStatus(HttpStatus.OK.value());
            case 1 -> response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            case 2 -> response.setStatus(HttpStatus.UNAUTHORIZED.value());
        }
    }

    @CrossOrigin
    @PutMapping("/edit/{homeworkid}")
    public void edit(@PathVariable String homework, @RequestBody EditHomework eh, HttpServletResponse response, HttpServletRequest request){
        switch (homeworkService.edit(homework,eh,request.getHeader("Authorization").substring(7))){
            case 0 -> response.setStatus(HttpStatus.OK.value());
            case 1 -> response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            case 2 -> response.setStatus(HttpStatus.UNAUTHORIZED.value());
        }
    }

    @CrossOrigin()
    @PostMapping("/submit/{homeworkid}")
    public void submit(@PathVariable String homeworkid, HttpServletRequest request, HttpServletResponse response){
        switch (homeworkService.submit(request.getHeader("Authorization").substring(7), homeworkid)){
            case 0 -> response.setStatus(HttpStatus.CREATED.value());
            case 1 -> response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            case 2 -> response.setStatus(HttpStatus.UNAUTHORIZED.value());
        }
    }

}
