using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace tp7_pb.Models
{
    class Professor
    {
        private int _matricula;
        private string _nome;
        private Turma _turma;

        public int matricula { get => _matricula; set => _matricula = value; }
        public string nome { get => _nome; set => _nome = value; }
    }
}
