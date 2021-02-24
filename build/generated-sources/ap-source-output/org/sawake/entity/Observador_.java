package org.sawake.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Observador.class)
public abstract class Observador_ {

	public static volatile SingularAttribute<Observador, Date> fechaObs;
	public static volatile SingularAttribute<Observador, Integer> idObs;
	public static volatile SingularAttribute<Observador, Estudiantes> estId;
	public static volatile SingularAttribute<Observador, Docentes> docId;
	public static volatile SingularAttribute<Observador, Date> fechaCitaobs;
	public static volatile SingularAttribute<Observador, String> detalleObs;
	public static volatile SingularAttribute<Observador, Acudientes> acuId;

}

