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
    public class UsuariosHasInstrumentosController
    {
        /*[ResponseType(typeof(UsuariosHasInstrumentos))]
        public IHttpActionResult PostIUHI(UsuariosHasInstrumentos oUsuario, string NombreUsu, string NombreInst)
        {
            int IdInstrumento = InstrumentosData.ObtenerInstrumentosPorNombre(NombreInst);
            int IdUsuario = UsuariosData.ObtenerUsuariosPorNombre(NombreUsu);   
            UsuariosHasInstrumentosData.InsertarUHI(oUsuario, IdUsuario, IdInstrumento);

            return Ok();
        }*/
    }
}