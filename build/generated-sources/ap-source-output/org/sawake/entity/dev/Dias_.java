package org.sawake.entity.dev;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.sawake.entity.Asistencia;
import org.sawake.entity.Dias;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-05-08T12:35:59")
@StaticMetamodel(Dias.class)
public class Dias_ { 

    public static volatile SingularAttribute<Dias, String> nomDias;
    public static volatile SingularAttribute<Dias, Integer> idDias;
    public static volatile ListAttribute<Dias, Asistencia> asistenciaList;

}