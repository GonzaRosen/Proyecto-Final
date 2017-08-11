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
    public class GenerosController : ApiController
    {
        [ResponseType(typeof(Generos))]
        public IHttpActionResult Post(Generos oGenero)
        {
            if (oGenero == null || string.IsNullOrEmpty(oGenero.Nombre))
            {
                return BadRequest("Datos incorrectos.");
            }
            try
            {
                GenerosData.InsertarGenero(oGenero);
            }
            catch (Exception e)
            {
                return BadRequest("Se ha producido un error al intentar realizar la consulta.");
            }
            return Ok();
        }

        public IHttpActionResult Delete(int IdGenero)
        {
            if (GenerosData.ObtenerPorId(IdGenero) == null)
            {
                return NotFound();
            }
            GenerosData.DeleteGenero(IdGenero);
            return Ok();
        }

        public IList<Generos> Get()
        {
            return GenerosData.ObtenerGeneros();
        }
    }
}