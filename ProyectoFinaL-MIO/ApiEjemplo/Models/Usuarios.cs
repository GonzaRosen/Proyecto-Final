using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace ApiEjemplo.Models
{
    public class Usuarios
    {
        public int IdUsuario { get; set; }
        public string Nombre { get; set; }
        public string Apellido { get; set; }
        public string Email { get; set; }
        public string Password { get; set; }
        public string Fecha_Nacimiento { get; set; }
        public string  Instrumentos { get; set; }   
        public string  Generos { get; set; }
        public string Influencias { get; set; }  
        public string UrlImagen { get; set; }
        public HttpPostedFileBase Foto { get; set; }
        public string Descripcion { get; set; }
        public string Ubicacion { get; set; }

    }
}