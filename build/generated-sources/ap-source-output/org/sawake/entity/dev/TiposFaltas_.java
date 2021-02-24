package org.sawake.entity.dev;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.sawake.entity.CorrectivosFaltas;
import org.sawake.entity.FaltasAlertas;
import org.sawake.entity.TiposFaltas;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-05-08T12:35:59")
@StaticMetamodel(TiposFaltas.class)
public class TiposFaltas_ { 

    public static volatile SingularAttribute<TiposFaltas, String> nombreTipo;
    public static volatile SingularAttribute<TiposFaltas, Integer> idTipo;
    public static volatile ListAttribute<TiposFaltas, CorrectivosFaltas> correctivosFaltasList;
    public static volatile SingularAttribute<TiposFaltas, String> detalleTipo;
    public static volatile ListAttribute<TiposFaltas, FaltasAlertas> faltasAlertasList;

}