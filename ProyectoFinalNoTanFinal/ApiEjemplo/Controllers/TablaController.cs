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
    public class TablaController : ApiController
    {
        // GET: api/ObtenerTodoDeTabla
        public IList<Tabla> Get()
        {
            return TablaData.ObtenerTodoDeTabla();
        }
    }
}
    
