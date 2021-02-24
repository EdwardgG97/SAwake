package org.sawake.entity.dev;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.sawake.entity.Acudientes;
import org.sawake.entity.Docentes;
import org.sawake.entity.Estudiantes;
import org.sawake.entity.Observador;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-05-08T12:35:59")
@StaticMetamodel(Observador.class)
public class Observador_ { 

    public static volatile SingularAttribute<Observador, Date> fechaObs;
    public static volatile SingularAttribute<Observador, Integer> idObs;
    public static volatile SingularAttribute<Observador, Estudiantes> estId;
    public static volatile SingularAttribute<Observador, Docentes> docId;
    public static volatile SingularAttribute<Observador, Date> fechaCitaobs;
    public static volatile SingularAttribute<Observador, String> detalleObs;
    public static volatile SingularAttribute<Observador, Acudientes> acuId;

}