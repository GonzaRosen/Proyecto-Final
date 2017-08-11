using ApiEjemplo.Models;
using System;
using System.Collections.Generic;
using System.Data;
using System.Linq;
using System.Web;

namespace ApiEjemplo.Data
{
    public class InstrumentosData
    {
        public static void InsertarInstrumento(Instrumentos oInstrumento)
        {
            string sInsert =
            string.Format(
            "Insert into tinstrumento (Nombre) " + "values ('{0}')",
                oInstrumento.Nombre
                    );
            DBHelper.EjecutarIUD(sInsert);
        }

        public static void DeleteInstrumento(int IdInstrumento)
        {
            string sUpdate = "delete from tinstrumento where IdInstrumento = " + IdInstrumento.ToString();
            DBHelper.EjecutarIUD(sUpdate);
        }

        public static List<Instrumentos> ObtenerInstrumentosPorUsuario(int IdUsuario)
        {
            string select = "select tinstrumentos.Nombre from tusuarios inner join tusuarios_has_tinstrumentos on" +
            "tusuarios.IdUsuario = tusuarios_has_tinstrumentos.tUsuarios_IdUsuario inner join tinstrumentos on" +
            "tusuarios_has_tinstrumentos.tInstrumentos_IdInstrumento = tinstrumentos.IdInstrumento" +
            "WHERE tusuarios.IdUsuario = " + IdUsuario;
            DataTable dt = DBHelper.EjecutarSelect(select);
            List<Instrumentos> lista = new List<Instrumentos>();
            if (dt.Rows.Count > 0)
            {
                foreach (DataRow row in dt.Rows)
                {
                    lista.Add(ObtenerPorRow(row));
                }
            }
            return lista;
        }

        public static List<Instrumentos> ObtenerInstrumentos()
        {
            string select = "select * from tinstrumentos";
            DataTable dt = DBHelper.EjecutarSelect(select);
            List<Instrumentos> lista = new List<Instrumentos>();
            Instrumentos oInstrumento;
            if (dt.Rows.Count > 0)
            {
                foreach (DataRow row in dt.Rows)
                {
                    oInstrumento = ObtenerPorRow(row);
                    lista.Add(oInstrumento);
                }
                oInstrumento = ObtenerPorRow(dt.Rows[0]);
            }
            return lista;
        }
        public static Instrumentos ObtenerPorId(int IdInstrumento)
        {
            string select = "select * from tinstrumentos where IdInstrumento = " + IdInstrumento.ToString();
            DataTable dt = DBHelper.EjecutarSelect(select);
            Instrumentos oInstrumento;
            if (dt.Rows.Count > 0)
            {
                oInstrumento = ObtenerPorRow(dt.Rows[0]);
                return oInstrumento;
            }
            return null;
        }

        public static Instrumentos ObtenerPorRow(DataRow Row)
        {
            Instrumentos oInstrumento = new Instrumentos();
            oInstrumento.IdInstrumento = Row.Field<int>("IdInstrumento");
            oInstrumento.Nombre = Row.Field<string>("Nombre");
            return oInstrumento;
        }
    }
}