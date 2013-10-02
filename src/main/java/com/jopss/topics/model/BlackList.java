package com.jopss.topics.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.jopss.topics.util.Repository;
import com.jopss.topics.util.StringUtils;
import java.util.List;
import javax.persistence.TableGenerator;

/**
 * Objeto de suporte para palavras em que devem ser censuradas do conteudo do comentario.
 * Nao existe tela de cadastro, somente em base.
 */
@Entity
public class BlackList extends Repository {

	private static final long serialVersionUID = -7194862055167038787L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "generatorName")
	@TableGenerator(name = "generatorName", allocationSize = 1)
	private Long id;
	
	@NotEmpty
	@NotNull
	private String keyword;

        public static List<BlackList> findAll(){
            return findAll(BlackList.class);
        }
        
        /**
         * Dado uma mensagem, identifica e substitui as palavras chaves a serem censuradas.
         */
        public static String parse(String msg){
            if(msg!=null && msg.trim().length() > 0){
                
                msg = StringUtils.replaceSpecial(msg);
                
                List<BlackList> blacklists = findAll();
                for(BlackList bl : blacklists){
                    if(msg.toUpperCase().contains(bl.getKeyword().toUpperCase())){
                        msg = msg.replaceAll( bl.getKeyword(), getXs(bl.getKeyword()) );
                    }
                }
                
            }
            return msg;
        }
        
        private static String getXs(String msg){
            String result = "";
            for(int x = 0; x < msg.length(); x++){
                result+="X";
            }
            return result;
        }
	
        public Long getId() {
		return id;
	}

        public String getKeyword() {
            return keyword;
        }
	
}
