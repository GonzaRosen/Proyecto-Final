using ApiEjemplo.Models;
using System;
using System.Collections.Generic;
using System.Data;
using System.Linq;
using System.Web;

namespace ApiEjemplo.Data
{
    public class BandasData
    {
            public static void InsertarBanda(Bandas oBanda)
            {
                string sInsert =
                string.Format(
                "Insert into tbandas (Nombre,Descripcion,UrlImagen,Ubicacion) " + "values ('{0}','{1}','{2}',{3})",
                    oBanda.Nombre,
                    oBanda.Descripcion,
                    oBanda.UrlImagen,
                    oBanda.Ubicacion
                        );
                DBHelper.EjecutarIUD(sInsert);
            }

            /*public static void Update(Bandas oBanda)
            {
                string sUpdate = "update tbandas set Nombre='" + oBanda.Nombre + "',FechaNac='" + "' where IdBanda=" + oBanda.IdBanda.ToString();
                DBHelper.EjecutarIUD(sUpdate);
            }*/

            public static void DeleteBanda(int IdBanda)
            {
                string sUpdate = "delete from tbandas where IdBanda = " + IdBanda.ToString();
                DBHelper.EjecutarIUD(sUpdate);
            }

            public static Bandas ObtenerPorId(int IdBanda)
            {
                string select = "select * from tbandas where IdBanda = " + IdBanda.ToString();
                DataTable dt = DBHelper.EjecutarSelect(select);
                Bandas oBanda;
                if (dt.Rows.Count > 0)
                {
                    oBanda = ObtenerPorRow(dt.Rows[0]);
                    return oBanda;
                }
                return null;
            }

            public static List<Bandas> ObtenerBandas()
            {
                string select = "select * from tbandas";
                DataTable dt = DBHelper.EjecutarSelect(select);
                List<Bandas> lista = new List<Bandas>();
                Bandas oBanda;
                if (dt.Rows.Count > 0)
                {
                    foreach (DataRow row in dt.Rows)
                    {
                        oBanda = ObtenerPorRow(row);
                        lista.Add(oBanda);
                    }
                    oBanda = ObtenerPorRow(dt.Rows[0]);
                }
                return lista;
            }

            public static List<Bandas> ObtenerBandasPorNombre(string Nombre)
            {
                string select = "select * from tbandas where Nombre like '%" + Nombre + "%'";
                DataTable dt = DBHelper.EjecutarSelect(select);
                List<Bandas> lista = new List<Bandas>();
                Bandas oBanda;
                if (dt.Rows.Count > 0)
                {
                    foreach (DataRow row in dt.Rows)
                    {
                    oBanda = ObtenerPorRow(row);
                        lista.Add(oBanda);
                    }
                    oBanda = ObtenerPorRow(dt.Rows[0]);
                }
                return lista;
            }

            public static Bandas ObtenerPorRow(DataRow Row)
            {
                Bandas oBanda = new Bandas();
                oBanda.IdBanda = Row.Field<int>("IdBanda");
                oBanda.Nombre = Row.Field<string>("Nombre");
                oBanda.UrlImagen = Row.Field<string>("UrlImagen");
                oBanda.Descripcion = Row.Field<string>("Descripcion");
                oBanda.Ubicacion = Row.Field<double>("Ubicacion");
                return oBanda;
            }
        }
    }
}
}