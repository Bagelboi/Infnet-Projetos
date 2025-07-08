using System.ComponentModel;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using static System.Runtime.InteropServices.JavaScript.JSType;

namespace at_cnet.Models
{
    public class Funcionario
    {
        [Key]
        public int id { get; set; }
        [DisplayName("Nome")]
        [MaxLength(50)]
        [Required]
        public string? nome { get; set; }
        [DisplayName("E-mail")]
        [EmailAddress]
        [Required]
        public string? email { get; set; }
        [DisplayName("Data de Nascimento")]
        [DisplayFormat(DataFormatString = "{0:yyyy-MM-dd}", ApplyFormatInEditMode = true)]
        [Required]
        public DateTime? data_nascimento { get; set; }

        [ForeignKey("local")]
        public int? local_id { get; set; }

        [DisplayName("Endereço")]
        public virtual Endereco? local { get; set; }

        [DisplayName("Telefone")]
        public long? telefone { get; set; }

        [ForeignKey("departamento")]
        public int? departamento_id { get; set; }

        [DisplayName("Departamento")]
        public virtual Departamento? departamento { get; set; }
    }
}
