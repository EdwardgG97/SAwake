package org.sawake.entity.dev;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.sawake.entity.AcuEst;
import org.sawake.entity.Acudientes;
import org.sawake.entity.FaltasAlertas;
import org.sawake.entity.Observador;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-05-08T12:35:59")
@StaticMetamodel(Acudientes.class)
public class Acudientes_ { 

    public static volatile ListAttribute<Acudientes, Observador> observadorList;
    public static volatile SingularAttribute<Acudientes, String> apeAcu;
    public static volatile SingularAttribute<Acudientes, String> nomAcu;
    public static volatile SingularAttribute<Acudientes, String> sexAcu;
    public static volatile SingularAttribute<Acudientes, String> emailAcu;
    public static volatile SingularAttribute<Acudientes, Long> telAcu;
    public static volatile SingularAttribute<Acudientes, String> passAcu;
    public static volatile ListAttribute<Acudientes, FaltasAlertas> faltasAlertasList;
    public static volatile SingularAttribute<Acudientes, Integer> idAcu;
    public static volatile SingularAttribute<Acudientes, Long> docAcu;
    public static volatile ListAttribute<Acudientes, AcuEst> acuEstList;
    public static volatile SingularAttribute<Acudientes, String> logAcu;
    public static volatile ListAttribute<Acudientes, Observador> obsList;

}