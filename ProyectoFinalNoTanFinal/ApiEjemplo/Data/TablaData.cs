using ApiEjemplo.Models;
using System;
using System.Collections.Generic;
using System.Data;
using System.Linq;
using System.Web;


namespace ApiEjemplo.Data
{
    public class TablaData
    {
        public static List<Tabla> ObtenerTodoDeTabla()
        {            
            string select = "select * from tabla";
            DataTable dt = DBHelper.EjecutarSelect(select);
            List<Tabla> lista = new List<Tabla>();
            Tabla oTabla;
            if (dt.Rows.Count > 0)
            {
                foreach (DataRow row in dt.Rows)
                {
                    oTabla = ObtenerPorRow(row);
                    lista.Add(oTabla);
                }
                oTabla = ObtenerPorRow(dt.Rows[0]);                
            }
            return lista;
        }

        public static Tabla ObtenerPorRow(DataRow Row)
        {
            Tabla oTabla = new Tabla();
            oTabla.texto = Row.Field<string>("texto");
            return oTabla;
        }
    }
}
