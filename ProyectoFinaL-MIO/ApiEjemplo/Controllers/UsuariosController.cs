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
        [ResponseType(typeof(Usuarios))]
        public IHttpActionResult Post(Usuarios oUsuario)
        {
            if (oUsuario == null || string.IsNullOrEmpty(oUsuario.Nombre) || string.IsNullOrEmpty(oUsuario.Apellido))
            {
                return BadRequest("Datos vacios.");
            }
            try
            {
                UsuariosData.InsertarUsuario(oUsuario);
            }
            catch (Exception e)
            {
                return BadRequest("Se ha producido un error al intentar realizar la consulta.");
            }
            return Ok();
        }

        public IHttpActionResult Update(Usuarios oUsuario)
        {
            UsuariosData.Update(oUsuario);
            return Ok();
        }

        public IHttpActionResult Delete(int IdUsuario)
        {
            if (UsuariosData.ObtenerPorId(IdUsuario) == null)
            {
                return NotFound();
            }
            UsuariosData.Delete(IdUsuario);
            return Ok();
        }

        [ResponseType(typeof(Usuarios))]
        public IHttpActionResult Get(int IdUsuario)
        {
            Usuarios oUsuario = UsuariosData.ObtenerPorId(IdUsuario);
            if (oUsuario == null)
            {
                return NotFound();
            }
            return Ok(oUsuario);
        }

        public IList<Usuarios> Get()
        {
            return UsuariosData.ObtenerUsuarios();
        }


        [ResponseType(typeof(IList<Usuarios>))]
        public IHttpActionResult Get(string Nombre)
        {
            List<Usuarios> lista = new List<Usuarios>();
            lista = UsuariosData.ObtenerUsuariosPorNombre(Nombre);
            if (lista.Count == 0)
            {
                return NotFound();
            }
            return Ok(lista);
        }

        [ResponseType(typeof(IList<Usuarios>))]
        public IHttpActionResult Get2(string Email, string Password)
        {
            List<Usuarios> lista = new List<Usuarios>();
            lista = UsuariosData.Login(Email, Password);
            if (lista.Count == 0)
            {
                return NotFound();
            }
            return Ok(lista);
        }

        public IHttpActionResult GetS(int IdUsuario, string Nombre)
        {
            List<Usuarios> lista = UsuariosData.ObtenerSeguidosPorUsuario(IdUsuario, Nombre);                        
            return Ok(lista);
        }

        public IHttpActionResult Get(string Instrumentos, string Generos, string Influencias, string Ubicacion)
        {
            List<Usuarios> lista = new List<Usuarios>();
            lista = UsuariosData.ObtenerUsuariosPorRequisitos(Instrumentos, Generos, Influencias, Ubicacion);
            if (lista.Count == 0)
            {
                return NotFound();
            }
            return Ok(lista);
        }

        //http://localhost:8080/api/usuarios/Get2?Email=lkristal@gmail.com%20Password=aguantepf
    }
}