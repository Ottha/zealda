package com.pink.zealda.controller;


import com.pink.zealda.model.Quest;
import com.pink.zealda.service.QuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BackofficeRestController {

    @Autowired
    QuestService questService;

    @RequestMapping(value = "/quest", method = RequestMethod.POST, consumes = "application/json")
    public void createQuest(@RequestBody Quest quest) {
        questService.save(quest);
    }


}
