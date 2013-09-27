package com.jopss.topics.util;

import java.util.Date;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;


/**
 * Auxilia na insercao dos dados de auditoria simples nas acoes dos objetos, como data e usuario.
 */
public class AuditListener {

	@PrePersist
	public void onPreInsert(Repository rep) {
		rep.setDateCreated(new Date());
	}

	@PreUpdate
	public void onPreUpdate(Repository rep) {
		rep.setDateUpdated(new Date());
	}
}