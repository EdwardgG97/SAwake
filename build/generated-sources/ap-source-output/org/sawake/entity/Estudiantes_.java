package org.sawake.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Estudiantes.class)
public abstract class Estudiantes_ {

	public static volatile SingularAttribute<Estudiantes, Long> docEst;
	public static volatile ListAttribute<Estudiantes, Observador> observadorList;
	public static volatile SingularAttribute<Estudiantes, String> sexEst;
	public static volatile ListAttribute<Estudiantes, Registro> registroList;
	public static volatile SingularAttribute<Estudiantes, Grupo> grupoId;
	public static volatile ListAttribute<Estudiantes, FaltasAlertas> faltasAlertasList;
	public static volatile SingularAttribute<Estudiantes, String> apeEst;
	public static volatile ListAttribute<Estudiantes, Asistencia> asistenciaList;
	public static volatile ListAttribute<Estudiantes, AcuEst> acuEstList;
	public static volatile SingularAttribute<Estudiantes, Long> telEst;
	public static volatile SingularAttribute<Estudiantes, String> nomEst;
	public static volatile ListAttribute<Estudiantes, Observador> obsList;
	public static volatile SingularAttribute<Estudiantes, Short> edadEst;
	public static volatile SingularAttribute<Estudiantes, Integer> idEst;

}

