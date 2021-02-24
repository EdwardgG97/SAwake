package org.sawake.entity.dev;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.sawake.entity.Estudiantes;
import org.sawake.entity.Registro;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-05-08T12:35:59")
@StaticMetamodel(Registro.class)
public class Registro_ { 

    public static volatile SingularAttribute<Registro, Estudiantes> estId;
    public static volatile SingularAttribute<Registro, Date> fEntradareg;
    public static volatile SingularAttribute<Registro, Date> fSalidareg;
    public static volatile SingularAttribute<Registro, Integer> idReg;

}