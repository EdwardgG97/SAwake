package org.sawake.entity.dev;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.sawake.entity.CorrectivosFaltas;
import org.sawake.entity.TiposFaltas;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-05-08T12:35:59")
@StaticMetamodel(CorrectivosFaltas.class)
public class CorrectivosFaltas_ { 

    public static volatile SingularAttribute<CorrectivosFaltas, TiposFaltas> tipoId;
    public static volatile SingularAttribute<CorrectivosFaltas, Integer> idCorrectivo;
    public static volatile SingularAttribute<CorrectivosFaltas, String> detalleCorrectivo;
    public static volatile SingularAttribute<CorrectivosFaltas, String> pasoCorrectivo;

}