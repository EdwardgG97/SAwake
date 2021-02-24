package org.sawake.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Asistencia.class)
public abstract class Asistencia_ {

	public static volatile SingularAttribute<Asistencia, Salones> salonId;
	public static volatile SingularAttribute<Asistencia, Integer> idAsist;
	public static volatile SingularAttribute<Asistencia, Date> fechaAsist;
	public static volatile SingularAttribute<Asistencia, Estudiantes> estId;
	public static volatile SingularAttribute<Asistencia, String> asistAsist;
	public static volatile SingularAttribute<Asistencia, Docentes> docId;
	public static volatile SingularAttribute<Asistencia, Dias> diasId;

}

