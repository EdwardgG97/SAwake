package org.sawake.entity.dev;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.sawake.entity.Estudiantes;
import org.sawake.entity.Grados;
import org.sawake.entity.Grupo;
import org.sawake.entity.Salones;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-05-08T12:35:59")
@StaticMetamodel(Grupo.class)
public class Grupo_ { 

    public static volatile ListAttribute<Grupo, Estudiantes> estudiantesList;
    public static volatile ListAttribute<Grupo, Salones> salonesList;
    public static volatile SingularAttribute<Grupo, String> numGrupo;
    public static volatile SingularAttribute<Grupo, Grados> gradoId;
    public static volatile SingularAttribute<Grupo, Integer> idGrupo;

}