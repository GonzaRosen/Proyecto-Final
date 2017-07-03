using ApiEjemplo.Data;
using ApiEjemplo.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using System.Web.Http.Description;

namespace ApiEjemplo.Controllers
{
    public class UsuariosController : ApiController
    {
        /*[ResponseType(typeof(Usuarios))]
        public IHttpActionResult Post(Usuarios oUsuario)
        {
            if (oUsuario == null || string.IsNullOrEmpty(oUsuario.Nombre) || string.IsNullOrEmpty(oUsuario.Apellido))//validamos nombre(validar todoooo)
            {
                return BadRequest("Datos incorrectos.");
            }
            UsuariosData.Insert(oUsuario);
            return Ok();
        }*/

        public IList<Usuarios> Get()
        {
            return UsuariosData.ObtenerTodoDeUsuarios();
        }


        [ResponseType(typeof(IList<Usuarios>))]
        public IHttpActionResult Get(string Nombre)
        {
            List<Usuarios> lista = new List<Usuarios>();
            lista = UsuariosData.ObtenerPorNombre(Nombre);
            if (lista.Count == 0)
            {
                return NotFound();
            }
            return Ok(lista);
        }
    }
}