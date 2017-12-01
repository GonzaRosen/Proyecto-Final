using ApiEjemplo.Models;
using System;
using System.Collections.Generic;
using System.Data;
using System.Linq;
using System.Web;


namespace ApiEjemplo.Data
{
    public class UsuariosData
    { 
        public static void InsertarUsuario(Usuarios oUsuario)
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
            "Insert into tusuarios (Nombre,Apellido,Email,Password,Fecha_Nacimiento,Instrumentos,Generos,Influencias" +
                ",UrlImagen," + "Descripcion,Ubicacion) " +
                "values ('{0}','{1}','{2}','{3}','{4}','{5}','{6}','{7}','{8}','{9}','{10}')",
                oUsuario.Nombre,
                oUsuario.Apellido,
                oUsuario.Email,
                oUsuario.Password,
                oUsuario.Fecha_Nacimiento, 
                oUsuario.Instrumentos,
                oUsuario.Generos,
                oUsuario.Influencias,
                oUsuario.UrlImagen,
                oUsuario.Descripcion,
                oUsuario.Ubicacion
                    );
            DBHelper.EjecutarIUD(sInsert);
        }

        public static void Update(Usuarios oUsuario)
        {
            string sUpdate = "update tusuarios set Nombre='" + oUsuario.Nombre + "',Apellido = '" + oUsuario.Apellido + "',Instrumentos = '" + oUsuario.Instrumentos + "',Generos = '" + oUsuario.Generos + "',Influencias = '" + oUsuario.Influencias + "',Descripcion = '" + oUsuario.Descripcion + "',Ubicacion = '" + oUsuario.Ubicacion + "' where IdUsuario = " + oUsuario.IdUsuario.ToString();
            DBHelper.EjecutarIUD(sUpdate);
        }

        public static void Delete(int IdUsuario)
        {
            string sUpdate = "delete from tusuarios where IdUsuario = " + IdUsuario.ToString();
            DBHelper.EjecutarIUD(sUpdate);
        }

        public static Usuarios ObtenerPorId(int IdUsuario)
        {
            string select = "select * from tusuarios where IdUsuario = " + IdUsuario.ToString();
            DataTable dt = DBHelper.EjecutarSelect(select);
            Usuarios oUsuario;
            if (dt.Rows.Count > 0)
            {
                oUsuario = ObtenerPorRow(dt.Rows[0]);
                return oUsuario;
            }
            return null;
        }

        public static List<Usuarios> ObtenerUsuarios()
        {            
            string select = "select * from tusuarios";
            DataTable dt = DBHelper.EjecutarSelect(select);
            List<Usuarios> lista = new List<Usuarios>();
            Usuarios oUsuario;
            if (dt.Rows.Count > 0)
            {
                foreach (DataRow row in dt.Rows)
                {
                    oUsuario = ObtenerPorRow(row);
                    lista.Add(oUsuario);
                }
                oUsuario = ObtenerPorRow(dt.Rows[0]);                
            }
            return lista;
        }

        public static List<Usuarios> ObtenerSeguidosPorUsuario(int IdUsuario, string Nombre)
        {
            string select = "select tusuarios.*  from tusuarios inner join tusuarios_has_tusuarios on" +
            " tusuarios.IdUsuario = tusuarios_has_tusuarios.tSeguidos_IdSeguido" +
            " WHERE tusuarios_has_tusuarios.tUsuarios_IdUsuario = " + IdUsuario.ToString();
            DataTable dt = DBHelper.EjecutarSelect(select);
            List<Usuarios> lista = new List<Usuarios>();
            Usuarios oUsuario;
            if (dt.Rows.Count > 0)
            {
                foreach (DataRow row in dt.Rows)
                {
                    oUsuario = ObtenerPorRow(row);
                    lista.Add(oUsuario);
                }
                oUsuario = ObtenerPorRow(dt.Rows[0]);
            }
            return lista;
        }

        public static List<Usuarios> Login(string Email, string Password)
        {
            string select = "select * from tusuarios WHERE tusuarios.Email = '" + Email + "' AND tusuarios.Password = '" + Password + "'";
            DataTable dt = DBHelper.EjecutarSelect(select);
            List<Usuarios> lista = new List<Usuarios>();
            Usuarios oUsuario;
            if (dt.Rows.Count > 0)
            {
                foreach (DataRow row in dt.Rows)
                {
                    oUsuario = ObtenerPorRow(row);
                    lista.Add(oUsuario);
                }
                oUsuario = ObtenerPorRow(dt.Rows[0]);
            }
            return lista;
        }

        public static List<Usuarios> ObtenerUsuariosPorNombre(string Nombre)
        {
            string select = "select IdUsuario from tusuarios where Nombre like '%" + Nombre + "%'";
            DataTable dt = DBHelper.EjecutarSelect(select);
            List<Usuarios> lista = new List<Usuarios>();
            Usuarios oUsuario;
            if (dt.Rows.Count > 0)
            {
                foreach (DataRow row in dt.Rows)
                {
                    oUsuario = ObtenerPorRow(row);
                    lista.Add(oUsuario);
                }
                oUsuario = ObtenerPorRow(dt.Rows[0]);
            }
            return lista;
        }

        public static List<Usuarios> ObtenerUsuariosPorRequisitos(string Instrumentos, string Generos, string Influencias, string Ubicacion)
        {
            if (Instrumentos == null && Generos == null && Influencias == null)
            {
                string select = "select * from tusuarios where Ubicacion like '%" + Ubicacion + "%'";
                DataTable dt = DBHelper.EjecutarSelect(select);
                List<Usuarios> lista = new List<Usuarios>();
                Usuarios oUsuario;
                if (dt.Rows.Count > 0)
                {
                    foreach (DataRow row in dt.Rows)
                    {
                        oUsuario = ObtenerPorRow(row);
                        lista.Add(oUsuario);
                    }
                    oUsuario = ObtenerPorRow(dt.Rows[0]);
                }
                return lista;
            }
            else
            {
                if (Instrumentos == null)
                { Instrumentos = "ñqñ ñqñ"; }
                if (Generos == null)
                {Generos = "ñqñ ñqñ";}
                if (Influencias == null)
                { Influencias = "ñqñ ñqñ"; }

                String[] VInstrumentos = Instrumentos.Split(' ');
                String[] VGeneros = Generos.Split(' ');
                String[] VInfluencias = Influencias.Split(' ');

                int cantIns = VInstrumentos.Count();
                int cantG = VGeneros.Count();
                int cantInf = VInfluencias.Count();

                DataTable dt = null;
                string select = "select DISTINCT * from tusuarios where Ubicacion  = '" + Ubicacion + "' && (";
                for (int i = 0; i < cantIns; i++)
                {
                    select = select + " Instrumentos like '%" + VInstrumentos[i] + "%' ||";
                }
                for (int i = 0; i < cantG; i++)
                {
                    select = select + " Generos like '%" + VGeneros[i] + "%' ||";
                }
                for (int i = 0; i < cantInf; i++)
                {
                    if (i == cantInf - 1)
                        select = select + " Influencias like '%" + VInfluencias[i] + "%' )";
                    else
                        select = select + " Influencias like '%" + VInfluencias[i] + "%' ||";
                }

                dt = DBHelper.EjecutarSelect(select);
                List<Usuarios> lista = new List<Usuarios>();
                Usuarios oUsuario;
                if (dt.Rows.Count > 0)
                {
                    foreach (DataRow row in dt.Rows)
                    {
                        oUsuario = ObtenerPorRow(row);
                        lista.Add(oUsuario);
                    }
                    oUsuario = ObtenerPorRow(dt.Rows[0]);
                }
                return lista;
            }
        }

        public static Usuarios ObtenerPorRow(DataRow Row)
        {
            Usuarios oUsuario = new Usuarios();
            oUsuario.IdUsuario = Row.Field<int>("IdUsuario");
            oUsuario.Nombre = Row.Field<string>("Nombre");
            oUsuario.Apellido = Row.Field<string>("Apellido");
            oUsuario.Email = Row.Field<string>("Email");
            oUsuario.Password = Row.Field<string>("Password");
            oUsuario.Fecha_Nacimiento = Row.Field<string>("Fecha_Nacimiento");
            oUsuario.Instrumentos = Row.Field<string>("Instrumentos");
            oUsuario.Generos = Row.Field<string>("Generos");
            oUsuario.Influencias = Row.Field<string>("Influencias");
            oUsuario.UrlImagen = Row.Field<string>("UrlImagen");
            oUsuario.Descripcion = Row.Field<string>("Descripcion");
            oUsuario.Ubicacion = Row.Field<string>("Ubicacion");
            return oUsuario;
        }
    }
}
