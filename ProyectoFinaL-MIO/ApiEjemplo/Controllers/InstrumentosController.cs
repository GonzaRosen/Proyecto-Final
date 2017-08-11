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
    public class InstrumentosController : ApiController
    {
        [ResponseType(typeof(Instrumentos))]
        public IHttpActionResult Post(Instrumentos oInstrumento)
        {
            if (oInstrumento == null || string.IsNullOrEmpty(oInstrumento.Nombre))
            {
                return BadRequest("Datos incorrectos.");
            }
            try
            {
                InstrumentosData.InsertarInstrumento(oInstrumento);
            }
            catch (Exception e)
            {
                return BadRequest("Se ha producido un error al intentar realizar la consulta.");
            }
            return Ok();
        }
       

        public IHttpActionResult Delete(int IdInstrumento)
        {
            if (InstrumentosData.ObtenerPorId(IdInstrumento) == null)
            {
                return NotFound();
            }
            InstrumentosData.DeleteInstrumento(IdInstrumento);
            return Ok();
        }

        public IList<Instrumentos> Get()
        {
            return InstrumentosData.ObtenerInstrumentos();
        }

        [ResponseType(typeof(Instrumentos))]
        public IHttpActionResult Get(int IdUsuario)
        {
            List<Instrumentos> lista = InstrumentosData.ObtenerInstrumentosPorUsuario(IdUsuario);
            if (lista.Count == 0)
            {
                return NotFound();
            }
            return Ok(lista);
        }
    }
}