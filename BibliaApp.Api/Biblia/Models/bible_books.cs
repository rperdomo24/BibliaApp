using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Threading.Tasks;

namespace Biblia.Models
{
    public class bible_books
    {
        [Key]
        public int idBook { get; set; }
        public string name { get; set; }
        public string testament { get; set; }
    }
}
