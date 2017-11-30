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
        [HttpGet][ResponseType(typeof(UHasU))] 
        public IHttpActionResult GetIUHU(UHasU oUHasU)
        {
            UHasUData.InsertarUHU(oUHasU);
            return Ok();
        }

        /*public IHttpActionResult GetIUHU(int IdUsuario, int IdSeguido)
        {
            UHasUData.InsertarUHU(IdUsuario, IdSeguido);
            return Ok();
        }*/
    }
}