package org.sawake.entity.dev;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.sawake.entity.AcuEst;
import org.sawake.entity.Acudientes;
import org.sawake.entity.Estudiantes;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-05-08T12:35:59")
@StaticMetamodel(AcuEst.class)
public class AcuEst_ { 

    public static volatile SingularAttribute<AcuEst, Integer> idAcuest;
    public static volatile SingularAttribute<AcuEst, Estudiantes> estId;
    public static volatile SingularAttribute<AcuEst, Acudientes> acuId;

}