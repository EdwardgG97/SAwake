package org.sawake.entity.dev;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.sawake.entity.Asistencia;
import org.sawake.entity.Docentes;
import org.sawake.entity.FaltasAlertas;
import org.sawake.entity.Observador;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-05-08T12:35:59")
@StaticMetamodel(Docentes.class)
public class Docentes_ { 

    public static volatile SingularAttribute<Docentes, String> emailDoc;
    public static volatile ListAttribute<Docentes, Observador> observadorList;
    public static volatile SingularAttribute<Docentes, String> logDoc;
    public static volatile SingularAttribute<Docentes, Integer> idDoc;
    public static volatile ListAttribute<Docentes, FaltasAlertas> faltasAlertasList;
    public static volatile SingularAttribute<Docentes, Long> docDoc;
    public static volatile SingularAttribute<Docentes, Long> telDoc;
    public static volatile SingularAttribute<Docentes, String> passDoc;
    public static volatile SingularAttribute<Docentes, String> nomDoc;
    public static volatile ListAttribute<Docentes, Asistencia> asistenciaList;
    public static volatile SingularAttribute<Docentes, String> sexDoc;
    public static volatile ListAttribute<Docentes, Observador> obsList;
    public static volatile SingularAttribute<Docentes, String> apeDoc;

}