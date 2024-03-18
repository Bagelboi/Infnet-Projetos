using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.RazorPages;
using Microsoft.EntityFrameworkCore;
using tp3_cnet.Data;
using tp3_cnet.Model;

namespace tp3_cnet.Pages
{
    public class IndexModel : PageModel
    {
        private readonly tp3_cnet.Data.InfnetDbContext _context;

        public IndexModel(tp3_cnet.Data.InfnetDbContext context)
        {
            _context = context;
        }

        public IList<Computador> Computador { get;set; } = default!;

        public async Task OnGetAsync()
        {
            Computador = await _context.Computador.ToListAsync();
        }
    }
}
