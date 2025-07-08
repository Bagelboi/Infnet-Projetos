using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.Rendering;
using Microsoft.EntityFrameworkCore;
using at_cnet;
using at_cnet.Models;
using Microsoft.AspNetCore.Authorization;

namespace at_cnet.Controllers
{
    //[Authorize]
    public class FuncionariosController : Controller
    {
        private readonly InfnetAppContext _context;

        public FuncionariosController(InfnetAppContext context)
        {
            _context = context;
        }

        // GET: Funcionarios
        public async Task<IActionResult> Index(string searchName)
        {
            IQueryable<Funcionario> query = _context.Funcionarios.Include(f => f.departamento).Include(f => f.local);

            if (!string.IsNullOrEmpty(searchName))
            {
                query = query.Where(f => f.nome.Contains(searchName));
            }

            List<Funcionario> funcionarios = await query.ToListAsync();
            return View(funcionarios);
        }

        // GET: Funcionarios/Details/5
        public async Task<IActionResult> Details(int? id)
        {
            if (id == null)
            {
                return NotFound();
            }

            var funcionario = await _context.Funcionarios
                .Include(f => f.departamento)
                .Include(f => f.local)
                .FirstOrDefaultAsync(m => m.id == id);
            if (funcionario == null)
            {
                return NotFound();
            }

            return View(funcionario);
        }

        // GET: Funcionarios/Create
        public IActionResult Create()
        {
            ViewData["departamento_id"] = new SelectList(_context.Departamentos, "id", "nome");
            ViewData["local_id"] = new SelectList(_context.Enderecos, "id", null);
            return View();
        }

        // POST: Funcionarios/Create
        // To protect from overposting attacks, enable the specific properties you want to bind to.
        // For more details, see http://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> Create([Bind("id,nome,email,data_nascimento,local_id,telefone,departamento_id")] Funcionario funcionario)
        {
            if (ModelState.IsValid)
            {
                _context.Add(funcionario);
                await _context.SaveChangesAsync();
                return RedirectToAction(nameof(Index));
            }
            ViewData["departamento_id"] = new SelectList(_context.Departamentos, "id", "nome", funcionario.departamento_id);
            ViewData["local_id"] = new SelectList(_context.Enderecos, "id", null, funcionario.local_id);
            return View(funcionario);
        }

        // GET: Funcionarios/Edit/5
        public async Task<IActionResult> Edit(int? id)
        {
            if (id == null)
            {
                return NotFound();
            }

            var funcionario = await _context.Funcionarios.FindAsync(id);
            if (funcionario == null)
            {
                return NotFound();
            }
            ViewData["departamento_id"] = new SelectList(_context.Departamentos, "id", "nome", funcionario.departamento_id);
            ViewData["local_id"] = new SelectList(_context.Enderecos, "id", null, funcionario.local_id);
            return View(funcionario);
        }

        // POST: Funcionarios/Edit/5
        // To protect from overposting attacks, enable the specific properties you want to bind to.
        // For more details, see http://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> Edit(int id, [Bind("id,nome,email,data_nascimento,local_id,telefone,departamento_id")] Funcionario funcionario)
        {
            if (id != funcionario.id)
            {
                return NotFound();
            }

            if (ModelState.IsValid)
            {
                try
                {
                    _context.Update(funcionario);
                    await _context.SaveChangesAsync();
                }
                catch (DbUpdateConcurrencyException)
                {
                    if (!FuncionarioExists(funcionario.id))
                    {
                        return NotFound();
                    }
                    else
                    {
                        throw;
                    }
                }
                return RedirectToAction(nameof(Index));
            }
            ViewData["departamento_id"] = new SelectList(_context.Departamentos, "id", "nome", funcionario.departamento_id);
            ViewData["local_id"] = new SelectList(_context.Enderecos, "id", null, funcionario.local_id);
            return View(funcionario);
        }

        // GET: Funcionarios/Delete/5
        public async Task<IActionResult> Delete(int? id)
        {
            if (id == null)
            {
                return NotFound();
            }

            var funcionario = await _context.Funcionarios
                .Include(f => f.departamento)
                .Include(f => f.local)
                .FirstOrDefaultAsync(m => m.id == id);
            if (funcionario == null)
            {
                return NotFound();
            }

            return View(funcionario);
        }

        // POST: Funcionarios/Delete/5
        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> DeleteConfirmed(int id)
        {
            var funcionario = await _context.Funcionarios.FindAsync(id);
            if (funcionario != null)
            {
                _context.Funcionarios.Remove(funcionario);
            }

            await _context.SaveChangesAsync();
            return RedirectToAction(nameof(Index));
        }

        private bool FuncionarioExists(int id)
        {
            return _context.Funcionarios.Any(e => e.id == id);
        }
    }
}
