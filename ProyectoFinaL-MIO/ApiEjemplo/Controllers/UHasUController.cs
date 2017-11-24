﻿using ApiEjemplo.Data;
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
    public class UHasUController : ApiController
    {
        [ResponseType(typeof(UHasU))]
        public IHttpActionResult PostIUHU(UHasU oUsuario, int IdUsuario, int IdSeguido)
        {
            UHasUData.InsertarUHU(oUsuario, IdUsuario, IdSeguido);
            return Ok();
        }
    }
}