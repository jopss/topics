package com.jopss.topics.model;

import com.jopss.topics.util.Paginator;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.jopss.topics.util.Repository;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.persistence.TableGenerator;
import javax.persistence.TypedQuery;

@Entity
public class Topic extends Repository {

    private static final long serialVersionUID = 2255060059417187982L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "generatorName")
    @TableGenerator(name = "generatorName", allocationSize = 1)
    private Long id;
    
    @NotEmpty
    @NotNull
    private String description;

    public static Topic findById(Long id){
        return Topic.findById(Topic.class, id);
    }
    
    public static List<Topic> findAll(Paginator paginator){
        return findAllWithPaginator(Topic.class, paginator, "dateCreated");
    }
    
    public Set<Comment> searchHierarchy(){
        
        Set<Comment> result = new LinkedHashSet<Comment>();
        
        TypedQuery<Comment> q = getConnection().createQuery("FROM Comment c WHERE c.topic.id = :topicId AND c.reference is null ORDER BY c.dateCreated", Comment.class);
        q.setParameter("topicId", this.getId());
        
        int count = 1;
        for(Comment comment : q.getResultList()){
            
            comment.setNivel(""+count);
            result.add(comment);
            addRecursive(comment, result, ""+count, 1);
            
            count++;
        }
        
        return result;
    }
    
    private void addRecursive(Comment comment, Set<Comment> result, String suf, int nivel){
        
        if(comment!=null){
            TypedQuery<Comment> q2 = getConnection().createQuery("FROM Comment c WHERE c.reference.id = :referenceId", Comment.class);
            q2.setParameter("referenceId", comment.getId());

            for(Comment ref : q2.getResultList()){
                ref.setNivel(suf+"."+nivel);
                result.add(ref);
                addRecursive(ref, result, suf+"."+nivel, 1);
                nivel++;
            }
        }
    }

    @Override
    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
