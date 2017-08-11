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
    public class BandasController : ApiController
    {
        [ResponseType(typeof(Bandas))]
        public IHttpActionResult Post(Bandas oBanda)
        {
            if (oBanda == null || string.IsNullOrEmpty(oBanda.Nombre))
            {
                return BadRequest("Datos incorrectos.");
            }
            try
            {
                BandasData.InsertarBanda(oBanda);
            }
            catch (Exception e)
            {
                return BadRequest("Se ha producido un error al intentar realizar la consulta.");
            }
            return Ok();
        }

        /*public IHttpActionResult Put(int IdBanda, Bandas oBanda)
        {
            if (IdBanda != oBanda.IdBanda)//Nos tiene que llegar el objeto correctamente
            {
                return BadRequest("El id de la persona es incorrecto.");
            }
            if (BandasData.ObtenerPorId(IdBanda) == null)
            {
                return NotFound();
            }
            BandasData.Update(oBanda);
            return Ok();
        }*/

        public IHttpActionResult Delete(int IdBanda)
        {
            if (BandasData.ObtenerPorId(IdBanda) == null)
            {
                return NotFound();
            }
            BandasData.DeleteBanda(IdBanda);
            return Ok();
        }

        [ResponseType(typeof(Bandas))]
        public IHttpActionResult Get(int IdBanda)
        {
            Bandas oBanda = BandasData.ObtenerPorId(IdBanda);
            if (oBanda == null)
            {
                return NotFound();
            }
            return Ok(oBanda);
        }

        public IList<Bandas> Get()
        {
            return BandasData.ObtenerBandas();
        }

        [ResponseType(typeof(IList<Bandas>))]
        public IHttpActionResult Get(string Nombre)
        {
            List<Bandas> lista = new List<Bandas>();
            lista = BandasData.ObtenerBandasPorNombre(Nombre);
            if (lista.Count == 0)
            {
                return NotFound();
            }
            return Ok(lista);
        }
    }
}