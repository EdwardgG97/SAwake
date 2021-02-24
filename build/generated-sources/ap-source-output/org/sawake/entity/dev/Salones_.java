package org.sawake.entity.dev;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.sawake.entity.Asistencia;
import org.sawake.entity.Grupo;
import org.sawake.entity.Salones;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-05-08T12:35:59")
@StaticMetamodel(Salones.class)
public class Salones_ { 

    public static volatile SingularAttribute<Salones, Integer> idSalon;
    public static volatile SingularAttribute<Salones, String> nomSalon;
    public static volatile SingularAttribute<Salones, Grupo> grupoId;
    public static volatile ListAttribute<Salones, Asistencia> asistenciaList;

}