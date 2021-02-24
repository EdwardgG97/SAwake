package org.sawake.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Acudientes.class)
public abstract class Acudientes_ {

	public static volatile ListAttribute<Acudientes, Observador> observadorList;
	public static volatile SingularAttribute<Acudientes, String> apeAcu;
	public static volatile SingularAttribute<Acudientes, String> nomAcu;
	public static volatile SingularAttribute<Acudientes, String> sexAcu;
	public static volatile SingularAttribute<Acudientes, String> emailAcu;
	public static volatile SingularAttribute<Acudientes, Long> telAcu;
	public static volatile SingularAttribute<Acudientes, String> passAcu;
	public static volatile ListAttribute<Acudientes, FaltasAlertas> faltasAlertasList;
	public static volatile SingularAttribute<Acudientes, Integer> idAcu;
	public static volatile SingularAttribute<Acudientes, Long> docAcu;
	public static volatile ListAttribute<Acudientes, AcuEst> acuEstList;
	public static volatile SingularAttribute<Acudientes, String> logAcu;
	public static volatile ListAttribute<Acudientes, Observador> obsList;

}

