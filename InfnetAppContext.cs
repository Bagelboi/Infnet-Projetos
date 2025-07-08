using at_cnet.Models;
using Microsoft.AspNetCore.Identity;
using Microsoft.AspNetCore.Identity.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore;
using Microsoft.VisualStudio.Web.CodeGenerators.Mvc.Templates.General;
using System.Reflection.Emit;

namespace at_cnet
{
    public class InfnetAppContext : IdentityDbContext<IdentityUser>
    {
        public InfnetAppContext(DbContextOptions<InfnetAppContext> options) : base(options)
        {
        }

        public DbSet<Departamento> Departamentos { get; set; } = default!;
        public DbSet<Funcionario> Funcionarios { get; set; } = default!;

        public DbSet<Endereco> Enderecos { get; set; } = default!;

   
        protected override void OnModelCreating(ModelBuilder model)
        {
            base.OnModelCreating(model);

            model.Entity<Departamento>()
              .HasOne(d => d.local)
              .WithMany()
              .HasForeignKey(d => d.local_id)
              .OnDelete(DeleteBehavior.Cascade);

            model.Entity<Funcionario>(e =>
            {
                e.HasOne(d => d.local)
                .WithMany()
                .HasForeignKey(d => d.local_id)
                .OnDelete(DeleteBehavior.Cascade);
                e.HasOne(d => d.departamento)
                .WithMany( t => t.funcionarios )
                .HasForeignKey(d => d.departamento_id)
                .OnDelete(DeleteBehavior.Cascade);
            });
        }
    }
}
