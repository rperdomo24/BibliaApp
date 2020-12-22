using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Threading.Tasks;

namespace Biblia.Models
{
    public class bible_verses
    {
        [Key]
        public int idVerse { get; set; }
        public int idBible { get; set; }
        public int idBook { get; set; }
        public int chapter { get; set; }
        public int verse { get; set; }
        public string text { get; set; }
    }
}
