package org.sawake.entity.dev;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.sawake.entity.Acudientes;
import org.sawake.entity.Docentes;
import org.sawake.entity.Estudiantes;
import org.sawake.entity.FaltasAlertas;
import org.sawake.entity.TiposFaltas;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-05-08T12:35:59")
@StaticMetamodel(FaltasAlertas.class)
public class FaltasAlertas_ { 

    public static volatile SingularAttribute<FaltasAlertas, TiposFaltas> tipoId;
    public static volatile SingularAttribute<FaltasAlertas, Estudiantes> estId;
    public static volatile SingularAttribute<FaltasAlertas, String> detalleAlertas;
    public static volatile SingularAttribute<FaltasAlertas, Docentes> docId;
    public static volatile SingularAttribute<FaltasAlertas, Acudientes> acuId;
    public static volatile SingularAttribute<FaltasAlertas, Date> fechaAlertas;
    public static volatile SingularAttribute<FaltasAlertas, Integer> idAlertas;

}