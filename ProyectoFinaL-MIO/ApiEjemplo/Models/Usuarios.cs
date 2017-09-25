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
        public DateTime Fecha_Nacimiento { get; set; }
        
        //public List<Generos> Generos { get; set; }
        //public List<Instrumentos> Instrumentos { get; set; }
        public string Influencias { get; set; }      
        public string UrlImagen { get; set; }
        public HttpPostedFileBase Foto { get; set; }
        public string Descripcion { get; set; }
        public double Ubicacion { get; set; }

    }
}