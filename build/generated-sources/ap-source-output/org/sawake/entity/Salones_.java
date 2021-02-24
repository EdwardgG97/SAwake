package org.sawake.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Salones.class)
public abstract class Salones_ {

	public static volatile SingularAttribute<Salones, Integer> idSalon;
	public static volatile SingularAttribute<Salones, String> nomSalon;
	public static volatile SingularAttribute<Salones, Grupo> grupoId;
	public static volatile ListAttribute<Salones, Asistencia> asistenciaList;

}

