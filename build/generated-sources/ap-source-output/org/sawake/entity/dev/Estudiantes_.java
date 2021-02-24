package org.sawake.entity.dev;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.sawake.entity.AcuEst;
import org.sawake.entity.Asistencia;
import org.sawake.entity.Estudiantes;
import org.sawake.entity.FaltasAlertas;
import org.sawake.entity.Grupo;
import org.sawake.entity.Observador;
import org.sawake.entity.Registro;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-05-08T12:35:59")
@StaticMetamodel(Estudiantes.class)
public class Estudiantes_ { 

    public static volatile SingularAttribute<Estudiantes, Long> docEst;
    public static volatile ListAttribute<Estudiantes, Observador> observadorList;
    public static volatile SingularAttribute<Estudiantes, String> sexEst;
    public static volatile ListAttribute<Estudiantes, Registro> registroList;
    public static volatile SingularAttribute<Estudiantes, Grupo> grupoId;
    public static volatile ListAttribute<Estudiantes, FaltasAlertas> faltasAlertasList;
    public static volatile SingularAttribute<Estudiantes, String> apeEst;
    public static volatile ListAttribute<Estudiantes, Asistencia> asistenciaList;
    public static volatile ListAttribute<Estudiantes, AcuEst> acuEstList;
    public static volatile SingularAttribute<Estudiantes, Long> telEst;
    public static volatile SingularAttribute<Estudiantes, String> nomEst;
    public static volatile ListAttribute<Estudiantes, Observador> obsList;
    public static volatile SingularAttribute<Estudiantes, Short> edadEst;
    public static volatile SingularAttribute<Estudiantes, Integer> idEst;

}