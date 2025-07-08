using System.ComponentModel;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace at_cnet.Models
{
    public class Departamento
    {
        [Key]
        public int id { get; set; }

        [DisplayName("Nome")]
        [Required]
        public string? nome { get; set; }

        [ForeignKey("local")]
        public int local_id { get; set; }

        [DisplayName("Local")]
        public virtual Endereco? local { get; set; }

        [DisplayName("Funcionarios")]
        public virtual ICollection<Funcionario>? funcionarios { get; set; }
    }
}
