package com.jopss.topics.model;

import com.jopss.topics.exception.ValidatingException;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.jopss.topics.util.Repository;
import javax.persistence.TableGenerator;

@Entity
public class Comment extends Repository {

    private static final long serialVersionUID = -821603216119988496L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "generatorName")
    @TableGenerator(name = "generatorName", allocationSize = 1)
    private Long id;

    @NotEmpty
    @NotNull
    private String content;
    
    @ManyToOne
    private Comment reference;
    
    @ManyToOne
    private Topic topic;
    
    private transient String nivel;
    
    public Comment prepareToSaveReference(Long referenceId) {
        Comment ref = Comment.findById(Comment.class, referenceId);
        this.setReference(ref);
        this.setTopic(ref.getTopic());
        return this;
    }
    
    public Comment prepareToSaveTopic(Long topicId) {
        Topic topic = Topic.findById(topicId);
        this.setReference(null);
        this.setTopic(topic);
        return this;
    }
    
    public Comment save() throws ValidatingException {
        
        this.setContent( BlackList.parse(this.getContent()) );
        return (Comment) super.save();
    }
    
    @Override
    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public Comment getReference() {
        return reference;
    }

    public void setReference(Comment reference) {
        this.reference = reference;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

}

