using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using ApiEjemplo.Models;

namespace ApiEjemplo.Data
{
    public class UHasUData
    {      
        public static void InsertarUHU(UHasU oUHasU)
        {
            string sInsert = string.Format("Insert into tusuarios_has_tusuarios (" + oUHasU.IdUsuario + "," + oUHasU.IdSeguido + ") values ({0},{1})",
            oUHasU.IdUsuario,
            oUHasU.IdSeguido);
            DBHelper.EjecutarIUD(sInsert);
        }
    }
}