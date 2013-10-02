package com.jopss.topics.util;

import java.io.Serializable;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

/**
 * Objeto suporte para paginacoes, com dados de pagina atual, total de resultados e metodos de acoes.
 * Auxilia a paginacao real dos dados.
 */
@Component
@Scope(value="session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Paginator implements Serializable {

	private static final long serialVersionUID = -665198335930955704L;

	private static final Integer MAX_BY_PAGE = 10;
	
	private Integer currentPage = 1;
	private Integer countResults = 1;
	
	public Integer getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		if(currentPage == null || currentPage < 1){
			currentPage = 1;
		}
		this.currentPage = currentPage;
	}
	public Integer getCountResults() {
		return countResults;
	}
	public void setCountResults(Integer countResults) {
		this.countResults = countResults;
	}
	
	//metodos utilizados pela paginacao para suporte.
	
	public Integer getPageCount() {
		return (int) Math.ceil(countResults / new Float(MAX_BY_PAGE));
	}
	public Integer getFirstResult() {
		if(currentPage == 1){
			return 0;
		}
		
		return (currentPage-1) * MAX_BY_PAGE;
	}
	public Integer getMaxResult() {
		return MAX_BY_PAGE;
	}
	
}
