using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using ApiEjemplo.Models;

namespace ApiEjemplo.Data
{
    public class UHasUData
    {      
        public static void InsertarUHU(int IdUsuario, int IdSeguido)
        {
            string sInsert = string.Format("Insert into tusuarios_has_tusuarios (" + IdUsuario + "," + IdSeguido + ") " +
                "values ({0},{1})"
                    );
            DBHelper.EjecutarIUD(sInsert);
        }
    }
}