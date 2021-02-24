package org.sawake.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(FaltasAlertas.class)
public abstract class FaltasAlertas_ {

	public static volatile SingularAttribute<FaltasAlertas, TiposFaltas> tipoId;
	public static volatile SingularAttribute<FaltasAlertas, Estudiantes> estId;
	public static volatile SingularAttribute<FaltasAlertas, String> detalleAlertas;
	public static volatile SingularAttribute<FaltasAlertas, Docentes> docId;
	public static volatile SingularAttribute<FaltasAlertas, Acudientes> acuId;
	public static volatile SingularAttribute<FaltasAlertas, Date> fechaAlertas;
	public static volatile SingularAttribute<FaltasAlertas, Integer> idAlertas;

}

