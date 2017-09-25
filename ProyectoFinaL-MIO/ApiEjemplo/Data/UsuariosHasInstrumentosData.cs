using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using ApiEjemplo.Models;

namespace ApiEjemplo.Data
{
    public class UsuariosHasInstrumentosData
    {
        public static void InsertarUHI(UsuariosHasInstrumentos oUHI, int IdUsuario, int IdInstrumento)
        {
            #region comments
            //     te conviene usar string.Format lo voy a usar te das cuenta enseguida.
            //   Es una funcion para concatenar
            /*
         son todos strings?fecha es datey ubicacion es double ok
         yo a la base le hubiese agregado un id tipo identity o alguna primary key por dni o algo asi
         pero asumo que no lo vieron

         igual ahor para probarlo si no tengo en la bse ningun parametro exepto id y nombre no combiene hacerlo de esos?
         todas las tablas deberian tener un id pero no se si lo vieron
         bien esta funcion string.Format tiene un parametro obligatorio y el resto depende de la cantidad
         de "index" que se agreguen { 0}
 { 1} // vamos a hacer de cuenta q vienen todos pq se debe validar en otra instancia
 { 2} etc luego del primer parametro estos son reemplazados de manera ordenada por los valores que pongamos.
     Hay que validarlos */
            //escucha no uses enies cambialo pq te genera problemas con los urls etc (probamos igual
            //pero desde una url vas a tener problemas tenes qo pkoaksarlo desde un programa como postman
            #endregion
            string sInsert =
                string.Format(
            "Insert into tusuarios_has_tinstrumentos (" + IdUsuario + "," + IdInstrumento + ") " +
                "values ({0},{1})",
                oUHI.IdUsuario,
                oUHI.IdInstrumento
                    );
            DBHelper.EjecutarIUD(sInsert);
        }
    }
}