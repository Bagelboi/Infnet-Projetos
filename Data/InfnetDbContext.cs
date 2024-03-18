using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.EntityFrameworkCore;
using tp3_cnet.Model;

namespace tp3_cnet.Data
{
    public class InfnetDbContext : DbContext
    {
        public InfnetDbContext (DbContextOptions<InfnetDbContext> options)
            : base(options)
        {
        }

        public DbSet<tp3_cnet.Model.Computador> Computador { get; set; } = default!;
    }
}
