using Biblia.Models;
using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Biblia.Data
{
    public class BibliaContext : DbContext
    {
        public virtual DbSet<bible_verses> bible_verses { get; set; }
        public virtual DbSet<bible_books> bible_books { get; set; }

        //Store Procedure
        public virtual DbSet<sp_GetAllChapter> sp_GetAllChapter { get; set; }

        public BibliaContext(DbContextOptions<BibliaContext> options) : base(options) { }

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            base.OnModelCreating(modelBuilder);
        }

    }
}
