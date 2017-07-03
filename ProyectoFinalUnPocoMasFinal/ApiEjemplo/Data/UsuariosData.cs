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
        /*public static void Insert(Usuarios oUsuario)
        {
            string sInsert = "Insert into tusuarios (Nombre,Apellido,Email,Contraseña,Fecha_Nacimiento,Influencias,UrlImagen,Descripcion,Ubicacion) values ('" + oUsuario.Nombre + "','" + oUsuario.Apellido + "','" + oUsuario.Email + "','" + oUsuario.Contraseña + "','" + oUsuario.FechaNacimiento.ToString("yyyy-MM-dd HH:mm") + "','" + oUsuario.Influencias + "','" + oUsuario.URLimagen + "','" + oUsuario.Foto + "','" + oUsuario.Descripcion + "','" + oUsuario.Ubicacion "')";
            DBHelper.EjecutarIUD(sInsert);
        }*/

        public static List<Usuarios> ObtenerTodoDeUsuarios()
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

        public static List<Usuarios> ObtenerPorNombre(string Nombre)//cambiar por nombre por mismos intereses
        {
            string select = "select * from tusuarios where Nombre like '%" + Nombre + "%'";//cambiar por nombre por influencias,genero,instrumentos
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

        public static Usuarios ObtenerPorRow(DataRow Row)
        {
            Usuarios oUsuario = new Usuarios();
            oUsuario.IdUsuario = Row.Field<int>("IdUsuario");
            oUsuario.Nombre = Row.Field<string>("Nombre");
            oUsuario.Apellido = Row.Field<string>("Apellido");
            oUsuario.Email = Row.Field<string>("Email");
            oUsuario.Contraseña = Row.Field<string>("Contraseña");
            oUsuario.FechaNacimiento = Row.Field<DateTime>("FechaNacimiento");
            //oUsuario.Generos = Row.Field<Array>("Generos");
            //oUsuario.Instrumentos = Row.Field<Array>("Instrumentos");
            oUsuario.Influencias = Row.Field<string>("Influencias");
            oUsuario.URLimagen = Row.Field<string>("URLimagen");
            oUsuario.Descripcion = Row.Field<string>("Descripcion");
            oUsuario.Ubicacion = Row.Field<double>("Ubicacion");
            return oUsuario;
        }
    }
}
