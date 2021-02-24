package org.sawake.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Docentes.class)
public abstract class Docentes_ {

	public static volatile SingularAttribute<Docentes, String> emailDoc;
	public static volatile ListAttribute<Docentes, Observador> observadorList;
	public static volatile SingularAttribute<Docentes, String> logDoc;
	public static volatile SingularAttribute<Docentes, Integer> idDoc;
	public static volatile ListAttribute<Docentes, FaltasAlertas> faltasAlertasList;
	public static volatile SingularAttribute<Docentes, Long> docDoc;
	public static volatile SingularAttribute<Docentes, Long> telDoc;
	public static volatile SingularAttribute<Docentes, String> passDoc;
	public static volatile SingularAttribute<Docentes, String> nomDoc;
	public static volatile ListAttribute<Docentes, Asistencia> asistenciaList;
	public static volatile SingularAttribute<Docentes, String> sexDoc;
	public static volatile ListAttribute<Docentes, Observador> obsList;
	public static volatile SingularAttribute<Docentes, String> apeDoc;

}

