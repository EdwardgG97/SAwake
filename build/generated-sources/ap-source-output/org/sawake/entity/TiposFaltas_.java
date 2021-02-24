package org.sawake.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(TiposFaltas.class)
public abstract class TiposFaltas_ {

	public static volatile SingularAttribute<TiposFaltas, String> nombreTipo;
	public static volatile SingularAttribute<TiposFaltas, Integer> idTipo;
	public static volatile ListAttribute<TiposFaltas, CorrectivosFaltas> correctivosFaltasList;
	public static volatile SingularAttribute<TiposFaltas, String> detalleTipo;
	public static volatile ListAttribute<TiposFaltas, FaltasAlertas> faltasAlertasList;

}

