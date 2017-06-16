using MySql.Data.MySqlClient;
using System;
using System.Collections.Generic;
using System.Configuration;
using System.Data;
using System.Linq;
using System.Web;
using Microsoft.Azure;
namespace ApiEjemplo.Data
{
    public class DBHelper
    {

        public static string ConnectionString
        {
            get
            {
                return CloudConfigurationManager.GetSetting("MySqlConnectionString");
            }
        }

        /// <summary>
        /// Ejecutar un comando select y devolver su datatable correspondiente
        /// </summary>
        /// <param name="select"></param>
        /// <returns></returns>
        public static DataTable EjecutarSelect(string select)
        {
            DataTable dt = new DataTable();
            using (MySqlConnection miConn = new MySqlConnection(ConnectionString))
            {
                miConn.Open();
                MySqlCommand miCommand = new MySqlCommand(select, miConn);
                MySqlDataAdapter da = new MySqlDataAdapter(miCommand);
                da.Fill(dt);
                miConn.Close(); //Nos aseguramos de cerrar la conexion
            }
            return dt;
        }

    }
}
