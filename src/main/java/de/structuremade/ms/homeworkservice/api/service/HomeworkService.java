package de.structuremade.ms.homeworkservice.api.service;

import de.structuremade.ms.homeworkservice.api.json.CreateHomework;
import de.structuremade.ms.homeworkservice.api.json.EditHomework;
import de.structuremade.ms.homeworkservice.api.json.answer.GetMyHomework;
import de.structuremade.ms.homeworkservice.util.JWTUtil;
import de.structuremade.ms.homeworkservice.util.database.entity.*;
import de.structuremade.ms.homeworkservice.util.database.repo.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class HomeworkService {

    private final Logger LOGGER = LoggerFactory.getLogger(HomeworkService.class);
    @Autowired
    LessonsRepo lessonsRepo;
    @Autowired
    HomeworkRepo homeworkRepo;
    @Autowired
    HomeworkDoneRepo homeworkDoneRepo;
    @Autowired
    UserRepo userRepo;
    @Autowired
    LessonSubstitutesRepo lessonSubstitutesRepo;
    @Autowired
    LessonrolesRepo lessonrolesRepo;
    @Autowired
    JWTUtil jwtUtil;

    @Transactional
    public int create(CreateHomework ch, String jwt) {
        Homework homework = new Homework();
        User user;
        Lessons lesson;
        try {
            lesson = lessonsRepo.getOne(ch.getLesson());
            if (lessonsRepo.existsByTeacherAndId((user = userRepo.getOne(jwtUtil.extractIdOrEmail(jwt))), ch.getLesson()) || lessonSubstitutesRepo.existsBySubstituteTeacherAndLesson(user,lesson)) {
                homework.setLesson(lesson.getLessonRoles());
            }else{
                return 2;
            }
            LOGGER.info("Set Homework variables");
            homework.setTitle(ch.getTitle());
            homework.setDescription(ch.getDescription());
            LOGGER.info("Format Date");
            homework.setValidTill(converter(ch.getDate()));
            homework.setCreator(user);
            homeworkRepo.save(homework);
            return 0;
        } catch (Exception e) {
            LOGGER.error("Couldn't create Homework", e.fillInStackTrace());
            return 1;
        }
    }

    public Object getMy(String jwt) {
        List<Homework> homework;
        try {
            if (jwtUtil.isTokenExpired(jwt)) {
                return new GetMyHomework();
            }
            homework = new ArrayList<>();
            User user = userRepo.getOne(jwtUtil.extractIdOrEmail(jwt));
            user.getLessonRoles().forEach(lr -> {
                homework.addAll(lr.getHomework());
            });
            return new GetMyHomework(homework);
        } catch (Exception e) {
            LOGGER.error("Couldn't get Homework from user", e.fillInStackTrace());
            return null;
        }
    }

    public int delete(String homework, String jwt) {
        try {
            if (!homeworkRepo.existsByCreatorAndId(userRepo.getOne(jwtUtil.extractIdOrEmail(jwt)), homework)){
                return 2;
            }
            LOGGER.info("Delete the homework");
            homeworkRepo.delete(homeworkRepo.getOne(homework));
            return 0;
        } catch (Exception e) {
            LOGGER.error("Couldn't delete Homework", e.fillInStackTrace());
            return 1;
        }
    }

    public int edit(String homework, EditHomework eh, String jwt) {
        Homework homeworkEdit;
        try {
            LOGGER.info("Try to get the Homework");
            if (!homeworkRepo.existsByCreatorAndId(userRepo.getOne(jwtUtil.extractIdOrEmail(jwt)), homework)){
                return 2;
            }
            homeworkEdit = homeworkRepo.getOne(homework);
            LOGGER.info("Got homework successfully and add the new Informations");
            if (eh.getTitle() != null) {
                homeworkEdit.setTitle(eh.getTitle());
            }
            if (eh.getDescription() != null) {
                homeworkEdit.setDescription(eh.getDescription());
            }
            if (eh.getDate() != null) {
                homeworkEdit.setValidTill(converter(eh.getDate()));
            }
            if (eh.getLesson() != null) {
                homeworkEdit.setLesson(lessonrolesRepo.getOne(eh.getLesson()));
            }
            LOGGER.info("Save homework");
            homeworkRepo.save(homeworkEdit);
            return 0;
        } catch (Exception e) {
            LOGGER.error("Couldn't edit homework",e.fillInStackTrace());
            return 1;
        }
    }

    public int submit(String jwt, String homework){
        String userId;
        Homework submitHomework;
        HomeworkDone homeworkDone = new HomeworkDone();
        try {
            LOGGER.info("Check if user have rights to submit the Homework");
            if (jwtUtil.isTokenExpired(jwt)) return 2;
           if(!userRepo.existsByIdAndLessonRoles((userId = jwtUtil.extractIdOrEmail(jwt)), (submitHomework = homeworkRepo.getOne(homework)).getLesson())){
               return 2;
           }
            homeworkDone.setHomework(submitHomework);
            homeworkDone.setUser(userRepo.getOne(userId));
            LOGGER.info("Save homework submit");
            homeworkDoneRepo.save(homeworkDone);
            return 0;
        }catch (Exception e){
            LOGGER.error("Couldn't submit Homework", e.fillInStackTrace());
            return 1;
        }
    }

    private Date converter(String date) {
        Calendar calendar = Calendar.getInstance();
        String[] splitDate = date.split("\\.");
        calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(splitDate[0]));
        calendar.set(Calendar.MONTH, Integer.parseInt(splitDate[1]));
        calendar.set(Calendar.YEAR, Integer.parseInt(splitDate[2]));
        return calendar.getTime();
    }
}
