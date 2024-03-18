using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace tp7_pb.Models
{
    class Turma
    {
        private int _codigo;
        private List<Aluno> _alunos;
        private Professor _professor;
        private Disciplina _disciplina;
        public int codigo { get => _codigo; set => _codigo = value; }

        public string addAluno(Aluno aluno) { return ""; }

        public bool abrirTurma() { return true; }

        public string gerarPauta() { return ""; }
    }
}
