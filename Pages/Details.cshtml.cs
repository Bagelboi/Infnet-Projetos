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
    public class DetailsModel : PageModel
    {
        private readonly tp3_cnet.Data.InfnetDbContext _context;

        public DetailsModel(tp3_cnet.Data.InfnetDbContext context)
        {
            _context = context;
        }

        public Computador Computador { get; set; } = default!;

        public async Task<IActionResult> OnGetAsync(int? id)
        {
            if (id == null)
            {
                return NotFound();
            }

            var computador = await _context.Computador.FirstOrDefaultAsync(m => m.Id == id);
            if (computador == null)
            {
                return NotFound();
            }
            else
            {
                Computador = computador;
            }
            return Page();
        }
    }
}
