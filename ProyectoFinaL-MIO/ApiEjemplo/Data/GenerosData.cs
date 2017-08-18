using ApiEjemplo.Models;
using System;
using System.Collections.Generic;
using System.Data;
using System.Linq;
using System.Web;

namespace ApiEjemplo.Data
{
    public class GenerosData
    {
        public static void InsertarGenero(Generos oGenero)
        {
            string sInsert =
            string.Format(
            "Insert into tgenero (Nombre) " + "values ('{0}')",
                oGenero.Nombre
                    );
            DBHelper.EjecutarIUD(sInsert);
        }
     
        public static void DeleteGenero(int IdGenero)
        {
            string sUpdate = "delete from tgenero where IdGenero = " + IdGenero.ToString();
            DBHelper.EjecutarIUD(sUpdate);
        }

        public static List<Generos> ObtenerGeneros()
        {
            string select = "select * from tgeneros";
            DataTable dt = DBHelper.EjecutarSelect(select);
            List<Generos> lista = new List<Generos>();
            Generos oGenero;
            if (dt.Rows.Count > 0)
            {
                foreach (DataRow row in dt.Rows)
                {
                    oGenero = ObtenerPorRow(row);
                    lista.Add(oGenero);
                }
                oGenero = ObtenerPorRow(dt.Rows[0]);
            }
            return lista;
        }

        public static Generos ObtenerPorId(int IdGenero)
        {
            string select = "select * from tgeneros where IdGenero = " + IdGenero.ToString();
            DataTable dt = DBHelper.EjecutarSelect(select);
            Generos oGenero;
            if (dt.Rows.Count > 0)
            {
                oGenero = ObtenerPorRow(dt.Rows[0]);
                return oGenero;
            }
            return null;
        }

        public static Generos ObtenerPorRow(DataRow Row)
        {
            Generos oGenero = new Generos();
            oGenero.IdGenero = Row.Field<int>("IdGenero");
            oGenero.Nombre = Row.Field<string>("Nombre");
            return oGenero;
        }
    }
}