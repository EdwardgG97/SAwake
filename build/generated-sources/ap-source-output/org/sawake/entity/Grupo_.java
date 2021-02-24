package org.sawake.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Grupo.class)
public abstract class Grupo_ {

	public static volatile ListAttribute<Grupo, Estudiantes> estudiantesList;
	public static volatile ListAttribute<Grupo, Salones> salonesList;
	public static volatile SingularAttribute<Grupo, String> numGrupo;
	public static volatile SingularAttribute<Grupo, Grados> gradoId;
	public static volatile SingularAttribute<Grupo, Integer> idGrupo;

}

