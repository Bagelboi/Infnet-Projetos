using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace tp7_pb.Models
{
    class Disciplina
    {
        private int _codigo;
        private string _nome;
        private Turma _turma;

        public int codigo { get => _codigo; set => _codigo = value; }
        public string nome { get => _nome; set => _nome = value; }

    }
}
