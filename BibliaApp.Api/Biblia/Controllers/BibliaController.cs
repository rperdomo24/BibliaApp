using Biblia.Data;
using Biblia.Models;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

// For more information on enabling Web API for empty projects, visit https://go.microsoft.com/fwlink/?LinkID=397860

namespace Biblia.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class BibliaController : ControllerBase
    {
        private readonly BibliaContext _context;

        public BibliaController(BibliaContext context)
        {
            _context = context;
        }

        #region Busqueda Libros
        [HttpGet("AllBooks")]
        public List<bible_books> AllBooks()
        {
            var resultado = new List<bible_books>();
            try
            {
                resultado = _context.bible_books.ToList();

            }
            catch (Exception ex)
            {
                throw;
            }

            return resultado;
        }

        [HttpGet("AllBooksTestament/{testament}")]
        public IActionResult AllBooksTestament(string testament)
        {
            var resultado = new List<bible_books>();
            try
            {
                resultado = _context.bible_books.Where(x => x.testament == testament).ToList();

            }
            catch (Exception ex)
            {
                throw;
            }

            return Ok(resultado);
        }

        [HttpGet("Bookname/{namebook}")]
        public IActionResult Bookname(string namebook)
        {
            var resultado = new List<bible_books>();
            try
            {
                resultado = _context.bible_books.Where(x => x.name.Contains(namebook)).ToList();

            }
            catch (Exception ex)
            {
                throw;
            }

            return Ok(resultado);
        }
        #endregion

        #region Busqueda Capitulos
        [HttpGet("AllChapter/{capitulo}")]
        public IActionResult AllChapter(int capitulo)
        {
            var resultado = new List<sp_GetAllChapter>();
            try
            {
                resultado = _context.Set<sp_GetAllChapter>().FromSqlInterpolated($"CALL GetAllChapter({capitulo})").ToList();                

            }
            catch (Exception ex)
            {
                throw;
            }

            return Ok(resultado);
        }

        [HttpGet("ReadChapter/{idbook}/{chapter}")]
        public IActionResult ReadChapter(int idbook,int chapter)
        {
            var resultado = new List<bible_verses>();
            try
            {
                resultado = _context.bible_verses.Where(x => x.idBook == idbook && x.chapter == chapter).OrderBy(x=>x.verse).ToList();

            }
            catch (Exception ex)
            {
                throw;
            }

            return Ok(resultado);
        }

        #endregion

        [HttpGet("Test")]
        public IActionResult Test()
        {
            return Ok("Servicio encendido.");
        }

    }
}
