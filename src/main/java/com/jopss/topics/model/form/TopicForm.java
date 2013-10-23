package com.jopss.topics.model.form;

import com.jopss.topics.model.Topic;
import javax.validation.Valid;

public class TopicForm {
    
    @Valid
    private Topic topic;
    private String responseUser;

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public String getResponseUser() {
        return responseUser;
    }

    public void setResponseUser(String responseUser) {
        this.responseUser = responseUser;
    }
    
}
