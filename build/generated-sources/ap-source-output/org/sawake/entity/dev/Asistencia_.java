package org.sawake.entity.dev;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.sawake.entity.Asistencia;
import org.sawake.entity.Dias;
import org.sawake.entity.Docentes;
import org.sawake.entity.Estudiantes;
import org.sawake.entity.Salones;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-05-08T12:35:59")
@StaticMetamodel(Asistencia.class)
public class Asistencia_ { 

    public static volatile SingularAttribute<Asistencia, Salones> salonId;
    public static volatile SingularAttribute<Asistencia, Integer> idAsist;
    public static volatile SingularAttribute<Asistencia, Date> fechaAsist;
    public static volatile SingularAttribute<Asistencia, Estudiantes> estId;
    public static volatile SingularAttribute<Asistencia, String> asistAsist;
    public static volatile SingularAttribute<Asistencia, Docentes> docId;
    public static volatile SingularAttribute<Asistencia, Dias> diasId;

}